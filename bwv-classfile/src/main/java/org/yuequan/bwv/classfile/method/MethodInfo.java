package org.yuequan.bwv.classfile.method;

import org.yuequan.bwv.classfile.attribute.AttributeInfo;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

public class MethodInfo {
    private final U2 accessFlags;
    private final U2 nameIndex;
    private final U2 descriptorIndex;
    private final U2 attributesCount;
    private final AttributeInfo[] attributeInfos;

    public MethodInfo(ClassFileReader fileReader) {
        this.accessFlags = new U2("access_flags", fileReader.readShort());
        this.nameIndex = new U2("name_index", fileReader.readShort());
        this.descriptorIndex = new U2("descriptor_index", fileReader.readShort());
        this.attributesCount = new U2("attributes_count", fileReader.readShort());
        this.attributeInfos = new AttributeInfo[this.attributesCount.getValue()];
        for (int i = 0; i < attributeInfos.length; i++) {
            attributeInfos[i] = new AttributeInfo(fileReader);
        }
    }
}
