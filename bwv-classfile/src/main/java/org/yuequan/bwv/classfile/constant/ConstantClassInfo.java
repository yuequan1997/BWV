package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.nio.ByteBuffer;

public class ConstantClassInfo extends Info {
    private U2 nameIndex;
    public ConstantClassInfo(U1 tag, ClassFileReader fileReader) {
        super(tag, fileReader);
        nameIndex = new U2("name_index", fileReader.readShort());
    }
}
