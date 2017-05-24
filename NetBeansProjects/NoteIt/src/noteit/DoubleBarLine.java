package noteit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author adengoolsbee
 */
public class DoubleBarLine extends MusicalCharacter implements Serializable {
    
  private double y;
    private double x;
    transient private ImageView myView;
   
     public DoubleBarLine(double x, double y){
        super(-1, x, y);
    }
    
}
