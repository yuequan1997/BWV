package org.yuequan.bwv.gui.tree;

import org.yuequan.bwv.classfile.ClassFileComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Class file node.
 * @see org.yuequan.bwv.classfile.ClassFileComponent
 * @author yuequan
 */
public class ClassFileNode {
    private ClassFileComponent classFileComponent;
    private List<ClassFileNode> children = new ArrayList<>();

    /**
     * Instantiates a new Class file node.
     *
     * @param classFileComponent the class file component
     */
    public ClassFileNode(ClassFileComponent classFileComponent) {
        this.classFileComponent = classFileComponent;
    }

    @Override
    public String toString() {
        return classFileComponent.getName();
    }

    /**
     * Gets children.
     *
     * @return the children
     */
    public List<ClassFileNode> getChildren() {
        return children;
    }
}
