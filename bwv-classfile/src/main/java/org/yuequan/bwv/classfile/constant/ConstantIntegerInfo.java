package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U4;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.nio.ByteBuffer;

public class ConstantIntegerInfo extends Info{
    private U4 bytes;
    public ConstantIntegerInfo(U1 tag, ClassFileReader fileReader) {
        super(tag, fileReader);
        bytes = new U4("bytes", fileReader.readInt());
    }

    public U4 getBytes() {
        return bytes;
    }
}
