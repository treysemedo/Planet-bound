/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import logica.dados.util.EnumAlien;
import logica.dados.util.EnumResource;
import static ui.gui.util.Constantes.BOTAOCSS;
import static ui.gui.util.Constantes.BOTAOHOVERCSS;

/**
 *
 * @author treys
 */
public abstract class UtilUI {
    
    static final Map<EnumResource, Color> CORES_R1 = new HashMap<>();
    static final Map<EnumResource, Color> CORES_R2 = new HashMap<>();
    static final Map<EnumAlien, Color> CORES_A = new HashMap<>();
    static{
        CORES_R1.put(EnumResource.RED, Color.RED);
        CORES_R1.put(EnumResource.BLUE, Color.BLUE);
        CORES_R1.put(EnumResource.GREEN, Color.GREEN);
        CORES_R1.put(EnumResource.BLACK, Color.BLACK);
        
        
        CORES_R2.put(EnumResource.RED, Color.TOMATO);
        CORES_R2.put(EnumResource.BLUE, Color.DARKCYAN);
        CORES_R2.put(EnumResource.GREEN, Color.OLIVEDRAB);
        CORES_R2.put(EnumResource.BLACK, Color.WHITE);
        
        CORES_A.put(EnumAlien.RED, Color.TOMATO);
        CORES_A.put(EnumAlien.BLUE, Color.DARKCYAN);
        CORES_A.put(EnumAlien.GREEN, Color.OLIVEDRAB);
        CORES_A.put(EnumAlien.BLACK, Color.WHITE);
        
    }
    
    
    
    public static Color getCorResource1(EnumResource tipo){
        return CORES_R1.get(tipo);
    }
    
    public static Color getCorResource2(EnumResource tipo){
        return CORES_R2.get(tipo);
    }
    
    public static Color getCorAlien(EnumAlien tipo){
        return CORES_A.get(tipo);
    }
    
    public static void configuraBotoes(List<Button> botoes) {
        for (Button bt : botoes) {
            bt.setOnMouseEntered(eh -> {
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

    public static void desligarBotoes(List<Button> botoes) {
        for (Button bt : botoes) {
            bt.setStyle(BOTAOCSS);
        }
    }

    public static void mostrarFocado(List<Button> botoes) {
        for (Button bt : botoes) {
            if (bt.isFocused()) {
                bt.setStyle(BOTAOHOVERCSS);
            } else {
                bt.setStyle(BOTAOCSS);
            }
        }
    }
    
    
}
