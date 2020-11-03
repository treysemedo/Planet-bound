/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.components;

import java.io.Serializable;
import logica.dados.planets.terrain.Coordinate;
import logica.dados.util.EnumAlien;
import logica.dados.util.Utility;

/**
 *
 * @author treys
 */
public class Alien implements Serializable{
    private static final long serialVersionUID = 4L;

   
    private EnumAlien alienType;
    private Coordinate position;  
    int probAtack, probDeath;
    
    
    public Alien(Coordinate position, int n) {
        this.position=position;
        alienType=EnumAlien.valueOf(n);
        probAtack =alienType.getAtack();
        probDeath =alienType.getDeath();
    }
    
     public Coordinate getCoordenate(){
        return position;
    }
     
     public void move(int i, int j){
        position.move(i, j);  
    }
     public int getposy(){
         return position.getposy();
     }
     public int getposx(){
         return position.getposx();
     }
    public boolean atackOnTarget(){
        Utility temp=new Utility();
        return temp.randomInt(1, 6)<=probAtack;
    }
    public boolean takeShot(int num){
        
        return num<=probDeath;
    }
     public EnumAlien getType(){
        return alienType;
    }

}
