package org.yuequan.bwv.gui.tree;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.yuequan.bwv.classfile.ClassFile;
import org.yuequan.bwv.classfile.constant.InfoWrapper;
import org.yuequan.bwv.classfile.datatype.U2;
import org.yuequan.bwv.classfile.flag.AccessFlag;

import java.util.stream.IntStream;

public class ClassFileTreePackable {
    public static void pack(ClassFile classFile, TreeView<ClassFileNode> treeView){
        ClassFileTreeItem rootItem = new ClassFileTreeItem(new ClassFileNode(classFile.getName()));
        rootItem.setExpanded(true);
        rootItem.getChildren().add(new ClassFileTreeItem(new ClassFileNode("magic：" + classFile.getMagicHex())));
        rootItem.getChildren().add(new ClassFileTreeItem(new ClassFileNode("minor_version：" + classFile.getMinorVersion().getValue())));
        rootItem.getChildren().add(new ClassFileTreeItem(new ClassFileNode("major_version：" + classFile.getMajorVersion().getValue())));
        rootItem.getChildren().add(new ClassFileTreeItem(new ClassFileNode("constant_pool_count：" + classFile.getConstantPoolCount().getValue())));
        ClassFileTreeItem constantPool = new ClassFileTreeItem(new ClassFileNode("constant_pool"));
        rootItem.getChildren().add(constantPool);
        IntStream.range(0, classFile.getConstantPool().getConstantInfo().getInfoWrappers().size()).forEach(index -> {
            InfoWrapper infoWrapper = classFile.getConstantPool().getConstantInfo().getInfoWrappers().get(index);
            constantPool.getChildren().add(new ClassFileTreeItem(new ClassFileNode("#"+(index+1)+" ("+infoWrapper.getTypeName()+"): " + infoWrapper.getValue())));
        });
        rootItem.getChildren().add(new ClassFileTreeItem(new ClassFileNode("access_flags：" + AccessFlag.getAccessFlags(classFile.getAccessFlags().getValue()))));
        rootItem.getChildren().add(new ClassFileTreeItem(new ClassFileNode("this_class: " + classFile.getThisClassString())));
        rootItem.getChildren().add(new ClassFileTreeItem(new ClassFileNode("super_class: " + classFile.getSuperClassString())));
        rootItem.getChildren().add(new ClassFileTreeItem(new ClassFileNode("interfaces_count: " + classFile.getInterfacesCount().getValue())));

        ClassFileTreeItem interfaces = new ClassFileTreeItem(new ClassFileNode("interfaces"));
        rootItem.getChildren().add(interfaces);
        classFile.getInterfaceStringList().forEach(interfaceString -> interfaces.getChildren().add(new ClassFileTreeItem(new ClassFileNode(interfaceString))));

        rootItem.getChildren().add(new ClassFileTreeItem(new ClassFileNode("fields_count: " + classFile.getFieldsCount().getValue())));

        ClassFileTreeItem fields = new ClassFileTreeItem(new ClassFileNode("fields"));
        rootItem.getChildren().add(fields);
        classFile.getFieldList().forEach(field -> fields.getChildren().add(new ClassFileTreeItem(new ClassFileNode(field))));

        rootItem.getChildren().add(new ClassFileTreeItem(new ClassFileNode("method_count: "+classFile.getMethodCount().getValue())));
        ClassFileTreeItem methods = new ClassFileTreeItem(new ClassFileNode("methods"));
        rootItem.getChildren().add(methods);
        classFile.getMethodList().forEach(method -> methods.getChildren().add(new ClassFileTreeItem(new ClassFileNode(method))));

        rootItem.getChildren().add(new ClassFileTreeItem(new ClassFileNode("attributes_count: "+classFile.getAttributesCount().getValue())));
        ClassFileTreeItem attributes = new ClassFileTreeItem(new ClassFileNode("attributes"));
        classFile.getAttributeList().forEach(attribute -> attributes.getChildren().add(new ClassFileTreeItem(new ClassFileNode(attribute))));
        rootItem.getChildren().add(attributes);
        treeView.setRoot(rootItem);
    }
}
