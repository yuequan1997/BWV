package org.yuequan.bwv.gui.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Class file node.
 * @author yuequan
 */
public class ClassFileNode {
    private String name;
    private List<ClassFileNode> children = new ArrayList<>();

    /**
     * Instantiates a new Class file node.
     */
    public ClassFileNode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
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
