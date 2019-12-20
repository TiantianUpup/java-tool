package com.h2t.study.enums;

/**
 * 压缩文件格式
 *
 * @version 1.0
 * @author: hetiantian
 * @date: 2019/12/12 15:37
 */
public enum FileTypeEnum {
    TARGZ("tar.gz"), ZIP("zip"), RAR("rar"), GZ("gz"), TAR(".tar");
    private String typeName;

    FileTypeEnum(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
