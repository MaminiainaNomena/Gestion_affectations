package com.example.gestion.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "affecter")
public class Affecter {
    @EmbeddedId
    private AffecterId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codeemp")
    @JoinColumn(name = "codeemp", nullable = false)
    private Employe employe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codelieu")
    @JoinColumn(name = "codelieu", nullable = false)
    private Lieu lieu;

    @Column(name = "date_affectation", nullable = false)
    private LocalDate date;

    public Affecter() {
    }

    public Affecter(Employe employe, Lieu lieu, LocalDate date) {
        this.employe = employe;
        this.lieu = lieu;
        this.date = date;
        this.id = new AffecterId(employe.getCodeemp(), lieu.getCodelieu());
    }

    @PrePersist
    @PreUpdate
    private void syncId() {
        if (employe != null && lieu != null) {
            id = new AffecterId(employe.getCodeemp(), lieu.getCodelieu());
        }
    }

    public AffecterId getId() {
        return id;
    }

    public void setId(AffecterId id) {
        this.id = id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
