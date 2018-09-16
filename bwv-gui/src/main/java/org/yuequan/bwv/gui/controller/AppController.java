package org.yuequan.bwv.gui.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

public class AppController {

    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane rightPane;

    @FXML
    private BorderPane rootPane;

    private double xOffset;
    private double yOffset;

    @FXML
    public void handleCloseButtonAction(ActionEvent event){
        Platform.exit();
    }

    @FXML
    public void handleTitleWindowOnMousePressed(MouseEvent mouseEvent){
        xOffset = getWindow().getX() - mouseEvent.getScreenX();
        yOffset = getWindow().getY() - mouseEvent.getScreenY();
    }

    @FXML
    public void handleTitleWindowOnMouseDragged(MouseEvent mouseEvent){
        getWindow().setX(mouseEvent.getScreenX() + xOffset);
        getWindow().setY(mouseEvent.getScreenY() + yOffset);
    }

    private Window getWindow(){
        return rootPane.getScene().getWindow();
    }
}
