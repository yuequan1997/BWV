package org.yuequan.bwv.classfile.parser;

import org.yuequan.bwv.classfile.ClassFile;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClassFileParser {
    private File classFile;
    private static final int BYTES_PER_ROW = 16;
    public ClassFileParser(File classFile) {
        this.classFile = classFile;
    }

    public ClassFile parser(){
        try {
            byte[] classBytes = Files.readAllBytes(Paths.get(classFile.getAbsolutePath()));
            ClassFileReader reader = new ClassFileReader(classBytes);
            ClassFile classFile = new ClassFile(this.classFile.getName() ,reader);

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < classBytes.length; i+=BYTES_PER_ROW) {
                stringBuilder.append(String.format("%08x", i));
                stringBuilder.append("| ");
                hexRow(classBytes, i, stringBuilder);
                stringBuilder.append(" |");
                toRowAscii(classBytes, i, stringBuilder);
                stringBuilder.append('\n');
            }
            classFile.setHexAndAscii(stringBuilder.toString());
            return classFile;
        } catch (IOException e) {
            //TODO: Exception Handler
            e.printStackTrace();
        }
        return null;
    }


    public static void hexRow(byte[] bytes, int offset, StringBuilder sb){
        for (int i = 0; i < BYTES_PER_ROW; i++) {
            if(i + offset < bytes.length){
                byte b = bytes[i + offset];
                sb.append(String.format("%02x ", b).toUpperCase());
            }else{
                sb.append("   ");
            }
        }
    }

    public static void toRowAscii(byte[] bytes, int offset, StringBuilder sb){
        for (int i = 0; i < BYTES_PER_ROW; i++) {
            if(i + offset < bytes.length){
                char c = (char) bytes[i + offset];
                if(c >= '!' && c <= '~'){
                    sb.append(c);
                }else{
                    sb.append(".");
                }
            }
        }
    }
}
