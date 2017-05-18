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
    private Button measureBarButton;
    
    @FXML
    private String noteImage;
    private String restImage;
    
    @FXML
    private Button save;
    
    @FXML
    private Button load;
    
    @FXML
    private ImageView newQuarterNote;
    
    @FXML 
    private ImageView newHalfNote;
    
    @FXML
    private Button halfNote;
    
    @FXML
    private Button eighthNote;
    
    @FXML
    private Button measureButton;
    
    @FXML private ImageView newEighthNote;
    
    @FXML private Boolean hasEighthNote;
    
    @FXML 
    private ImageView quarterNoteForStaff;
    private boolean hasQuarterRest;
    
    
    @FXML
    private Button quarterRestButton;
    
    private Desktop desktop = Desktop.getDesktop();
    
    private ArrayList<Note> notes = new ArrayList<Note>();
    
    private ArrayList<Rest> restsArray = new ArrayList<Rest>();
    private ArrayList<MeasureBar> measureBarArray = new ArrayList<MeasureBar>();
    private ArrayList<Object> charactersonStaff = new ArrayList<Object>();
    
    ImageView details;
    
    @FXML
    private FileChooser fc;
    
    private Stage stage;
    
    private Boolean hasQuarterNote;
    
    private Boolean hasMeasureBar;
    
    private Boolean deletePressed;
    
    @FXML
    private Button delete;
    
    @FXML private Boolean hasHalfNote;
    @FXML private boolean hasEighthRest;
    
    @FXML
    private void handleClickQuarterNote(MouseEvent me) {
        hasQuarterNote=true;
        deleteFunction = false;
        hasEighthNote = false;
        hasHalfNote = false;
        noteImage = "quarternote.png";
        hasQuarterRest = false;
        hasEighthRest = false;
    }
    
    @FXML
    private void handleClickHalfNote(MouseEvent me){
        hasHalfNote = true;
        hasQuarterNote = false;
        deleteFunction = false;
        hasEighthNote = false;
        noteImage = "halfnote.png";
        hasQuarterRest = false;
        hasEighthRest = false;
    }
    
    @FXML private void handleClickEighthNote(MouseEvent me){
        hasHalfNote = false;
        hasEighthNote = true;
        hasQuarterNote = false;
        deleteFunction = false;
        noteImage = "eighthnote.png";
        hasQuarterRest = false;
        hasEighthRest = false;
    }
    
    @FXML private void handleClickEighthRest(MouseEvent me){
        hasHalfNote = false;
        hasEighthNote = false;
        hasQuarterNote = false;
        deleteFunction = false;
        restImage = "eighthRest.png";
        hasQuarterRest = false;
        hasEighthRest = true;
    }
    
    @FXML private void handleClickBar(MouseEvent me){
        hasHalfNote = false;
        hasEighthNote = false;
        hasQuarterNote = false;
        deleteFunction = false;
        hasMeasureBar = true;
        noteImage = "measure bar.png";
    }
    
    
    @FXML

    private double imageX;
    private double imageY;
    private boolean deleteFunction;
   
    private ArrayList<ImageView> images = new ArrayList<ImageView>();
    private ArrayList<ImageView> images2 = new ArrayList<ImageView>();
    
    @FXML
    private void handleDeleteNote(ActionEvent me){
     
       deleteFunction = true;
       hasEighthNote = false;
       hasQuarterNote=false;
       hasHalfNote = false;
       hasEighthNote = false;
       spaceClicked = false;
       lineClicked = false;
       hasHalfNote = false;
       hasQuarterRest = false;
       hasEighthRest = false;
    }
    
     @FXML
    private void handleRestButton(MouseEvent me){
     
       deleteFunction = false;
       hasQuarterNote=false;
       spaceClicked = false;
       lineClicked = false;
       hasQuarterRest = true;
       restImage = "quarter-rest-hi.png";
       hasEighthRest = false;
    }
    
    
    @FXML private boolean spaceClicked;
    private boolean lineClicked;
   
    @FXML
    private void handleClickStaff(MouseEvent me){
        if(hasQuarterRest == true || hasEighthRest == true){
            handleClickStaffForRests(me);
        } else{
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
        if(hasQuarterNote == true||  hasHalfNote == true||hasEighthNote == true || hasMeasureBar == true) {
            if((spaceClicked == true) || (lineClicked == true)){
            ImageView newNote = new ImageView(getClass().getResource(noteImage).toString());
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
            
            if(mouseY < 120 && mouseY > 44){
                if(spaceClicked == true){
                    newNote.setX(mouseX - 17);
                    newNote.setY(mouseY - 43);
            } else if(lineClicked == true){
                newNote.setX(mouseX-17);
                newNote.setY(mouseY-45);  
            }
            newNote.setFitWidth(41);
            newNote.setFitHeight(57);
            screen.getChildren().add(newNote);
            if(hasQuarterNote == true){
                QuarterCount q = new QuarterCount(newNote.getX(), newNote.getY());
                q.setImageView(newNote);
                charactersonStaff.add(q);
            } else if(hasHalfNote == true){
                 HalfCount h = new HalfCount(newNote.getX(), newNote.getY());
                h.setImageView(newNote);
                charactersonStaff.add(h);
            } else if(hasEighthNote == true){
                 EighthCount e = new EighthCount(newNote.getX(), newNote.getY());
                e.setImageView(newNote);
                charactersonStaff.add(e);
            } else if(hasMeasureBar == true){
                newNote.setX(mouseX-175);
                newNote.setY(-80); 
                newNote.setFitWidth(350);
                newNote.setFitHeight(320);
                MeasureBar m = new MeasureBar(newNote.getX(), newNote.getY());
                m.setImageView(newNote);
                charactersonStaff.add(m);
            }
            
        }
            
            
        }
    }
        }
    }
    
   
    @FXML
    private ImageView newRest;
    @FXML
    private void handleClickStaffForRests(MouseEvent me){
            Line clickedLine = null;
            double mouseX = me.getSceneX();
            double mouseY = me.getSceneY();
            
            if((hasQuarterRest == true || hasEighthRest == true) && me.getSource()==lineB){
            ImageView newRest = new ImageView(getClass().getResource(restImage).toString());
            newRest.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    if(deleteFunction ==true){
                        ImageView clickedView = (ImageView) me.getTarget();
                        for (Rest rest: restsArray) {
                            ImageView thisImage = rest.getImageView();
                            if (thisImage == clickedView) {
                                images2.remove(thisImage);
                                screen.getChildren().remove(thisImage);
                                restsArray.remove(rest);
                            }
                        }
                    }
                };
            });
           

                newRest.setX(mouseX - 17);
                newRest.setY(mouseY - 30);
                
                newRest.setFitWidth(20);
                newRest.setFitHeight(55);
                screen.getChildren().add(newRest);
                if(hasEighthRest == true){
                    newRest.setY(mouseY - 15);
                    newRest.setFitHeight(29);
                    EighthRestCount erc = new EighthRestCount(newRest.getX(), newRest.getY());
                    erc.setImageView(newRest);
                    charactersonStaff.add(erc);
                } else if(hasQuarterRest == true){
                    QuarterRestCount qrc = new QuarterRestCount(newRest.getX(), newRest.getY());
                    qrc.setImageView(newRest);
                    charactersonStaff.add(qrc);
                }
            
            
        }
    
        
    
                    
    }
    
    private void handleClickMeasureBar(MouseEvent me){
            Line clickedLine = null;
            double mouseX = me.getSceneX();
            double mouseY = me.getSceneY();
            if((hasMeasureBar == true) && me.getSource()==lineB){
            ImageView newBar = new ImageView(getClass().getResource("measure bar.png").toString());
            newRest.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    if(deleteFunction ==true){
                        ImageView clickedView = (ImageView) me.getTarget();
                        for (MeasureBar bar: measureBarArray) {
                            ImageView thisImage = bar.getImageView();
                            if (thisImage == clickedView) {
                                images2.remove(thisImage);
                                screen.getChildren().remove(thisImage);
                                measureBarArray.remove(bar);
                            }
                        }
                    }
                };
                
            });
                newBar.setX(mouseX - 17);
                newBar.setY(mouseY - 30);
                newBar.setFitWidth(50);
                newBar.setFitHeight(120);
                screen.getChildren().add(newBar);
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
                out.writeObject(charactersonStaff);
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
         charactersonStaff = (ArrayList<Object>) in.readObject();
         for(Object i: charactersonStaff){
             ImageView newNote = null;
             if(i.getClass() == QuarterCount.class){
                newNote = new ImageView(getClass().getResource("quarternote.png").toString());
             } else if(i.getClass() == HalfCount.class){
                 newNote = new ImageView(getClass().getResource("halfnote.png").toString());
             } else if(i.getClass() == EighthCount.class){
                 newNote = new ImageView(getClass().getResource("eighthnote.png").toString());
             } if(i.getClass() == EighthRestCount.class){
                 newNote = new ImageView(getClass().getResource("eighthrest.png").toString());
             } if(i.getClass() == QuarterRestCount.class){
                 newNote = new ImageView(getClass().getResource("quarterrest.png").toString());
             }
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
        spaceClicked = false;
        lineClicked = false;
        hasQuarterRest = false;
        hasEighthRest = false;
    }    
    
}

