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
public class GameOver extends IStateAdapter{
    Dados data;
    public GameOver(Dados dados, boolean win) {
        super(dados);
 
        data=super.getData();
        data.setWin(win);
    }

    

    @Override
    public IState newGame(){
        data.resetDados();
        return new ShipSelection(data);
    }
    @Override
    public EnumState getState() {
        return EnumState.GAMEOVER;
    }
    
}
