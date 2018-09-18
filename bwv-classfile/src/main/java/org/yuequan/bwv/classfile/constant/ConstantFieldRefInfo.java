package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;

import java.nio.ByteBuffer;

public class ConstantFieldRefInfo extends Info{
    private final U2 classIndex;
    private final U2 nameAndTypeIndex;
    public ConstantFieldRefInfo(U1 tag, ByteBuffer buffer) {
        super(tag, buffer);
        this.classIndex = new U2("class_index", buffer.getShort());
        this.nameAndTypeIndex = new U2("name_and_type_index", buffer.getShort());
    }
}
