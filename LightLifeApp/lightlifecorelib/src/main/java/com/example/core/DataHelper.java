package com.example.core;

import com.example.consts.ConfigurationSchema;
import com.example.consts.EnumConverter;
import com.example.consts.Status;
import com.example.impl.RecordFactory;
import com.example.interfaces.IRecord;
import com.example.interfaces.IRecordSegment;
import com.example.utils.FileUtils;
import com.example.utils.StringUtils;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * To support data parsing and saving
 *
 * @author Administrator
 */
public class DataHelper {

    protected static boolean write(ProjectTree projects, String path) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(ConfigurationSchema.RootTag);
            doc.appendChild(rootElement);

            addProjects(projects.getAllProjects(), doc, rootElement);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(generateFile(path));
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            return true;
        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
        return false;
    }

    protected static ProjectTree read(String path) {
        try {
            if (!FileUtils.fileExist(path)) {
                return null;
            }
            ProjectTree tree = new ProjectTree();
            File fXmlFile = generateFile(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(ConfigurationSchema.ProjectTag);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Project project = parseProjects(eElement);
                    if (project != null) {
                        tree.addProject(project);
                    }
                }
            }
            return tree;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Project parseProjects(Element element) {
        if (element == null) {
            return null;
        }
        Project project = new Project();
        // parse this project
        // parse id
        String idString = element.getAttribute(ConfigurationSchema.ProjectId);
        if (idString != null) {
            project.setId(StringUtils.string2Long(idString));
        }
        // parse id
        String parentIdString = element.getAttribute(ConfigurationSchema.ProjectParentId);
        if (parentIdString != null) {
            project.setParentId(StringUtils.string2Long(parentIdString));
        }
        // parse name
        String name = getNodeValue(element, ConfigurationSchema.NameTag);
        project.setName(name);
        // parse descriptions
        String des = getNodeValue(element, ConfigurationSchema.DescriptionsTag);
        project.setDescriptions(des);
        // parse time spends
        String timeSpends = getNodeValue(element, ConfigurationSchema.TimeCostsTag);
        double timeCosts = StringUtils.string2Double(timeSpends);
        project.setTotalTimeSpend(timeCosts);
        // parse records
        IRecord record = parseRecords(element);
        project.setRecord(record);
        // parse sub-projects
        NodeList rootProjects = element.getElementsByTagName(
                ConfigurationSchema.ProjectTag);
        if (rootProjects != null && rootProjects.getLength() > 0) {
            for (int i = 0; i < rootProjects.getLength(); ++i) {
                Node nNode = rootProjects.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Project subPrj = parseProjects(eElement);
                    if (subPrj != null) {
                        project.addSubProject(project);
                    }
                }
            }
        }
        return project;
    }

    private static IRecord parseRecords(Element element) {
        NodeList recordsNodes = element.getElementsByTagName(
                ConfigurationSchema.RecordTag);
        if (recordsNodes != null && recordsNodes.getLength() > 0) {
            IRecord record = RecordFactory.makeRecord();
            Element recordElem = (Element) recordsNodes.item(0);
            NodeList segments = recordElem.getElementsByTagName(
                    ConfigurationSchema.RecordSegmentTag);
            if (segments != null && segments.getLength() > 0) {
                for (int i = 0; i < segments.getLength(); ++i) {
                    Node nNode = segments.item(i);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element segmentElem = (Element) nNode;
                        String outputs = getNodeValue(segmentElem,
                                ConfigurationSchema.RecordOutputsTag);
                        String descriptions = getNodeValue(segmentElem,
                                ConfigurationSchema.RecordDescriptionTag);
                        Status status = EnumConverter.toStatus(
                                getNodeValue(segmentElem, ConfigurationSchema.RecordStatusTag));
                        Double time = StringUtils.string2Double(
                                getNodeValue(segmentElem, ConfigurationSchema.RecordTimeTag));
                        if (status == null) {
                            status = Status.UNDEFINED;
                        }
                        IRecordSegment segment = RecordFactory.makeRecordSegment(outputs,
                                descriptions, time, status);
                        record.addSegment(segment);
                    }
                }
            }
            return record;
        }
        return null;
    }

    private static String getNodeValue(Element element, String tag) {
        return element.getElementsByTagName(tag)
                .item(0)
                .getFirstChild()
                .getNodeValue();
    }

    private static File generateFile(String path) {
        return FileUtils.generate(path);
    }

    private static void addProjects(List<Project> projects, Document doc, Element rootElement) {
        if (projects == null || projects.isEmpty()) {
            return;
        }
        for (Project prj : projects) {
            writeProject(prj, doc, rootElement);
        }
    }

    private static void writeProject(Project prj, Document doc, Element rootElement) {
        Element project = doc.createElement(ConfigurationSchema.ProjectTag);
        Attr attrId = doc.createAttribute(ConfigurationSchema.ProjectId);
        attrId.setValue(StringUtils.long2String(prj.getId()));
        project.setAttributeNode(attrId);
        Attr attrParentId = doc.createAttribute(ConfigurationSchema.ProjectParentId);
        attrParentId.setValue(StringUtils.long2String(prj.getParentId()));
        project.setAttributeNode(attrParentId);
        rootElement.appendChild(project);
        // write name
        Element name = doc.createElement(ConfigurationSchema.NameTag);
        name.appendChild(doc.createTextNode(prj.getName()));
        project.appendChild(name);
        // write description
        Element des = doc.createElement(ConfigurationSchema.DescriptionsTag);
        des.appendChild(doc.createTextNode(prj.getDescriptions()));
        project.appendChild(des);
        // write time spend
        Element timeCosts = doc.createElement(ConfigurationSchema.TimeCostsTag);
        timeCosts.appendChild(doc.createTextNode(StringUtils.double2String(
                prj.getTimeCosts())));
        project.appendChild(timeCosts);
        // write record
        Element record = doc.createElement(ConfigurationSchema.RecordTag);
        project.appendChild(record);
        List<IRecordSegment> segments = prj.getSegments();
        if (segments != null) {
            for (int i = 0; i < segments.size(); ++i) {
                IRecordSegment seg = segments.get(i);
                // write id
                Element segment = doc.createElement(ConfigurationSchema.RecordSegmentTag);
                record.appendChild(segment);
                // write outputs
                Element out = doc.createElement(ConfigurationSchema.RecordOutputsTag);
                out.appendChild(doc.createTextNode(seg.getOutputs()));
                segment.appendChild(out);
                // write description
                Element segDes = doc.createElement(ConfigurationSchema.RecordDescriptionTag);
                segDes.appendChild(doc.createTextNode(seg.getDescription()));
                segment.appendChild(segDes);
                // write time spend
                Element segTimeCosts = doc.createElement(ConfigurationSchema.RecordTimeTag);
                segTimeCosts.appendChild(doc.createTextNode(StringUtils.double2String(
                        seg.getTimeSpend())));
                segment.appendChild(segTimeCosts);
                // write status
                Element status = doc.createElement(ConfigurationSchema.RecordStatusTag);
                status.appendChild(doc.createTextNode(seg.getStatus().toString()));
                segment.appendChild(status);
            }
        }
        // write sub projects
        if (prj.hasSubProjects()) {
            addProjects(prj.getSubProjects(), doc, project);
        }
    }

}
