package com.example.impl;

import com.example.consts.Status;
import com.example.interfaces.IRecord;
import com.example.interfaces.IRecordSegment;

/**
 * For record
 * Created by Administrator on 2016/11/27.
 */
public class RecordFactory {
    public static IRecord makeRecord() {
        return new Record();
    }

    public static IRecordSegment makeRecordSegment(String outputs,
                                                   String description,
                                                   Double timeSpend,
                                                   Status status) {
        return new RecordSegment(outputs, description, timeSpend, status);
    }
}
