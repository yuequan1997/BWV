package org.yuequan.bwv.classfile.datatype;

public class U1 extends UInt{
    private final byte value;
    public U1(String name, byte value) {
        super(name);
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
