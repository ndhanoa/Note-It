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
public class Note implements Serializable {
    private double x;
    private double y;
    transient private ImageView myView;
    private double count;
    public Note(double count, double x, double y){
        this.x = x;
        this.y = y;
        this.count = count;
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
