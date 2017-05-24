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
import javafx.stage.Window;

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
    private ArrayList<MusicalCharacter> charactersonStaff = new ArrayList<MusicalCharacter>();
    
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
        hasMeasureBar = false;
    }
    
   
    
    @FXML private void handleClickEighthNote(MouseEvent me){
        hasHalfNote = false;
        hasEighthNote = true;
        hasQuarterNote = false;
        deleteFunction = false;
        noteImage = "eighthnote.png";
        hasQuarterRest = false;
        hasEighthRest = false;
        hasMeasureBar = false;
    }
    
    @FXML private void handleClickEighthRest(MouseEvent me){
        hasHalfNote = false;
        hasEighthNote = false;
        hasQuarterNote = false;
        deleteFunction = false;
        restImage = "eighthRest.png";
        hasQuarterRest = false;
        hasEighthRest = true;
        hasMeasureBar = false;
    }
    
    @FXML private void handleClickBar(MouseEvent me){
        hasHalfNote = false;
        hasEighthNote = false;
        hasQuarterNote = false;
        deleteFunction = false;
        hasMeasureBar = true;
        hasQuarterRest = false;
        hasEighthRest = false;
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
       spaceClicked = false;
       lineClicked = false;
       hasHalfNote = false;
       hasQuarterRest = false;
       hasEighthRest = false;
       hasMeasureBar = false;
    }
    
     @FXML
    private void handleQuarterRestButton(MouseEvent me){
        restImage = "quarter-rest-hi.png";
       deleteFunction = false;
       hasQuarterNote=false;
       hasHalfNote = false;
       hasEighthNote = false;
       spaceClicked = false;
       lineClicked = false;
       hasQuarterRest = true;
       hasEighthRest = false;
       hasMeasureBar = false;
       
    }
    
    
    @FXML private boolean spaceClicked;
    private boolean lineClicked;
    
    @FXML
    private double s1StartY;
    
    private double s2StartY;
   
    private double s3StartY;
    
    private double s4StartY;
    
    private double s5StartY;
    
    
   
    @FXML
    private void handleClickStaff(MouseEvent me){
        spaceClicked = false;
       lineClicked = false;
        if(hasQuarterRest == true || hasEighthRest == true){
            handleClickStaffForRests(me);
        } else{
        Line clickedLine = null;
        AnchorPane staff = null;
        double mouseX = me.getSceneX();
        double mouseY = me.getSceneY();
        if((me.getSource() == lineF) || (me.getSource() == lineD) || (me.getSource() == lineB) || (me.getSource() == lineG) || (me.getSource() == lineE) || (me.getSource() == l1) || (me.getSource() == l2) || (me.getSource() == l3) || (me.getSource() == l4) || (me.getSource() == l5)){
            clickedLine = (Line) me. getTarget();
        } else {
            staff = (AnchorPane) me.getTarget();
        }
        s1StartY = 46;
        s2StartY = s1StartY + 18;
        s3StartY = s2StartY + 18;
        s4StartY = s3StartY + 18;
        s5StartY = s4StartY + 18;
        
        
        
        if(staff == screen && ((mouseY>s1StartY && mouseY<s2StartY)||(mouseY>s2StartY && mouseY<s3StartY)||(mouseY>s3StartY && mouseY<s4StartY)||(mouseY>s4StartY &&mouseY< s5StartY)||(mouseY>s1StartY + 106 && mouseY<s2StartY + 106)||(mouseY>s2StartY + 106 && mouseY<s3StartY + 106)||(mouseY>s3StartY + 106 && mouseY<s4StartY + 106)||(mouseY>s4StartY + 106 && mouseY< s5StartY + 106))){
            spaceClicked = true;
        }
        if((lineF == clickedLine) ||(lineD == clickedLine )|| (lineB == clickedLine )|| (lineG == clickedLine) || (lineE== clickedLine) || (l1 == clickedLine) || (l2 == clickedLine) || (l3 == clickedLine) || (l4 == clickedLine) || (l5 == clickedLine)){
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
                        for (MusicalCharacter m: charactersonStaff) {
                            ImageView thisImage = m.getImageView();
                            if (thisImage == clickedView) {
     //                           images.remove(thisImage);
                                screen.getChildren().remove(thisImage);
                                charactersonStaff.remove(m);
                            }
                        }
                    }
                };
            });
            
           
                if(spaceClicked == true||lineClicked == true){
                    newNote.setX(mouseX - 17);
                    newNote.setY(mouseY - 44);
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
                newNote.setY(mouseY-48);
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
    
   
    @FXML
    private ImageView newRest;
    @FXML
    private void handleClickStaffForRests(MouseEvent me){
            Line clickedLine = null;
            double mouseX = me.getSceneX();
            double mouseY = me.getSceneY();
            
            if((hasQuarterRest == true || hasEighthRest == true) && (me.getSource()==lineB || me.getSource() == l3)){
            ImageView newRest = new ImageView(getClass().getResource(restImage).toString());
            newRest.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    if(deleteFunction == true){
                        ImageView clickedView = (ImageView) me.getTarget();
                        for (Rest rest: restsArray) {
                            ImageView thisImage = rest.getImageView();
                            if (thisImage == clickedView) {
   //                             images2.remove(thisImage);
                                screen.getChildren().remove(thisImage);
                                restsArray.remove(rest);
                            }
                        }
                    }
                };
            });
           

               
                
                
                if(hasEighthRest == true){
                    newRest.setFitWidth(20);
                    newRest.setFitHeight(25);
                    screen.getChildren().add(newRest);
                     newRest.setX(mouseX - 17);
                     newRest.setY(70);
                    EighthRestCount erc = new EighthRestCount(newRest.getX(), newRest.getY());
                    erc.setImageView(newRest);
                    charactersonStaff.add(erc);
                } else if(hasQuarterRest == true){
                    newRest.setFitWidth(20);
                    newRest.setFitHeight(55);
                     screen.getChildren().add(newRest);
                    newRest.setX(mouseX - 17);
                    newRest.setY(50);
                    QuarterRestCount qrc = new QuarterRestCount(newRest.getX(), newRest.getY());
                    qrc.setImageView(newRest);
                    charactersonStaff.add(qrc);
                }
            
            
        }
    }
    @FXML
    private double screenHeight;
    private Window window;
    private double lineStartX;
    private double lineEndX;
    private double lineStartY;
    private double lineEndY;
    private Line l1;
    private Line l2;
    private Line l3;
    private Line l4;
    private Line l5;
    private double newStaffCount;
    private double lineCount;
    private double firstLineY;
    
    
    
    @FXML
    private void handleAddNewStaff(MouseEvent me){
        newStaffCount++;
        lineCount = 0;
        double height = ((Stage)screen.getScene().getWindow()).getHeight();


        ((Stage)screen.getScene().getWindow()).setHeight(height + 120);
        l1 = new Line(lineStartX + 50, 43.5 + (newStaffCount * 120), lineEndX + 50, 43.5 + (newStaffCount * 120));
        firstLineY = 43.5 + (newStaffCount * 120);
        lineCount ++;
        l2 = new Line(lineStartX + 50, firstLineY + (18 * lineCount), lineEndX + 50, firstLineY + (18 * lineCount));
        lineCount ++;
        l3 = new Line(lineStartX + 50, firstLineY + (18 * lineCount), lineEndX + 50, firstLineY + (18 * lineCount));
        lineCount ++;
        l4 = new Line(lineStartX + 50, firstLineY + (18 * lineCount), lineEndX + 50, firstLineY + (18 * lineCount));
        lineCount ++;
        l5 = new Line(lineStartX + 50, firstLineY + (18 * lineCount), lineEndX + 50, firstLineY + (18 * lineCount));
        l1.setStrokeWidth(5);
        l2.setStrokeWidth(5);
        l3.setStrokeWidth(5);
        l4.setStrokeWidth(5);
        l5.setStrokeWidth(5);
        screen.getChildren().add(l1);
        screen.getChildren().add(l2);
        screen.getChildren().add(l3);
        screen.getChildren().add(l4);  
        screen.getChildren().add(l5);
        handleNewNotesOnNewStaff(l1);
        handleNewNotesOnNewStaff(l2);
        handleNewNotesOnNewStaff(l3); 
        handleNewNotesOnNewStaff(l4);
        handleNewNotesOnNewStaff(l5);
        
    }
    
    @FXML 
    private void handleNewNotesOnNewStaff(Line l){
        l.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    Line clickedLine = (Line) me.getTarget();
                    if(clickedLine == l && (hasQuarterNote == true|| hasHalfNote == true||hasEighthNote == true || hasQuarterRest == true || hasEighthRest == true)){
                        handleClickStaff(me);
                    }
                };
            });
        l.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                AnchorPane clickedScreen = (AnchorPane) me.getTarget();
                if(clickedScreen == screen && (hasQuarterNote == true|| hasHalfNote == true||hasEighthNote == true || hasQuarterRest == true || hasEighthRest == true)){
                        handleClickStaff(me);
                }
            };
        });
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
        int extraStaffs = 0;
        ArrayList<MusicalCharacter> charactersonStaff2 = new ArrayList<MusicalCharacter>();
        fc = new FileChooser();
        fc.setTitle("Open text file");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fc.showOpenDialog(stage);
      try {
         FileInputStream fileIn = new FileInputStream(selectedFile);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         charactersonStaff = (ArrayList<MusicalCharacter>) in.readObject();
          for(MusicalCharacter i: charactersonStaff){
             ImageView newNote = null;
             if(i.getClass() == QuarterCount.class || i.getClass() == HalfCount.class || i.getClass() == EighthCount.class || i.getClass() == MeasureBar.class){
                    if(i.getClass() == QuarterCount.class){
                        newNote = new ImageView(getClass().getResource("quarternote.png").toString());
                        newNote.setFitWidth(41);
                        newNote.setFitHeight(57);
                        QuarterCount quc = new QuarterCount(newNote.getX(), newNote.getY());
                        charactersonStaff2.add(quc);
                    } else if(i.getClass() == HalfCount.class){
                        newNote = new ImageView(getClass().getResource("halfnote.png").toString());
                        newNote.setFitWidth(41);
                        newNote.setFitHeight(57);
                        HalfCount hfc = new HalfCount(newNote.getX(), newNote.getY());
                        charactersonStaff2.add(hfc);
                    } else if(i.getClass() == EighthCount.class){
                        newNote = new ImageView(getClass().getResource("eighthnote.png").toString());
                        newNote.setFitWidth(41);
                        newNote.setFitHeight(57);
                        EighthCount etc = new EighthCount(newNote.getX(), newNote.getY());
                        charactersonStaff2.add(etc);
                    } else if (i.getClass() == MeasureBar.class){
                    newNote = new ImageView(getClass().getResource("measure bar.png").toString());
                    newNote.setFitWidth(350);
                    newNote.setFitHeight(320);
                    MeasureBar mb = new MeasureBar(newNote.getX(), newNote.getY());
                        charactersonStaff2.add(mb);
                    } 
                    screen.getChildren().add(newNote);
                newNote.setX(i.getX());
                newNote.setY(i.getY());
            }else if (i.getClass() == EighthRestCount.class || i.getClass() == QuarterRestCount.class){
                    if(i.getClass() == EighthRestCount.class){
                        newNote = new ImageView(getClass().getResource("eighthRest.png").toString());
                        newNote.setFitWidth(20);
                        newNote.setFitHeight(25);
                        screen.getChildren().add(newNote);
                        newNote.setX(i.getX());
                        newNote.setY(i.getY() - 5);
                        EighthRestCount erc = new EighthRestCount(newNote.getX(), newNote.getY());
                        charactersonStaff2.add(erc);
                        
                    } else if (i.getClass() == QuarterRestCount.class){
                        newNote = new ImageView(getClass().getResource("quarter-rest-hi.png").toString());
                        newNote.setFitWidth(20);
                        newNote.setFitHeight(50);
                        screen.getChildren().add(newNote);
                        newNote.setX(i.getX());
                        newNote.setY(i.getY() - 30);
                        QuarterRestCount qrc = new QuarterRestCount(newNote.getX(), newNote.getY());
                        charactersonStaff2.add(qrc);
                    }
             }
             if(i.getY() > 142){
             int staffNumber = ((int) ((i.getY() - 142)/120) + 1);
            if(staffNumber > extraStaffs){
                extraStaffs = staffNumber;
                }
             }
          
            for(int v = 0; v < extraStaffs; v++){
                    newStaffCount++;
                    lineCount = 0;
                    double height = ((Stage)screen.getScene().getWindow()).getHeight();
                    ((Stage)screen.getScene().getWindow()).setHeight(height + 120);
                    l1 = new Line(lineStartX + 50, 43.5 + (newStaffCount * 120), lineEndX + 50, 43.5 + (newStaffCount * 120));
                    firstLineY = 43.5 + (newStaffCount * 120);
                    lineCount ++;
                    l2 = new Line(lineStartX + 50, firstLineY + (18 * lineCount), lineEndX + 50, firstLineY + (18 * lineCount));
                    lineCount ++;
                    l3 = new Line(lineStartX + 50, firstLineY + (18 * lineCount), lineEndX + 50, firstLineY + (18 * lineCount));
                    lineCount ++;
                    l4 = new Line(lineStartX + 50, firstLineY + (18 * lineCount), lineEndX + 50, firstLineY + (18 * lineCount));
                    lineCount ++;
                    l5 = new Line(lineStartX + 50, firstLineY + (18 * lineCount), lineEndX + 50, firstLineY + (18 * lineCount));
                    l1.setStrokeWidth(5);
                    l2.setStrokeWidth(5);
                    l3.setStrokeWidth(5);
                    l4.setStrokeWidth(5);
                    l5.setStrokeWidth(5);
                    screen.getChildren().add(l1);
                    screen.getChildren().add(l2);
                    screen.getChildren().add(l3);
                    screen.getChildren().add(l4);  
                    screen.getChildren().add(l5);
                    handleNewNotesOnNewStaff(l1);
                    handleNewNotesOnNewStaff(l2);
                    handleNewNotesOnNewStaff(l3); 
                    handleNewNotesOnNewStaff(l4);
                    handleNewNotesOnNewStaff(l5);
              }
          }

         in.close();
         fileIn.close();
         charactersonStaff = charactersonStaff2;
         
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
        lineStartX = lineF.getStartX();
        lineStartY = lineF.getStartY();
        lineEndX = lineF.getEndX();
        lineEndY = lineF.getEndY();
        newStaffCount = 0;
    }    
    
}

