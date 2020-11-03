/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.components;

import java.io.Serializable;
import logica.dados.planets.terrain.Coordinate;
import logica.dados.util.Utility;

/**
 *
 * @author treys
 */
public class Drone implements Serializable{
    private static final long serialVersionUID = 4L;
    private int health;
    private Coordinate position;
    private boolean load;
    private boolean weaponOfficer;
    public Drone(Coordinate position, boolean weaponOfficer){
        health=6;
        this.position=position;
        load=false;
        this.weaponOfficer=weaponOfficer;
    }

    public void setLoad() {
        this.load = true;
    }
    public boolean checkLoad(){
        return load;
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
     
     public boolean isAlive(){
         return health>0;
     }
     public void loseHealth(){
         health--;
     }
     public int atack(){
         Utility temp=new Utility();
        return temp.randomInt(1, 6);
     }
     public int getHp(){
         return health;
     }

    public boolean hasWeaponOfficer() {
        return weaponOfficer;
    }

   
    

    public void resetHealth() {
         this.health = 6;
    }
     
}


