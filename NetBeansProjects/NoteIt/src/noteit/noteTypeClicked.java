/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noteit;

/**
 *
 * @author adengoolsbee
 */
public enum noteTypeClicked {
    HASQUARTERNOTE("hasQuarterNote"), HASHALFNOTE("hasHalfNote"), HASEIGHTHNOTE("hasEighthNote"), HASQUARTERREST("hasQuarterRest"), HASMEASUREBAR("hasMeasureBar"), HASEIGHTHREST("hasEighthRest"), HASDELETE("hasDelete"), HASDOUBLEBARLINE("hasDoubleBarLine");
    private String nameAsString;
    
    private noteTypeClicked(String nameAsString){
        this.nameAsString = nameAsString;
    }
    @Override
    public String toString(){
        return this.nameAsString;
    }

}


