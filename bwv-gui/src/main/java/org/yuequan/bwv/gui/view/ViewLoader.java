package org.yuequan.bwv.gui.view;

import javafx.fxml.FXMLLoader;

public class ViewLoader {
    public static FXMLLoader load(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(ViewLoader.class.getResource(fxmlPath));
        return loader;
    }
}
