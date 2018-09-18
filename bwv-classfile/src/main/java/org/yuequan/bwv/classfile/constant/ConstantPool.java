package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U2;

public class ConstantPool {
    private U2 constantPoolCount;
    private ConstantInfo constantInfo;
    public ConstantPool(U2 constantPoolCount,byte[] bytes) {
        this.constantPoolCount = constantPoolCount;
        this.constantInfo = new ConstantInfo(constantPoolCount, bytes);
    }
}
