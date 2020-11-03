/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import modeloObservavel.GameObservable;
import static ui.gui.util.Propriedade.ALTERA_AMMO;
import static ui.gui.util.Propriedade.ALTERA_FUEL;
import static ui.gui.util.Propriedade.ALTERA_SHIELD;

/**
 *
 * @author treys
 */
public class PainelNave extends HBox {

    private HBox fuel;
    private HBox shield;
    private HBox ammo;
    private Rectangle desenhoOcupado;
    private GameObservable jogoObs;

    public PainelNave(GameObservable jogoObs) {
        this.jogoObs = jogoObs;

        fuel = desenho("FUEL", jogoObs.getFuel()[0], jogoObs.getFuel()[1], Color.ORANGE, Color.RED, Color.YELLOW);
        shield = desenho("SHIELD", jogoObs.geShield()[0], jogoObs.geShield()[1], Color.WHITE, Color.DARKGREY, Color.WHITE);
        ammo = desenho("AMMO", jogoObs.getWeapon()[0], jogoObs.getWeapon()[1], Color.GREEN, Color.DARKGREEN, Color.GREEN);

        
        registaListenerPropiedade();
        this.setSpacing(30);
        this.setAlignment(Pos.TOP_LEFT);
        this.setPadding(new Insets(20, 20, 20, 20));
        this.getChildren().addAll(fuel, shield, ammo);
        
    }
    
    private void registaListenerPropiedade() {
        jogoObs.addPropertyChangeListener(ALTERA_FUEL, listener->{
            fuel=desenho("FUEL", jogoObs.getFuel()[0], jogoObs.getFuel()[1], Color.ORANGE, Color.RED, Color.YELLOW);
            this.getChildren().set(0, fuel);
        });
        
         jogoObs.addPropertyChangeListener(ALTERA_SHIELD, listener->{
             shield = desenho("SHIELD", jogoObs.geShield()[0], jogoObs.geShield()[1], Color.WHITE, Color.DARKGREY, Color.WHITE);
               this.getChildren().set(1, shield);
         });
         
          jogoObs.addPropertyChangeListener(ALTERA_AMMO, listener->{
              ammo = desenho("AMMO", jogoObs.getWeapon()[0], jogoObs.getWeapon()[1], Color.GREEN, Color.DARKGREEN, Color.GREEN);
              this.getChildren().set(2, ammo);
          });
        
        
    }
    

    private HBox desenho(String titulo, int atual, int max, Color corT, Color cor1, Color cor2) {
        Label lf = new Label(titulo);
        lf.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        lf.setTextFill(corT);

        Stop inicioGradient = new Stop(0, cor1);
        Stop fimGradient = new Stop(1, cor2);
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, inicioGradient, fimGradient);

        Text text = new Text(Integer.toString(atual));
        text.setFont(Font.font("verdana", FontWeight.BOLD, 12));
        
        
        desenhoOcupado = new Rectangle(0, 0, atual * 3, 15);
        desenhoOcupado.setFill(lg1);

        StackPane grafico = new StackPane(desenhoOcupado, text);
        grafico.setMinSize(max * 3 + 4, 19);
        grafico.setMaxSize(max * 3 + 4, 19);
        grafico.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2, 2, 2, 2))));
        grafico.setAlignment(Pos.CENTER_LEFT);
        HBox resultado = new HBox(lf, grafico);
        resultado.setSpacing(10);
        return resultado;
    }
    
    

}
