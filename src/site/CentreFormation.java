/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package site;

/**
 *
 * @author gaetan
 */
public class CentreFormation extends Site{
    
    //qu'une formation par ville
    public CentreFormation(String name, Double longitude, Double latitude, int id) {
        super(name, longitude, latitude, id);
    }
    
}
