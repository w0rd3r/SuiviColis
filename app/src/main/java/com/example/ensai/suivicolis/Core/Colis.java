package com.example.ensai.suivicolis.Core;

/**
 * Created by ensai on 10/05/16.
 */
public class Colis {
    private Transporteur transporteur;
    private String description;
    private String reference;

    public String getReference(){
        return reference;
    }

    public void setReference(String reference){
        this.reference = reference;
    }

    public Transporteur getTransporteur() {
        return transporteur;
    }


    public void setTransporteur(Transporteur transporteur) {
        this.transporteur = transporteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL() {
       return this.transporteur.getURLtransporteur().replaceAll("%1",getReference());
    }
}
