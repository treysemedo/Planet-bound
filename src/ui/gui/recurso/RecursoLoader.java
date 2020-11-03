/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.gui.recurso;

import java.io.InputStream;

/**
 *
 * @author treys
 */
public class RecursoLoader {
    public static InputStream getMyResourceFile(String nomeF){
        InputStream in=RecursoLoader.class.getResourceAsStream(nomeF);
        return in;
    }
}
