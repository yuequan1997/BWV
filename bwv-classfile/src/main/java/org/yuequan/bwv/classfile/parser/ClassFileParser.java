package org.yuequan.bwv.classfile.parser;

import org.yuequan.bwv.classfile.ClassFile;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClassFileParser {
    private File classFile;

    public ClassFileParser(File classFile) {
        this.classFile = classFile;
    }

    public void parser(){
        try {
            byte[] classBytes = Files.readAllBytes(Paths.get(classFile.getAbsolutePath()));
            ClassFileReader reader = new ClassFileReader(classBytes);
            ClassFile classFile = new ClassFile(reader);
        } catch (IOException e) {
            //TODO: Exception Handler
            e.printStackTrace();
        }
    }
}
