/**
 *
 */
package com.example.core;

import com.example.utils.FileUtils;

/**
 * Main entry of the core
 *
 * @author Administrator
 */
@SuppressWarnings("unused")
public class LightLifeCore {

    private ProjectTree mProjectTree;
    private boolean mValid;
    private String mPath;
    private PatternLibrary mPatternLibrary;

    public void initialize(String path) {
        mPath = path;
        mValid = FileUtils.fileExist(path) || FileUtils.createFile(path);
        if (mValid) {
            load();
        }
    }

    private boolean load() {
        try {
            mProjectTree = DataHelper.read(mPath);
            mValid = mProjectTree != null;
        } catch (Exception e) {
            mValid = false;
        }
        return mValid;
    }

    public boolean isValid() {
        return mValid;
    }

    private void setProjectTree(ProjectTree projectTree) {
        mProjectTree = projectTree;
    }

    private void save() {
        if (mValid) {
            if (mProjectTree != null) {
                DataHelper.write(mProjectTree, mPath);
            }
        }
    }
}
