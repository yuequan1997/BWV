package org.yuequan.bwv.gui.view;

import javafx.fxml.FXMLLoader;

/**
 * Load Current Catalog XML
 * @author yuequan
 */
public class ViewLoader {
    /**
     * Load fxml loader.
     *
     * @param fxmlPath the fxml path
     * @return the fxml loader
     */
    public static FXMLLoader load(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(ViewLoader.class.getResource(fxmlPath));
        return loader;
    }
}
