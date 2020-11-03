/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import ui.gui.util.UtilUI;
import ui.gui.util.Imagens;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logica.dados.util.EnumResource;
import modeloObservavel.GameObservable;
import static ui.gui.util.Constantes.PLANET_B;
import static ui.gui.util.Constantes.PLANET_BL;
import static ui.gui.util.Constantes.PLANET_G;
import static ui.gui.util.Constantes.PLANET_R;
import static ui.gui.util.UtilUI.configuraBotoes;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import static ui.gui.util.Propriedade.ALTERA_ESTADO;

/**
 *
 * @author treys
 */
public class PlanetOrbitingUI extends BorderPane {

    private final GameObservable jogoObs;
    
    private final Button travel;
    private final Button land;
    private final Button convertR;
    

    PlanetOrbitingUI(GameObservable jogoObs) {
        this.jogoObs = jogoObs;
        this.setMaxSize(800, 600);
        this.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");


        
        land = new Button("LAND ON PLANET");
        convertR = new Button("CONVERT RESOURSE");
        travel = new Button("TRAVEL");
        
        registaListenerPropiedade();
        registarListenersEvent();
    }
    
    private void registaListenerPropiedade() {
        jogoObs.addPropertyChangeListener(ALTERA_ESTADO, (listener) -> {

            switch (jogoObs.getState()) {
                case PLANETORBITING:
                    PainelCrew painelC = new PainelCrew(jogoObs);
                    this.setCenter(desenhaPlanetBox());
                    this.setRight(painelC);
                    this.setVisible(true);
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });
    }
    
    private void registarListenersEvent() {
        land.setOnAction(eh -> {
            jogoObs.landOnPlanet();
        });
        convertR.setOnAction(eh -> {
            jogoObs.convertResources();
        });
        travel.setOnAction(eh -> {
            jogoObs.spaceTravel();
        });
    }

   

    private VBox desenhaPlanetBox() {
        PainelNave painelN = new PainelNave(jogoObs);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setFitWidth(500);

        switch (jogoObs.getPlanetType()) {
            case RED:
                imageView.setImage(Imagens.getImagem(PLANET_R));
                break;
            case GREEN:
                imageView.setImage(Imagens.getImagem(PLANET_G));
                break;
            case BLUE:
                imageView.setImage(Imagens.getImagem(PLANET_B));
                break;
            case BLACK:
               imageView.setImage(Imagens.getImagem(PLANET_BL));
                break;
        }

        StackPane paneImg = new StackPane();
        paneImg.getChildren().add(imageView);
        paneImg.setAlignment(Pos.CENTER_RIGHT);

        

        land.setStyle(BOTAOHOVERCSS);
        travel.setStyle(BOTAOCSS);
        convertR.setStyle(BOTAOCSS);
        land.setMaxWidth(Double.MAX_VALUE);
        travel.setMaxWidth(Double.MAX_VALUE);;
        convertR.setMaxWidth(Double.MAX_VALUE);
        ArrayList<Button> botoes = new ArrayList<>(Arrays.asList(land, travel, convertR));

        GridPane gp = new GridPane();
        gp.setVgap(20);
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(20);
        gp.addRow(0, land, convertR, travel);

        if (jogoObs.planetHasStation()) {

            Button visitaS = new Button("VISIT SPACE STATION");
            visitaS.setStyle(BOTAOCSS);
            visitaS.setMaxWidth(Double.MAX_VALUE);
            visitaS.setOnAction(eh -> {
                jogoObs.visitStation();
            });
            botoes.add(visitaS);
            gp.add(visitaS, 1, 1);

        }

        configuraBotoes(botoes);
        
        
        VBox res = new VBox(painelN, paneImg, infoPlanet(), gp);
        res.setSpacing(20);
        return res;

    }

    private HBox infoPlanet() {

        List<EnumResource> aux = jogoObs.getResources();
        HBox hb = new HBox();
        Label lb = new Label("RESOURCES AVALIABLE");
        lb.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        lb.setTextFill(Color.WHITE);
        hb.getChildren().add(lb);
        hb.setAlignment(Pos.CENTER);

        for (EnumResource a : aux) {
            hb.getChildren().add(desenho(UtilUI.getCorResource1(a)));
        }
        if (jogoObs.planetHasArtfact()) {
            hb.getChildren().add(desenho(Color.FUCHSIA));
        }
        hb.setSpacing(30);
        return hb;
    }

    private StackPane desenho(Color cor) {

        StackPane grafico = new StackPane();
        grafico.setPadding(new Insets(10, 10, 10, 10));
        grafico.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2, 2, 2, 2))));
        grafico.setAlignment(Pos.CENTER_RIGHT);
        grafico.setBackground(new Background(new BackgroundFill(cor, new CornerRadii(15), null)));

        return grafico;

    }

    
    
     
}
