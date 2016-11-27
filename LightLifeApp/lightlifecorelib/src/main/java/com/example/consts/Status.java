package com.example.consts;

public enum Status {
    PLAN("planing"),
    DO("doing"),
    SUMMARY("summary"),
    DONE("finished"),
    CLOSED("closed"),
    UNDEFINED("undefined");
    String mStringValue;

    Status(String stringValue) {
        mStringValue = stringValue;
    }

    public String toString() {
        return mStringValue;
    }
}
