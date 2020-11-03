/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.planets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import logica.dados.util.EnumResource;
import logica.dados.components.Resource;

/**
 *
 * @author treys
 */
public class BluePlanet extends Planet {

    public BluePlanet() {   
           super(new ArrayList<EnumResource>(Arrays.asList(EnumResource.BLACK, EnumResource.GREEN, EnumResource.BLUE)), true);
           
    }
     @Override
    public String toString() {
        return "Blue Planet->"+super.toString();
    }
    
    @Override
    public EnumPlaneta getPlanetType(){
       return EnumPlaneta.BLUE;
    }
}
