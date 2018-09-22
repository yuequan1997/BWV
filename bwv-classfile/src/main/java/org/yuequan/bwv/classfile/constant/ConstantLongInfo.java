package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U4;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.nio.ByteBuffer;

public class ConstantLongInfo extends Info {
    private final U4 highBytes;
    private final U4 lowBytes;
    public ConstantLongInfo(U1 tag, ClassFileReader fileReader) {
        super(tag, fileReader);
        this.highBytes = new U4("high_bytes",fileReader.readInt());
        this.lowBytes = new U4("low_bytes", fileReader.readInt());
    }

    public U4 getHighBytes() {
        return highBytes;
    }

    public U4 getLowBytes() {
        return lowBytes;
    }
}
