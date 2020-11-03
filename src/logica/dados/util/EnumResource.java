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
public enum EnumResource {
     RED(1),GREEN(2), BLACK(3), BLUE(4);
    private final Integer num;
    private static final Map<Integer,EnumResource> map = new HashMap<>();
     
    EnumResource(int num){
        this.num=num;
    }
   static {
        for (EnumResource a : EnumResource.values()) {
            map.put(a.num, a);
        }
    }

    public static EnumResource valueOf(int numResource) {
        if(numResource<=4&&numResource>=1)
            return (EnumResource) map.get(numResource);
        return null;
    }

    public int getValue() {
        return num;
    }
}
