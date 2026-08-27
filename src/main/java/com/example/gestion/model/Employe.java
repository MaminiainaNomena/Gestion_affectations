package com.example.gestion.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employe")
public class Employe {
    @Id
    @Column(name = "codeemp", length = 20, nullable = false)
    private String codeemp;

    @Column(name = "nom", length = 80, nullable = false)
    private String nom;

    @Column(name = "prenom", length = 80, nullable = false)
    private String prenom;

    @Column(name = "poste", length = 100, nullable = false)
    private String poste;

    public Employe() {}

    public Employe(String codeemp, String nom, String prenom, String poste) {
        this.codeemp = codeemp;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
    }

    public String getCodeemp() { return codeemp; }
    public void setCodeemp(String codeemp) { this.codeemp = codeemp; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }
}
