package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;

import java.nio.ByteBuffer;

public class Info {
    protected final U1 tag;
    protected final ByteBuffer buffer;
    public Info(U1 tag, ByteBuffer buffer) {
        this.tag = tag;
        this.buffer =buffer;
    }
}
