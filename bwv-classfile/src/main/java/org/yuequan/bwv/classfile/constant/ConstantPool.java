package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.io.FileReader;

public class ConstantPool {
    private U2 constantPoolCount;
    private ConstantInfo constantInfo;
    public ConstantPool(U2 constantPoolCount, ClassFileReader fileReader) {
        this.constantPoolCount = constantPoolCount;
        this.constantInfo = new ConstantInfo(constantPoolCount, fileReader);
    }
}
