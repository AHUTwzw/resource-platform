package com.resource.common.constant;

import lombok.Getter;

@Getter
public enum MediaType {
    // 文本类型
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    TEXT_XML("text/xml"),
    TEXT_CSS("text/css"),
    TEXT_JAVASCRIPT("text/javascript"),

    // 应用类型
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    APPLICATION_PDF("application/pdf"),
    APPLICATION_JAVASCRIPT("application/javascript"),
    APPLICATION_ZIP("application/zip"),
    APPLICATION_XHTML_XML("application/xhtml+xml"),
    APPLICATION_GRAPHQL("application/graphql"),

    // 图像类型
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_GIF("image/gif"),
    IMAGE_SVG_XML("image/svg+xml"),
    IMAGE_WEBP("image/webp"),

    // 音频类型
    AUDIO_MPEG("audio/mpeg"),
    AUDIO_OGG("audio/ogg"),
    AUDIO_WAV("audio/wav"),

    // 视频类型
    VIDEO_MP4("video/mp4"),
    VIDEO_WEBM("video/webm"),
    VIDEO_OGG("video/ogg"),

    // 多部分类型
    MULTIPART_FORM_DATA("multipart/form-data");

    private final String value;

    MediaType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * 根据字符串获取对应的MediaType枚举
     */
    public static MediaType fromString(String mediaType) {
        for (MediaType type : MediaType.values()) {
            if (type.getValue().equalsIgnoreCase(mediaType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown media type: " + mediaType);
    }
}
