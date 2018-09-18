package org.yuequan.bwv.classfile;

import org.yuequan.bwv.classfile.constant.ConstantPool;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.datatype.U4;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

public class ClassFile {
    private final ClassFileReader fileReader;
    private final U4 magic;
    private final U2 minorVersion;
    private final U2 majorVersion;
    private final U2 constantPoolCount;
    private final ConstantPool constantPool;
    public ClassFile(ClassFileReader fileReader) {
        this.fileReader = fileReader;
        this.magic = new U4("magic", fileReader.readInt());
        this.minorVersion = new U2("minor_version", fileReader.readShort());
        this.majorVersion = new U2("major_version", fileReader.readShort());
        this.constantPoolCount = new U2("constant_pool_count", fileReader.readShort());
        this.constantPool = new ConstantPool(this.constantPoolCount, fileReader.readBytes(constantPoolCount.getValue() - 1));
    }

}
