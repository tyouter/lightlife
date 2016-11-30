/**
 *
 */
package com.example.core;

import com.example.utils.FileUtils;

/**
 * Main entry of this project as a SDK
 *
 * @author Administrator
 */
public class LightLifeCore {

    private ProjectTree mProjectTree;
    private boolean mValid;
    private String mPath;

    public void initialize(String path) {
        mPath = path;
        mValid = FileUtils.fileExist(path) || FileUtils.createFile(path);
    }
    public boolean load() {
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

    public void setProjectTree(ProjectTree projectTree) {
        mProjectTree = projectTree;
    }

    public void save() {
        if (mValid) {
            if (mProjectTree != null) {
                DataHelper.write(mProjectTree, mPath);
            }
        }
    }
}
