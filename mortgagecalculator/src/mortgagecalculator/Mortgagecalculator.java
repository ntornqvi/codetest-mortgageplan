/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mortgagecalculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author nicke
 */
public class Mortgagecalculator extends Application {
    
    private ArrayList<String> strings = new ArrayList();
    
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
        
        VBox v = new VBox();
        
        Button showButton = new Button();
        showButton.setText("Show Output");
        showButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    getStrings(parser);
                } catch (IOException ex) {
                    Logger.getLogger(Mortgagecalculator.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(String s : strings) {
                    Text t = new Text(s);
                    t.setWrappingWidth(350);
                    v.getChildren().add(t);
                }
            }
        });
        
        v.setSpacing(10);
        v.setPadding(new Insets(10,10,10,10));
        v.setMinHeight(100);
        
        HBox h = new HBox();
        h.getChildren().addAll(fileButton, showButton);
        h.setSpacing(50);
        h.setPadding(new Insets(10,10,10,10));
        
        BorderPane root = new BorderPane();
        root.minHeight(400);
        root.minWidth(400);
        
        root.setCenter(v);
        root.setBottom(h);
        
        Scene scene = new Scene(root, 400, 400);
        
        primaryStage.setTitle("Mortgage Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void getStrings(Parser parser) throws IOException {
        this.strings = parser.getStrings();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
