/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.estados;

import java.util.List;
import logica.estados.util.EnumState;
import logica.dados.Dados;
import logica.dados.Event.Event;
import logica.dados.util.Utility;

/**
 *
 * @author treys
 */
public class WaitEvent extends IStateAdapter {
    private final Dados data;
   
    

    public WaitEvent(Dados dados) {
        super(dados);
      
        data=super.getData();
        
    }

    @Override
    public IState travel() {
        
        if(data.hasCrew()&& data.hasFuel()){
            data.travel();
            return new PlanetOrbiting(data);
        }else
            return new GameOver(data, false);
    }
     
    @Override
     public IState randomEvent(){
         data.eventHappen();
         if(data.hasFuel()&& data.hasCrew()){
            data.travel();
            return new PlanetOrbiting(data);
         }
         return new GameOver(data, false);
         
     }
    @Override
     public IState selectEvent(int n){
         data.eventHappen(n);
         if(data.hasFuel()&& data.hasCrew()){
            data.travel();
            return new PlanetOrbiting(data);
         }
         return new GameOver(data, false);
     }
   
     @Override
    public EnumState getState() {
        return EnumState.EVENT;
    }

}
