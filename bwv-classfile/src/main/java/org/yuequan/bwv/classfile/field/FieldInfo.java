package org.yuequan.bwv.classfile.field;

import org.yuequan.bwv.classfile.attribute.AttributeInfo;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

public class FieldInfo {
    private final U2 accessFlags;
    private final U2 nameIndex;
    private final U2 descriptorIndex;
    private final U2 attributeCount;
    private final AttributeInfo[] attributeInfos;

    public FieldInfo(ClassFileReader fileReader) {
        this.accessFlags = new U2("access_flags", fileReader.readShort());
        this.nameIndex = new U2("name_index", fileReader.readShort());
        this.descriptorIndex = new U2("descriptor_index", fileReader.readShort());
        this.attributeCount = new U2("attribute_count", fileReader.readShort());
        this.attributeInfos = new AttributeInfo[this.attributeCount.getValue()];
        for (int i = 0; i < attributeInfos.length; i++) {
            attributeInfos[i] = new AttributeInfo(fileReader);
        }
    }

    public U2 getDescriptorIndex() {
        return descriptorIndex;
    }

    public U2 getNameIndex() {
        return nameIndex;
    }

    public AttributeInfo[] getAttributeInfos() {
        return attributeInfos;
    }

    public U2 getAccessFlags() {
        return accessFlags;
    }

    public U2 getAttributeCount() {
        return attributeCount;
    }
}
