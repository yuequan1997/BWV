package org.yuequan.bwv.classfile.attribute;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.datatype.U4;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

public class AttributeInfo {
    private final U2 attributeNameIndex;
    private final U4 attributeLength;
    private final U1[] infos;

    public AttributeInfo(ClassFileReader fileReader) {
        this.attributeNameIndex = new U2("attribute_name_index", fileReader.readShort());
        this.attributeLength = new U4("attribute_length", fileReader.readInt());
        this.infos = new U1[this.attributeLength.getValue()];
        for (int i = 0; i <infos.length; i++) {
            infos[i] = new U1("info", fileReader.readByte());
        }
    }

    public U1[] getInfos() {
        return infos;
    }

    public U2 getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public U4 getAttributeLength() {
        return attributeLength;
    }
}
