package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.nio.ByteBuffer;

public class ConstantMethodHandleInfo extends Info {
    private final U1 referenceKind;
    private final U2 referenceIndex;
    public ConstantMethodHandleInfo(U1 tag, ClassFileReader fileReader) {
        super(tag, fileReader);
        this.referenceKind = new U1("reference_kind", fileReader.readByte());
        this.referenceIndex = new U2("reference_index", fileReader.readShort());
    }
}
