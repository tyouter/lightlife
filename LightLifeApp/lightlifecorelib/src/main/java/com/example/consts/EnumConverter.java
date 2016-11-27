package com.example.consts;

/**
 * Enum translator
 * Created by Administrator on 2016/11/27.
 */
public class EnumConverter {

    public static Status toStatus(String v) {
        if (v == null) {
            return null;
        }
        if (v.equalsIgnoreCase(Status.CLOSED.toString())) {
            return Status.CLOSED;
        }
        if (v.equalsIgnoreCase(Status.DO.toString())) {
            return Status.DO;
        }
        if (v.equalsIgnoreCase(Status.PLAN.toString())) {
            return Status.PLAN;
        }
        if (v.equalsIgnoreCase(Status.SUMMARY.toString())) {
            return Status.SUMMARY;
        }
        if (v.equalsIgnoreCase(Status.DONE.toString())) {
            return Status.DONE;
        }
        return null;
    }
}
