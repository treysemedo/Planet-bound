/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui;

import java.io.File;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import logica.dados.util.EnumOfficer;
import logica.dados.util.EnumResource;
import modeloObservavel.GameObservable;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.OPTION;
import ui.gui.util.Imagens;
import static ui.gui.util.Propriedade.ALTERA_CREW;
import static ui.gui.util.Propriedade.ALTERA_RESOURCE;

/**
 *
 * @author treys
 */
class PainelCrew extends VBox {

    private GameObservable jogoObs;
    private Label[] label;
    private HBox red;
    private HBox green;
    private HBox black;
    private HBox blue;
    private HBox artifact;
    private Label titulo1;
    public VBox info;
    public VBox options;
    public VBox resourceBox;
    public VBox crewBox;

    public PainelCrew(GameObservable jogoObs) {
        this.jogoObs = jogoObs;

        this.setPadding(new Insets(20, 40, 20, 20));
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 0;");
        this.setSpacing(20);

        ImageView iv = new ImageView();
        iv.setImage(Imagens.getImagem(OPTION));
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        iv.setOnMouseClicked(eh -> {
            options.setVisible(true);
            info.setVisible(false);
        });

        info = new VBox(desenhaCrewBox(), desenhaResourceBox());
        options = desenhaOpcoes();
        StackPane principal = new StackPane(info, options);

        this.getChildren().addAll(iv, principal);
        registaListenerPropiedade();
    }

    private void registaListenerPropiedade() {

        jogoObs.addPropertyChangeListener(ALTERA_CREW, listener -> {
            info.getChildren().set(0, desenhaCrewBox());
        });

        jogoObs.addPropertyChangeListener(ALTERA_RESOURCE, listener -> {
            info.getChildren().set(1, desenhaResourceBox());
        });

    }

    private VBox desenhaOpcoes() {

        Button bt1 = new Button("save");
        Button bt2 = new Button("load");
        Button bt4 = new Button("exit");

        bt1.setMinSize(100, 10);
        bt2.setMinSize(100, 10);
        bt4.setMinSize(100, 10);
        bt1.setStyle(BOTAOCSS);
        bt2.setStyle(BOTAOCSS);
        bt4.setStyle(BOTAOCSS);
        
        
        bt1.setOnAction(eh -> {

            TextInputDialog dialog = new TextInputDialog("ficheiro");
            dialog.setTitle("save");
            dialog.setHeaderText("Save your game");
            dialog.setContentText("Please enter the File name:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Save");
                alert.setHeaderText(null);
                if (jogoObs.saveGame("./saved games/" + result.get())) {
                    alert.setContentText("Game successfully saved");
                } else {
                    alert.setContentText("Game failed saving");
                }
                alert.showAndWait();

            }
        });

        bt2.setOnAction(eh -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./saved Games"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                
                 Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Save");
                alert.setHeaderText(null);
                if (jogoObs.loadGame(selectedFile)) {
                    alert.setContentText("Game successfully uploaded");
                } else {
                    alert.setContentText("Game failed uploading");
                }
                alert.showAndWait();
                
            }

        });
        
        bt4.setOnAction(eh->{
            Platform.exit();
        });
        
        VBox vb3 = new VBox(bt1, bt2, bt4);
        vb3.setVisible(false);
        vb3.setSpacing(20);
        vb3.setAlignment(Pos.CENTER);
        vb3.setOnMouseExited(eh -> {
            vb3.setVisible(false);
            info.setVisible(true);
        });

        return vb3;
    }

    private VBox desenhaCrewBox() {

        List<EnumOfficer> aux = jogoObs.getCrew();
        crewBox = new VBox();

        label = new Label[aux.size()];
        Label titulo = new Label("CREW OFICCERS");
        titulo.setPadding(new Insets(0, 0, 20, 0));
        titulo.setFont(Font.font("verdana", 15));
        titulo.setTextFill(Color.DARKORANGE);
        crewBox.getChildren().add(titulo);

        for (int i = 0; i < aux.size(); i++) {
            label[i] = new Label("" + aux.get(i));
            label[i].setTextFill(Color.WHITE);
            crewBox.getChildren().add(label[i]);
        }
        return crewBox;
    }

    private VBox desenhaResourceBox() {

        titulo1 = new Label("RESOURCES");
        titulo1.setFont(Font.font("verdana", 15));
        titulo1.setTextFill(Color.DARKORANGE);

        red = desenho("RED", Color.RED, jogoObs.getResource(EnumResource.RED));
        green = desenho("GREEN", Color.DARKGREEN, jogoObs.getResource(EnumResource.GREEN));
        blue = desenho("BLUE", Color.BLUE, jogoObs.getResource(EnumResource.BLUE));
        black = desenho("BLACK", Color.BLACK, jogoObs.getResource(EnumResource.BLACK));
        artifact = desenho("ARTIFACT", Color.FUCHSIA, jogoObs.getArtfact());
        resourceBox = new VBox(titulo1, red, green, blue, black, artifact);
        resourceBox.setSpacing(10);

        return resourceBox;

    }

    private HBox desenho(String titulo, Color cor, int num) {
        Label lf = new Label(titulo);

        lf.setFont(Font.font("verdana", FontWeight.BOLD, 12));
        lf.setTextFill(Color.WHITE);

        Text text = new Text(Integer.toString(num));
        text.setFill(Color.WHITE);
        text.setFont(Font.font("verdana", FontWeight.BOLD, 12));

        StackPane grafico = new StackPane(text);
        grafico.setPadding(new Insets(5, 10, 5, 10));
        grafico.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2, 2, 2, 2))));
        grafico.setAlignment(Pos.CENTER_RIGHT);
        grafico.setBackground(new Background(new BackgroundFill(cor, new CornerRadii(15), null)));

        HBox resultado = new HBox(lf, grafico);
        resultado.setSpacing(10);
        resultado.setAlignment(Pos.CENTER_RIGHT);
        return resultado;

    }

}
