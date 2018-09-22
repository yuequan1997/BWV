package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.nio.ByteBuffer;

public class ConstantMethodrefInfo extends Info{
    private final U2 classIndex;
    private final U2 nameAndTypeIndex;
    public ConstantMethodrefInfo(U1 tag, ClassFileReader fileReader) {
        super(tag, fileReader);
        this.classIndex = new U2("class_index", fileReader.readShort());
        this.nameAndTypeIndex = new U2("name_and_type_index", fileReader.readShort());
    }

    public U2 getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public U2 getClassIndex() {
        return classIndex;
    }
}
