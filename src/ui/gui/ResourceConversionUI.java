/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modeloObservavel.GameObservable;
import static ui.gui.util.UtilUI.configuraBotoes;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import static ui.gui.util.Constantes.CONVERSOR;
import ui.gui.util.Imagens;
import static ui.gui.util.Propriedade.ALTERA_ESTADO;

/**
 *
 * @author treys
 */
public class ResourceConversionUI extends BorderPane {
    private GameObservable jogoObs;
    private PainelCrew painelC;
    private Button fuel;
    private Button ammo;
    private Button shield;
    private Button back;
    
    
     public ResourceConversionUI(GameObservable jogoObs) {
        this.jogoObs = jogoObs;
        this.setMaxSize(800, 600);
        this.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");
        registaListenerPropiedade();
        
    }
      
      

    private void registaListenerPropiedade() {
        jogoObs.addPropertyChangeListener(ALTERA_ESTADO, (listener) -> {
            
            switch (jogoObs.getState()) {
                case RESOURCECONVERSION:
                    painelC = new PainelCrew(jogoObs);
                    this.setRight(painelC);
                    this.setCenter(conversorUI());
                    this.setVisible(true);
                    registarListenersEvent();
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });
    }
    
    
     private void registarListenersEvent() {
        fuel.setOnAction(eh -> {
            jogoObs.produceFuel();
        });
        ammo.setOnAction(eh -> {
            jogoObs.produceAmmo();
        });
        shield.setOnAction(eh -> {
            jogoObs.produceShield();
        });
        
        back.setOnAction(eh -> {
            jogoObs.goBack();
        });
    }
    
    
     private VBox conversorUI() {
         PainelNave painelN=new PainelNave(jogoObs);
                    
                 
         ImageView imageView = new ImageView();
         imageView.setImage(Imagens.getImagem(CONVERSOR));
         imageView.setFitHeight(350);
         imageView.setFitWidth(350);
         StackPane paneImg=new StackPane(imageView);
         paneImg.setAlignment(Pos.CENTER);
        
         
        fuel = new Button("PRODUCE FUEL");
        shield = new Button("PRODUCE SHIELD");
        ammo = new Button("PRODUCE AMMO");
        back = new Button("GO BACK");
        
        fuel.setStyle(BOTAOHOVERCSS);
        shield.setStyle(BOTAOCSS);
        ammo.setStyle(BOTAOCSS);
        back.setStyle(BOTAOCSS);
        ArrayList<Button> botoes = new ArrayList<>(Arrays.asList(fuel, shield, ammo, back));

        HBox hb2 = new HBox(fuel, shield, ammo, back);
        hb2.setAlignment(Pos.BOTTOM_CENTER);
        configuraBotoes(botoes);
        hb2.setSpacing(20);
         
        VBox vb1=new VBox();
        vb1.setSpacing(50);
        vb1.getChildren().addAll(painelN, paneImg, hb2);
         return vb1;
     }
     
    
    
}
