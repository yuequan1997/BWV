package org.yuequan.bwv.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.yuequan.bwv.gui.view.ViewLoader;

import java.io.IOException;


/**
 *  App starter
 */
public class App extends Application {
    public Stage primaryStage;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initUI();
        primaryStage.show();
    }

    private void initUI() {
        try {
            FXMLLoader loader = ViewLoader.load("AppView.fxml");
            Scene scene = new Scene(loader.load());
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
