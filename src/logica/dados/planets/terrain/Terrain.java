/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.planets.terrain;

import java.io.Serializable;
import logica.dados.components.Alien;
import logica.dados.components.Drone;
import logica.dados.util.EnumAlien;
import logica.dados.util.EnumCell;
import logica.dados.util.EnumExplore;
import logica.dados.util.EnumResource;
import logica.dados.util.Utility;

/**
 *
 * @author treys
 */
public class Terrain implements Serializable{
    private static final long serialVersionUID = 4L;
    private TerrainCell[][] terrainImage;
    private Drone drone;
    private Alien alien;
    private EnumExplore aux1;
    private Coordinate landingSpot;
    private Coordinate resource;
    private EnumResource type;
    private Utility util;
    private boolean artfact;
    private int numRound, timerAlien;
   
    public Terrain(boolean artfact,boolean weaponOfficer) {
        this(weaponOfficer);
        type = null;
        this.artfact = true;
    }

    public Terrain(EnumResource resourceT,boolean weaponOfficer) {
        this(weaponOfficer);
        type = resourceT;
        this.artfact = false;
   

    }

    public Terrain(boolean weaponOfficer) {

        util = new Utility();
        terrainImage = new TerrainCell[6][6];
        numRound = 0;
        timerAlien = -1;
        createComponents(weaponOfficer);
        createTerrainCells();
        updateTerrain();

    }

    private final void createComponents(boolean weaponOfficer) {
        int posyAux, posxAux;
        landingSpot = new Coordinate(util.randomInt(0, 5), util.randomInt(0, 5));
        drone = new Drone(landingSpot.getCordinate(),weaponOfficer );
        //colocar resource no terreno
        do {
            posyAux = util.randomInt(0, 5);
            posxAux = util.randomInt(0, 5);

        } while (landingSpot.getposy() == posyAux && landingSpot.getposx() == posxAux);

        resource = new Coordinate(posyAux, posxAux);

        createAlien();
    }

    private void createTerrainCells() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                terrainImage[i][j] = new TerrainCell();
            }
        }

    }

    private void createAlien() {
        int posyAux, posxAux;

        do {
            posyAux = util.randomInt(0, 5);
            posxAux = util.randomInt(0, 5);

        } while ((landingSpot.getposy() == posyAux && landingSpot.getposx() == posxAux) || (resource.getposy() == posyAux && resource.getposx() == posxAux) || (drone.getposy() == posyAux && drone.getposx() == posxAux));

        alien = new Alien(new Coordinate(posyAux, posxAux), util.randomInt(1, 4));
    }

    private void updateTerrain() {
        terrainImage[landingSpot.getposy()][landingSpot.getposx()].setLandingSpot();
        terrainImage[drone.getposy()][drone.getposx()].setDrone();
        terrainImage[resource.getposy()][resource.getposx()].setresource();
        terrainImage[alien.getposy()][alien.getposx()].setAlien();
    }

    public EnumCell[][] getInfoTerrain() {

        EnumCell aux[][] = new EnumCell[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                aux[i][j] = terrainImage[i][j].getCellState();
            }

        }
        return aux;
    }

    public EnumExplore DroneMove(int i, int j) {
        int posx = drone.getposx(), posy = drone.getposy();
        TerrainCell current;
        TerrainCell next;
        

        if (posx + i > 5 || posx + i < 0 || posy + j > 5 || posy + j < 0) {
            return EnumExplore.TRAPED;
        }

        current = terrainImage[posy][posx];
        next = terrainImage[posy + j][posx + i];
        
        switch (next.getCellState()) {

            case EMPTY:
                current.unsetDrone();
                drone.move(posy + j, posx + i);
                next.setDrone();
                if (drone.checkLoad()) {
                    current.unsetresource();
                    next.setresource();
                }
                aux1 = EnumExplore.MOVE;

                break;

            case SHUTLE:
                if (drone.checkLoad()) {
                    aux1 = EnumExplore.BACKTOSHIP;
                } else {
                    return EnumExplore.TRAPED;
                }

                break;

            case RESOURCE:
                current.unsetDrone();
                drone.move(posy + j, posx + i);
                drone.setLoad();
                next.setDrone();
                aux1 = EnumExplore.MOVE;
                break;
            default:
                aux1 = EnumExplore.ATACK;
                break;

        }
        if (alien == null) {
            if (aux1 == EnumExplore.MOVE) {
                numRound++;
            } else {
                numRound = 0;
            }

            if (numRound == timerAlien) {
                createAlien();
                numRound = 0;
                timerAlien = -1;
            }
           
            return aux1;
        }
        if (alienMove()) {
            aux1 = EnumExplore.ATACK;
            terrainImage[alien.getposy()][alien.getposx()].unsetAlien();
        }

        return aux1;
    }

    public EnumExplore getSituation() {
        return aux1;
    }

    private boolean alienMove() {
        int distX = Math.abs(drone.getposx() - alien.getposx()), distY = Math.abs(drone.getposy() - alien.getposy());
        int auxX = alien.getposx(), auxY = alien.getposy();
        
        if ((distX <= 1 &&distY<1) || (distX < 1 && distY<=1)) {
            return true;
        }
        terrainImage[alien.getposy()][alien.getposx()].unsetAlien();
        if (distX > distY) {
            
            if (drone.getposx() > auxX) {
                alien.move(auxY, auxX + 1);
            } else {
                alien.move(auxY, auxX - 1);
            }
        } else if (distX < distY) {
            
            if (drone.getposy() > auxY) {
                alien.move(auxY + 1, auxX);
            } else {
                alien.move(auxY - 1, auxX);
            }
        } else {
            
            if (drone.getposy() > alien.getposy()) {
                alien.move(auxY + 1, auxX);
            } else {
                alien.move(auxY - 1, auxX);
            }
        }
        terrainImage[alien.getposy()][alien.getposx()].setAlien();
         distX = Math.abs(drone.getposx() - alien.getposx());
         distY = Math.abs(drone.getposy() - alien.getposy());
                 
        return (distX <= 1 &&distY<1) || (distX < 1 && distY<=1);

    }

    public void atack() {
       
        while (alien != null && drone.isAlive()) {
            
            if (alien.atackOnTarget()) {
                drone.loseHealth();
                if(!drone.isAlive())
                    break;
            } 
            if (drone.hasWeaponOfficer()&&alien.takeShot(drone.atack())) {
                alien = null;
                timerAlien = util.randomInt(1, 6);
               
            }

        }

    }

    public EnumResource getTypeResource() {
        return type;
    }

    public boolean hasArtfact() {
        return artfact;
    }
    public int getDroneHp(){
         return drone.getHp();
     }
     public EnumAlien getAlienType(){
        return alien.getType();
    }
     
     public boolean DroneIsAlive(){
         return drone.isAlive();
     }

    public void resetDrone() {
        drone.resetHealth();
    }

}
