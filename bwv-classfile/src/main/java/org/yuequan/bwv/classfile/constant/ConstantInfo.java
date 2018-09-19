package org.yuequan.bwv.classfile.constant;

import org.yuequan.bwv.classfile.datatype.U1;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.reader.ClassFileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.runtime.ScriptingFunctions.exec;

public class ConstantInfo {
    private final U2 constantPoolCount;
    private final ClassFileReader fileReader;
    private List<Info> infos = new ArrayList<>();
    private Map<Integer, String> constantUtf8StringMapper = new HashMap<>();
    private Map<Integer, ConstantUtf8Info> constantUtf8InfoMapper = new HashMap<>();
    public ConstantInfo(U2 constantPoolCount, ClassFileReader fileReader) {
        this.constantPoolCount = constantPoolCount;
        this.fileReader = fileReader;
        fillInfos();
        System.out.println("Compeleted");
    }

    private void fillInfos() {
        for (short i = 1; i < constantPoolCount.getValue(); i++) {
            U1 tag = new U1("tag", fileReader.readByte());
            new ConstantInfoStrategy(i, tag);
        }
    }

    private class ConstantInfoStrategy{
        private final U1 tag;
        private final int index;
        public ConstantInfoStrategy(int index, U1 tag) {
            this.tag = tag;
            this.index = index;
            exec(tag.getValue());
        }
        public void exec(byte tag){
            switch (tag){
                case 7:
                    infos.add(new ConstantClassInfo(this.tag, fileReader));
                    break;
                case 9:
                    infos.add(new ConstantFieldRefInfo(this.tag, fileReader));
                    break;
                case 10:
                    infos.add(new ConstantMethodrefInfo(this.tag, fileReader));
                    break;
                case 11:
                    infos.add(new ConstantInterfaceMethodRefInfo(this.tag, fileReader));
                    break;
                case 8:
                    infos.add(new ConstantStringInfo(this.tag, fileReader));
                    break;
                case 3:
                    infos.add(new ConstantIntegerInfo(this.tag, fileReader));
                    break;
                case 4:
                    infos.add(new ConstantFloatInfo(this.tag, fileReader));
                    break;
                case 5:
                    infos.add(new ConstantLongInfo(this.tag, fileReader));
                    break;
                case 6:
                    infos.add(new ConstantDoubleInfo(this.tag, fileReader));
                    break;
                case 12:
                    infos.add(new ConstantNameAndTypeInfo(this.tag, fileReader));
                    break;
                case 1:
                    ConstantUtf8Info constantUtf8Info = new ConstantUtf8Info(this.tag, fileReader);
                    infos.add(constantUtf8Info);
                    constantUtf8StringMapper.put(index, new String(constantUtf8Info.getBytes()));
                    constantUtf8InfoMapper.put(index, constantUtf8Info);
                    break;
                case 15:
                    infos.add(new ConstantMethodHandleInfo(this.tag, fileReader));
                    break;
                case 16:
                    infos.add(new ConstantMethodTypeInfo(this.tag, fileReader));
                    break;
                case 18:
                    infos.add(new ConstantInvokeDynamicInfo(this.tag, fileReader));
                    break;
                default:
                    //TODO: Throws Exception
                    break;
            }
        }
    }
}
