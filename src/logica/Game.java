/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;


import java.io.Serializable;
import java.util.List;
import logica.dados.Dados;
import logica.dados.planets.EnumPlaneta;
import logica.dados.util.EnumAlien;
import logica.dados.util.EnumCell;
import logica.dados.util.EnumEvent;
import logica.dados.util.EnumMsg;
import logica.dados.util.EnumOfficer;
import logica.dados.util.EnumResource;
import logica.estados.util.EnumState;
import logica.estados.IState;
import logica.estados.ShipSelection;

/**
 *
 * @author treys
 */
public class Game implements Serializable{
    private static final long serialVersionUID = 4L;

    private Dados data;
    private IState state;

    public Game() {
        data = new Dados();
        state = new ShipSelection(data);
    }

    //state setter
    
    private void setState(IState state) {
        this.state = state;
    }

    //state changes
    public void SelectShip(int n) {
        setState(state.selectShip(n));
    }

    public void spaceTravel() {
        setState(state.travel());
    }

    public void convertResources() {
        setState(state.convertResourse());
    }

    public void goBack() {
        setState(state.goBack());
    }

    public void visitStation() {
        setState(state.visitStation());
    }


    public void undock() {
        setState(state.undock());
    }
    
    public void landOnPlanet(){
        setState(state.landOnPlanet());
    }
     public void DroneMove(int i, int j) {
        setState(state.moveDrone(i, j));
    }
     public void newGame(){
         setState(state.newGame());
     }
     
     public void randomEvent(){
         setState(state.randomEvent());
    }

    public void selectEvent(int n){
         setState(state.selectEvent(n));
    }
    
    
    //funcões q não alteram estado
      public void hireCrewMember(){
         state.hireCrewMember();
    }
    public void produceShield() {
         state.produceShield();
    }

    public void produceAmmo() {
         state.produceAmmo();
    }

    public void produceFuel() {
         state.produceFuel();
    }
    
    public void upgradeCargo(){
         state.upgradeCargo();
    }
    public void changeForResource(EnumResource a, EnumResource b){
         state.changeForResource(a, b);
    }

    public void upgradeWeapon(){
         state.upgradeWeapon();
    }
    public void setFullShield() {
        state.setFullShield();
    }
    

    //string with infos
    public String getInfoShip() {
        return data.getInfoShip();
    }

    public String getInfoPlanet() {
        return data.getInfoPlanet();
    }

    public String getInfoResources() {
        return data.getInfoResources();
    }
     public String listCrew() {
        return data.listCrew();
    }
    
    
    
    //info for GUI

    public EnumCell[][] getInfoTerrain() {
        return data.getInfoTerrain();
    }
    public List<EnumOfficer> getCrew() {
        return data.getCrew();
    }

    public boolean planetHasStation() {
        return data.hasStation();
    }

   
    public EnumState getState() {
        return state.getState();
    }

    public EnumEvent getEvent() {
        return data.getEvent();
    }

    
    public int getDroneHp(){
        return data.getDroneHp();
    }
    
    public EnumAlien getAlienType(){
        return data.getAlienType();
    }
    
     public boolean terrainHasArtfact(){
       return data.terrainHasArtfact();
    }
    public EnumResource terrainResource(){
        return data.terrainResource();
    }
    
    public boolean lostCrew(){
        return !data.hasCrew();
    }
    public boolean won() {
        return data.won();
    }
   

     public int[] getFuel(){
        return data.getFuel();
    }
     
      public int[] geShield(){
        return data.geShield();
    }
     public int[] getWeapon(){
        return data.getWeapon();
    }
     
     
    public int getResource(EnumResource r) {
        return data.getResource(r);
    }
    public int getArtfact() {
        return data.getArtfact();
    }
    
    public EnumPlaneta getPlanetType(){
       return data.getPlanetType();
    }
    
    public List<EnumResource> getResources() {
        return data.getResources();
    }
    
    public boolean planetHasArtfact() {
        return data.planetHasArtfact();
    }
    
    public EnumMsg getMsg() {
        return data.getMsg();
    }
}
