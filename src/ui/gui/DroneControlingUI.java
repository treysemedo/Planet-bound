/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import ui.gui.util.UtilUI;
import ui.gui.util.Imagens;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logica.dados.util.EnumCell;
import modeloObservavel.GameObservable;
import static ui.gui.util.Constantes.ALIEN;
import static ui.gui.util.Constantes.DRONE;
import static ui.gui.util.Constantes.RESOURCE;
import static ui.gui.util.Constantes.RESOURCEALIEN;
import static ui.gui.util.Constantes.SHUTLE;
import static ui.gui.util.Constantes.SHUTLEALIEN;
import static ui.gui.util.Constantes.SHUTLEDRONE;
import static ui.gui.util.Constantes.TERRENO;
import static ui.gui.util.Propriedade.ALIEN_ATACK;
import static ui.gui.util.Propriedade.ALTERA_ESTADO;
import static ui.gui.util.Propriedade.ALTERA_MAPA;

/**
 *
 * @author treys
 */
public class DroneControlingUI extends VBox {

    private final GameObservable jogoObs;
    private GridPane gp;

    public DroneControlingUI(GameObservable jogoObs) {
        this.jogoObs = jogoObs;
        this.setMaxSize(850, 650);
        this.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");
        this.setAlignment(Pos.CENTER);
        this.setSpacing(40);
        this.requestFocus();
        this.setFocusTraversable(true);

        Label label = new Label("SATELLITE IMAGE");
        label.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        label.setTextFill(Color.WHITE);
        this.getChildren().add(label);
        this.getChildren().add(new HBox());

        registaListenerPropiedade();
        registarListenerEvent();
    }

    private void registaListenerPropiedade() {
        jogoObs.addPropertyChangeListener(ALTERA_ESTADO, (listener) -> {

            switch (jogoObs.getState()) {
                case DRONECONTROLING:
                    this.getChildren().set(1, desenhaTerreno());
                    this.setVisible(true);
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });

        jogoObs.addPropertyChangeListener(ALTERA_MAPA, listener -> {
            this.getChildren().set(1, desenhaTerreno());

        });

        jogoObs.addPropertyChangeListener(ALIEN_ATACK, listener -> {
            Alert dg = new Alert(Alert.AlertType.INFORMATION);
            dg.setTitle("ALIEN ATACK");
            dg.setHeaderText("DRONE UNDER ATACK!!");

            dg.setContentText("drone´s health: " + jogoObs.getDroneHp());
            dg.show();

        });
    }

    private GridPane desenhaTerreno() {
        gp = new GridPane();
        gp.setMaxSize(700, 500);
        gp.setMinSize(700, 500);
        gp.setBackground(new Background(new BackgroundImage(Imagens.getImagem(TERRENO), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(705, 505, false, false, false, false))));
        gp.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        EnumCell aux[][];
        aux = jogoObs.getInfoTerrain();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                gp.add(desenhaCelula(aux[i][j]), i, j);
            }
        }
        return gp;
    }

    private HBox desenhaCelula(EnumCell enumCell) {
        HBox hb = new HBox();
        ImageView imageView = new ImageView();
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        hb.setMaxSize((gp.getMaxWidth() / 6) - 1, gp.getMaxHeight() / 6 - 0.5);
        hb.setMinSize(gp.getMinWidth() / 6 - 1, gp.getMinHeight() / 6 - 0.5);
        hb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(0.5))));

        switch (enumCell) {
            case SHUTLEDRONE:
                imageView.setImage(Imagens.getImagem(SHUTLEDRONE));
                imageView.setFitHeight(80);
                imageView.setFitWidth(90);
                break;
            case SHUTLE:
                imageView.setFitHeight(75);
                imageView.setFitWidth(75);
                imageView.setImage(Imagens.getImagem(SHUTLE));

                break;
            case ALIEN:
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                hb.setBackground(new Background(new BackgroundFill(UtilUI.getCorAlien(jogoObs.getAlienType()), new CornerRadii(60), new Insets(18, 35, 20, 36))));
                imageView.setImage(Imagens.getImagem(ALIEN));
                break;
            case DRONE:
                imageView.setImage(Imagens.getImagem(DRONE));
                break;
            case RESOURCE:
                imageView.setImage(Imagens.getImagem(RESOURCE));
                imageView.setFitHeight(35);
                imageView.setFitWidth(35);

                if (jogoObs.terrainHasArtfact()) {
                    hb.setBackground(new Background(new BackgroundFill(Color.FUCHSIA, new CornerRadii(100), new Insets(25, 40, 25, 40))));
                } else {
                    hb.setBackground(new Background(new BackgroundFill(UtilUI.getCorResource2(jogoObs.terrainResource()), new CornerRadii(100), new Insets(25, 40, 25, 40))));
                }

                break;
            case RESOURCEDRONE:
                imageView.setImage(Imagens.getImagem(DRONE));
                 if (jogoObs.terrainHasArtfact()) {
                    hb.setBackground(new Background(new BackgroundFill(Color.FUCHSIA, new CornerRadii(100), new Insets(25, 40, 25, 40))));
                } else {
                    hb.setBackground(new Background(new BackgroundFill(UtilUI.getCorResource2(jogoObs.terrainResource()), new CornerRadii(100), new Insets(25, 40, 25, 40))));
                }
                break;

            case SHUTLEALIEN:
                imageView.setImage(Imagens.getImagem(SHUTLEALIEN));
                imageView.setFitHeight(80);
                imageView.setFitWidth(100);
                break;
            case RESOURCEALIEN:
                imageView.setImage(Imagens.getImagem(RESOURCEALIEN));
                break;

        }
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(imageView);

        return hb;
    }

    private void registarListenerEvent() {

        this.setOnKeyPressed(eh -> {
            switch (eh.getCode()) {
                case UP:
                    jogoObs.DroneMove(-1, 0);
                    break;
                case DOWN:
                    jogoObs.DroneMove(1, 0);
                    break;
                case LEFT:
                    jogoObs.DroneMove(0, -1);
                    break;
                case RIGHT:
                    jogoObs.DroneMove(0, 1);
                    break;
            }
        });
    }

}
