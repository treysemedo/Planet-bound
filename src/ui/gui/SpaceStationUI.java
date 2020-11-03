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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logica.dados.util.EnumResource;
import modeloObservavel.GameObservable;
import static ui.gui.util.UtilUI.configuraBotoes;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;
import static ui.gui.util.Constantes.SPACE_STATION;
import ui.gui.util.Imagens;
import static ui.gui.util.Propriedade.ALTERA_ESTADO;

/**
 *
 * @author treys
 */
public class SpaceStationUI extends BorderPane {

    private GameObservable jogoObs;
    private PainelCrew painelC;
    private GridPane gp;
    private VBox vb1;
    private Button cargo, weapon,resource,  crew, ship, back;
    private Button convert;
    private int n1, n2;
    

    public SpaceStationUI(GameObservable jogoObs) {
        this.jogoObs = jogoObs;
        this.setMaxSize(800, 600);
        this.setStyle("-fx-background-color: rgba(43, 52, 64, 0.85); -fx-background-radius: 10;");
        registaListenerPropiedade();

    }

    private void registaListenerPropiedade() {
        jogoObs.addPropertyChangeListener(ALTERA_ESTADO, (listener) -> {
           
            switch (jogoObs.getState()) {
                case SPACESTATION:
                   
                    painelC = new PainelCrew(jogoObs);
                    this.setRight(painelC);
                    this.setCenter(operacoesUI());
                    this.setVisible(true);
                    registaListenerEvent();
                    break;
                default:
                    this.setVisible(false);
                    break;
            }
        });
    }

    private void registaListenerEvent() {
        cargo.setOnAction(eh -> {
            jogoObs.upgradeCargo();
        });
        weapon.setOnAction(eh -> {
            jogoObs.upgradeWeapon();
        });
        resource.setOnAction(eh -> {
            gp.setVisible(false);
            vb1.setVisible(true);
        });
        convert.setOnAction(eh -> {
            jogoObs.changeForResource(EnumResource.valueOf(n1), EnumResource.valueOf(n2));
            n1=n2=1;
            gp.setVisible(true);
            vb1.setVisible(false);
        });
        crew.setOnAction(eh->{
            jogoObs.hireCrewMember();
            
        });
        ship.setOnAction(eh->{
            jogoObs.setFullShield();    
        });
        
        back.setOnAction(eh->{
            jogoObs.undock();
        });
        
        

    }

    private VBox operacoesUI() {
        PainelNave painelN = new PainelNave(jogoObs);
        
        ImageView imageView = new ImageView();
        imageView.setImage(Imagens.getImagem(SPACE_STATION));
        imageView.setFitHeight(200);
        imageView.setFitWidth(500);
        StackPane paneImg = new StackPane(imageView);
        paneImg.setAlignment(Pos.CENTER);

        cargo = new Button("Upgrade Cargo Holder");
        weapon = new Button("Upgrade weapon system");
        resource = new Button("Exchange  Resources");
        crew = new Button("Hire a crew Member");
        ship = new Button("Fix Ship Armor");
        back = new Button("Go Back");

        ToggleGroup takeGroup = new ToggleGroup();
        ToggleGroup putGroup = new ToggleGroup();
        RadioButton[] botao = new RadioButton[8];
        GridPane gp1 = new GridPane();

        gp1.setVgap(40);
        gp1.setHgap(40);
        gp1.setAlignment(Pos.CENTER);
        botao[0] = new RadioButton("Red");
        botao[1] = new RadioButton("Green");
        botao[2] = new RadioButton("Black");
        botao[3] = new RadioButton("Blue");
        botao[4] = new RadioButton("Red");
        botao[5] = new RadioButton("Green");
        botao[6] = new RadioButton("Black");
        botao[7] = new RadioButton("Blue");
        Label lose = new Label("RESOURCE TO LOSE:");
        Label gain = new Label("RESOURCE TO GAIN:");
        lose.setTextFill(Color.DARKORANGE);
        gain.setTextFill(Color.DARKORANGE);
        lose.setFont(Font.font(("verdana"), 15));
        gain.setFont(Font.font(("verdana"), 15));
        gp1.addRow(0, lose);
        gp1.addRow(1, gain);
        
        for (int i = 0; i < 4; i++) {
            botao[i].setToggleGroup(takeGroup);
            botao[i].setTextFill(Color.WHITE);
            botao[i].setFont(Font.font(("verdana"), 13));
            final int aux=i;
            botao[i].setOnAction(eh->{
                n1=aux+1;
            });
            gp1.addRow(0, botao[i]);
        }
        
        for (int i = 4; i < 8; i++) {
            botao[i].setToggleGroup(putGroup);
            botao[i].setTextFill(Color.WHITE);
            botao[i].setFont(Font.font(("verdana"), 13));
             final int aux=i;
            botao[i].setOnAction(eh->{
                n2=aux-3;
            });
            gp1.addRow(1, botao[i]);
        }
        n1=1;
        n2=1;
        botao[0].setSelected(true);
        botao[4].setSelected(true);

        convert = new Button("Convert");
        convert.setStyle(BOTAOHOVERCSS);
        configuraBotoes(Arrays.asList(convert));
        vb1 = new VBox(gp1, convert);
        vb1.setVisible(false);
        vb1.setAlignment(Pos.CENTER);
        vb1.setSpacing(50);

        ArrayList<Button> botoes = new ArrayList<>(Arrays.asList(cargo, weapon, resource, crew, ship, back));
        for (Button bt : botoes) {
            bt.setStyle(BOTAOCSS);
            bt.setMaxWidth(Double.MAX_VALUE);
        }
        cargo.setStyle(BOTAOHOVERCSS);

        gp = new GridPane();
        gp.addRow(0, cargo, weapon, resource);
        gp.addRow(1, crew, ship, back);
        gp.setVisible(true);
        gp.setVgap(20);
        gp.setHgap(20);
        gp.setAlignment(Pos.CENTER);
        configuraBotoes(botoes);

        StackPane botoesPane = new StackPane(gp, vb1);

        VBox vbF = new VBox();
        vbF.setSpacing(70);
        vbF.getChildren().addAll(painelN, paneImg, botoesPane);
        return vbF;
    }
}
