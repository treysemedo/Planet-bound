/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados;

import java.io.Serializable;
import logica.dados.Event.Event;
import java.util.List;
import logica.dados.Event.CargoLoss;
import logica.dados.Event.CrewDeath;
import logica.dados.Event.CrewRescue;
import logica.dados.Event.FuelLoss;
import logica.dados.Event.NoEvent;
import logica.dados.Event.SalvageShip;
import logica.dados.Ship.Ship;
import logica.dados.Ship.Military;
import logica.dados.Ship.Mining;
import logica.dados.planets.BlackPlanet;
import logica.dados.planets.BluePlanet;
import logica.dados.planets.EnumPlaneta;
import logica.dados.planets.GreenPlanet;
import logica.dados.planets.Planet;
import logica.dados.planets.RedPlanet;
import logica.dados.util.EnumAlien;
import logica.dados.util.EnumCell;
import logica.dados.util.EnumEvent;
import logica.dados.util.EnumExplore;
import logica.dados.util.EnumMsg;
import logica.dados.util.EnumOfficer;
import logica.dados.util.EnumResource;
import logica.dados.util.EnumSector;
import logica.dados.util.Utility;

/**
 *
 * @author treys
 */
public class Dados implements Serializable{
    private static final long serialVersionUID = 4L;

    private Ship ship;
    private Planet planet;
    private Utility util;
    private Event event;
    private EnumMsg msg;
    private boolean win;

    public Dados() {
        planet = null;
        util = new Utility();
        win = false;

    }

    public boolean setShip(int n) {

        switch (n) {
            case 1:
                ship = new Military();
                break;
            case 2:
                ship = new Mining();
                break;
            default:
                return false;

        }
        return true;
    }

    public void removeOfficer() {
        ship.removeOfficer();
    }

    

    public void addOfficer() {
        ship.addOfficer();
    }

  

    public EnumSector travel() {
        int mode = 1;
        if (util.randomInt(1, 8) == 1) {
            mode = 2;
        }

        switch (ship.getSector()) {
            case FEDERATION:
                ship.travelTo(EnumSector.PLANET, mode);
                planet = generatePlanet();
                break;
            case EVENT:
                ship.travelTo(EnumSector.PLANET, mode);
                planet = generatePlanet();
                break;
            case PLANET:
                ship.travelTo(EnumSector.EVENT, mode);

        }
        return ship.getSector();

    }

    //gerar planeta e evento
    public Planet generatePlanet() {
        int aux = util.randomInt(1, 4);
        Planet newP = null;
        switch (aux) {
            case 1:
                newP = new BlackPlanet();
                break;
            case 2:
                newP = new RedPlanet();
                break;
            case 3:
                newP = new GreenPlanet();
                break;
            case 4:
                newP = new BluePlanet();
                break;

        }
        return newP;
    }
    
     

    public void generateEvent(int aux) {
       
        Event newE = null;

        switch (aux) {
            case 0:
                newE = new CrewDeath(ship);
                break;
            case 1:
                newE = new SalvageShip(ship);
                break;
            case 2:
                newE = new CargoLoss(ship);
                break;
            case 3:
                newE = new FuelLoss(ship);
                break;
            case 4:
                newE = new NoEvent(ship);
                break;
            case 5:
                 newE =  new CrewRescue(ship);
                break;
        }
        
        event=newE;
    }

    public final void explorePlanet() {
        planet.explorePlanet(ship.existsOfficer(EnumOfficer.WEAPONS));
    }

   

   
    public void hireCrewMember() {
        if(hasStation() && ship.hireCrewMember())
            msg=EnumMsg.SUCCESS;
        else
             msg=EnumMsg.FAIL;
    }

    public void changeForResource(EnumResource type1, EnumResource type2) {
        if(hasStation() && ship.changeForResource(type1, type2))
              msg=EnumMsg.SUCCESS;
        else
             msg=EnumMsg.FAIL;
    }

    

    //operacoes cargohold
    public void produceShield() {
        if(ship.produceShield())
            msg=EnumMsg.SUCCESS;
        else
            msg=EnumMsg.FAIL;
    }

    public void produceAmmo() {
        if(ship.produceAmmo())
             msg=EnumMsg.SUCCESS;
        else
            msg=EnumMsg.FAIL;
    }

    public void produceFuel() {
        if(ship.produceFuel())
             msg=EnumMsg.SUCCESS;
        else
            msg=EnumMsg.FAIL;
    }

    public void upgradeCargo() {
        if(hasStation() && ship.upgradeCargo())
              msg=EnumMsg.SUCCESS;
        else
             msg=EnumMsg.FAIL;
    }

    //verifca se ja se fez update do cargo
    public void canUpgradeCargo() {
        ship.canUpgradeCargo();
    }

    public void upgradeWeapon() {
        if( ship.UpgradeWeapon())
              msg=EnumMsg.SUCCESS;
        else
             msg=EnumMsg.FAIL;
    }
    
     public void setFullShield() {
        if(ship.setFullShield())
              msg=EnumMsg.SUCCESS;
        else
             msg=EnumMsg.FAIL;
    }

     
     
    public EnumExplore DroneMove(int i, int j) {
        
         EnumExplore aux= planet.DroneMove(i, j);
        switch (aux) {
            case BACKTOSHIP:
                msg=EnumMsg.BACKTOSHIP;
                break;
            case ATACK:
                msg=EnumMsg.ATACK;
                break;
            default:
                msg=EnumMsg.SUCCESS;
                break;
        }
         return aux;
         
    }
     public void eventHappen(int n) {
       generateEvent(n); 
       event.act();
    }
     public void eventHappen() {
         generateEvent(util.randomInt(0, 5)); 
         event.act();
    }
     

    public void atack() {
         planet.atack();
    }
    
     public void resetDrone() {
        planet.resetDrone();
    }

    public boolean explorationEnd() {
        if (planet.loseResource()) {
            ship.addResource(planet.getExploredResource(), util.randomInt(1, 6));
        } else {
            ship.addArtfact();
        }

        return ship.achievedArtifacts();
    }

   
    public void expendFuel() {
        ship.loseFuel();
    }

    public boolean hasFuel() {
        return !ship.emptyFuelCells();
    }

    public void resetDados() {
        planet = null;
        util = new Utility();
        win = false;
    }

   

    public void setWin(boolean win) {
        this.win = win;
    }
    
    
    
    
    
    
    
    
    //get Info
    
      public boolean hasLandingParty() {
        return ship.existsOfficer(EnumOfficer.LANDINGPARTY);
    }

    public boolean canExplore() {
        return planet.canExplore();
    }

    public boolean hasCrew() {
        return ship.hasCrew();
    } 
    public String getInfoShip() {
        return ship.toString();
    }

    public String getInfoPlanet() {
        if (planet != null) {
            return planet.toString();
        }
        return "Sem planeta";
    }

    public EnumCell[][] getInfoTerrain() {
        return planet.getInfoTerrain();
    }

    public String getInfoResources() {

        return ship.getInfoResources();
    }

    public int getDroneHp() {
        return planet.getDroneHp();
    }

    public EnumAlien getAlienType() {
        return planet.getAlienType();
    }

    public EnumEvent getEvent() {
        return event.getEnumEvent();
    }
    
    public List<EnumOfficer> getCrew() {
        return ship.getCrew();
    }

    public boolean hasStation() {
        return planet.getSpaceStation();
    }
    
    public String listCrew() {
        return ship.listCrew();
    }

    public boolean won() {
        return win;
    }

   
     public int[] getFuel(){
        return ship.getFuel();
    }
     public int[] geShield(){
        return ship.getShield();
    }
     public int[] getWeapon(){
        return ship.getWeapon();
    }
    public int getResource(EnumResource r) {
        return ship.getResource(r);
    }
    public EnumPlaneta getPlanetType(){
       return planet.getPlanetType();
    }
    public boolean DroneIsAlive(){
         return planet.DroneIsAlive();
     }
    
    public EnumResource terrainResource(){
        return planet.terrainResource();
    }
    public boolean terrainHasArtfact(){
       return planet.terrainHasArtfact();
    }
    
     public List<EnumResource> getResources() {
        return planet.getResources();
    }
    
    public int getArtfact() {
        return ship.getArtfact();
    }
    
    public boolean planetHasArtfact() {
        return planet.hasArtfact();
    }

    public EnumMsg getMsg() {
        return msg;
    }

   
    
    

}
