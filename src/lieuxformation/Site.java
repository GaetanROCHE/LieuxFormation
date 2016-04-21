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
public abstract class Site {

    private int id;
    private String nom;
    private Double longitude;
    private Double latitude;
    
    public Site(String nom, Double longitude, Double latitude, int id){
        this.nom = nom;
        this.longitude = longitude;
        this.latitude = latitude;
        this.id = id;
    }
    
    public Double distance(Site lieux){
        //la distance (A, B) = R * arccos (sin (lata) * sin (LATB) + cos (lata) * cos (LATB) * cos (Lona-lonB))
        return 60 * Math.acos( Math.sin(this.getLatitude()) * Math.sin(lieux.getLatitude()) +
                Math.cos(this.getLatitude())  * Math.cos(lieux.getLatitude()) * Math.cos(this.getLongitude()-lieux.getLongitude()));
    }
    
    public Double getLongitude(){
        return this.longitude;
    }
    
    public Double getLatitude(){
        return this.latitude;
    }

    public int getId(){
        return this.id;
    }
    
}
