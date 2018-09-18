package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;

import java.nio.ByteBuffer;

public class ConstantClassInfo extends Info {
    private U2 nameIndex;
    public ConstantClassInfo(U1 tag, ByteBuffer buffer) {
        super(tag, buffer);
        nameIndex = new U2("name_index", buffer.getShort());
    }
}
