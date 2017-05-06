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
    
    @FXML
    private void handleClickQuarterNote(MouseEvent me) {
        hasQuarterNote=true;
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
    private void handleClickStaffLine(MouseEvent me){
      
       if(deleteFunction == true){
           double mouseX = me.getSceneX() -17;
           double mouseY = me.getSceneY() - 45;
           for(ImageView i: images){
               imageX = i.getX();
               imageY = i.getY();
               if(abs(mouseX - imageX) < 10 && abs(mouseY - imageY) < 10){
                   i.setImage(null);
                   screen.getChildren().remove(i);
                   
               }
           }
           
       }else{
       double mouseX = me.getSceneX();
       double mouseY = me.getSceneY();
       if(hasQuarterNote==true){
             ImageView newNote = new ImageView(getClass().getResource("quarternote.png").toString());
             screen.getChildren().add(newNote);
             newNote.setFitWidth(41);
             newNote.setFitHeight(57);
             newNote.setX(mouseX-17);
             newNote.setY(mouseY-45);
            NoteClass y = new NoteClass();
            y.setX(mouseX);
            y.setY(mouseY-43);
            array.add(y);  
       }
       }
       
    }
    
    
    @FXML
    private void handleDeleteNote(MouseEvent me){
     
       deleteFunction = true;
    }
    
    
    @FXML
    private void handleClickStaffSpace(MouseEvent me){
        double mouseX = me.getX();
        double mouseY = me.getY();
        if(hasQuarterNote == true && ((mouseY>52 && mouseY<60)||(mouseY>70 && mouseY<78)||(mouseY>88&& mouseY<96)||(mouseY>107&&mouseY<115))){
            ImageView newNote = new ImageView(getClass().getResource("quarternote.png").toString());
            newNote.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    ImageView clickedView = (ImageView) me.getTarget();
                    for (NoteClass note: array) {
                        ImageView thisImage = note.getImageView();
                        if (thisImage == clickedView) {
                            images.remove(thisImage);
                            screen.getChildren().remove(thisImage);
                            array.remove(note);
                        }
                    }
                };
            });
            screen.getChildren().add(newNote);
            newNote.setFitWidth(41);
            newNote.setFitHeight(57);
            newNote.setX(mouseX);
            newNote.setY(mouseY-43);
            NoteClass y = new NoteClass();
            y.setX(mouseX);
            y.setY(mouseY-43);
            y.setImageView(newNote);
            array.add(y);
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
                out.writeObject(array);
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
         array = (ArrayList<NoteClass>) in.readObject();
         for(NoteClass i: array){
             ImageView newNote = new ImageView(getClass().getResource("quarternote.png").toString());
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

