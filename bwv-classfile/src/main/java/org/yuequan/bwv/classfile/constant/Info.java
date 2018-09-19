package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.nio.ByteBuffer;

public class Info {
    protected final U1 tag;
    protected final ClassFileReader fileReader;
    public Info(U1 tag, ClassFileReader fileReader) {
        this.tag = tag;
        this.fileReader = fileReader;
    }
}
