/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.estados;

import logica.dados.Dados;
import logica.dados.util.EnumResource;

/**
 *
 * @author treys
 */
public abstract class IStateAdapter implements IState {

    private final Dados dados;

    public IStateAdapter(Dados dados) {
        this.dados = dados;
    }

    public final Dados getData() {
        return dados;
    }

    @Override
    public IState undock() {
        return this;
    }

    @Override
    public IState visitStation() {
        return this;
    }

    @Override
    public IState selectShip(int n) {
        return this;
    }

    @Override
    public IState travel() {
        return this;
    }

    @Override
    public IState convertResourse() {
        return this;
    }

    @Override
    public IState contractCrew() {
        return this;
    }

    @Override
    public IState landOnPlanet() {
        return this;
    }

    @Override
    public IState moveDrone(int i, int j) {
        return this;
    }

    @Override
    public IState alienAtack() {
        return this;
    }

    @Override
    public IState newGame() {
        return this;
    }

    @Override
    public IState goBack() {
        return this;
    }
    
     @Override
    public IState randomEvent() {
    return this;
    }

    @Override
    public IState selectEvent(int n) {
    return this;
    }

  

    

    @Override
    public void hireCrewMember() {}

    @Override
    public void produceShield() {}

    @Override
    public void produceAmmo() {}

    @Override
    public void produceFuel() {}

    @Override
    public void upgradeCargo() {}

    @Override
    public void changeForResource(EnumResource a, EnumResource b) {}

    @Override
    public void upgradeWeapon() {}

    @Override
    public void setFullShield() {}

   

}
