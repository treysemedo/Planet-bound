/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.estados;

import logica.dados.Dados;
import logica.dados.util.EnumResource;
import logica.estados.util.EnumState;

/**
 *
 * @author treys
 */
public class SpaceStation extends IStateAdapter {
    Dados data;
    public SpaceStation(Dados dados) {
        super(dados);

        data=getData();
    }

    @Override
    public IState undock() {
        return new PlanetOrbiting(data);
    }
    @Override
     public void hireCrewMember(){
        data.hireCrewMember();
    }
     
     @Override
    public void changeForResource(EnumResource a, EnumResource b){
         data.changeForResource(a, b);
    }

    
    
    @Override
    public void upgradeWeapon() {
        data.upgradeWeapon();
    }

    @Override
    public void upgradeCargo() {
         data.upgradeCargo(); 
    }

    @Override
    public void setFullShield() {
         data.setFullShield(); 
    }
    
    
    @Override
    public EnumState getState() {
        return EnumState.SPACESTATION;
    }
}
