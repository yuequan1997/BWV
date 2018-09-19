package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.nio.ByteBuffer;

public class ConstantInvokeDynamicInfo extends Info {
    private final U2 bootstrapMethodAttrIndex;
    private final U2 nameAndTypeIndex;
    public ConstantInvokeDynamicInfo(U1 tag, ClassFileReader fileReader) {
        super(tag, fileReader);
        this.bootstrapMethodAttrIndex = new U2("bootstrap_method_attr_index", fileReader.readShort());
        this.nameAndTypeIndex = new U2("name_and_type_index", fileReader.readShort());
    }
}
