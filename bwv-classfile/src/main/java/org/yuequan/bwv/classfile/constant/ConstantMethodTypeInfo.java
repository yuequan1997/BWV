package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.nio.ByteBuffer;

public class ConstantMethodTypeInfo extends Info {
    private final U2 descriptorIndex;
    public ConstantMethodTypeInfo(U1 tag, ClassFileReader fileReader) {
        super(tag, fileReader);
        this.descriptorIndex = new U2("descriptor_index", fileReader.readShort());
    }

    public U2 getDescriptorIndex() {
        return descriptorIndex;
    }
}
