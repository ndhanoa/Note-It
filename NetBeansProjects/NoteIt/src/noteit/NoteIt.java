/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteit;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author ndhanoa
 */
public class NoteIt extends Application {
    
    private Button save;
    private FileChooser fileChooser;
    private File file;
    private Desktop desktop = Desktop.getDesktop();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*txt"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*wav", "*.mp3", "*.aac"),
                new ExtensionFilter("All Files", "*.*")
        );
        save = new Button("Save");
        save.setOnAction(e ->{
            file = fileChooser.showOpenDialog(stage);
            if(file != null){
                try {
                    desktop.open(file);
                } catch (IOException ex) {
                    Logger.getLogger(NoteIt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
