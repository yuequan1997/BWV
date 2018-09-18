package org.yuequan.bwv.classfile.datatype;

public class U2 extends UInt {
    private final short value;
    public U2(String name, short value) {
        super(name);
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
