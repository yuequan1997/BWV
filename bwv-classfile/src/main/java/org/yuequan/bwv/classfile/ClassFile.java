package org.yuequan.bwv.classfile;

import org.yuequan.bwv.classfile.attribute.AttributeInfo;
import org.yuequan.bwv.classfile.constant.ConstantClassInfo;
import org.yuequan.bwv.classfile.constant.ConstantFieldRefInfo;
import org.yuequan.bwv.classfile.constant.ConstantPool;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.datatype.U4;
import org.yuequan.bwv.classfile.field.FieldInfo;
import org.yuequan.bwv.classfile.method.MethodInfo;
import org.yuequan.bwv.classfile.reader.ClassFileReader;

import java.util.ArrayList;
import java.util.List;

public class ClassFile {
    private final U4 magic;
    private final U2 minorVersion;
    private final U2 majorVersion;
    private final U2 constantPoolCount;
    private final ConstantPool constantPool;
    private final U2 accessFlags;
    private final U2 thisClass;
    private final U2 superClass;
    private final U2 interfacesCount;
    private final U2[] interfaces;
    private final U2 fieldsCount;
    private final FieldInfo[] fieldInfos;
    private final U2 methodCount;
    private final MethodInfo[] methodInfos;
    private final U2 attributesCount;
    private final AttributeInfo[] attributeInfos;
    private String name;
    private String hexAndAscii;
    public ClassFile(String name, ClassFileReader fileReader) {
        this.name = name;
        this.magic = new U4("magic", fileReader.readInt());
        this.minorVersion = new U2("minor_version", fileReader.readShort());
        this.majorVersion = new U2("major_version", fileReader.readShort());
        this.constantPoolCount = new U2("constant_pool_count", fileReader.readShort());
        this.constantPool = new ConstantPool(this.constantPoolCount, fileReader);
        this.accessFlags = new U2("access_flags", fileReader.readShort());
        this.thisClass = new U2("this_class", fileReader.readShort());
        this.superClass = new U2("super_class", fileReader.readShort());
        this.interfacesCount = new U2("interfaces_count", fileReader.readShort());
        this.interfaces = new U2[this.interfacesCount.getValue()];
        for (int i = 0; i < this.interfaces.length; i++) {
            interfaces[i] = new U2("interface", fileReader.readShort());
        }
        this.fieldsCount = new U2("fields_count", fileReader.readShort());
        this.fieldInfos = new FieldInfo[this.fieldsCount.getValue()];
        for (int i = 0; i < fieldInfos.length; i++) {
            fieldInfos[i] = new FieldInfo(fileReader);
        }
        this.methodCount = new U2("method_count", fileReader.readShort());
        methodInfos = new MethodInfo[this.methodCount.getValue()];
        for (int i = 0; i < methodInfos.length; i++) {
            methodInfos[i] = new MethodInfo(fileReader);
        }
        this.attributesCount = new U2("attributes_count", fileReader.readShort());
        this.attributeInfos = new AttributeInfo[this.attributesCount.getValue()];
        for (int i = 0; i < attributeInfos.length; i++) {
            attributeInfos[i] = new AttributeInfo(fileReader);
        }
    }


    public U4 getMagic() {
        return magic;
    }

    public String getMagicHex(){
        return "0x" + Integer.toHexString(magic.getValue()).toUpperCase();
    }

    public U2 getMinorVersion() {
        return minorVersion;
    }

    public U2 getMajorVersion() {
        return majorVersion;
    }

    public U2 getConstantPoolCount() {
        return constantPoolCount;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public U2 getAccessFlags() {
        return accessFlags;
    }

    public U2 getThisClass() {
        return thisClass;
    }

    public U2 getSuperClass() {
        return superClass;
    }

    public U2 getInterfacesCount() {
        return interfacesCount;
    }

    public U2[] getInterfaces() {
        return interfaces;
    }

    public U2 getFieldsCount() {
        return fieldsCount;
    }

    public FieldInfo[] getFieldInfos() {
        return fieldInfos;
    }

    public U2 getMethodCount() {
        return methodCount;
    }

    public MethodInfo[] getMethodInfos() {
        return methodInfos;
    }

    public U2 getAttributesCount() {
        return attributesCount;
    }

    public AttributeInfo[] getAttributeInfos() {
        return attributeInfos;
    }

    public String getThisClassString(){
        ConstantClassInfo classInfo = (ConstantClassInfo) constantPool.getConstantInfo().getInfos().get(thisClass.getValue() - 1);
        return new String(constantPool.getConstantInfo().getConstantUtf8InfoMapper().get(Integer.valueOf(classInfo.getNameIndex().getValue())).getBytes());
    }

    public String getSuperClassString(){
        ConstantClassInfo classInfo = (ConstantClassInfo) constantPool.getConstantInfo().getInfos().get(superClass.getValue() - 1);
        return new String(constantPool.getConstantInfo().getConstantUtf8InfoMapper().get(Integer.valueOf(classInfo.getNameIndex().getValue())).getBytes());
    }

    public List<ConstantClassInfo> getInterfaceList(){
        List<ConstantClassInfo> constantClassInfos = new ArrayList<>();
        for (U2 interfaceU2 : this.getInterfaces()) {
            ConstantClassInfo classInfo = (ConstantClassInfo) constantPool.getConstantInfo().getInfos().get(interfaceU2.getValue() - 1);
            constantClassInfos.add(classInfo);
        }
        return constantClassInfos;
    }

    public List<String> getInterfaceStringList(){
        List<String> interfaceStringList  = new ArrayList<>();
        getInterfaceList().forEach(constantClassInfo -> {
            interfaceStringList.add(new String(constantPool.getConstantInfo().getConstantUtf8InfoMapper().get(Integer.valueOf(constantClassInfo.getNameIndex().getValue())).getBytes()));
        });
        return  interfaceStringList;
    }

    public List<String> getFieldList(){
        List<String> constantFieldRefInfos = new ArrayList<>();
        for (FieldInfo fieldInfo : this.fieldInfos) {
            constantFieldRefInfos.add(constantPool.getConstantInfo().getInfoWrappers().get(fieldInfo.getNameIndex().getValue() - 1).getValue());
        }
        return constantFieldRefInfos;
    }

    public List<String> getMethodList(){
        List<String> methods = new ArrayList<>();
        for (MethodInfo methodInfo : this.methodInfos) {
            methods.add(constantPool.getConstantInfo().getInfoWrappers().get(methodInfo.getNameIndex().getValue() - 1).getValue());
        }
        return methods;
    }

    public List<String> getAttributeList(){
        List<String> attributes = new ArrayList<>();
        for (AttributeInfo attributeInfo : this.getAttributeInfos()) {
            attributes.add(constantPool.getConstantInfo().getInfoWrappers().get(attributeInfo.getAttributeNameIndex().getValue() - 1).getValue());
        }
        return attributes;
    }

    public String getName() {
        return name;
    }

    public String getHexAndAscii() {
        return hexAndAscii;
    }

    public void setHexAndAscii(String hexAndAscii) {
        this.hexAndAscii = hexAndAscii;
    }
}
