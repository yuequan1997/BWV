package org.yuequan.bwv.gui.tree;

import javafx.scene.control.TreeItem;


/**
 * The type Class file tree item.
 * @see javafx.scene.control.TreeItem
 * @author yuequan
 */
public class ClassFileTreeItem extends TreeItem<ClassFileNode> {
    private boolean isRootNode = true;

    /**
     * Instantiates a new Class file tree item.
     *
     * @param classFileNode the class file node
     */
    public ClassFileTreeItem(ClassFileNode classFileNode) {
        super(classFileNode);
    }
}
