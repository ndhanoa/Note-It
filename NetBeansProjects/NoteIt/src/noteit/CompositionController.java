/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteit;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CompositionController implements Initializable {
    
    @FXML 
    private AnchorPane screen;
    
    @FXML
    private Label label;
    
    @FXML
    private Line lineF;
    
    @FXML
    private Line lineD;
    
    @FXML
    private Line lineB;
    
    @FXML
    private Line lineG;
    
    @FXML
    private Line lineE;
    
    @FXML
    private Button quarternote;
    
    @FXML
    private Button save;
    
    @FXML
    private Button load;
    
    @FXML
    private ImageView quarternoteImage;

    ImageView details;
    
    @FXML
    private FileChooser fc;
    
    private Desktop desktop = Desktop.getDesktop();
    
    private Stage stage;
    
    private Boolean hasQuarterNote;
    
    @FXML
    private void handleClickQuarterNote(MouseEvent me) {
        hasQuarterNote=true;
    }
    
    @FXML
    private void handleClickStaffLine(MouseEvent me){
      
      
       double mouseX = me.getSceneX();
       double mouseY = me.getSceneY();
       if(hasQuarterNote==true){
             ImageView newNote = new ImageView(getClass().getResource("quarternote.png").toString());
             screen.getChildren().add(newNote);
             newNote.setFitWidth(41);
             newNote.setFitHeight(57);
             newNote.setX(mouseX-17);
             newNote.setY(mouseY-45);
       }
             
       
     
    }
    
    @FXML
    private void handleClickStaffSpace(MouseEvent me){
        double mouseX = me.getX();
        double mouseY = me.getY();
        if(hasQuarterNote == true && ((mouseY>52 && mouseY<60)||(mouseY>70 && mouseY<78)||(mouseY>88&& mouseY<96)||(mouseY>107&&mouseY<115))){
            ImageView newNote = new ImageView(getClass().getResource("quarternote.png").toString());
            screen.getChildren().add(newNote);
            newNote.setFitWidth(41);
            newNote.setFitHeight(57);
            newNote.setX(mouseX);
            newNote.setY(mouseY-43);
        }
    }
    
    
    
    @FXML
    private void save(MouseEvent change) throws FileNotFoundException, IOException{
         fc = new FileChooser();
        File selectedFile = fc.showSaveDialog(stage);
        if(selectedFile != null){
            try(FileOutputStream out = new FileOutputStream(selectedFile)){
                
            }
        } else {
            System.out.println("Error: File is not valid.");
        }
    }
    @FXML
    private void load(MouseEvent change){
        fc = new FileChooser();
        fc.setTitle("Open text file");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fc.showOpenDialog(stage);
        if(selectedFile != null){
            System.out.println("Chosen file: " + selectedFile);
            openFile(selectedFile);
        } else {
            System.out.println("Error: File is not valid.");
        }
        
        
    }
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                CompositionController.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
    
    public void init(Stage stage){
        this.stage = stage;
    }   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hasQuarterNote=false;
    }    
    
}

