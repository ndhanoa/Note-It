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
import java.util.Timer;
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


   @FXML 
   private ImageView quarterNoteForStaff;


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

   noteTypeClicked type;
   
   private Boolean lineClickedNewStaff;

   private Boolean spaceClickedNewStaff;

   @FXML
   private Button delete;
   
   

   @FXML private Button doubleBarLine;

   @FXML private ImageView newDoubleBarLine;
   
   private Synthesizer synth;
   
   private Receiver synthRcvr;


   @FXML
   private void handleClickDoubleBarLine(MouseEvent me){
       noteImage = "doubleBarLine.png";
       type = noteTypeClicked.HASDOUBLEBARLINE;
   }

   @FXML
   private void handleClickQuarterNote(MouseEvent me) {
       type = noteTypeClicked.HASQUARTERNOTE;
       noteImage = "quarternote.png";
   }

   @FXML
   private void handleClickHalfNote(MouseEvent me){
       noteImage = "halfnote.png";
       type = noteTypeClicked.HASHALFNOTE;
   }



   @FXML private void handleClickEighthNote(MouseEvent me){
       noteImage = "eighthnote.png";
       type= noteTypeClicked.HASEIGHTHNOTE;
   }

   @FXML private void handleClickEighthRest(MouseEvent me){
       restImage = "eighthRest.png";
       type = noteTypeClicked.HASEIGHTHREST;
   }

   @FXML private void handleClickBar(MouseEvent me){
       noteImage = "measure bar.png";
       type = noteTypeClicked.HASMEASUREBAR;
   }


   @FXML

   private double imageX;
   private double imageY;

   private ArrayList<ImageView> images = new ArrayList<ImageView>();
   private ArrayList<ImageView> images2 = new ArrayList<ImageView>();

   @FXML
   private void handleDeleteNote(ActionEvent me){

      spaceClicked = false;
      lineClicked = false;
      type = noteTypeClicked.HASDELETE;
   }

    @FXML
   private void handleQuarterRestButton(MouseEvent me){
      restImage = "quarter-rest-hi.png";
      spaceClicked = false;
      lineClicked = false;
      type = noteTypeClicked.HASQUARTERREST;

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
       if(type == noteTypeClicked.HASQUARTERREST || type == noteTypeClicked.HASEIGHTHREST){
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


       if(type == noteTypeClicked.HASQUARTERNOTE || type == noteTypeClicked.HASHALFNOTE || type == noteTypeClicked.HASEIGHTHNOTE || type == noteTypeClicked.HASMEASUREBAR || type == noteTypeClicked.HASDOUBLEBARLINE || type == noteTypeClicked.HASEIGHTHREST || type == noteTypeClicked.HASQUARTERREST) {
           if((spaceClicked == true) || (lineClicked == true)){
           ImageView newNote = new ImageView(getClass().getResource(noteImage).toString());
           newNote.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent me) {
                   if(type == noteTypeClicked.HASDELETE){
                        
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
           if(type == noteTypeClicked.HASQUARTERNOTE){
               QuarterCount q = new QuarterCount(newNote.getX(), newNote.getY());
               q.setImageView(newNote);
               int detectedStaff = (int) Math.floor((q.getY())/127);
               charactersonStaff.get(detectedStaff).add(q);
           } else if(type == noteTypeClicked.HASHALFNOTE){
                HalfCount h = new HalfCount(newNote.getX(), newNote.getY());
               h.setImageView(newNote);
               int detectedStaff = (int) Math.floor((h.getY())/127);
               charactersonStaff.get(detectedStaff).add(h);
           } else if(type == noteTypeClicked.HASEIGHTHNOTE){
               newNote.setY(mouseY-48);
                EighthCount e = new EighthCount(newNote.getX(), newNote.getY());
               e.setImageView(newNote);
               int detectedStaff = (int) ((e.getY())/127);
                   (charactersonStaff.get(detectedStaff)).add(e);
           } else if(type == noteTypeClicked.HASMEASUREBAR){
               newNote.setX(mouseX-175);
               newNote.setY(l3.getStartY() - 175); 
               newNote.setFitWidth(350);
               newNote.setFitHeight(285);
               MeasureBar m = new MeasureBar(newNote.getX(), newNote.getY());
               m.setImageView(newNote);
               int detectedStaff = (int) Math.floor((m.getY())/127);
               charactersonStaff.get(detectedStaff).add(m);
               newNote.setY((lineBStartY+lineBEndY)/2-170);
           }
           else if (type == noteTypeClicked.HASDOUBLEBARLINE){
               newNote.setX(mouseX-10);
               newNote.setY(l3.getStartY() - 72);
               newNote.setFitWidth(25);
               newNote.setFitHeight(73);
               DoubleBarLine d = new DoubleBarLine(newNote.getX(), newNote.getY());
               d.setImageView(newNote);
               int detectedStaff = (int) Math.floor((d.getY())/127);
               charactersonStaff.get(detectedStaff).add(d);
           }else if(type == noteTypeClicked.HASEIGHTHREST ||type == noteTypeClicked.HASQUARTERREST){
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
           if((type == noteTypeClicked.HASEIGHTHREST ||type == noteTypeClicked.HASQUARTERREST) && (lineClicked == true || spaceClicked == true)){
           ImageView newRest = new ImageView(getClass().getResource(restImage).toString());
           newRest.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent me) {
                   if(type == noteTypeClicked.HASDELETE){
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
           /*double restY; 
           
           if(lineClicked == true && hasQuarterRest == true){
               restY = l3.getStartY()-60;
           } else if(spaceClicked == true && hasQuarterRest == true){
               restY = 
           }*/
            if(lineClicked == true  && type == noteTypeClicked.HASQUARTERREST){
                   newRest.setX(mouseX-13);

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
            if(spaceClicked == true && type == noteTypeClicked.HASQUARTERREST){
                   newRest.setX(mouseX-13);
                   if(mouseY>lineFStartY && mouseY< lineFEndY){
                       newRest.setY(((lineBStartY+lineBEndY)/2)-70);
                   }
                   else if(mouseY>lineDStartY && mouseY<lineDEndY){
                       newRest.setY(((lineBStartY+lineBEndY)/2)-70);
                   }
                   else if(mouseY> lineBStartY && mouseY<lineBEndY){
                       newRest.setY(((lineBStartY+lineBEndY)/2)-70);
                   }
                   else if(mouseY>lineGStartY && mouseY<lineGEndY){
                       newRest.setY(((lineBStartY+lineBEndY)/2)-70);
                   }
                   else if(mouseY>lineEStartY && mouseY<lineEEndY){
                       newRest.setY(((lineBStartY+lineBEndY)/2)-70);
                   }


               }
            
            
            if(lineClicked == true  && type == noteTypeClicked.HASEIGHTHREST){
                   newRest.setX(mouseX-17);

                       if(mouseY>l1.getStartY()-5 && mouseY<l1.getStartY()+5){
                           newRest.setY(l3.getStartY()-48);
                       }
                       else if(mouseY>l2.getStartY()-5 && mouseY<l2.getStartY()+5){
                           newRest.setY(l3.getStartY()-48);
                       }
                        else if(mouseY>l3.getStartY()-5 && mouseY<l3.getStartY()+5){
                           newRest.setY(l3.getStartY()-48);
                       }
                       else if(mouseY>l4.getStartY()-5 && mouseY<l4.getStartY()+5){
                           newRest.setY(l3.getStartY()-48);
                       }
                        else if(mouseY>l5.getStartY()-5 && mouseY<l5.getStartY()+5){
                           newRest.setY(l3.getStartY()-48);
                       }
            }
               if(spaceClicked == true && type == noteTypeClicked.HASEIGHTHREST){
                   newRest.setX(mouseX-13);
                   if(mouseY>lineFStartY && mouseY< lineFEndY){
                       newRest.setY(((lineBStartY+lineBEndY)/2)-57);
                   }
                   else if(mouseY>lineDStartY && mouseY<lineDEndY){
                       newRest.setY(((lineBStartY+lineBEndY)/2)-57);
                   }
                   else if(mouseY> lineBStartY && mouseY<lineBEndY){
                       newRest.setY(((lineBStartY+lineBEndY)/2)-57);
                   }
                   else if(mouseY>lineGStartY && mouseY<lineGEndY){
                       newRest.setY(((lineBStartY+lineBEndY)/2)-57);
                   }
                   else if(mouseY>lineEStartY && mouseY<lineEEndY){
                       newRest.setY(((lineBStartY+lineBEndY)/2)-57);
                   }


               }


               if(type == noteTypeClicked.HASEIGHTHREST){
                   newRest.setFitWidth(20);
                   newRest.setFitHeight(25);
                   staff.getChildren().add(newRest);
                    //newRest.setX(mouseX - 11);
                    //newRest.setY(mouseY-45);
                   EighthRestCount erc = new EighthRestCount(newRest.getX(), newRest.getY());
                   erc.setImageView(newRest);
                   int detectedStaff = (int) Math.floor((erc.getY() -25)/127);
                   charactersonStaff.get(detectedStaff).add(erc);
               } else if(type == noteTypeClicked.HASQUARTERREST){
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
                   if(clickedLine == l && (type == noteTypeClicked.HASQUARTERNOTE||type == noteTypeClicked.HASHALFNOTE ||type == noteTypeClicked.HASEIGHTHNOTE || type == noteTypeClicked.HASQUARTERREST|| type == noteTypeClicked.HASEIGHTHREST)){
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
                   if(clickedPane == p && (type == noteTypeClicked.HASQUARTERNOTE||type == noteTypeClicked.HASHALFNOTE ||type == noteTypeClicked.HASEIGHTHNOTE || type == noteTypeClicked.HASQUARTERREST|| type == noteTypeClicked.HASEIGHTHREST)){
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
                   if(type == noteTypeClicked.HASDELETE){
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
   public void play() throws InvalidMidiDataException, MidiUnavailableException, InterruptedException{
	for(ArrayList<MusicalCharacter> musicLine: charactersonStaff){
		for(MusicalCharacter note : musicLine){
			double Cposition = ((lineDStartY+lineDEndY)/2)-80;
			double Dposition = l2.getStartY();
			double Eposition = ((lineFStartY+lineFEndY)/2) -80;
			double Fposition = ((lineGStartY+lineGEndY)/2)-80;
			double Gposition = l4.getStartY();
			double Aposition =  ((lineBStartY+lineBEndY)/2)-80;
			double Bposition = l3.getStartY();
                        double highFposition = l1.getStartY();
                        double timing = note.getCount();
                        if(timing == 0.5){
                            timing = 125;
                        } else if(timing == 1){
                            timing = 250;
                        } else if(timing == 2){
                            timing = 500;
                        }
			if(type == noteTypeClicked.HASQUARTERNOTE || type == noteTypeClicked.HASHALFNOTE || type == noteTypeClicked.HASEIGHTHNOTE){
				if(note.getY() == Cposition){
					ShortMessage myMsg = new ShortMessage();
					 myMsg.setMessage(ShortMessage.NOTE_ON, 4, 60, 93); 
        				Synthesizer synth = MidiSystem.getSynthesizer();
       					 synthRcvr.send(myMsg, -1);
                                         Thread.sleep((long) timing);
				 } else if(note.getY() == Dposition){
					ShortMessage myMsg = new ShortMessage();
					 myMsg.setMessage(ShortMessage.NOTE_ON, 4, 62, 93); 
        					Synthesizer synth = MidiSystem.getSynthesizer();
       	 				synthRcvr.send(myMsg, -1);
                                        Thread.sleep((long) timing);
				 } else if(note.getY() == Eposition){
					ShortMessage myMsg = new ShortMessage();
 					myMsg.setMessage(ShortMessage.NOTE_ON, 4, 64, 93); 
        					Synthesizer synth = MidiSystem.getSynthesizer();
       	 					synthRcvr.send(myMsg, -1);
                                                Thread.sleep((long) timing);
				} else if(note.getY() == highFposition){
                                        ShortMessage myMsg = new ShortMessage();
 					myMsg.setMessage(ShortMessage.NOTE_ON, 4, 65, 93); 
        					Synthesizer synth = MidiSystem.getSynthesizer();
       	 					synthRcvr.send(myMsg, -1);
                                                Thread.sleep((long) timing);
                                }else if(note.getY() == Fposition){
					ShortMessage myMsg = new ShortMessage();
 						myMsg.setMessage(ShortMessage.NOTE_ON, 4, 53, 93); 
       					 	Synthesizer synth = MidiSystem.getSynthesizer();
       	 					synthRcvr.send(myMsg, -1);
                                                Thread.sleep((long) timing);
				 } else if(note.getY() == Gposition){
					ShortMessage myMsg = new ShortMessage();
 						myMsg.setMessage(ShortMessage.NOTE_ON, 4, 55, 93); 
       	 					Synthesizer synth = MidiSystem.getSynthesizer();
       	 					synthRcvr.send(myMsg, -1);
                                                Thread.sleep((long) timing);
				 } else if(note.getY() == Aposition){
                                                ShortMessage myMsg = new ShortMessage();
 						myMsg.setMessage(ShortMessage.NOTE_ON, 4, 57, 93); 
       	 					Synthesizer synth = MidiSystem.getSynthesizer();
       	 					synthRcvr.send(myMsg, -1);
                                                Thread.sleep((long) timing);
				 }else if(note.getY() == Bposition){
					ShortMessage myMsg = new ShortMessage();
 						myMsg.setMessage(ShortMessage.NOTE_ON, 4, 59, 93); 
       					Synthesizer synth = MidiSystem.getSynthesizer();
       	 					synthRcvr.send(myMsg, -1);
                                                Thread.sleep((long) timing);
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
       spaceClicked = false;
       lineClicked = false;
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
