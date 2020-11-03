/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.util;

import java.io.Serializable;

/**
 *
 * @author treys
 */
public class Utility implements Serializable{
    private static final long serialVersionUID = 4L;
    public Utility(){
        
    }
    public int randomInt(int min, int max){
        int aux=(int)(Math.random()*((max-min)+1))+min;
        return aux;
    }
}
