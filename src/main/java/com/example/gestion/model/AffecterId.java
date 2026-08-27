package com.example.gestion.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AffecterId implements Serializable {
    private String codeemp;
    private String codelieu;

    public AffecterId() {}

    public AffecterId(String codeemp, String codelieu) {
        this.codeemp = codeemp;
        this.codelieu = codelieu;
    }

    public String getCodeemp() { return codeemp; }
    public void setCodeemp(String codeemp) { this.codeemp = codeemp; }
    public String getCodelieu() { return codelieu; }
    public void setCodelieu(String codelieu) { this.codelieu = codelieu; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AffecterId other)) return false;
        return Objects.equals(codeemp, other.codeemp) && Objects.equals(codelieu, other.codelieu);
    }

    @Override public int hashCode() { return Objects.hash(codeemp, codelieu); }
}
