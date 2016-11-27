package com.example.core;

import com.example.consts.Status;
import com.example.impl.RecordFactory;
import com.example.interfaces.IRecord;
import com.example.interfaces.IRecordSegment;

import org.junit.Assert;
import org.junit.Test;

public class LightLifeCoreTest {

    @Test
    public void initialize() {
        String path = "C:/Users/Administrator/Desktop/temp/";
        String name = "test.xml";
        String absolutePath = path + name;
        ProjectTree tree = new ProjectTree();

        Project one = new Project();
        one.setName("one tt");
        one.setId(1);
        one.setTotalTimeSpend(1);
        one.setDescriptions("one des");
        IRecord recordOne = RecordFactory.makeRecord();
        IRecordSegment segmentOne = RecordFactory.makeRecordSegment("one seg", "one seg", 1.0, Status.PLAN);
        recordOne.addSegment(segmentOne);
        one.setRecord(recordOne);

        Project onePone = new Project();
        onePone.setName("onePone");
        onePone.setId(2);
        onePone.setTotalTimeSpend(1.1);
        onePone.setDescriptions("onePone des");
        IRecord recordPOne = RecordFactory.makeRecord();
        IRecordSegment segmentPOne = RecordFactory.makeRecordSegment("one point seg", "one point seg", 1.1, Status.DO);
        recordPOne.addSegment(segmentPOne);
        onePone.setRecord(recordPOne);

        one.addSubProject(onePone);

        tree.addProject(one);

        Project two = new Project();
        two.setName("two");
        two.setId(3);
        two.setTotalTimeSpend(2.0);
        two.setDescriptions("one des");
        IRecord recordTwo = RecordFactory.makeRecord();
        IRecordSegment segmentTwo = RecordFactory.makeRecordSegment("two seg", "two seg", 2.0, Status.SUMMARY);
        recordTwo.addSegment(segmentTwo);
        two.setRecord(recordTwo);

        Project twoPone = new Project();
        twoPone.setName("twoPone");
        twoPone.setId(4);
        twoPone.setTotalTimeSpend(4);
        twoPone.setDescriptions("twoPone des");
        IRecord recordPtwo = RecordFactory.makeRecord();
        IRecordSegment segmentPtwo = RecordFactory.makeRecordSegment("two point seg", "two point seg", 4.1, Status.DONE);
        recordPtwo.addSegment(segmentPtwo);
        twoPone.setRecord(recordPtwo);

        two.addSubProject(twoPone);

        tree.addProject(two);

        LightLifeCore core = new LightLifeCore();
        core.initialize(absolutePath);
        core.setProjectTree(tree);
        core.save();
        boolean success = core.load();
        Assert.assertTrue(success);
    }
}