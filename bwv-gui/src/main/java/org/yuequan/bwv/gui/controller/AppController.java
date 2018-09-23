package org.yuequan.bwv.gui.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.yuequan.bwv.classfile.ClassFile;
import org.yuequan.bwv.classfile.parser.ClassFileParser;
import org.yuequan.bwv.gui.tree.ClassFileNode;
import org.yuequan.bwv.gui.tree.ClassFileTreePackable;

import java.io.File;

/**
 * The type App controller.
 * @author yuequan
 */
public class AppController {

    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private BorderPane rootPane;
    @FXML
    private TreeView<ClassFileNode> treeView;
    @FXML
    private TextArea hexTextArea;

    private double xOffset;
    private double yOffset;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
    }

    /**
     * Handle close button action.
     *
     * @param event the event
     */
    @FXML
    public void handleCloseButtonAction(ActionEvent event){
        Platform.exit();
    }

    /**
     * Handle title window on mouse pressed.
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    public void handleTitleWindowOnMousePressed(MouseEvent mouseEvent){
        xOffset = getWindow().getX() - mouseEvent.getScreenX();
        yOffset = getWindow().getY() - mouseEvent.getScreenY();
    }

    /**
     * Handle title window on mouse dragged.
     *
     * @param mouseEvent the mouse event
     */
    @FXML
    public void handleTitleWindowOnMouseDragged(MouseEvent mouseEvent){
        getWindow().setX(mouseEvent.getScreenX() + xOffset);
        getWindow().setY(mouseEvent.getScreenY() + yOffset);
    }

    @FXML
    public void handleOpenFileOnAction(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Class File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Choose Class File", "*.class"));
        File file = fileChooser.showOpenDialog(getWindow());
        if(file != null){
            ClassFile classFile = new ClassFileParser(file).parser();
            ClassFileTreePackable.pack(classFile, treeView);
            hexTextArea.setText(classFile.getHexAndAscii());
        }
    }

    private Window getWindow(){
        return rootPane.getScene().getWindow();
    }
}
