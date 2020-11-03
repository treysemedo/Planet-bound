/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.Event;

import logica.dados.Ship.Ship;
import logica.dados.util.EnumEvent;

/**
 *
 * @author treys
 */
public class CrewDeath extends Event{

    public CrewDeath(Ship ship) {
        super(ship);
    }

    @Override
    public void act() {
        ship.removeOfficer();
       }

    @Override
    public EnumEvent getEnumEvent() {
       return EnumEvent.CREWDEATH;
    }
}
