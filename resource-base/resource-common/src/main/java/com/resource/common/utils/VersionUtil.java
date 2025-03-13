package com.resource.common.utils;

import io.micrometer.common.util.StringUtils;

public class VersionUtil {

    // 静态变量用于保存当前的最高版本号
    private final static int CURRENT_VERSION = 1;

    /**
     * 获取当前版本号并生成下一个版本号
     * @return 当前版本号字符串（例如 "v1", "v2"）
     */
    public static synchronized String getNextVersion(String version) {
        // 增加版本号
        int currentVersion = getCurrentVersion(version);
        currentVersion++;
        // 返回格式化的版本号字符串
        return "v" + currentVersion;
    }

    /**
     * 设置初始版本号（可选）
     * @param currentVersion 版本号（例如 1 表示从 v1 开始）
     */
    public static int getCurrentVersion(String currentVersion) {
        if (StringUtils.isBlank(currentVersion)) {
            throw new IllegalArgumentException("Initial version cannot be negative");
        }
        String versionNo = currentVersion.substring(1);
        return Integer.parseInt(versionNo);
    }

    /**
     * 设置初始版本号（可选）
     */
    public static String getInitialVersion() {
        return "v" + CURRENT_VERSION;
    }
}
