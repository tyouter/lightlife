package com.example.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**for unique id test
 * Created by Administrator on 2016/12/5.
 */
public class UniqueIDUtilsTest {

    @Test
    public void testGenerateUUID() throws Exception {
        List<String> ids = new ArrayList<>();
        long i = 0;
        long testLimit = 100000;
        while(i < testLimit) {
            String id = UniqueIDUtils.generateUUID();
            if (ids.contains(id)) {
                throw new Exception();
            }
            ids.add(id);
            ++i;
        }
    }
}