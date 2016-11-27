package com.example.utils;

import java.io.File;
import java.io.IOException;

/**
 * For file operations
 * Created by Administrator on 2016/11/27.
 */
public class FileUtils {
    /**
     * To detect a file existence
     *
     * @param path file absolute path
     * @return true if exist, false otherwise
     */
    public static boolean fileExist(String path) {
        try {
            File f = new File(path);
            return f.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /** generate a file at path
     * @param path path
     * @return file
     */
    public static File generate(String path) {
        return new File(path);
    }

    /** create a file at path
     * @param path path
     * @return file create success return true
     */
    public static boolean createFile(String path) {
        File f = generate(path);
        try {
            return f.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }
}
