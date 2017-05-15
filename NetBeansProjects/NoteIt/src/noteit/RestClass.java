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
public class RestClass implements Serializable{
    
    private double x;
    private double y;
    transient private ImageView myView;
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public double setX(double x){
        this.x = x;
        return x;
    }
    
    public double setY(double y){
        this.y = y;
        return y;
    }
    
    public void setImageView(ImageView iv) {
        this.myView = iv;
    }
    
    public ImageView getImageView() {
        return myView;
    }
    
    /**
     * @param args the command line arguments
     */
    
}
