/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author treys
 */
public enum EnumAlien{
     RED(1, 2, 2),GREEN(2, 2, 3), BLACK(3, 1, 2), BLUE(4,3,3);
    private final Integer num, probAtack, probDeath;
    private static final Map<Integer,EnumAlien> map = new HashMap<>();
     
    EnumAlien(int num, int atack, int death){
        this.num=num;
        this.probAtack=atack;
        this.probDeath=death;
    }
   static {
        for (EnumAlien a : EnumAlien.values()) {
            map.put(a.num, a);
        }
    }

    public static EnumAlien valueOf(int numAlien) {
        if(numAlien<=4&&numAlien>=1)
            return (EnumAlien) map.get(numAlien);
        return null;
    }

    public int getValue() {
        return num;
    }
    
    public int getAtack(){
            return probAtack;
    }
     public int getDeath(){
            return probDeath;
    }
    
}
