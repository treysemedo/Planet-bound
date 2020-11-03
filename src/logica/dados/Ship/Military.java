/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.dados.Ship;

import logica.dados.util.EnumOfficer;



/**
 *
 * @author treys
 */
public class Military extends Ship{
    
        public Military(){
            super(35, 9, 9, 2);
            
        }
     
    @Override
    public boolean UpgradeWeapon() {
        if(super.getMaxWeapon()<18 && super.getCargoHolder().canUpgradeWeapon()&&super.existsOfficer(EnumOfficer.WEAPONS)){
            super.setMaxWeapon(18);
            super.getCargoHolder().upgradeWeapon();
            return true;
        }
        return false;
        
    }
        
}
