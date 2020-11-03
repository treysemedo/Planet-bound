/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import modeloObservavel.GameObservable;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import static ui.gui.util.Constantes.infoEvents;
import static ui.gui.util.Propriedade.ALTERA_ESTADO;
import static ui.gui.util.Propriedade.EVENTO_ALEATORIO;

/**
 *
 * @author treys
 */
public class WaitEventUI extends BorderPane {

    private GameObservable jogoObs;
    private PainelCrew painelC;

    public WaitEventUI(GameObservable jogoObs) {
        this.jogoObs = jogoObs;
        this.setMaxSize(800, 600);
        this.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");
        registaListenerPropiedade();

    }

    private void registaListenerPropiedade() {
        jogoObs.addPropertyChangeListener(ALTERA_ESTADO, (listener) -> {

            switch (jogoObs.getState()) {
                case EVENT:
                    painelC = new PainelCrew(jogoObs);
                    this.setRight(painelC);
                    this.setCenter(eventsUI());
                    this.setVisible(true);
                    
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });
        
        jogoObs.addPropertyChangeListener(EVENTO_ALEATORIO, listener->{
            
             Alert dg=new Alert(Alert.AlertType.INFORMATION);
             dg.setTitle("RANDOM EVENT");
             dg.setHeaderText("");
             dg.setContentText("your random event: "+ jogoObs.getEvent());
             dg.show();
        });
    }

    private VBox eventsUI() {
        PainelNave painelN = new PainelNave(jogoObs);

        VBox vb = new VBox(painelN, informacaoEvento());
        vb.setSpacing(100);
        return vb;
    }

    private BorderPane informacaoEvento() {
        BorderPane bp = new BorderPane();

        Label info = new Label();
        info.setTextFill(Color.WHITE);
        info.setFont(Font.font("arial", FontWeight.NORMAL, 20));
        info.setWrapText(true);

        StackPane infoBox = new StackPane(info);
        infoBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");
        infoBox.setPadding(new Insets(40, 40, 40, 40));
        infoBox.setMinSize(300, 300);
        infoBox.setMaxSize(300, 300);
        infoBox.setAlignment(Pos.CENTER);
        ToggleGroup group = new ToggleGroup();
        RadioButton[] events = new RadioButton[7];

        events[0] = new RadioButton("Crew Death");
        events[1] = new RadioButton("Salvage Ship");
        events[2] = new RadioButton("Cargo Loss");
        events[3] = new RadioButton("Fuel Loss");
        events[4] = new RadioButton("No Event");
        events[5] = new RadioButton("Crew Rescue");
        events[6] = new RadioButton("Random Event");
        events[6].setSelected(true);

        for (int i = 0; i < events.length; i++) {
            events[i].setToggleGroup(group);
            events[i].setTextFill(Color.WHITE);
            events[i].setFont(Font.font("arial", 15));
            final int aux = i;
            if (i != 6) {
                
                events[i].setOnAction(eh -> {
                    info.setText(infoEvents.get(aux));
                });
            } else {
                events[i].setOnAction(eh -> {
                    info.setText("");
                });
            }

        }
        Button goAhead=new Button("Go Ahead");
        goAhead.setStyle(BOTAOCSS);
        goAhead.setOnMouseEntered(eh->{
            goAhead.setStyle(BOTAOHOVERCSS);
        });
        goAhead.setOnMouseExited(eh->{
            goAhead.setStyle(BOTAOCSS);
        });
        
        goAhead.setOnAction(eh->{
            
            for (int i = 0; i < events.length-1; i++) {
                if(events[i].isSelected()) {
                    jogoObs.selectEvent(i);
                }  
            }
            if(events[6].isSelected())
                jogoObs.randomEvent();
        });
        
        VBox botoesBox = new VBox();
        botoesBox.getChildren().addAll(events);
         botoesBox.getChildren().add(goAhead);
        botoesBox.setSpacing(15);
        botoesBox.setAlignment(Pos.CENTER_LEFT);
        botoesBox.setPadding(new Insets(0, 30, 0, 60));
        bp.setCenter(infoBox);
        bp.setLeft(botoesBox);

        return bp;
    }

}
