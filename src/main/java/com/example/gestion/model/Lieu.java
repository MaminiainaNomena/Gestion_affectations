package com.example.gestion.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lieu")
public class Lieu {
    @Id
    @Column(name = "codelieu", length = 20, nullable = false)
    private String codelieu;

    @Column(name = "designation", length = 120, nullable = false)
    private String designation;

    @Column(name = "province", length = 120, nullable = false)
    private String province;

    public Lieu() {
    }

    public Lieu(String codelieu, String designation, String province) {
        this.codelieu = codelieu;
        this.designation = designation;
        this.province = province;
    }

    public String getCodelieu() {
        return codelieu;
    }

    public void setCodelieu(String codelieu) {
        this.codelieu = codelieu;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
