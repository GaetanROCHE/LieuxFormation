/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lieuxformation;

/**
 *
 * @author gaetan
 */
public class Agence extends Site{
    
    private int nb_employes;
    
    public Agence(String name, Double longitude, Double latitude, int nb_employes) {
        super(name, longitude, latitude);
        this.nb_employes = nb_employes;
    }
    
}
