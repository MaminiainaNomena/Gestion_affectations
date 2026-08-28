CREATE DATABASE gestion_affectations;
\connect gestion_affectations
-- Hibernate créera/actualisera les tables avec hibernate.hbm2ddl.auto=update.

CREATE TABLE IF NOT EXISTS employe (
    codeemp VARCHAR(20) PRIMARY KEY,
    nom VARCHAR(80) NOT NULL,
    prenom VARCHAR(80) NOT NULL,
    poste VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS lieu (
    codelieu VARCHAR(20) PRIMARY KEY,
    designation VARCHAR(120) NOT NULL,
    province VARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS affecter (
    codeemp VARCHAR(20) NOT NULL,
    codelieu VARCHAR(20) NOT NULL,
    date_affectation DATE NOT NULL,
    PRIMARY KEY (codeemp, codelieu),
    CONSTRAINT fk_affecter_employe FOREIGN KEY (codeemp) REFERENCES employe(codeemp) ON DELETE CASCADE,
    CONSTRAINT fk_affecter_lieu FOREIGN KEY (codelieu) REFERENCES lieu(codelieu) ON DELETE CASCADE
);
