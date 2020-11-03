/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author treys
 */
public class Constantes {


    public static final String TERRENO = "terreno";
    public static final String PATH_TERRENO = "imagens/terrain.png";
    public static final String DRONE = "drone";
    public static final String PATH_DRONE = "imagens/drone.png";
    public static final String ALIEN = "alien";
    public static final String PATH_ALIEN = "imagens/alien.png";
    public static final String SHUTLEDRONE = "shutledrone";
    public static final String PATH_SHUTEDRONE = "imagens/shutledrone.png";
    public static final String SHUTLE = "shutle";
    public static final String PATH_SHUTLE = "imagens/shutle.png";
    public static final String RESOURCE = "resource";
    public static final String PATH_RESOURCE = "imagens/resource.png";
    public static final String SHUTLEALIEN = "shutleAlien";
    public static final String PATH_SHUTLEALIEN = "imagens/shutleAlien.png";
    public static final String RESOURCEALIEN = "resourceAlien";
    public static final String PATH_RESOURCEALIEN = "imagens/resourceAlien.png";
    public static final String GAMEOVER = "gameOver";
    public static final String PATH_GAMEOVER = "imagens/gameOver.png";
    public static final String VICTORY = "victory";
    public static final String PATH_VICTORY = "imagens/victory.png";
    public static final String DEFEAT = "defeat";
    public static final String PATH_DEFEAT = "imagens/defeat.png";
    public static final String PLANET_B = "planetB";
    public static final String PATH_PLANET_B = "imagens/planetB.png";
    public static final String PLANET_R = "planetR";
    public static final String PATH_PLANET_R = "imagens/planetR.png";
    public static final String PLANET_BL = "planetBL";
    public static final String PATH_PLANET_BL = "imagens/planetBL.png";
    public static final String PLANET_G = "planetG";
    public static final String PATH_PLANET_G = "imagens/planetG.png";
    public static final String CONVERSOR = "conversor";
    public static final String PATH_CONVERSOR= "imagens/conversion.png";
    public static final String LOGO = "logo";
    public static final String PATH_LOGO= "imagens/logo.png";
    public static final String MILITARY = "military";
    public static final String PATH_MILITARY= "imagens/military.png";
    public static final String MINING = "mining";
    public static final String PATH_MINING= "imagens/mining.png";
     public static final String SPACE_STATION = "spacestation";
    public static final String PATH_SPACE_STATION= "imagens/station.png";
    public static final String BACKGROUND = "background";
    public static final String PATH_BACKGROUND= "imagens/espaco.jpg";
     public static final String OPTION = "options";
    public static final String PATH_OPTION= "imagens/options.png";
    public final static String INFO_CREWDEATH="One of your crew members was irresponsible and played with a space creature, he died imidiatly after touching it ";
    public final static String INFO_SALVAGESHIP="You and your crew have encountered a ghost spaceship, after safely explore it you´ve found some resources that can still be used ";
    public final static String INFO_CARGOLOSS="An asteroid field, luckly your captain has guided your crew out of there, some damage were made to the casquet and you lost some resource";
    public final static String INFO_FUELLOSS="A dangerous camp force is ahead, to escape it your ship needs max potency,extra fuel will needed";
    public final static String INFO_NOEVENT="Just a regular exploration day thru space, nothing new today";
    public final static String INFO_CREWRESCUE="You find a ship in destress with a lone crew member. your crew has new member, make her feel welcome";
    public static ArrayList<String> infoEvents=new ArrayList<>(Arrays.asList(INFO_CREWDEATH ,INFO_SALVAGESHIP, INFO_CARGOLOSS,INFO_FUELLOSS, INFO_NOEVENT, INFO_CREWRESCUE));
    public static String BOTAOCSS="-fx-background-color: " +
"        #090a0c" +
"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%)," +
"        linear-gradient(#20262b, #191d22)," +
"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
"    -fx-background-radius: 5,4,3,5;" +
"    -fx-background-insets: 0,1,2,0;" +
"    -fx-text-fill: white;" +
"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" +
"    -fx-font-family: \"Arial\";" +
"    -fx-text-fill: linear-gradient(white, #d0d0d0);" +
"    -fx-font-size: 12px;" +
"    -fx-padding: 10 20 10 20;";
 
            
      public static String BOTAOHOVERCSS="-fx-background-color: " +
"        #090a0c" +
"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%)," +
"        linear-gradient(#20262b, #191d22)," +
"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" +
"    -fx-background-radius: 5,4,3,5;" +
"    -fx-background-insets: 0,1,2,0;" +
"    -fx-text-fill: white;" +
"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,100,10) , 5, 0.0 , 2 , 3 );" +
"    -fx-font-family: \"Arial\";" +
"    -fx-text-fill: linear-gradient(white, #d0d0d0);" +
"    -fx-font-size: 12px;" +
"    -fx-padding: 10 20 10 20;";
    

}
