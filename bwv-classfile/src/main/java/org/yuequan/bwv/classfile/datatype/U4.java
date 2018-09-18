package org.yuequan.bwv.classfile.datatype;

public class U4 extends UInt{
    private final int value;
    public U4(String name, int value) {
        super(name);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
