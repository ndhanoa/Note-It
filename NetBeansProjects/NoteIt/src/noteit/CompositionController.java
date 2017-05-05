/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteit;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
    private ListView fileList;
    @FXML
    private FileChooser fc;
    
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
    private void save(MouseEvent change){
         fc = new FileChooser();
        File selectedFile = fc.showSaveDialog(stage);
        if(selectedFile != null){
            fileList.getItems().add(selectedFile.getName());
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
            fileList.getItems().add(selectedFile.getName());
        } else {
            System.out.println("Error: File is not valid.");
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

