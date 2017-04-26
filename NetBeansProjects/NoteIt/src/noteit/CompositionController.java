/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javax.media.j3d.View;
import static javax.media.j3d.View.VISIBILITY_DRAW_VISIBLE;

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

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    ImageView details;
    @FXML
    private void handleClickQuarterNote(MouseEvent me) {
        
        save.setText("clicked!");
        quarterNoteForStaff.setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        quarterNoteForStaff.setVisible(false);
    }    
    
}

