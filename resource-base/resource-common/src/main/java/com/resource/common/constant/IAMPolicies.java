package com.resource.common.constant;

public enum IAMPolicies {
    CONSOLE_ADMIN("consoleAdmin"),
    DIAGNOSTICS("diagnostics"),
    READ_ONLY("readOnly"),
    READWRITE("readWrite"),
    WRITE_ONLY("writeOnly");

    IAMPolicies(String name) {
    }
}
