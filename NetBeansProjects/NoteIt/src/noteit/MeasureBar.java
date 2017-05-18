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
 * @author ndhanoa
 */
public class MeasureBar implements Serializable {
    
     private double y;
    private double x;
    transient private ImageView myView;
    
     public MeasureBar(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return x;
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
