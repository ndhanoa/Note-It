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
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

public class CompositionController implements Initializable {
   @FXML 
   private AnchorPane screen;

   @FXML
   private Label label;

   /*@FXML
   private Line lineF;

   @FXML
   private Line lineD;

   @FXML
   private Line lineB;

   @FXML
   private Line lineG;

   @FXML
   private Line lineE;

   */
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
   
   @FXML
   private Button playButton;

   private Desktop desktop = Desktop.getDesktop();

   private ArrayList<Note> notes = new ArrayList<Note>();

   private ArrayList<Rest> restsArray = new ArrayList<Rest>();
   private ArrayList<MeasureBar> measureBarArray = new ArrayList<MeasureBar>();
   private ArrayList<ArrayList<MusicalCharacter>> charactersonStaff = new ArrayList<ArrayList<MusicalCharacter>>();

   ImageView details;

   @FXML
   private FileChooser fc;

   private Stage stage;

   private Boolean hasQuarterNote;

   private Boolean hasMeasureBar;

   private Boolean deletePressed;

   private Boolean lineClickedNewStaff;

   private Boolean spaceClickedNewStaff;

   @FXML
   private Button delete;

   @FXML private Boolean hasHalfNote;
   
   @FXML private boolean hasEighthRest;
   
   

   @FXML private Button doubleBarLine;

   @FXML private ImageView newDoubleBarLine;

   @FXML private Boolean hasDoubleBarLine;
   
   private Synthesizer synth;
   
   private Receiver synthRcvr;


   @FXML
   private void handleClickDoubleBarLine(MouseEvent me){
       hasQuarterNote= false;
       deleteFunction = false;
       hasEighthNote = false;
       hasMeasureBar = false;
       noteImage = "doubleBarLine.png";
       hasQuarterRest = false;
       hasEighthRest = false;
       hasHalfNote=false;
       hasDoubleBarLine = true;

   }

   @FXML
   private void handleClickQuarterNote(MouseEvent me) {
       hasQuarterNote=true;
       deleteFunction = false;
       hasEighthNote = false;
       hasHalfNote = false;
       noteImage = "quarternote.png";
       hasQuarterRest = false;
       hasEighthRest = false;
       hasMeasureBar = false;
       hasDoubleBarLine = false;
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
       hasDoubleBarLine = false;
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
       hasDoubleBarLine = false;
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
       hasDoubleBarLine = false;
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
       hasDoubleBarLine = false;
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
      hasDoubleBarLine = false;
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
      hasDoubleBarLine = false;

   }


   @FXML private boolean spaceClicked;
   private boolean lineClicked;

   @FXML
   private Double lineFStartY;

   private Double lineDStartY;

   private Double lineBStartY;

   private Double lineGStartY;

   private Double lineEStartY;

   private Double lineFEndY;

   private Double lineDEndY;

   private Double lineBEndY;

   private Double lineGEndY;

   private Double lineEEndY;



   private double staffNumber;
   private Pane staff = null;


   @FXML
   private void handleClickStaff(MouseEvent me){
       spaceClicked = false;
      lineClicked = false;
       if(hasQuarterRest == true || hasEighthRest == true){
           handleClickStaffForRests(me);
       } else{
       Line clickedLine = null;
       
       double mouseX = me.getSceneX();
       double mouseY = me.getSceneY();
       if(me.getTarget().getClass() == Line.class){
           clickedLine = (Line) me.getTarget();
       } else if(me.getSource().getClass() == Pane.class) {
           staff = (Pane) me.getTarget();
       }
      /* for(double i = 0; i <=newStaffCount;i++){
            if(118+ ((i-1)*90)<mouseY&& mouseY>(118+(i*90))){
                staffNumber= i;
       }
      */ 

    

       lineFStartY= 49 + (120 * newStaffCount); 
       lineDStartY=lineFStartY + 18;
       lineBStartY=lineDStartY + 18;
       lineGStartY=lineBStartY + 18;
       lineEStartY=lineGStartY + 18;


       lineFEndY = 57 + (120*newStaffCount);
       lineDEndY = lineFEndY+18;
       lineBEndY = lineDEndY+18;
       lineGEndY = lineBEndY+18;
       lineEEndY = lineGEndY+18;

       if((mouseY>lineFStartY && mouseY<lineFEndY)||(mouseY>lineDStartY && mouseY<lineDEndY)||(mouseY>lineBStartY && mouseY<lineBEndY)||(mouseY>lineGStartY && mouseY<lineGEndY)||(mouseY>lineEStartY && mouseY<lineEEndY)){
           spaceClicked = true;
       }
       if(((mouseY>=l1.getStartY()-5 && mouseY<=l1.getStartY()+5)||(mouseY>=l2.getStartY()-5 && mouseY<=l2.getStartY()+5)||(mouseY>=l3.getStartY()-5 && mouseY<=l3.getStartY()+5)||(mouseY>=l4.getStartY()-5 &&mouseY<=l4.getStartY()+5))||(mouseY>=l5.getStartY()-5&&mouseY<=l5.getStartY()+5)){
           lineClicked = true;
       }


       if(hasQuarterNote == true||  hasHalfNote == true||hasEighthNote == true || hasMeasureBar == true|| hasDoubleBarLine ==true || hasEighthRest == true || hasQuarterRest == true) {
           if((spaceClicked == true) || (lineClicked == true)){
           ImageView newNote = new ImageView(getClass().getResource(noteImage).toString());
           newNote.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent me) {
                   if(deleteFunction ==true){
                        
                       ImageView clickedView = (ImageView) me.getTarget();
                       for (ArrayList<MusicalCharacter> musicArray: charactersonStaff) {
                           for(MusicalCharacter note: musicArray){
                           int detectedStaff = (int) Math.floor((note.getY() -25)/127);
                           ImageView thisImage = note.getImageView();
                           if (thisImage == clickedView) {
    //                           images.remove(thisImage);
                               staff.getChildren().remove(thisImage);
                               charactersonStaff.get(detectedStaff).remove(note);
                           }
                           }
                       }
                   }
               };
           });


               //if(spaceClicked == true||lineClicked == true){
                 //  newNote.setX(mouseX - 17);
                   //newNote.setY(mouseY - 44);
         // } 

               if(spaceClicked == true){
                   newNote.setX(mouseX-17);
                   if(mouseY>lineFStartY && mouseY< lineFEndY){
                       newNote.setY(((lineFStartY+lineFEndY)/2)-80);
                   }
                   else if(mouseY>lineDStartY && mouseY<lineDEndY){
                       newNote.setY(((lineDStartY+lineDEndY)/2)-80);
                   }
                   else if(mouseY> lineBStartY && mouseY<lineBEndY){
                       newNote.setY(((lineBStartY+lineBEndY)/2)-80);
                   }
                   else if(mouseY>lineGStartY && mouseY<lineGEndY){
                       newNote.setY(((lineGStartY+lineGEndY)/2)-80);
                   }
                   else if(mouseY>lineEStartY && mouseY<lineEEndY){
                       newNote.setY(((lineEStartY+lineEEndY)/2)-80);
                   }


               }

               if(lineClicked == true){
                   newNote.setX(mouseX-17);

                       if(mouseY>l1.getStartY()-5 && mouseY<l1.getStartY()+5){
                           newNote.setY(l1.getStartY()-80);
                       }
                       else if(mouseY>l2.getStartY()-5 && mouseY<l2.getStartY()+5){
                           newNote.setY(l2.getStartY()-80);
                       }
                        else if(mouseY>l3.getStartY()-5 && mouseY<l3.getStartY()+5){
                           newNote.setY(l3.getStartY()-80);
                       }
                       else if(mouseY>l4.getStartY()-5 && mouseY<l4.getStartY()+5){
                           newNote.setY(l4.getStartY()-80);
                       }
                        else if(mouseY>l5.getStartY()-5 && mouseY<l5.getStartY()+5){
                           newNote.setY(l5.getStartY()-80);
                       }
               }

           newNote.setFitWidth(41);
           newNote.setFitHeight(57);
           staff.getChildren().add(newNote);
           if(hasQuarterNote == true){
               QuarterCount q = new QuarterCount(newNote.getX(), newNote.getY());
               q.setImageView(newNote);
               int detectedStaff = (int) Math.floor((q.getY() -25)/127);
               charactersonStaff.get(detectedStaff).add(q);
           } else if(hasHalfNote == true){
                HalfCount h = new HalfCount(newNote.getX(), newNote.getY());
               h.setImageView(newNote);
               int detectedStaff = (int) Math.floor((h.getY() -25)/127);
               charactersonStaff.get(detectedStaff).add(h);
           } else if(hasEighthNote == true){
               newNote.setY(mouseY-48);
                EighthCount e = new EighthCount(newNote.getX(), newNote.getY());
               e.setImageView(newNote);
               int detectedStaff = (int) ((e.getY()- 25)/127);
                   (charactersonStaff.get(detectedStaff)).add(e);
           } else if(hasMeasureBar == true){
               newNote.setX(mouseX-175);
               //newNote.setY(mouseY-160); 
               newNote.setFitWidth(350);
               newNote.setFitHeight(320);
               MeasureBar m = new MeasureBar(newNote.getX(), newNote.getY());
               m.setImageView(newNote);
               int detectedStaff = (int) Math.floor((m.getY())/127);
               charactersonStaff.get(detectedStaff).add(m);
               newNote.setY((lineBStartY+lineBEndY)/2-170);
           }
           else if (hasDoubleBarLine == true){
               newNote.setX(mouseX-10);
               newNote.setY(mouseY - 40);
               newNote.setFitWidth(25);
               newNote.setFitHeight(80);
               DoubleBarLine d = new DoubleBarLine(newNote.getX(), newNote.getY());
               d.setImageView(newNote);
               int detectedStaff = (int) Math.floor((d.getY() -25)/127);
               charactersonStaff.get(detectedStaff).add(d);
           }else if(hasEighthRest == true||hasQuarterRest == true){
               handleClickStaffForRests(me);
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
           lineFStartY= 49 + (120 * newStaffCount); 
            lineDStartY=lineFStartY + 18;
            lineBStartY=lineDStartY + 18;
            lineGStartY=lineBStartY + 18;
            lineEStartY=lineGStartY + 18;


            lineFEndY = 57 + (120*newStaffCount);
            lineDEndY = lineFEndY+18;
            lineBEndY = lineDEndY+18;
            lineGEndY = lineBEndY+18;
            lineEEndY = lineGEndY+18;
            if(me.getSource().getClass()==Pane.class&& (mouseY>lineFStartY && mouseY<lineFEndY)||(mouseY>lineDStartY && mouseY<lineDEndY)||(mouseY>lineBStartY && mouseY<lineBEndY)||(mouseY>lineGStartY && mouseY<lineGEndY)||(mouseY>lineEStartY && mouseY<lineEEndY)){
           spaceClicked = true;
            }
            if(((mouseY>=l1.getStartY()-5 && mouseY<=l1.getStartY()+5)||(mouseY>=l2.getStartY()-5 && mouseY<=l2.getStartY()+5)||(mouseY>=l3.getStartY()-5 && mouseY<=l3.getStartY()+5)||(mouseY>=l4.getStartY()-5 &&mouseY<=l4.getStartY()+5))||(mouseY>=l5.getStartY()-5&&mouseY<=l5.getStartY()+5)){
           lineClicked = true;
            }
           if((hasQuarterRest == true || hasEighthRest == true) && (lineClicked == true || spaceClicked == true)){
           ImageView newRest = new ImageView(getClass().getResource(restImage).toString());
           newRest.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent me) {
                   if(deleteFunction == true){
                       ImageView clickedView = (ImageView) me.getTarget();
                       for (ArrayList<MusicalCharacter> musicList: charactersonStaff) {
                           for(MusicalCharacter note: musicList){
                           ImageView thisImage = note.getImageView();
                           int detectedStaff = (int) Math.floor((note.getY() -25)/127);
                           if (thisImage == clickedView) {
  //                             images2.remove(thisImage);
                               staff.getChildren().remove(thisImage);
                                charactersonStaff.get(detectedStaff).remove(note);
                           }
                       }
                       }
                   }
               };
           });

            if(lineClicked == true){
                   newRest.setX(mouseX-17);

                       if(mouseY>l1.getStartY()-5 && mouseY<l1.getStartY()+5){
                           newRest.setY(l3.getStartY()-60);
                       }
                       else if(mouseY>l2.getStartY()-5 && mouseY<l2.getStartY()+5){
                           newRest.setY(l3.getStartY()-60);
                       }
                        else if(mouseY>l3.getStartY()-5 && mouseY<l3.getStartY()+5){
                           newRest.setY(l3.getStartY()-60);
                       }
                       else if(mouseY>l4.getStartY()-5 && mouseY<l4.getStartY()+5){
                           newRest.setY(l3.getStartY()-60);
                       }
                        else if(mouseY>l5.getStartY()-5 && mouseY<l5.getStartY()+5){
                           newRest.setY(l3.getStartY()-60);
                       }
            }



               if(hasEighthRest == true){
                   newRest.setFitWidth(20);
                   newRest.setFitHeight(25);
                   staff.getChildren().add(newRest);
                    //newRest.setX(mouseX - 11);
                    //newRest.setY(mouseY-45);
                   EighthRestCount erc = new EighthRestCount(newRest.getX(), newRest.getY());
                   erc.setImageView(newRest);
                   int detectedStaff = (int) Math.floor((erc.getY() -25)/127);
                   charactersonStaff.get(detectedStaff).add(erc);
               } else if(hasQuarterRest == true){
                   newRest.setFitWidth(20);
                   newRest.setFitHeight(55);
                    staff.getChildren().add(newRest);
                   //newRest.setX(mouseX - 11);
                   //newRest.setY(mouseY-60);
                   QuarterRestCount qrc = new QuarterRestCount(newRest.getX(), newRest.getY());
                   qrc.setImageView(newRest);
                   int detectedStaff = (int) Math.floor((qrc.getY() -25)/127);
                   charactersonStaff.get(detectedStaff).add(qrc);
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
       //Create a new pane on top of the new staff:

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
       Pane p = new Pane();
       charactersonStaff.add(new ArrayList<MusicalCharacter>());
       p.setPrefSize(screen.getWidth(), 120);
       p.setLayoutX(-1);
       double yposition = (10 * newStaffCount); //152
       p.setLayoutY(yposition);
       screen.getChildren().add(p);
       handleClickPane(p);



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

   }
   @FXML 
   private void handleClickPane(Pane p){
       p.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent me) {
                   Pane clickedPane = (Pane) me.getTarget();
                   if(clickedPane == p && (hasQuarterNote == true|| hasHalfNote == true||hasEighthNote == true || hasQuarterRest == true || hasEighthRest == true)){
                       handleClickStaff(me);
                   }
               };
          });

   }
  /* @FXML 
   private void handleNewSpaceNotes(AnchorPane p){
       p.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent me) {
                   AnchorPane clickedScreen = (AnchorPane) me.getTarget();
               if(clickedScreen == screen && (hasQuarterNote == true|| hasHalfNote == true||hasEighthNote == true || hasQuarterRest == true || hasEighthRest == true)){
                       handleClickStaff(me);
               }
               };
          });

   }
*/             
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
       ArrayList<ArrayList<MusicalCharacter>> savedCharacters = new ArrayList<ArrayList<MusicalCharacter>>();
       fc = new FileChooser();
       fc.setTitle("Open text file");
       fc.setInitialDirectory(new File(System.getProperty("user.home")));
       File selectedFile = fc.showOpenDialog(stage);
     try {
        FileInputStream fileIn = new FileInputStream(selectedFile);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        savedCharacters = (ArrayList<ArrayList<MusicalCharacter>>) in.readObject();
        charactersonStaff = savedCharacters;
        for(ArrayList<MusicalCharacter> musicList : charactersonStaff){
            for(MusicalCharacter i: musicList){
            ImageView newNote = null;
            if(i.getClass() == QuarterCount.class || i.getClass() == HalfCount.class || i.getClass() == EighthCount.class || i.getClass() == MeasureBar.class || i.getClass() == DoubleBarLine.class){
                   if(i.getClass() == QuarterCount.class){
                       newNote = new ImageView(getClass().getResource("quarternote.png").toString());
                       newNote.setFitWidth(41);
                       newNote.setFitHeight(57);
                       screen.getChildren().add(newNote);
                       newNote.setX(i.getX());
                       newNote.setY(i.getY());
                       QuarterCount quc = new QuarterCount(newNote.getX(), newNote.getY());
                       quc.setImageView(newNote);
                   } else if(i.getClass() == HalfCount.class){
                       newNote = new ImageView(getClass().getResource("halfnote.png").toString());
                       newNote.setFitWidth(41);
                       newNote.setFitHeight(57);
                       newNote.setX(i.getX());
                       newNote.setY(i.getY());
                       screen.getChildren().add(newNote);
                       HalfCount hfc = new HalfCount(newNote.getX(), newNote.getY());
                       hfc.setImageView(newNote);
                   } else if(i.getClass() == EighthCount.class){
                       newNote = new ImageView(getClass().getResource("eighthnote.png").toString());
                       screen.getChildren().add(newNote);
                       newNote.setX(i.getX());
                       newNote.setY(i.getY());
                       newNote.setFitWidth(41);
                       newNote.setFitHeight(57);
                       EighthCount etc = new EighthCount(newNote.getX(), newNote.getY());
                       etc.setImageView(newNote);
                   } else if (i.getClass() == MeasureBar.class){
                       newNote = new ImageView(getClass().getResource("measure bar.png").toString());
                       screen.getChildren().add(newNote);
                       newNote.setX(i.getX());
                       newNote.setY(i.getY());
                       newNote.setFitWidth(350);
                       newNote.setFitHeight(320);
                       MeasureBar mb = new MeasureBar(newNote.getX(), newNote.getY());
                       mb.setImageView(newNote);
                   } 
                   else if(i.getClass() == DoubleBarLine.class){
                       newNote = new ImageView(getClass().getResource("doubleBarLine.png").toString());
                       screen.getChildren().add(newNote);
                       newNote.setX(i.getX());
                       newNote.setY(i.getY());
                       newNote.setFitWidth(100);
                       newNote.setFitHeight(160);
                       DoubleBarLine d = new DoubleBarLine(newNote.getX(), newNote.getY());
                       d.setImageView(newNote);
                   }

           }else if (i.getClass() == EighthRestCount.class || i.getClass() == QuarterRestCount.class){
                   if(i.getClass() == EighthRestCount.class){
                       newNote = new ImageView(getClass().getResource("eighthRest.png").toString());
                       newNote.setFitWidth(20);
                       newNote.setFitHeight(25);
                       screen.getChildren().add(newNote);
                       newNote.setX(i.getX());
                       newNote.setY(i.getY());
                       EighthRestCount erc = new EighthRestCount(newNote.getX(), newNote.getY());
                       erc.setImageView(newNote);

                   } else if (i.getClass() == QuarterRestCount.class){
                       newNote = new ImageView(getClass().getResource("quarter-rest-hi.png").toString());
                       newNote.setFitWidth(20);
                       newNote.setFitHeight(50);
                       screen.getChildren().add(newNote);
                       newNote.setX(i.getX());
                       newNote.setY(i.getY());
                       QuarterRestCount qrc = new QuarterRestCount(newNote.getX(), newNote.getY());
                       qrc.setImageView(newNote);
                       int detectedStaff = (int) Math.floor((qrc.getY() -25)/127);
                   }

            }
            newNote.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                       @Override
               public void handle(MouseEvent me) {
                   if(deleteFunction == true){
                       ImageView clickedView = (ImageView) me.getTarget();
                       for(ArrayList<MusicalCharacter> musicList : charactersonStaff){
                            for (MusicalCharacter musicNote : musicList) {
                           ImageView thisImage = musicNote.getImageView();
                           if (thisImage == clickedView) {
  //                             images2.remove(thisImage);
                               screen.getChildren().remove(thisImage);
                               restsArray.remove(musicNote);
                           }
                         }
                       }
                   }
               };
                   });
            if(i.getY() > 142){
            int staffNumber = ((int) ((i.getY() - 142)/120) + 1);
           if(staffNumber > extraStaffs){
               extraStaffs = staffNumber;
               }
            }
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
   public void play() throws InvalidMidiDataException, MidiUnavailableException{
	for(ArrayList<MusicalCharacter> musicLine: charactersonStaff){
		for(MusicalCharacter note : musicLine){
			double Cposition = ((lineDStartY+lineDEndY)/2)-46;
			double Dposition = l2.getStartY()-44;
			double Eposition = ((lineFStartY+lineFEndY)/2) -46;
			double Fposition = ((lineGStartY+lineGEndY)/2)-46;
			double Gposition = ((lineGStartY+lineGEndY)/2)-46;
			double Aposition =  ((lineBStartY+lineBEndY)/2)-46;
			double Bposition = l3.getStartY()-44;
			if(note.getClass() == QuarterCount.class || note.getClass() == HalfCount.class || note.getClass() == EighthCount.class){
				if(note.getY() == Cposition){
					ShortMessage myMsg = new ShortMessage();
					 myMsg.setMessage(ShortMessage.NOTE_ON, 4, 60, 93); 
        				Synthesizer synth = MidiSystem.getSynthesizer();
       					 synthRcvr.send(myMsg, -1);
				 } else if(note.getY() == Dposition){
					ShortMessage myMsg = new ShortMessage();
					 myMsg.setMessage(ShortMessage.NOTE_ON, 4, 62, 93); 
        					Synthesizer synth = MidiSystem.getSynthesizer();
       	 				synthRcvr.send(myMsg, -1);
				 } else if(note.getY() == Eposition){
					ShortMessage myMsg = new ShortMessage();
 					myMsg.setMessage(ShortMessage.NOTE_ON, 4, 64, 93); 
        					Synthesizer synth = MidiSystem.getSynthesizer();
       	 					synthRcvr.send(myMsg, -1);
				} else if(note.getY() == Fposition){
                                    
					ShortMessage myMsg = new ShortMessage();
 						myMsg.setMessage(ShortMessage.NOTE_ON, 4, 66, 93); 
       					 	Synthesizer synth = MidiSystem.getSynthesizer();
       	 					synthRcvr.send(myMsg, -1);
				 } else if(note.getY() == Gposition){
					ShortMessage myMsg = new ShortMessage();
 						myMsg.setMessage(ShortMessage.NOTE_ON, 4, 66, 93); 
       	 					Synthesizer synth = MidiSystem.getSynthesizer();
       	 					synthRcvr.send(myMsg, -1);
				 } else if(note.getY() == Aposition){
                                                ShortMessage myMsg = new ShortMessage();
 						myMsg.setMessage(ShortMessage.NOTE_ON, 4, 66, 93); 
       	 					Synthesizer synth = MidiSystem.getSynthesizer();
       	 					synthRcvr.send(myMsg, -1);
				 }else if(note.getY() == Bposition){
					ShortMessage myMsg = new ShortMessage();
 						myMsg.setMessage(ShortMessage.NOTE_ON, 4, 66, 93); 
       					Synthesizer synth = MidiSystem.getSynthesizer();
       	 					synthRcvr.send(myMsg, -1);
				 }
			}
		}
	}

}
   public void init(Stage stage) throws InvalidMidiDataException, MidiUnavailableException{
       this.stage = stage;
       
       synth = MidiSystem.getSynthesizer();
       synth.open();
       synthRcvr = MidiSystem.getReceiver();
   }   

   @Override
   public void initialize(URL url, ResourceBundle rb){
       // TODO
       staffNumber = 0;
       newStaffCount=1;
       hasQuarterNote=false;
       deleteFunction = false;
       spaceClicked = false;
       lineClicked = false;
       hasQuarterRest = false;
       hasEighthRest = false;
       lineStartX = -50;
       lineStartY = 0;
       lineEndX = 650;
       lineEndY = 0;
       newStaffCount = 0;
       l1 = new Line(lineStartX, 43.5 , lineEndX, 43.5 );
       lineCount = 0;
       firstLineY = 43.5;
       lineCount ++;
       l2 = new Line(lineStartX, firstLineY + (18 * lineCount), lineEndX, firstLineY + (18 * lineCount));
       lineCount ++;
       l3 = new Line(lineStartX, firstLineY + (18 * lineCount), lineEndX, firstLineY + (18 * lineCount));
       lineCount ++;
       l4 = new Line(lineStartX, firstLineY + (18 * lineCount), lineEndX, firstLineY + (18 * lineCount));
       lineCount ++;
       l5 = new Line(lineStartX, firstLineY + (18 * lineCount), lineEndX, firstLineY + (18 * lineCount));
       screen.getChildren().add(l1);
       screen.getChildren().add(l2);
       screen.getChildren().add(l3);
       screen.getChildren().add(l4);  
       screen.getChildren().add(l5);
       l1.setStrokeWidth(5);
       l2.setStrokeWidth(5);
       l3.setStrokeWidth(5);
       l4.setStrokeWidth(5);
       l5.setStrokeWidth(5);
       Pane p = new Pane();
       charactersonStaff.add(new ArrayList<MusicalCharacter>());
       p.setPrefSize(screen.getWidth(), 120);
       p.setLayoutX(-1);
       double yposition = 25; 
       p.setLayoutY(yposition);
       screen.getChildren().add(p);
       handleClickPane(p);
   }      

}
