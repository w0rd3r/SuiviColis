package com.example.ensai.suivicolis.Core;

/**
 * Created by ensai on 10/05/16.
 */
public class Colis {
    private String transporteur;
    private String description;
    private String reference;

    public String getReference(){
        return reference;
    }

    public void setReference(String reference){
        this.reference = reference;
    }

    public String getTransporteur() {
        return transporteur;
    }

    public void setTransporteur(String transporteur) {
        this.transporteur = transporteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL() {
        return "http://google.fr";
    }
}
