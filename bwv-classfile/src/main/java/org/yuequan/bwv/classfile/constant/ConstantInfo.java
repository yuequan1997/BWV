package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ConstantInfo {
    private ByteBuffer buffer;
    public ConstantInfo(U2 constantPoolCount, byte[] bytes) {
        buffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);
        for (short i = 0; i < constantPoolCount.getValue(); i++) {
            U1 tag = new U1("tag", (byte) i);
        }
    }
}
