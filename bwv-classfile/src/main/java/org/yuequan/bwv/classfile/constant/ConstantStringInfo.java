package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.nio.ByteBuffer;

public class ConstantStringInfo extends Info{
    private final U2 stringIndex;
    public ConstantStringInfo(U1 tag, ClassFileReader fileReader) {
        super(tag, fileReader);
        stringIndex = new U2("string_index", fileReader.readShort());
    }

    public U2 getStringIndex() {
        return stringIndex;
    }
}
