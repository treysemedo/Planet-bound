/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.Event;


import java.io.Serializable;
import logica.dados.Ship.Ship;
import logica.dados.util.EnumEvent;


/**
 *
 * @author treys
 */
public abstract class Event implements Serializable{
    private static final long serialVersionUID = 4L;
    
    
    Ship ship;
    
    public Event(Ship ship){
        this.ship=ship;
    }
    
     public abstract void act();

     public abstract EnumEvent getEnumEvent();
}
