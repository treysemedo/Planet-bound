/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ui.texto;
//
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//import logica.Game;
//import logica.dados.util.EnumCell;
//import logica.dados.util.EnumExplore;
//import logica.dados.util.EnumResource;
//
///**
// *
// * @author treys
// */
//public class UItexto {
//
//    private Game jogo;
//    private boolean end = false;
//    private Scanner sc;
//
//    public UItexto(Game jg) {
//        jogo = jg;
//        sc = new Scanner(System.in);
//    }
//
//    public void IShipSelection() {
//        System.out.println("\n\t___________________________" + jogo.getState() + "___________________________\n");
//        System.out.println("\tSelect a type of ship");
//        System.out.println("\n\t1- MILITARY\t2-MINING\n");
//        System.out.print("\t");
//        jogo.SelectShip(getInt());
//    }
//
//    public void ISpaceTravel() {
//        System.out.println("\n\t___________________________" + jogo.getState() + "___________________________\n");
//        System.out.println("\tSelect a Operation");
//        System.out.println("\n\t 1-Travel\t2-Convert Resources\t3-Scan Ship \n");
//        System.out.print("\t");
//        switch (getInt()) {
//            case 1:
//                jogo.spaceTravel();
//                break;
//            case 2:
//                jogo.convertResources();
//                break;
//            case 3:
//                System.out.println("\tShip Scan Completed:");
//                System.out.println("\t" + jogo.getInfoShip());
//                break;
//
//        }
//
//    }
//
//    public void IPlanetOrbiting() {
//        System.out.println("\n\t___________________________" + jogo.getState() + "___________________________\n");
//        System.out.println("\tSelect a Operation");
//
//        if (jogo.planetHasStation()) {
//            System.out.println("\n\t1-Land On Planet\t2-Convert Resources\t\t3-Scan Ship ");
//            System.out.println("\t4-Planet Information\t5-Dock with Space Station\t6-Back to Space Travel\n");
//            System.out.print("\t");
//
//            switch (getInt()) {
//                case 1:
//                    jogo.landOnPlanet();
//                    break;
//                case 2:
//                    jogo.convertResources();
//                    break;
//                case 3:
//                    System.out.println(jogo.getInfoShip());
//                    break;
//                case 4:
//                    System.out.print("\t");
//                    System.out.println(jogo.getInfoPlanet());
//                    break;
//                case 5:
//                    jogo.visitStation();
//                    break;
//                case 6:
//                    jogo.spaceTravel();
//                    break;
//
//            }
//        } else {
//            System.out.println("\n\t1-Land On Planet\t2-Convert Resources\t\t3-Scan Ship ");
//            System.out.println("\t4-Planet Information\t5-Back to Space Travel\n");
//            System.out.print("\t");
//
//            switch (getInt()) {
//                case 1:
//                    jogo.landOnPlanet();
//                    break;
//                case 2:
//                    jogo.convertResources();
//                    break;
//                case 3:
//                    System.out.println(jogo.getInfoShip());
//                    break;
//                case 4:
//                    System.out.print("\t");
//                    System.out.println(jogo.getInfoPlanet());
//                    break;
//                case 5:
//                    jogo.spaceTravel();
//                    break;
//
//            }
//        }
//
//    }
//
//    public void IEvent() {
//        boolean fim=false;
//        System.out.println("\n\t___________________________" + jogo.getState() + "___________________________\n");
//
//        System.out.println("\tSelect a Operation");
//        System.out.println("\n\t 1-Random Event\t2-Select Event\n");
//        System.out.print("\t");
//       
//        switch (getInt()) {
//            case 1:
//                jogo.randomEvent();
//                System.out.println("\tSYSTEM ALERT: " + jogo.getEvent());
//                System.out.println("ENTER to continue...");
//                sc.nextLine();
//                jogo.spaceTravel();
//                break;
//
//            case 2:
//                System.out.println("\t Events List:\n\t1-CargoLoss\n\t2-CrewDeath\n\t3-CrewRescue\n\t4-FuelLoss\n\t5-NoEvent\n\t6-SalvageShip");
//                jogo.selectEvent(getInt()-1);
//                System.out.println("\tSYSTEM ALERT: " + jogo.getEvent());
//                System.out.println("\tENTER to continue...");
//                sc.nextLine();
//                jogo.spaceTravel();
//                break;
//
//        }
//    }
//
//    public void IDroneControling() {
//
//        EnumCell aux[][];
//
//        System.out.println("\n\t___________________________" + jogo.getState() + "___________________________\n\n");
//        aux = jogo.getInfoTerrain();
//        displayTerrain(aux);
//
//        int cmd;
//
//        System.out.println("\n\tSelect a Operation");
//        System.out.println("\n\t\t\t\t\t8-up");
//        System.out.println("\n\t\t\t\t4-left\t2-down\t6-right");
//        System.out.print("\t");
//
//        cmd = getInt();
//
//        switch (cmd) {
//            case 8:
//                jogo.DroneMove(0, -1);
//                break;
//            case 2:
//                jogo.DroneMove(0, 1);
//                break;
//            case 4:
//                jogo.DroneMove(-1, 0);
//                break;
//            case 6:
//                jogo.DroneMove(1, 0);
//                break;
//            default:
//                break;
//
//        }
//
//        if (jogo.alienAtack()) {
//            System.out.println("\tDRONE UNDER ATACK!!");
//            System.out.println("\tdrone´s health: " + jogo.getDroneHp());
//        }
//
//    }
//
//    public void displayTerrain(EnumCell aux[][]) {
//        System.out.print("\t");
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 6; j++) {
//                System.out.print("|");
//                String auxS;
//                switch (aux[i][j]) {
//                    case EMPTY:
//                        auxS = "            ";
//                        break;
//                    default:
//                        auxS = aux[i][j].toString();
//                        break;
//                }
//
//                System.out.print(auxS);
//
//            }
//            System.out.print("|\n\t");
//        }
//        System.out.println("SD-drone in ship     RS- Resource     DR-Drone with resource     ");
//
//    }
//
//    public boolean IgameOver() {
//        System.out.println("\n\t___________________________" + jogo.getState() + "___________________________\n");
//        if (jogo.won()) {
//            System.out.println("\n\n\t5 ARTFACTS COLLECTED!!!\n");
//            System.out.println("\\n\ntCONGRATULATIONS YOU SAVED THE HUMAN RACE!!!\n");
//        } else {
//            if (jogo.lostCrew()) {
//                System.out.println("\t2LAST MEMBER OF CREW HAS FALLEN");
//            } else {
//                System.out.println("SHIP ABANDONED IN SPACE, SO HAS OUR MISSION");
//            }
//            System.out.println("\n\n\tUNFORTUNATELY OUR MISSION IS OVER, HUMANITY IS DOOMED\n\n");
//
//        }
//
//        System.out.println("\n\tSelect a Operation");
//        System.out.println("\n\t1-Start new game\t2-Exit Game");
//        System.out.print("\t");
//        switch (getInt()) {
//            case 1:
//                jogo.newGame();
//                break;
//            case 2:
//                return true;
//        }
//        return false;
//    }
//
//    public void IResourceConversion() {
//        System.out.println("\n\t___________________________" + jogo.getState() + "___________________________\n");
//        System.out.println("\n\tResources: " + jogo.getInfoResources());
//        System.out.println("\n\tSelect a Operation");
//        System.out.print("\n\t1-Produce Shield\t2-Produce Ammo\t3-Produce Fuel\t4-Go Back\n\n\t");
//
//        switch (getInt()) {
//            case 1:
//                if (jogo.produceShield()) {
//                    System.out.println("\t+1 Shield cell");
//                } else {
//                    System.out.println("\tCannot produce shield");
//                }
//                break;
//            case 2:
//                if (jogo.produceAmmo()) {
//                    System.out.println("\t+1 Ammo");
//                } else {
//                    System.out.println("\tCannot produce Ammo");
//                }
//                break;
//
//            case 3:
//                if (jogo.produceFuel()) {
//                    System.out.println("\t+1 Fuel cell");
//                } else {
//                    System.out.println("\tCannot produce Fuel");
//                }
//                break;
//            case 4:
//                jogo.goBack();
//                break;
//
//        }
//
//    }
//
//    public void ISpaceStation() {
//        System.out.println("\n\t___________________________" + jogo.getState() + "___________________________\n");
//        System.out.println("\n\tResources: " + jogo.getInfoResources());
//        System.out.println("\n\tSelect a Operation");
//        System.out.print("\n\t1-Upgrade Cargo Holder\t2-Upgrade weapon system\t3-Change One Resource for another");
//        System.out.print("\n\t4-Hire a crew Member\t5-Fix Ship Armor\t6-Go Back\n\n\t");
//
//        switch (getInt()) {
//            case 1:
//                if (jogo.upgradeCargo()) {
//                    System.out.println("\tCargo Upgraded Successfuly");
//                } else {
//                    System.out.println("\tCannot Upgrade");
//                }
//                break;
//
//            case 2:
//                if (jogo.upgradeWeapon()) {
//                    System.out.println("\tWEAPONS Upgraded Successfuly");
//                } else {
//                    System.out.println("\tWEAPONS cannot be Upgrade");
//                }
//                break;
//
//            case 3:
//                int aux1,
//                 aux2;
//                System.out.print("\tresource to lose: 1-Red 2-Green 3-Black 4-Blue\n\t");
//                aux1 = getInt();
//                if (aux1 > 4 || aux1 < 1) {
//                    break;
//                }
//                System.out.print("\tresource to receive: 1-Red 2-Green 3-Black 4-Blue\n\t");
//                aux2 = getInt();
//                if (aux2 > 4 || aux2 < 1) {
//                    break;
//                }
//                if (jogo.changeForResource(EnumResource.valueOf(aux1), EnumResource.valueOf(aux2))) {
//                    System.out.println("\tExchange successfully");
//                } else {
//                    System.out.println("\tCannot change resources");
//                }
//                break;
//
//            case 4:
//                if (jogo.hireCrewMember()) {
//                    System.out.println("\tOfficer Hired!");
//                } else {
//                    System.out.println("\tCannot Hire a Officer");
//                }
//                break;
//            case 5:
//                if (jogo.setFullShield()) {
//                    System.out.println("\tShields Fixed");
//                } else {
//                    System.out.println("\tCannot Max Shields");
//                }
//                break;
//            case 6:
//                jogo.undock();
//                break;
//
//        }
//    }
//
//    private int getInt() {
//        Boolean naoLeu = true;
//        String buffer = "";
//        int num = 0;
//
//        while (naoLeu) {
//
//            try {
//                buffer = sc.nextLine();
//                num = Integer.parseInt(buffer);
//                naoLeu = false;
//            } catch (NoSuchElementException e) {
//                System.out.println("\tUps!Expected a number");
//            } catch (NumberFormatException e) {
//                System.out.println("\tUps!Expected a number");
//            }
//            System.out.print("\t");
//
//        }
//
//        return num;
//    }
//
//    public void controlo() {
//
//        while (!end) {
//
//            switch (jogo.getState()) {
//                case SHIPSELECTION:
//                    IShipSelection();
//                    break;
//                case SPACETRAVEL:
//                    ISpaceTravel();
//                    break;
//                case PLANETORBITING:
//                    IPlanetOrbiting();
//                    break;
//                case EVENT:
//                    IEvent();
//                    break;
//                case DRONECONTROLING:
//                    IDroneControling();
//                    break;
//                case GAMEOVER:
//                    end = IgameOver();
//                    break;
//                case RESOURCECONVERSION:
//                    IResourceConversion();
//                    break;
//                case SPACESTATION:
//                    ISpaceStation();
//                    break;
//
//            }
//        }
//    }
//
//}
