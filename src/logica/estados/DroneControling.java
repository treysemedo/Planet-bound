/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.estados;

import logica.estados.util.EnumState;
import logica.dados.Dados;
import logica.dados.util.EnumExplore;

/**
 *
 * @author treys
 */
public class DroneControling extends IStateAdapter{
    Dados data;
    public DroneControling(Dados data) {
        super(data);
        this.data=data;
        data.explorePlanet();
        data.expendFuel();
        data.resetDrone();
    }
  

    @Override
    public IState moveDrone(int i, int j) {
        EnumExplore aux=data.DroneMove(i, j);
        switch(aux){
        
            case ATACK:
                data.atack();
                   if(!data.DroneIsAlive()){
                       return new PlanetOrbiting(data);
                   }
                else 
                    return this;
               
            case BACKTOSHIP:
               if (data.explorationEnd())
                   return new GameOver(data, true);
               else
                    return new PlanetOrbiting(data);
              
            default:
                return this;
                     
        }
    }
    @Override
    public EnumState getState() {
        return EnumState.DRONECONTROLING;
    }
    
    
}
