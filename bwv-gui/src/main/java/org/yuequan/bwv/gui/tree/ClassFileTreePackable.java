package org.yuequan.bwv.gui.tree;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.yuequan.bwv.classfile.ClassFile;
import org.yuequan.bwv.classfile.constant.InfoWrapper;

import java.util.stream.IntStream;

public class ClassFileTreePackable {
    public static void pack(ClassFile classFile, TreeView<ClassFileNode> treeView){
        ClassFileTreeItem rootItem = new ClassFileTreeItem(new ClassFileNode("XXX"));
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

        treeView.setRoot(rootItem);
    }
}
