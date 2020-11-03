/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.estados;

import java.io.Serializable;
import logica.dados.util.EnumResource;
import logica.estados.util.EnumState;

/**
 *
 * @author treys
 */
public interface IState extends Serializable{
    IState selectShip(int n);
    IState travel();
    IState convertResourse();
    IState contractCrew();
    IState landOnPlanet();
    IState moveDrone(int i, int j);
    IState alienAtack();
    IState newGame();
    IState visitStation();
    IState undock();
    IState randomEvent();
    IState selectEvent(int n);
    IState goBack();
    
    
    EnumState getState();
    
    void hireCrewMember();
    void produceShield();
    void produceAmmo();
    void produceFuel();
    void upgradeCargo();
    void changeForResource(EnumResource a, EnumResource b);
    void upgradeWeapon();
    void setFullShield();
    
    
}