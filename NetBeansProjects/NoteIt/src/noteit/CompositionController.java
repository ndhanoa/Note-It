/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteit;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;

public class CompositionController implements Initializable {
    
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
    private ImageView quarternoteImage;
    
    @FXML 
    private ImageView quarterNoteForStaff;

    ImageView details;
    @FXML
    private void handleClickQuarterNote(MouseEvent me) {
        
        save.setText("clicked!");
        quarterNoteForStaff.setVisible(true);
    }
     @FXML
    private ListView fileList;
    private FileChooser fileChooser;
    private void handleButtonAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null){
            fileList.getItems().add(selectedFile.getName());
        } else {
            System.out.println("Error: File is not valid.");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        quarterNoteForStaff.setVisible(false);
    }    
    
}

