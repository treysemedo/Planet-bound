/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author treys
 */
public enum EnumOfficer {
    CAPTAIN(1), NAVIGATION(2),LANDINGPARTY(3),  SHIELDS(4), WEAPONS(5), CARGOHOLD(6);
    
    
    private final Integer num;
    private static final Map<Integer,EnumOfficer> map = new HashMap<>();
     
    EnumOfficer(int num){
        this.num=num;
    }
   static {
        for (EnumOfficer a : EnumOfficer.values()) {
            map.put(a.num, a);
        }
    }

    public static EnumOfficer valueOf(int numOfficer) {
        if(numOfficer<=6&&numOfficer>=1)
            return (EnumOfficer) map.get(numOfficer);
        return null;
    }

    public int getValue() {
        return num;
    }
}
