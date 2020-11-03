/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.util;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import static ui.gui.util.Constantes.ALIEN;
import static ui.gui.util.Constantes.DEFEAT;
import static ui.gui.util.Constantes.DRONE;
import static ui.gui.util.Constantes.GAMEOVER;
import static ui.gui.util.Constantes.PATH_ALIEN;
import ui.gui.recurso.RecursoLoader;
import static ui.gui.util.Constantes.BACKGROUND;
import static ui.gui.util.Constantes.CONVERSOR;
import static ui.gui.util.Constantes.LOGO;
import static ui.gui.util.Constantes.MILITARY;
import static ui.gui.util.Constantes.MINING;
import static ui.gui.util.Constantes.OPTION;
import static ui.gui.util.Constantes.PATH_BACKGROUND;
import static ui.gui.util.Constantes.PATH_CONVERSOR;
import static ui.gui.util.Constantes.PATH_DEFEAT;
import static ui.gui.util.Constantes.PATH_DRONE;
import static ui.gui.util.Constantes.PATH_GAMEOVER;
import static ui.gui.util.Constantes.PATH_LOGO;
import static ui.gui.util.Constantes.PATH_MILITARY;
import static ui.gui.util.Constantes.PATH_MINING;
import static ui.gui.util.Constantes.PATH_OPTION;
import static ui.gui.util.Constantes.PATH_PLANET_B;
import static ui.gui.util.Constantes.PATH_PLANET_BL;
import static ui.gui.util.Constantes.PATH_PLANET_G;
import static ui.gui.util.Constantes.PATH_PLANET_R;
import static ui.gui.util.Constantes.PATH_RESOURCE;
import static ui.gui.util.Constantes.PATH_RESOURCEALIEN;
import static ui.gui.util.Constantes.PATH_SHUTEDRONE;
import static ui.gui.util.Constantes.PATH_SHUTLE;
import static ui.gui.util.Constantes.PATH_SHUTLEALIEN;
import static ui.gui.util.Constantes.PATH_SPACE_STATION;
import static ui.gui.util.Constantes.PATH_TERRENO;
import static ui.gui.util.Constantes.PATH_VICTORY;
import static ui.gui.util.Constantes.PLANET_B;
import static ui.gui.util.Constantes.PLANET_BL;
import static ui.gui.util.Constantes.PLANET_G;
import static ui.gui.util.Constantes.PLANET_R;
import static ui.gui.util.Constantes.RESOURCE;
import static ui.gui.util.Constantes.RESOURCEALIEN;
import static ui.gui.util.Constantes.SHUTLE;
import static ui.gui.util.Constantes.SHUTLEALIEN;
import static ui.gui.util.Constantes.SHUTLEDRONE;
import static ui.gui.util.Constantes.SPACE_STATION;
import static ui.gui.util.Constantes.TERRENO;
import static ui.gui.util.Constantes.VICTORY;

/**
 *
 * @author treys
 */
public abstract class Imagens {

    private static final Map<String, Image> IMAGENS = new HashMap<>();

    static {
        IMAGENS.put(TERRENO, new Image(RecursoLoader.getMyResourceFile(PATH_TERRENO)));
        IMAGENS.put(DRONE, new Image(RecursoLoader.getMyResourceFile(PATH_DRONE)));
        IMAGENS.put(ALIEN, new Image(RecursoLoader.getMyResourceFile(PATH_ALIEN)));
        IMAGENS.put(SHUTLEDRONE, new Image(RecursoLoader.getMyResourceFile(PATH_SHUTEDRONE)));
        IMAGENS.put(SHUTLE, new Image(RecursoLoader.getMyResourceFile(PATH_SHUTLE)));
        IMAGENS.put(RESOURCE, new Image(RecursoLoader.getMyResourceFile(PATH_RESOURCE)));
        IMAGENS.put(SHUTLEALIEN, new Image(RecursoLoader.getMyResourceFile(PATH_SHUTLEALIEN)));
        IMAGENS.put(RESOURCEALIEN, new Image(RecursoLoader.getMyResourceFile(PATH_RESOURCEALIEN)));
        IMAGENS.put(GAMEOVER, new Image(RecursoLoader.getMyResourceFile(PATH_GAMEOVER)));
        IMAGENS.put(VICTORY, new Image(RecursoLoader.getMyResourceFile(PATH_VICTORY)));
        IMAGENS.put(DEFEAT, new Image(RecursoLoader.getMyResourceFile(PATH_DEFEAT)));
        IMAGENS.put(PLANET_B, new Image(RecursoLoader.getMyResourceFile(PATH_PLANET_B)));
        IMAGENS.put(PLANET_R, new Image(RecursoLoader.getMyResourceFile(PATH_PLANET_R)));
        IMAGENS.put(PLANET_BL, new Image(RecursoLoader.getMyResourceFile(PATH_PLANET_BL)));
        IMAGENS.put(PLANET_G, new Image(RecursoLoader.getMyResourceFile(PATH_PLANET_G)));
        IMAGENS.put(CONVERSOR, new Image(RecursoLoader.getMyResourceFile(PATH_CONVERSOR)));
        IMAGENS.put(LOGO, new Image(RecursoLoader.getMyResourceFile(PATH_LOGO)));
        IMAGENS.put(MILITARY, new Image(RecursoLoader.getMyResourceFile(PATH_MILITARY)));
        IMAGENS.put(MINING, new Image(RecursoLoader.getMyResourceFile(PATH_MINING)));
        IMAGENS.put(SPACE_STATION, new Image(RecursoLoader.getMyResourceFile(PATH_SPACE_STATION)));
        IMAGENS.put(BACKGROUND, new Image(RecursoLoader.getMyResourceFile(PATH_BACKGROUND)));
        IMAGENS.put(OPTION, new Image(RecursoLoader.getMyResourceFile(PATH_OPTION)));
        
       }    

    public static Image getImagem(String nome) {
        return IMAGENS.get(nome);
    }

}
