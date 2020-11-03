/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.planets.terrain;

import java.io.Serializable;

/**
 *
 * @author treys
 */
public class Coordinate implements Serializable{
    private static final long serialVersionUID = 4L;
    private int posy, posx; 
    
    
    public Coordinate(int posy, int posx){
        this.posy=posy;
        this.posx=posx;
        
    }
    public int getposy(){
        return posy;
    }
     public int getposx(){
        return posx;
    }
     
    public void move(int posy, int posx){
       
            this.posy=posy;
            this.posx=posx;
             
        
        
        
    }
    
    public Coordinate getCordinate(){
        return this;
    }
}

