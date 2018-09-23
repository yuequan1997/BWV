package org.yuequan.bwv.classfile.flag;

import org.yuequan.bwv.classfile.constant.ConstantClassInfo;

import java.util.List;

public enum AccessFlag {
    // 0x0001
    ACC_PUBLIC(0x0001),
    // 0x0010
    ACC_FINAL(0x0010),
    // 0x0020
    ACC_SUPER(0x0020),
    // 0x0200
    ACC_INTERFACE(0x0200),
    // 0x0400
    ACC_ABSTRACT(0x0400),
    // 0x1000
    ACC_SYNTHETIC(0x1000),
    // 0x2000
    ACC_ANNOTATION(0x2000),
    // 0x4000
    ACC_ENUM(0x4000);

    AccessFlag(int bitmask) {
        this.bitmask = bitmask;
    }

    private int bitmask;

    public static String getAccessFlags(int flag){
        StringBuilder stringBuilder = new StringBuilder();
        for (AccessFlag accessFlag : values()) {
            if((flag & accessFlag.bitmask) != 0){
                stringBuilder.append(accessFlag.toString());
                stringBuilder.append(",");
            }
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
