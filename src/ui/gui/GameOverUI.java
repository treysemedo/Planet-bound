/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import ui.gui.util.Imagens;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modeloObservavel.GameObservable;
import static ui.gui.util.Constantes.DEFEAT;
import static ui.gui.util.Constantes.GAMEOVER;
import static ui.gui.util.Constantes.VICTORY;
import static ui.gui.util.UtilUI.configuraBotoes;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import static ui.gui.util.Propriedade.ALTERA_ESTADO;

/**
 *
 * @author treys
 */
public class GameOverUI extends VBox {

    private GameObservable jogoObs;
    private final Button newGame;
    private final Button exit;
    private final ImageView imageView1;

    public GameOverUI(GameObservable jogoObs) {
        this.jogoObs = jogoObs;
        this.setMaxSize(800, 600);
        this.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");

        ImageView imageView = new ImageView();
        imageView.setFitHeight(350);
        imageView.setFitWidth(350);
        imageView.setImage(Imagens.getImagem(GAMEOVER));

        imageView1 = new ImageView();
        imageView1.setFitHeight(56);
        imageView1.setFitWidth(260);

        if (jogoObs.won()) {
            imageView1.setImage(Imagens.getImagem(VICTORY));
        } else {
            imageView1.setImage(Imagens.getImagem(DEFEAT));
        }

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(imageView, imageView1);

        newGame = new Button("NEW GAME");
        exit = new Button("EXIT");
        newGame.setStyle(BOTAOHOVERCSS);
        exit.setStyle(BOTAOCSS);

        ArrayList<Button> botoes = new ArrayList<>(Arrays.asList(newGame, exit));
        HBox hb = new HBox(newGame, exit);
        hb.setSpacing(30);
        hb.setAlignment(Pos.BOTTOM_CENTER);
        this.getChildren().add(hb);
        this.setSpacing(50);

        configuraBotoes(botoes);
        registaListenerPropiedade();
        registaListenersEvent();
    }

    private void registaListenerPropiedade() {
        jogoObs.addPropertyChangeListener(ALTERA_ESTADO, (listener) -> {

            switch (jogoObs.getState()) {
                case GAMEOVER:
                    this.setVisible(true);
                    if (jogoObs.won()) {
                        imageView1.setImage(Imagens.getImagem(VICTORY));
                    } else {
                        imageView1.setImage(Imagens.getImagem(DEFEAT));
                    }
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });
    }

    private void registaListenersEvent() {
        newGame.setOnAction(eh -> {
            jogoObs.newGame();
        });
        exit.setOnAction(eh -> {
            Platform.exit();
        });
    }

}
