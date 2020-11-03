/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.planets;

import java.io.Serializable;
import java.util.List;
import logica.dados.util.EnumResource;
import logica.dados.planets.terrain.Terrain;
import logica.dados.util.EnumAlien;
import logica.dados.util.EnumCell;
import logica.dados.util.EnumExplore;
import logica.dados.util.Utility;

/**
 *
 * @author treys
 */
public abstract class Planet implements Serializable{
    private static final long serialVersionUID = 4L;

    private List<EnumResource> resources;
    private EnumResource exploredResource;
    private boolean artfact;
    private Utility util;
    private Terrain terrain;
    private int numExploration;
     private int numResource;
    private boolean spaceStation;

    public Planet(List<EnumResource> resources, boolean artfact) {
        this.resources = resources;
        this.artfact = artfact;
        util = new Utility();
        numExploration = 0;
        numResource=resources.size();
        spaceStation = util.randomInt(1, 10) <= 3;
        
        
        
    }
    
    
    

    public boolean loseResource() {
        numExploration++;
        if (!terrain.hasArtfact()) {
            exploredResource = terrain.getTypeResource();
            resources.remove(exploredResource);
            
        } else {
            numExploration--;
            loseArtfact();
            return false;
        }
        
       return !terrain.hasArtfact();
    }
    
    public boolean terrainHasArtfact(){
       return terrain.hasArtfact();
    }

    public EnumResource getExploredResource() {
        return exploredResource;
    }

    public void loseArtfact() {
        artfact = false;
    }

    public boolean canExplore() {
        return ((resources.size()>0) || (artfact));
    }

    public final boolean explorePlanet(boolean weaponOfficer) {
//        ve se ja chegou no limite de numero exploracao
        
        int aux;
        if (artfact) {
            aux = util.randomInt(1, resources.size() + 1);
            switch (aux) {
                case 1:
                    terrain = new Terrain(artfact, weaponOfficer );
                    break;
                default:
                    terrain = new Terrain(resources.get(aux - 2), weaponOfficer);
                    break;
            }

        } else {
            aux = util.randomInt(1, resources.size());
            terrain = new Terrain(resources.get(aux - 1), weaponOfficer);
        }

        return true;
    }

    public boolean getSpaceStation() {
        return spaceStation;
    }

    public void atack() {
         terrain.atack();
    }

    public EnumCell[][] getInfoTerrain() {
        return terrain.getInfoTerrain();
    }
    public EnumResource terrainResource(){
        return terrain.getTypeResource();
    }
    public int getDroneHp() {
        return terrain.getDroneHp();
    }

    public EnumAlien getAlienType() {
        return terrain.getAlienType();
    }

    public EnumExplore DroneMove(int i, int j) {
        return terrain.DroneMove(i, j);
    }

    public boolean atackHapening() {
        return terrain.getSituation()==EnumExplore.ATACK;
    }
    
    public EnumPlaneta getPlanetType(){
       return EnumPlaneta.RED;
    }
    
     public boolean DroneIsAlive(){
         return terrain.DroneIsAlive();
     }

    public List<EnumResource> getResources() {
        return resources;
    }

    public boolean hasArtfact() {
        return artfact;
    }

    @Override
    public String toString() {
        return "Planet{" + "resources=" + resources + "\n\tartfact=" + artfact + ", numExploration=" + numExploration + ", spaceStation=" + spaceStation + '}';
    }

    public void resetDrone() {
        terrain.resetDrone();
    }

}
