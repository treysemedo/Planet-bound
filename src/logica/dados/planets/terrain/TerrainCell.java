/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.planets.terrain;

import java.io.Serializable;
import logica.dados.util.EnumCell;

/**
 *
 * @author treys
 */
public class TerrainCell implements Serializable{
    private static final long serialVersionUID = 4L;
    private boolean drone;
    private boolean SHUTLE;
    private boolean alien;
    private boolean resource;
    private EnumCell estado;

    public TerrainCell() {
        drone = false;
        SHUTLE=false;
        alien=false;
        resource=false;
        updateState();
    }

    public final void updateState() {
        if (drone && resource && SHUTLE) {
            estado = EnumCell.SHUTLEDRONERESOURCE;
        } else if (drone && SHUTLE) {
            estado = EnumCell.SHUTLEDRONE;
        } else if (drone && resource) {
            estado = EnumCell.RESOURCEDRONE;
        } else if (alien && SHUTLE) {
            estado = EnumCell.SHUTLEALIEN;
        } else if (alien && resource) {
            estado = EnumCell.RESOURCEALIEN;
        } else if (drone) {
            estado = EnumCell.DRONE;
        } else if (resource) {
            estado = EnumCell.RESOURCE;
        } else if (alien) {
            estado = EnumCell.ALIEN;
        } else if (SHUTLE) {
            estado = EnumCell.SHUTLE;
        } else {
            estado = EnumCell.EMPTY;
        }
        
    }

    public EnumCell getCellState() {
        return estado;
    }

    public void setAlien() {
        alien = true;
        updateState();
    }

    public void setDrone() {
        drone = true;
        updateState();
    }
     public void setresource() {
        resource = true;
        updateState();
    }
    public void setLandingSpot() {
        SHUTLE = true;
        updateState();
    }

    public void unsetAlien() {
        alien = false;
        updateState();
    }

    public void unsetDrone() {
        drone = false;
        updateState();
    }
     public void unsetresource() {
        resource = false;
        updateState();
    }
   
    
    
}
