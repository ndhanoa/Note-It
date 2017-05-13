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
    private Button halfnote;
    
    @FXML
    private Button eighthnote;
    
    @FXML
    private Button save;
    
    @FXML
    private Button load;
    
    @FXML
    private ImageView newNote;
    
    @FXML 
    private ImageView quarterNoteForStaff;
    
    private Desktop desktop = Desktop.getDesktop();
    
    private ArrayList<Note> notes = new ArrayList<Note>();
    
    ImageView details;
    
    @FXML
    private FileChooser fc;
    
    private Stage stage;
    
    private Boolean hasQuarterNote;
    
    private Boolean hasHalfNote;
    
    private Boolean hasEighthNote;
    
    @FXML
    private void handleClickQuarterNote(MouseEvent me) {
        hasQuarterNote=true;
        deleteFunction = false;
    }
    
    @FXML
    private void handleClickHalfNote(MouseEvent me){
        hasHalfNote = true;
        deleteFunction = false;
    }
    
    @FXML
    private void handleClickEighthNote(MouseEvent me){
        hasEighthNote = true;
        deleteFunction = false;
    }
    
    @FXML

    private double imageX;
    private double imageY;
    private boolean deleteFunction;
   
    private ArrayList<ImageView> images = new ArrayList<ImageView>();
    
    @FXML
    private void handleDeleteNote(ActionEvent me){
     
       deleteFunction = true;
       hasQuarterNote=false;
       hasHalfNote = false;
       hasEighthNote = false;
       spaceClicked = false;
       lineClicked = false;
    }
    
    @FXML private boolean spaceClicked;
    private boolean lineClicked;
    
    @FXML
    private void handleClickStaff(MouseEvent me){
        Line clickedLine = null;
        AnchorPane staff = null;
        double mouseX = me.getSceneX();
        double mouseY = me.getSceneY();
        if((me.getSource() == lineF) || (me.getSource() == lineD) || (me.getSource() == lineB) || (me.getSource() == lineG) || (me.getSource() == lineE)){
            clickedLine = (Line) me. getTarget();
        } else {
            staff = (AnchorPane) me.getTarget();
        }
        
        if(staff == screen && ((mouseY>52 && mouseY<60)||(mouseY>70 && mouseY<78)||(mouseY>88&& mouseY<96)||(mouseY>107&&mouseY<115))){
            spaceClicked = true;
        }
        if((lineF == clickedLine) ||(lineD == clickedLine )|| (lineB == clickedLine )|| (lineG == clickedLine) || (lineE== clickedLine)){
            lineClicked = true;
        }
        if(hasQuarterNote == true) {
            if((spaceClicked == true) || (lineClicked == true)){
            ImageView newNote = new ImageView(getClass().getResource("quarternote.png").toString());
            newNote.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    if(deleteFunction ==true){
                        ImageView clickedView = (ImageView) me.getTarget();
                        for (Note note: notes) {
                            ImageView thisImage = note.getImageView();
                            if (thisImage == clickedView) {
                                images.remove(thisImage);
                                screen.getChildren().remove(thisImage);
                                notes.remove(note);
                            }
                        }
                    }
                };
            });
         
              
            Note y = new Note();
            if(spaceClicked == true){
                newNote.setX(mouseX - 17);
                newNote.setY(mouseY - 43);
                y.setX(mouseX-17);
                y.setY(mouseY-43);
            } else if(lineClicked == true){
                newNote.setX(mouseX-17);
                newNote.setY(mouseY-45);  
                y.setX(mouseX);
                y.setY(mouseY-43);
            }
            newNote.setFitWidth(41);
            newNote.setFitHeight(57);
            screen.getChildren().add(newNote);
            y.setImageView(newNote);
            notes.add(y);
        }
    }
    }
    
  
    
    @FXML
    private void save(MouseEvent change) throws FileNotFoundException, IOException{
         fc = new FileChooser();
         fc.setTitle("Open text file");
         fc.setInitialDirectory(new File(System.getProperty("user.home")));
         File selectedFile = fc.showSaveDialog(stage);
        if(selectedFile != null){
            try {
                FileOutputStream fileOut = new FileOutputStream(selectedFile);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(notes);
                out.close();
            } catch (IOException e){
            
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
      try {
         FileInputStream fileIn = new FileInputStream(selectedFile);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         notes = (ArrayList<Note>) in.readObject();
        ArrayList<NoteClass> t = new ArrayList<NoteClass>();
         for(Note i: notes){
             ImageView newNote = new ImageView(getClass().getResource("quarternote.png").toString());
             screen.getChildren().add(newNote);
             newNote.setFitWidth(41);
             newNote.setFitHeight(57);
             newNote.setX(i.getX());
             newNote.setY(i.getY());
             t.add(i);
             
     }
         notes = t;
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
        spaceClicked = false;
        lineClicked = false;
    }    
    
}

