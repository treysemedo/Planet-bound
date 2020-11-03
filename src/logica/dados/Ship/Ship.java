/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.Ship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import logica.dados.util.EnumOfficer;
import logica.dados.util.EnumResource;
import logica.dados.util.EnumSector;
import logica.dados.util.Utility;

/**
 *
 * @author treys
 */
public abstract class Ship implements Serializable{
    private static final long serialVersionUID = 4L;

    private int fuel, maxFuel, shield, maxShield, weapon, maxWeapon;
    private int numArtifats, crewNum;
    private List<EnumOfficer> crew;
    private Cargo cargoHolder;
    private EnumSector currentSector;
    private Utility util;

    public Ship(int fuel, int shield, int weapon, int cargo) {
        crew = new ArrayList<>(Arrays.asList(EnumOfficer.CAPTAIN,EnumOfficer.CARGOHOLD,EnumOfficer.LANDINGPARTY, EnumOfficer.NAVIGATION, EnumOfficer.SHIELDS, EnumOfficer.WEAPONS));
        crewNum = crew.size();
        numArtifats = 0;
        currentSector = EnumSector.FEDERATION;
        util = new Utility();
        setFuel(fuel);
        setShield(shield);
        setWeapon(weapon);
        setCargo(cargo);
    }

    //adcionar elemonto tripulacao
    public boolean hireCrewMember() {
        List<EnumResource> aux = cargoHolder.getResources();
        if (aux.size() == 4) {
            if (addOfficer()) {
                for (EnumResource r : aux) {
                    cargoHolder.loseResource(r, 1);
                }
                return true;
            }
        }
        return false;

    }

    public boolean addOfficer() {
        if (crew.size() < 6) {
            if (EnumOfficer.valueOf(crew.size() + 1) != null) {
                crew.add(EnumOfficer.valueOf(crew.size() + 1));
                return true;
            }
        }
        return false;
    }

    //remover um elemnto de tripulacao
    public void removeOfficer() {
        if (crew.size() > 0) {
            crew.remove(crew.size() - 1);
        }
    }

//verificar se determinado officer está vivo
    public boolean existsOfficer(EnumOfficer of) {
        return crew.contains(of);
    }

    // para definir atributos diferentes para tipo de nave diferente
    public final void setShield(int capacity) {
        shield = capacity;
        maxShield = capacity;
    }

    public final void setWeapon(int firePower) {
        weapon = firePower;
        maxWeapon = firePower;

    }

    public final void setFuel(int capacity) {
        fuel = capacity;
        maxFuel = capacity;
    }

    public final void setCargo(int lvl) {
        cargoHolder = new Cargo(lvl);
    }

    //Acrescentar recursos e retirar no suporte de carga
    public void addResource(EnumResource r, int unit) {
        cargoHolder.saveResource(r, unit);
    }

    public void addRandomResource() {
        addResource(EnumResource.valueOf(util.randomInt(1, 4)), util.randomInt(1, 6));
    }

    public void loseRandomResource() {
        List<EnumResource> aux = getResources();
        if (!aux.isEmpty()) {
            EnumResource type = aux.get(util.randomInt(0, aux.size() - 1));
            loseResource(type, util.randomInt(1, 3));
        }

    }

    public void loseResource(EnumResource r, int unit) {
        cargoHolder.loseResource(r, unit);
    }

    public void loseFuel() {
        fuel--;
    }

   

    // gasta os recursos necessarios para mover
    private void travelregularly() {
        fuel--;
    }

    private void travelThruBlackH() {
        fuel -= 3;
        shield -= 2;
    }

    // ferramentas de observação estado combustivel
    public boolean reserveFuel() {
        return fuel <= 3;
    }

    public boolean emptyFuelCells() {
        return fuel <= 0;
    }

    //ferramentas para converter recursos
    public boolean produceFuel() {
        if (crew.contains(EnumOfficer.CARGOHOLD) && fuel < maxFuel) {

            if (cargoHolder.produceFuel()) {
                fuel++;
                return true;
            }
        }

        return false;
    }

    public boolean produceShield() {
        if (crew.contains(EnumOfficer.CARGOHOLD) && shield < maxShield) {
            if (cargoHolder.produceShield()) {
                shield++;
                return true;
            }
        }

        return false;
    }

    public boolean produceAmmo() {
        if (crew.contains(EnumOfficer.CARGOHOLD) && weapon < maxWeapon) {
            if (cargoHolder.produceAmmo()) {
                weapon++;
                return true;
            }
        }

        return false;
    }
    
    public void setMaxWeapon(int maxWeapon) {
        this.maxWeapon = maxWeapon;
    }
    
    public boolean upgradeCargo() {

        return existsOfficer(EnumOfficer.CARGOHOLD) && cargoHolder.upgrade();
    }
     public boolean changeForResource(EnumResource a, EnumResource b) {
        return existsOfficer(EnumOfficer.CARGOHOLD) && cargoHolder.changeForResource(a, b);
    }
     
     public void travelTo(EnumSector sector, int mode) {
        currentSector = sector;
        if (mode == 1) {
            travelregularly();
        } else {
            travelThruBlackH();
        }

    }
     public boolean UpgradeWeapon() {
        return false;
    }
     public boolean setFullShield() {
        if (existsOfficer(EnumOfficer.SHIELDS) && shield < maxShield && cargoHolder.setFullShield()) {
            shield = maxShield;
            return true;
        }
        return false;
    }
     
     
     
     
     
     //getters
    public int getMaxWeapon() {
        return maxWeapon;
    }

    public List<EnumOfficer> getCrew() {
        return crew;
    }


    public void canUpgradeCargo() {
        cargoHolder.canUpgrade();
    }

   
    //verifica se os 5 artfactos foram encontrados
    public boolean achievedArtifacts() {
        return numArtifats == 5;
    }

    public Cargo getCargoHolder() {
        return cargoHolder;
    }
     //listar elemntos tripulacao
    public String listCrew() {
        StringBuilder aux = new StringBuilder();
        for (EnumOfficer a : crew) {
            aux.append(a + "\n");
        }
        return aux.toString();
    }

    public boolean hasCrew() {
        return crew.size() > 0;
    }

    //simular acontecimento de eventos
    public List<EnumResource> getResources() {
        return cargoHolder.getResources();
    }

    
    public int getArtfact() {
        return numArtifats;
    }
    //sector aonde nave se encontra
    public EnumSector getSector() {
        return currentSector;
    }

    

    public String getInfoResources() {
        return cargoHolder.getInfoResources();
    }

    public void addArtfact() {
        numArtifats++;
    }

    

    @Override
    public String toString() {
        return "\n\tfuel=" + fuel + " maxFuel=" + maxFuel + " shield=" + shield + " maxShield=" + maxShield + "\n\tweapon=" + weapon + " maxWeapon=" + maxWeapon + " numArtifats=" + numArtifats + " crewNum=" + crewNum + "\n\tcrew=" + crew + "\n\t" + cargoHolder + "\n\tcurrentSector=" + currentSector + '}';
    }

    public int[] getFuel() {
        int[] aux = new int[2];
        aux[0] = fuel;
        aux[1] = maxFuel;
        return aux;
    }

    public int[] getShield() {
        int[] aux = new int[2];
        aux[0] = shield;
        aux[1] = maxShield;
        return aux;
    }

    public int[] getWeapon() {
        int[] aux = new int[2];
        aux[0] = weapon;
        aux[1] = maxWeapon;
        return aux;
    }

    public int getResource(EnumResource r) {
        return cargoHolder.getResource(r);
    }

    

}
