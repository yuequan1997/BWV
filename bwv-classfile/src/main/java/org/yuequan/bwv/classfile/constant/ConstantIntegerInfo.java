package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U4;

import java.nio.ByteBuffer;

public class ConstantIntegerInfo extends Info{
    private U4 bytes;
    public ConstantIntegerInfo(U1 tag, ByteBuffer buffer) {
        super(tag, buffer);
        bytes = new U4("bytes", buffer.getInt());
    }
}
