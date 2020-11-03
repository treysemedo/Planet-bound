/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbonundv1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logica.Game;
import modeloObservavel.GameObservable;
import ui.gui.TelaPrincipal;

/**
 *
 * @author treys
 */
public class PlanetBonundv1 extends Application {

   
    public static void main(String[] args) {
      
        launch(args);
       
    }

    @Override
    public void start(Stage stage) throws Exception {
     Game jogo=new Game();
     GameObservable jogoObs= new GameObservable(jogo);
     
     Scene cena=new Scene(new TelaPrincipal(jogoObs), 1000, 700);
     stage.setScene(cena);
     stage.setTitle("PLANET BOUND");
     stage.show();
    }
    
}
