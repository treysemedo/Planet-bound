/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.components;

import java.io.Serializable;
import logica.dados.planets.terrain.Coordinate;
import logica.dados.util.EnumResource;

/**
 *
 * @author treys
 */
public class Resource implements Serializable{
    private static final long serialVersionUID = 4L;
    private boolean artfact;
    private Coordinate position;  
    private EnumResource type;
    
    public Resource( Coordinate position) {
        this.artfact = true;
        this.position = position;
        type=null;
    }
    public Resource( Coordinate position, EnumResource type) {
        this.artfact = false;
        this.position = position;
        this.type=type; 
    }
    
    public Coordinate getCoordenate(){
        return position;
    }
    public boolean isArtfact(){
        return artfact;
    }
}
