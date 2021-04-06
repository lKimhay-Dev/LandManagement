package com.klsoft.lms.utils;

public enum Status {
    SUCCESS("SUCCESS"), FAILED("FAILED"), ERROR("ERROR");

    private String value;

    private Status(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
