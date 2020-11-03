/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloObservavel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import logica.Game;
import logica.dados.planets.EnumPlaneta;
import logica.dados.util.EnumAlien;
import logica.dados.util.EnumCell;
import logica.dados.util.EnumEvent;
import logica.dados.util.EnumMsg;
import logica.dados.util.EnumOfficer;
import logica.dados.util.EnumResource;
import logica.estados.util.EnumState;
import static ui.gui.util.Propriedade.ALIEN_ATACK;
import static ui.gui.util.Propriedade.ALTERA_AMMO;
import static ui.gui.util.Propriedade.ALTERA_CREW;
import static ui.gui.util.Propriedade.ALTERA_ESTADO;
import static ui.gui.util.Propriedade.ALTERA_FUEL;
import static ui.gui.util.Propriedade.ALTERA_MAPA;
import static ui.gui.util.Propriedade.ALTERA_RESOURCE;
import static ui.gui.util.Propriedade.ALTERA_SHIELD;
import static ui.gui.util.Propriedade.EVENTO_ALEATORIO;

/**
 *
 * @author treys
 */
public class GameObservable {

    private Game jogo;
    private final PropertyChangeSupport propertyChangeSuport;

    public GameObservable(Game jogo) {
        this.jogo = jogo;
        propertyChangeSuport = new PropertyChangeSupport(jogo);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSuport.addPropertyChangeListener(propertyName, listener);
    }

    //state changes
    public void SelectShip(int n) {
        jogo.SelectShip(n);
        propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
    }

    public void spaceTravel() {
        jogo.spaceTravel();
        propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
    }

    public void convertResources() {
        jogo.convertResources();
        propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
    }

    public void goBack() {
        jogo.goBack();
        propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
    }

    public void visitStation() {
        jogo.visitStation();
        propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
    }

    public void undock() {
        jogo.undock();
        propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
    }

    public void landOnPlanet() {
        jogo.landOnPlanet();
        propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
    }

    public void DroneMove(int i, int j) {
        jogo.DroneMove(i, j);
        propertyChangeSuport.firePropertyChange(ALTERA_MAPA, null, null);

        if (jogo.getMsg() == EnumMsg.ATACK) {
            propertyChangeSuport.firePropertyChange(ALIEN_ATACK, null, null);
            if (jogo.getDroneHp() <= 0) {
                propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
            }
        } else if (jogo.getMsg() == EnumMsg.BACKTOSHIP) {
            propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
        }

    }

    public void randomEvent() {
        jogo.randomEvent();
        propertyChangeSuport.firePropertyChange(EVENTO_ALEATORIO, null, null);
        propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
    }

    public void selectEvent(int n) {
        jogo.selectEvent(n);
        propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
    }

    public void newGame() {
        jogo.newGame();
        propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
    }

    //operations
    public void hireCrewMember() {
        jogo.hireCrewMember();
        if (jogo.getMsg() == EnumMsg.SUCCESS) {
            propertyChangeSuport.firePropertyChange(ALTERA_CREW, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
    }

    public void produceShield() {
        jogo.produceShield();
        if (jogo.getMsg() == EnumMsg.SUCCESS) {
            propertyChangeSuport.firePropertyChange(ALTERA_SHIELD, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
    }

    public void produceAmmo() {
        jogo.produceAmmo();
        if (jogo.getMsg() == EnumMsg.SUCCESS) {
            propertyChangeSuport.firePropertyChange(ALTERA_AMMO, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
    }

    public void produceFuel() {
        jogo.produceFuel();
        if (jogo.getMsg() == EnumMsg.SUCCESS) {
            propertyChangeSuport.firePropertyChange(ALTERA_FUEL, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
    }

    public void upgradeCargo() {
        jogo.upgradeCargo();
        if (jogo.getMsg() == (EnumMsg.SUCCESS)) {
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
    }

    public void changeForResource(EnumResource a, EnumResource b) {
        jogo.changeForResource(a, b);
        if (jogo.getMsg() == (EnumMsg.SUCCESS)) {
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }

    }

    public void upgradeWeapon() {
        jogo.upgradeWeapon();
        if (jogo.getMsg() == (EnumMsg.SUCCESS)) {
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_AMMO, null, null);
        }
    }

    public void setFullShield() {
        jogo.setFullShield();
        if (jogo.getMsg() == EnumMsg.SUCCESS) {
            propertyChangeSuport.firePropertyChange(ALTERA_SHIELD, null, null);
            propertyChangeSuport.firePropertyChange(ALTERA_RESOURCE, null, null);
        }
    }

    //string with infos
    public String getInfoShip() {
        return jogo.getInfoShip();
    }

    public String getInfoPlanet() {
        return jogo.getInfoPlanet();
    }

    public String getInfoResources() {
        return jogo.getInfoResources();
    }

    public EnumCell[][] getInfoTerrain() {
        return jogo.getInfoTerrain();
    }

    public List<EnumOfficer> getCrew() {
        return jogo.getCrew();
    }

    //interface options
    public boolean planetHasStation() {
        return jogo.planetHasStation();
    }

    public boolean terrainHasArtfact() {
        return jogo.terrainHasArtfact();
    }

    public EnumResource terrainResource() {
        return jogo.terrainResource();
    }

    public String listCrew() {
        return jogo.listCrew();

    }

    //get infoEnums
    public EnumState getState() {
        return jogo.getState();
    }

    public EnumEvent getEvent() {
        return jogo.getEvent();
    }

    public int[] getFuel() {
        return jogo.getFuel();
    }

    public int[] geShield() {
        return jogo.geShield();
    }

    public int[] getWeapon() {
        return jogo.getWeapon();
    }

    public int getResource(EnumResource r) {
        return jogo.getResource(r);
    }

    public int getArtfact() {
        return jogo.getArtfact();
    }

    public EnumPlaneta getPlanetType() {
        return jogo.getPlanetType();
    }

    public List<EnumResource> getResources() {
        return jogo.getResources();
    }

    public boolean planetHasArtfact() {
        return jogo.planetHasArtfact();
    }

    public int getDroneHp() {
        return jogo.getDroneHp();
    }

    public EnumAlien getAlienType() {
        return jogo.getAlienType();
    }

    public EnumMsg getMsg() {
        return jogo.getMsg();
    }

    public boolean lostCrew() {
        return jogo.lostCrew();
    }

    public boolean won() {
        return jogo.won();
    }

    public boolean saveGame(String nomeFich) {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(nomeFich));
            out.writeObject(jogo);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    return false;
                }
            }

        }
    }

    public boolean loadGame(File inF) {
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(new FileInputStream(inF));
            jogo = (Game) in.readObject();
            propertyChangeSuport.firePropertyChange(ALTERA_ESTADO, null, null);
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
    }

}
