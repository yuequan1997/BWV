package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.io.FileReader;
import java.nio.ByteBuffer;

public class ConstantUtf8Info extends Info{
    private final U2 length;
    private final byte[] bytes;
    public ConstantUtf8Info(U1 tag, ClassFileReader fileReader) {
        super(tag, fileReader);
        this.length = new U2("length", fileReader.readShort());
        this.bytes = new byte[this.length.getValue()];
        for (int i = 0; i < this.length.getValue(); i++) {
            this.bytes[i] = fileReader.readByte();
        }
    }

    public byte[] getBytes() {
        return bytes;
    }
}
