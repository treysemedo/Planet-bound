/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;




import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import modeloObservavel.GameObservable;
import static ui.gui.util.Constantes.BACKGROUND;
import ui.gui.util.Imagens;

/**
 *
 * @author treys
 */
public class TelaPrincipal extends StackPane {
    private final GameObservable jogoObs;
    private final ShipSelectionUI shipSelection;
    private final PlanetOrbitingUI planetOrbiting;
    private final ResourceConversionUI resourceConvertion;
    private final WaitEventUI waitEvent;
    private final SpaceStationUI spaceStaion;
    private final GameOverUI  gameOver;
    private final DroneControlingUI  droneControling;
    
    public TelaPrincipal(GameObservable jogoObs) {
        this.jogoObs = jogoObs;
        
        shipSelection=new ShipSelectionUI(jogoObs);
        planetOrbiting= new PlanetOrbitingUI(jogoObs);
        resourceConvertion=new ResourceConversionUI(jogoObs);
        waitEvent=new WaitEventUI(jogoObs);
        spaceStaion= new SpaceStationUI(jogoObs);
        gameOver=new GameOverUI(jogoObs);
        droneControling=new DroneControlingUI(jogoObs);
        
        
        shipSelection.setVisible(true);
        planetOrbiting.setVisible(false);
        resourceConvertion.setVisible(false);
        waitEvent.setVisible(false);
        spaceStaion.setVisible(false);
        gameOver.setVisible(false);
        droneControling.setVisible(false);
        
        
        //background e logo display
       
        this.setBackground(new Background(new BackgroundImage(Imagens.getImagem(BACKGROUND), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,BackgroundPosition.CENTER, new BackgroundSize(1000, 750, false, false, false, false))));
        this.getChildren().addAll(shipSelection, planetOrbiting, resourceConvertion, waitEvent,spaceStaion, gameOver, droneControling);
    }
    
}
