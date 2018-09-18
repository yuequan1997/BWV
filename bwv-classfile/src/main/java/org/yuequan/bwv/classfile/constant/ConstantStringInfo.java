package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;

import java.nio.ByteBuffer;

public class ConstantStringInfo extends Info{
    private final U2 stringIndex;
    public ConstantStringInfo(U1 tag, ByteBuffer buffer) {
        super(tag, buffer);
        stringIndex = new U2("string_index", buffer.getShort());
    }
}
