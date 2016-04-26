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
        /*return Math.abs(60 * Math.acos( Math.sin(this.getLatitude()) * Math.sin(lieux.getLatitude()) +
                Math.cos(this.getLatitude())  * Math.cos(lieux.getLatitude()) * Math.cos(this.getLongitude()-lieux.getLongitude())));*/
        int R = 6371; // rayon de la terre en kilometre
        double latitudeSource = Math.toRadians(this.latitude);
        double latitudeAutre = Math.toRadians(lieux.latitude);
        double longituteDelta = Math.toRadians(lieux.longitude - this.longitude );
        double latitudeDelta = Math.toRadians(lieux.latitude - this.latitude);

        double a = Math.sin(latitudeDelta/2) * Math.sin(latitudeDelta)/2 + Math.cos(latitudeSource) * Math.cos(latitudeAutre)* Math.sin(longituteDelta/2) * Math.sin(longituteDelta/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }
    
    public Double getLongitude(){
        return this.longitude;
    }
    
    public Double getLatitude(){
        return this.latitude;
    }

    public String getNom(){ return this.nom; }

    public int getId(){
        return this.id;
    }
    
}
