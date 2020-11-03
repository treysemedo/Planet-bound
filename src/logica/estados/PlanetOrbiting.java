/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.estados;

import logica.estados.util.EnumState;
import logica.dados.Dados;

/**
 *
 * @author treys
 */
public class PlanetOrbiting extends IStateAdapter {

    private final Dados data;

    public PlanetOrbiting(Dados dados) {
        super(dados);
        data = getData();
        
    }

    @Override
    public IState convertResourse() {
        return new ResourceConversion(data, this);
    }

    @Override
    public IState visitStation() {
        if (data.hasStation()) {
            return new SpaceStation(data);
        }
        return this;
    }

    @Override
    public IState travel() {
        if (!data.hasFuel()) {
            return new GameOver(data, false);
        }
        data.canUpgradeCargo();
        data.travel();
        return new WaitEvent(data);       
    }

    @Override
    public IState landOnPlanet() {

        if (data.hasLandingParty() && data.canExplore() && data.hasFuel()) {
            return new DroneControling(data);
        }
        return this;
    }

    @Override
    public EnumState getState() {
        return EnumState.PLANETORBITING;
    }

}
