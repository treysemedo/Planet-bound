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
public class ResourceConversion extends IStateAdapter{
     private final Dados data;
     IState previousState;
     
    public ResourceConversion(Dados dados,IStateAdapter previousState ) {
        super(dados);
        
        data=getData();
        this.previousState=previousState;
    }
    
     @Override
    public IState goBack(){
        return previousState;
    }
    
     @Override
    public void produceShield() {
        data.produceShield();
    }
     @Override
    public void produceAmmo() {
         data.produceAmmo();
    }
     @Override
    public void produceFuel() {
         data.produceFuel();
    }

   @Override
    public EnumState getState() {
        return EnumState.RESOURCECONVERSION;
    }
     
}
