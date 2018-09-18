package org.yuequan.bwv.classfile.reader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * The type Byte reader.
 *
 * @author yuequan
 */
public class ByteReader {
    private final ByteBuffer buffer;

    /**
     * Instantiates a new Byte reader.
     *
     * @param bytes the bytes
     */
    public ByteReader(byte[] bytes) {
        buffer = ByteBuffer.wrap(bytes).asReadOnlyBuffer().order(ByteOrder.BIG_ENDIAN);
    }

    /**
     * 8bit signed int
     *
     * @return byte byte
     */
    public byte readByte(){
        return buffer.get();
    }

    /**
     * 8bit unsigned int
     *
     * @return int int
     */
    public int readUnsignedByte(){
        return Byte.toUnsignedInt(buffer.get());
    }

    /**
     * 16bit signed int.
     *
     * @return short short
     */
    public short readShort(){
        return buffer.getShort();
    }

    /**
     * 16bit unsigned int.
     *
     * @return int int
     */
    public int readUnsignedShort(){
        return Short.toUnsignedInt(buffer.getShort());
    }

    /**
     * 32bit signed int
     *
     * @return int
     */
    public int readInt(){
        return buffer.getInt();
    }

    /**
     * 32bit unsigned int
     *
     * @return the long
     */
    public long readUnsignedInt(){
        return Integer.toUnsignedLong(buffer.getInt());
    }


    /**
     * 64bit signed int
     *
     * @return the long
     */
    public long readLong(){
        return buffer.getLong();
    }

    public byte[] readBytes(int n){
        byte[] bytes = new byte[n];
        buffer.get(bytes);
        return bytes;
    }

    public void skipBytes(int n){
        for (int i = 0; i < n; i++) {
            buffer.get();
        }
    }
}
