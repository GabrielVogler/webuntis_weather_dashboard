package com.voglic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import com.voglic.backend.WebUntisApi;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    
    /** 
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {

        /**
         * Stop Programm on Window Close
         */
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });

        scene = new Scene(loadFXML("index"), 1280, 720);
        stage.setScene(scene);
        stage.setMinHeight(760);
        stage.setMaxHeight(760);
        stage.setMinWidth(1280);
        stage.setMaxWidth(1280);
        stage.show();
    }

    
    /** 
     * @param fxml
     * @throws IOException
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    
    /** 
     * @param fxml
     * @return Parent
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        WebUntisApi.run("cd ./WebUntis_Webscraper && npm start");
        launch();
    }

}