/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logica.estados.util.EnumState;
import modeloObservavel.GameObservable;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import static ui.gui.util.Constantes.LOGO;
import static ui.gui.util.Constantes.MILITARY;
import static ui.gui.util.Constantes.MINING;
import ui.gui.util.Imagens;
import static ui.gui.util.Propriedade.ALTERA_ESTADO;

/**
 *
 * @author treys
 */
public class ShipSelectionUI extends StackPane {

    private final ImageView imageView1;
    private final ImageView imageView2;
    private final StackPane shipPreview;
    private final VBox vb1;
    private final VBox vb2;
    private final Button continua;
    private final Button mining;
    private final Button military;
    private final GameObservable jogoObs;

    public ShipSelectionUI(GameObservable jogoObs) {
        this.jogoObs = jogoObs;

       
        ImageView imageView = new ImageView();
        imageView.setImage(Imagens.getImagem(LOGO));

        vb1 = new VBox();
        vb2 = new VBox();
        vb1.setVisible(true);
        vb2.setVisible(false);
        vb2.setMinSize(700, 500);
        vb2.setPrefSize(700, 500);
        vb2.setMaxSize(700, 500);
        vb2.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");

        continua = new Button("PRESS TO CONTINUE");
        continua.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        continua.setFocusTraversable(true);
        continua.requestFocus();
        continua.setFont(Font.font("verdana", FontWeight.BOLD, 15));
        continua.setTextFill(Color.DARKORANGE);
        continua.setBorder(new Border(new BorderStroke(Color.DARKORANGE, Color.TRANSPARENT, Color.DARKORANGE, Color.TRANSPARENT, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, null, null, new Insets(10, 0, 0, 0))));

        Label lb1 = new Label("SELECT YOUR SHIP");
        lb1.setFont(Font.font("verdana", FontWeight.BOLD, 15));
        lb1.setTextFill(Color.DARKORANGE);

        imageView1 = new ImageView();
        imageView1.setImage(Imagens.getImagem(MINING));
        imageView1.setFitHeight(350);
        imageView1.setFitWidth(350);
        imageView1.setVisible(true);

        imageView2 = new ImageView();
        imageView2.setImage(Imagens.getImagem(MILITARY));
        imageView2.setFitHeight(250);
        imageView2.setFitWidth(528);
        imageView2.setVisible(false);

        shipPreview = new StackPane(imageView1, imageView2);

        mining = new Button("MINING");
        military = new Button("MILITARY");
        mining.setStyle(BOTAOHOVERCSS);
        military.setStyle(BOTAOCSS);

        HBox hb1 = new HBox();
        hb1.setAlignment(Pos.CENTER);
        hb1.setSpacing(20);
        hb1.getChildren().addAll(mining, military);

        vb1.setAlignment(Pos.CENTER);
        vb2.setAlignment(Pos.CENTER);
        vb1.setSpacing(40);
        vb2.setSpacing(40);

        vb1.getChildren().addAll(imageView, continua);
        vb2.getChildren().addAll(lb1, shipPreview, hb1);
        
        
        
        this.getChildren().addAll(vb1, vb2);
        registaListenersControlo();
        registaListenerPropiedade();
    }

    private void registaListenerPropiedade() {
        jogoObs.addPropertyChangeListener(ALTERA_ESTADO, (listener) -> {
            if (jogoObs.getState() == EnumState.SHIPSELECTION) {
                this.setVisible(true);
            } else {
                this.setVisible(false);
            }
        });
    }

    private void registaListenersControlo() {
        continua.setOnAction(eh -> {
            vb1.setVisible(false);
            vb2.setVisible(true);
        });

        mining.setOnAction(eh -> {
            jogoObs.SelectShip(2);
        });
        military.setOnAction(eh -> {
            jogoObs.SelectShip(1);
        });


        ArrayList<Button> botoes = new ArrayList<>(Arrays.asList(mining, military));
        configuraButoes(botoes);

    }

    private void configuraButoes(List<Button> botoes) {
        for (Button bt : botoes) {
            bt.setOnMouseEntered(eh -> {
                if(bt.equals(mining)){
                     imageView1.setVisible(true);
                     imageView2.setVisible(false);
                }else if(bt.equals(military)){
                     imageView1.setVisible(false);
                     imageView2.setVisible(true);
                }
                desligarBotoes(botoes);
                bt.setStyle(BOTAOHOVERCSS);
                
            });
            bt.setOnMouseExited(eh -> {
                desligarBotoes(botoes);
                mostrarFocado(botoes);
            });

            bt.setOnKeyPressed(eh -> {
                desligarBotoes(botoes);
                mostrarFocado(botoes);
            });

        }
    }

    private void desligarBotoes(List<Button> botoes) {
        for (Button bt : botoes) {
            bt.setStyle(BOTAOCSS);
        }
    }

    private void mostrarFocado(List<Button> botoes) {
        for (Button bt : botoes) {
            if (bt.isFocused()) {
                if(bt.equals(mining)){
                     imageView1.setVisible(true);
                     imageView2.setVisible(false);
                }else if(bt.equals(military)){
                     imageView1.setVisible(false);
                     imageView2.setVisible(true);
                }
                bt.setStyle(BOTAOHOVERCSS);
            } else {
                bt.setStyle(BOTAOCSS);
            }
        }
    }

}
