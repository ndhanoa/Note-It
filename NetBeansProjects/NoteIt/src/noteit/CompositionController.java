/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteit;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Math.abs;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    private ImageView newNote;
    
    @FXML 
    private ImageView quarterNoteForStaff;
    
    private Desktop desktop = Desktop.getDesktop();
    
    private ArrayList<NoteClass> array = new ArrayList<NoteClass>();
    
    ImageView details;
    
    @FXML
    private FileChooser fc;
    
    private Stage stage;
    
    private Boolean hasQuarterNote;
    
    private Boolean deletePressed;
    
    @FXML
    private Button delete;
    
    @FXML
    private void handleClickQuarterNote(MouseEvent me) {
        hasQuarterNote=true;
        deleteFunction = false;
    }
    
    @FXML

    private Button bob;
    private double bobX;
    private double bobY;



    private double imageX;
    private double imageY;
    private boolean deleteFunction;
   
    private ArrayList<ImageView> images = new ArrayList<ImageView>();
    
    @FXML

      
       if(deleteFunction == true){

        double mouseX = me.getSceneX();
        double mouseY = me.getSceneY();
        if(hasQuarterNote == true){
            ImageView newNote = new ImageView(getClass().getResource("quarternote.png").toString());
            newNote.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    if(deleteFunction ==true){
                        ImageView clickedView = (ImageView) me.getTarget();
                        for (NoteClass note: array) {
                            ImageView thisImage = note.getImageView();
                            if (thisImage == clickedView) {
                                images.remove(thisImage);
                                screen.getChildren().remove(thisImage);
                                array.remove(note);
                            }
                        }
                    }
                };
            });
             screen.getChildren().add(newNote);
             newNote.setFitWidth(41);
             newNote.setFitHeight(57);
             newNote.setX(mouseX-17);
             newNote.setY(mouseY-45);
            NoteClass y = new NoteClass();
            y.setX(mouseX);
            y.setY(mouseY-43);
            y.setImageView(newNote);
            array.add(y);
        }
    }
/*       if(deleteFunction == true){
       
                    if(deleteFunction ==true){
                        ImageView clickedView = (ImageView) me.getTarget();
                        for (NoteClass note: array) {
                            ImageView thisImage = note.getImageView();
                            if (thisImage == clickedView) {
                                images.remove(thisImage);
                                screen.getChildren().remove(thisImage);
                                array.remove(note);
                            }
                    ImageView clickedView = (ImageView) me.getTarget();
                    for (NoteClass note: array) {
                        ImageView thisImage = note.getImageView();
                        if (thisImage == clickedView) {
                            images.remove(thisImage);
                            screen.getChildren().remove(thisImage);
                            array.remove(note);
             screen.getChildren().add(newNote);
             newNote.setFitWidth(41);
             newNote.setFitHeight(57);
             newNote.setX(i.getX());
             newNote.setY(i.getY());
             
      }
         in.close();
         fileIn.close();
         
         
      }catch(IOException i) {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c) {
         System.out.println("Composition not found");
         c.printStackTrace();
         return;
         
    }
        
       

      
      
    }
    public void init(Stage stage){
        this.stage = stage;
    }   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hasQuarterNote=false;
        deleteFunction = false;
    }    
    
}

