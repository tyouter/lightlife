package com.example.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Project tree
 *
 * @author Administrator
 */
public class ProjectTree {
    List<Project> mProjects;

    public ProjectTree() {
        mProjects = new ArrayList<>();
    }

    public boolean hasProjects() {
        return mProjects != null && !mProjects.isEmpty();
    }

    public List<Project> getAllProjects() {
        return mProjects;
    }

    public void addProject(Project project) {
        mProjects.add(project);
    }

    public Project getProject(long id) {
        for (Project prj :
                mProjects) {
            if (prj.hasId(id)) {
                return prj.getProject(id);
            }
        }
        return null;
    }
}
