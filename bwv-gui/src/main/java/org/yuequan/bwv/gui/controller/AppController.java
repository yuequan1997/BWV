package org.yuequan.bwv.gui.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.yuequan.bwv.classfile.ClassFileComponent;
import org.yuequan.bwv.classfile.parser.ClassFileParser;
import org.yuequan.bwv.gui.tree.ClassFileNode;
import org.yuequan.bwv.gui.tree.ClassFileTreeItem;

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

    private double xOffset;
    private double yOffset;

    /**
     * Initialize.
     */
    @FXML
    public void initialize(){
        ClassFileTreeItem rootItem = new ClassFileTreeItem(new ClassFileNode(new ClassFileComponent("Test.class")));
        rootItem.setExpanded(true);
        for (int i = 0; i < 6; i++) {
            ClassFileTreeItem item;
            if(i == 0){
                item = new ClassFileTreeItem(new ClassFileNode(new ClassFileComponent("macgic: 0XCAFEBABE")));
            }else if(i == 1){
                item = new ClassFileTreeItem(new ClassFileNode(new ClassFileComponent("minor_version: 0")));
            }else if(i == 2){
                item = new ClassFileTreeItem(new ClassFileNode(new ClassFileComponent("major_version: 52")));
            }else if(i == 3){
                item = new ClassFileTreeItem(new ClassFileNode(new ClassFileComponent("constant_pool_count: 13")));
            }else if(i == 4){
                item = new ClassFileTreeItem(new ClassFileNode(new ClassFileComponent("constant_pool")));
                item.getChildren().add(new ClassFileTreeItem(new ClassFileNode(new ClassFileComponent("no message"))));
                item.getChildren().add(new ClassFileTreeItem(new ClassFileNode(new ClassFileComponent("no message"))));
            } else if(i == 5){
                item = new ClassFileTreeItem(new ClassFileNode(new ClassFileComponent("interfaces_count: 0")));
            }else{
                item = new ClassFileTreeItem(new ClassFileNode(new ClassFileComponent("no message")));
            }
            rootItem.getChildren().add(item);
        }
        treeView.setRoot(rootItem);

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
            new ClassFileParser(file).parser();
        }
    }

    private Window getWindow(){
        return rootPane.getScene().getWindow();
    }
}
