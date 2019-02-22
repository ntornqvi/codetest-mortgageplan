/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mortgagecalculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author nicke
 */
public class Mortgagecalculator extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Parser parser = new Parser();
        
        Button fileButton = new Button();
        fileButton.setText("Select File");
        fileButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                parser.selectFile(primaryStage);
            }
        });
        
        GridPane root = new GridPane();
        
        GridPane.setConstraints(fileButton, 0, 1);
        root.getChildren().add(fileButton);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
