/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteit;

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
 * @author ndhanoa
 */
public class MusicalCharacter implements Serializable {
    private double x;
    private double y;
    private double count;
    transient private ImageView myView;
    
    public MusicalCharacter(double count, double x, double y){
        this.count = count;
        this.x = x;
        this.y = y;
    }
    
      public double compare(MusicalCharacter p1, MusicalCharacter p2) {
            return p1.getX() - p2.getX(); // Ascending
        }
    
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public double getCount(){
        return count;
    }
    
    
    public void setImageView(ImageView iv){
        this.myView = iv;
    }
    public ImageView getImageView(){
        return myView;
    }
    
    
}
