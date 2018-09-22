package org.yuequan.bwv.classfile.constant;

import com.sun.org.apache.bcel.internal.classfile.ConstantClass;
import com.sun.org.apache.bcel.internal.classfile.ConstantFloat;
import com.sun.org.apache.bcel.internal.classfile.ConstantInterfaceMethodref;
import com.sun.org.apache.bcel.internal.classfile.ConstantLong;

import java.util.List;
import java.util.Map;

public class InfoWrapper {
    private Info info;
    private String typeName;
    private String value;
    private Map<Integer, String> constantUtf8StringMapper;
    private List<Info> infos;

    public InfoWrapper(Info info, Map<Integer, String> constantUtf8StringMapper, List<Info> infos){
        this.info = info;
        this.constantUtf8StringMapper = constantUtf8StringMapper;
        this.infos =infos;
        wrap();
    }

    private void wrap() {
        if(info instanceof ConstantClassInfo){
            ConstantClassInfo classInfo = (ConstantClassInfo) info;
            typeName = "Class";
            value = constantUtf8StringMapper.get(Integer.valueOf(classInfo.getNameIndex().getValue()));
        }else if(info instanceof ConstantFieldRefInfo || info instanceof ConstantMethodrefInfo || info instanceof ConstantInterfaceMethodRefInfo){
            StringBuilder valueBuilder = new StringBuilder();
            ConstantClassInfo classInfo = null;
            ConstantNameAndTypeInfo nameAndTypeInfo = null;
            //TODO：Refactor Extends  ConstantRef
            if(info instanceof ConstantFieldRefInfo){
                typeName = "FieldRef";
                ConstantFieldRefInfo fieldRefInfo = (ConstantFieldRefInfo) info;
                classInfo = (ConstantClassInfo) infos.get(fieldRefInfo.getClassIndex().getValue() - 1);
                nameAndTypeInfo = (ConstantNameAndTypeInfo) infos.get(fieldRefInfo.getNameAndTypeIndex().getValue() - 1);
            }else if(info instanceof ConstantMethodrefInfo){
                typeName = "MethodRef";
                ConstantMethodrefInfo methodrefInfo = (ConstantMethodrefInfo) info;
                classInfo = (ConstantClassInfo) infos.get(methodrefInfo.getClassIndex().getValue() - 1);
                nameAndTypeInfo = (ConstantNameAndTypeInfo) infos.get(methodrefInfo.getNameAndTypeIndex().getValue() - 1);
            }else if(info instanceof ConstantInterfaceMethodRefInfo){
                typeName = "InterfaceMethodRef";
                ConstantInterfaceMethodRefInfo interfaceMethodRefInfo = (ConstantInterfaceMethodRefInfo) info;
                classInfo = (ConstantClassInfo) infos.get(interfaceMethodRefInfo.getClassIndex().getValue() - 1);
                nameAndTypeInfo = (ConstantNameAndTypeInfo) infos.get(interfaceMethodRefInfo.getNameAndTypeIndex().getValue() - 1);
            }
            valueBuilder.append(constantUtf8StringMapper.get(Integer.valueOf(classInfo.getNameIndex().getValue())));
            valueBuilder.append(".");
            valueBuilder.append(constantUtf8StringMapper.get(Integer.valueOf(nameAndTypeInfo.getNameIndex().getValue())));
            value = valueBuilder.toString();
        }else if(info instanceof ConstantStringInfo){
            ConstantStringInfo stringInfo = (ConstantStringInfo) info;
            typeName = "String";
            value = constantUtf8StringMapper.get(Integer.valueOf(stringInfo.getStringIndex().getValue()));
        }else if(info instanceof ConstantIntegerInfo || info instanceof ConstantFloatInfo){
            //TODO: Refactor extends 32bit Info
            if(info instanceof ConstantIntegerInfo){
                typeName = "Integer";
                ConstantIntegerInfo integerInfo = (ConstantIntegerInfo) info;
                value = "" + integerInfo.getBytes().getValue();
            }else if(info instanceof  ConstantFloatInfo){
                ConstantFloatInfo floatInfo = (ConstantFloatInfo) info;
                typeName = "Float";
                value = "" + floatInfo.getBytes().getValue();
            }
        }else if(info instanceof ConstantLongInfo || info instanceof ConstantDoubleInfo){
            //TODO: Refactor externds 64bit Info
            if(info instanceof ConstantLongInfo){
                typeName = "Long";
                ConstantLongInfo longInfo = (ConstantLongInfo) info;
                long  longValue = ((long) longInfo.getHighBytes().getValue() << 32) + (longInfo.getLowBytes().getValue() & 0xffffffffL);
                value = "" + longValue;
            }else if(info instanceof ConstantDoubleInfo){
                typeName = "Double";
                ConstantDoubleInfo doubleInfo = (ConstantDoubleInfo) info;
                double  doubleValue = Double.longBitsToDouble( ((long) doubleInfo.getHighBytes().getValue() << 32) + (doubleInfo.getLowBytes().getValue() & 0xffffffffL));
                value = "" + doubleValue;
            }
        }else if(info instanceof ConstantNameAndTypeInfo){
            ConstantNameAndTypeInfo constantNameAndTypeInfo = (ConstantNameAndTypeInfo) info;
            typeName = "NameAndType";
            value = constantUtf8StringMapper.get(Integer.valueOf(constantNameAndTypeInfo.getNameIndex().getValue()));
        }else if(info instanceof ConstantUtf8Info){
            typeName = "UTF8";
            ConstantUtf8Info utf8Info = (ConstantUtf8Info) info;
            value = new String(utf8Info.getBytes());
        }else if(info instanceof ConstantMethodHandleInfo){
            typeName = "MethodHandle";
            ConstantMethodHandleInfo methodHandleInfo = (ConstantMethodHandleInfo) info;
            byte kind = methodHandleInfo.getReferenceKind().getValue();
            StringBuilder valueBuilder = new StringBuilder();
            ConstantClassInfo classInfo = null;
            ConstantNameAndTypeInfo nameAndTypeInfo = null;
            // TODO: Refactor Constant Or Enum
            if(kind == 1 || kind == 2 || kind == 3 || kind == 4) {
                //TODO: Refactor
                ConstantFieldRefInfo constantFieldRefInfo = (ConstantFieldRefInfo) infos.get(methodHandleInfo.getReferenceIndex().getValue() - 1);
                classInfo = (ConstantClassInfo) infos.get(constantFieldRefInfo.getClassIndex().getValue() - 1);
                nameAndTypeInfo = (ConstantNameAndTypeInfo) infos.get(constantFieldRefInfo.getNameAndTypeIndex().getValue() - 1);
            }else if(kind == 5 || kind == 8){
                ConstantMethodrefInfo methodrefInfo = (ConstantMethodrefInfo) infos.get(methodHandleInfo.getReferenceIndex().getValue() - 1);
                classInfo = (ConstantClassInfo) infos.get(methodrefInfo.getClassIndex().getValue() - 1);
                nameAndTypeInfo = (ConstantNameAndTypeInfo) infos.get(methodrefInfo.getNameAndTypeIndex().getValue() - 1);
            }
            valueBuilder.append(constantUtf8StringMapper.get(Integer.valueOf(classInfo.getNameIndex().getValue())));
            valueBuilder.append(".");
            valueBuilder.append(constantUtf8StringMapper.get(Integer.valueOf(nameAndTypeInfo.getNameIndex().getValue())));
            value = valueBuilder.toString();
        }else if(info instanceof ConstantMethodTypeInfo){
            typeName = "MethodType";
            ConstantMethodTypeInfo methodTypeInfo = (ConstantMethodTypeInfo) info;
            value = constantUtf8StringMapper.get(Integer.valueOf(methodTypeInfo.getDescriptorIndex().getValue()));
        }else if(info instanceof ConstantInvokeDynamicInfo){
            typeName = "InvokeDynamic";
            ConstantInvokeDynamicInfo invokeDynamicInfo = (ConstantInvokeDynamicInfo) info;
            ConstantNameAndTypeInfo nameAndTypeInfo = (ConstantNameAndTypeInfo) infos.get(invokeDynamicInfo.getNameAndTypeIndex().getValue());
            value = constantUtf8StringMapper.get(Integer.valueOf(nameAndTypeInfo.getNameIndex().getValue()));
        }else{
            //TODO: unknow type exception
        }
    }

    public Info getInfo() {
        return info;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getValue() {
        return value;
    }

}
