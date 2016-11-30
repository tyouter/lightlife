/**
 *
 */
package com.example.core;

import com.example.interfaces.ICommand;
import com.example.interfaces.IRecord;
import com.example.interfaces.IRecordSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class Project {
    /**
     * Project name
     */
    private String mName;
    /**
     * Project descriptions
     */
    private String mDescriptions;
    /**
     * Time spend on this project in hours
     */
    private double mTotalTimeSpend;
    /**
     * Unique identification
     */
    private long mId;
    /**
     * Parent project id, 0 is for root
     */
    private long mParentId = 0;
    /**
     * A record for history options and outputs
     */
    private IRecord mRecord;
    /**
     * Define command for project
     * Use command to update data and get results
     */
    private ICommand mCommand;
    /**
     * List of sub-projects
     */
    private List<Project> mSubProjects = new ArrayList<>();

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescriptions() {
        return mDescriptions;
    }

    public void setDescriptions(String descriptions) {
        mDescriptions = descriptions;
    }

    public boolean hasSubProjects() {
        return mSubProjects != null && !mSubProjects.isEmpty();
    }

    public List<Project> getSubProjects() {
        return mSubProjects;
    }

    public Double getTimeCosts() {
        return mTotalTimeSpend;
    }

    public void setTotalTimeSpend(double totalTimeSpend) {
        mTotalTimeSpend = totalTimeSpend;
    }

    public List<IRecordSegment> getSegments() {
        return mRecord.getSegments();
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public IRecord getRecord() {
        return mRecord;
    }

    public void setRecord(IRecord record) {
        mRecord = record;
    }

    public boolean hasId(long id) {
        if (this.mId == id) {
            return true;
        }
        for (Project prj :
                mSubProjects) {
            if (prj.hasId(id)) {
                return true;
            }
        }
        return false;
    }

    public Project getProject(long id) {
        if (this.mId == id) {
            return this;
        }
        for (Project prj :
                mSubProjects) {
            Project project = prj.getProject(id);
            if (project != null) {
                return project;
            }
        }
        return null;
    }

    public long getParentId() {
        return mParentId;
    }

    public void setParentId(long parentId) {
        mParentId = parentId;
    }

    public void addSubProject(Project project) {
        if(project == null) {
            return;
        }
        project.setParentId(mId);
        mSubProjects.add(project);
    }
}
