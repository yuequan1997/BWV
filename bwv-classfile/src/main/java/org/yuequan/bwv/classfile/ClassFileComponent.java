package org.yuequan.bwv.classfile;


/**
 * Used to describe the class file structure
 * @author yuequan
 */
public class ClassFileComponent {
    private String name;

    /**
     * Instantiates a new Class file component.
     */
    public ClassFileComponent() {
    }

    /**
     * Instantiates a new Class file component.
     *
     * @param name the name
     */
    public ClassFileComponent(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
