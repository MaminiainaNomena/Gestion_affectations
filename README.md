# Gestion des affectations des employés

Application web Java basée sur JSP/Servlets, Hibernate et PostgreSQL, empaquetée en WAR pour Tomcat.

## Fonctionnalités

- CRUD des employés : code, nom, prénom et poste.
- CRUD des lieux : code, désignation et province.
- CRUD des affectations : employé, lieu et date.
- Recherche des employés par code ou nom.
- Relation `AFFECTER` avec clé primaire composite `(codeemp, codelieu)`.

## Structure

- `model` : entités JPA/Hibernate `Employe`, `Lieu` et `Affecter`.
- `dao` : opérations CRUD et recherche.
- `servlet` : contrôleurs HTTP.
- `src/main/webapp/WEB-INF/views` : vues JSP.

## Prérequis

- JDK 17 ou supérieur.
- Maven 3.9 ou supérieur, ou Maven Wrapper (`./mvnw`) s'il est fourni.
- PostgreSQL.
- Apache Tomcat 10.1 (Servlet 6.0).

## Installation

1. Installer PostgreSQL et créer la base :

   ```sql
   CREATE DATABASE gestion_affectations;
   ```

   Le script `database.sql` crée les tables et ajoute des données d'exemple. Il peut être exécuté dans la base avec `psql`.

2. Installer Tomcat 10.1 depuis le site officiel Apache, puis repérer son répertoire d'installation et son dossier `webapps`.

3. Configurer la connexion PostgreSQL avec les variables d'environnement suivantes (les valeurs indiquées sont les valeurs par défaut) :

   ```text
   DB_URL=jdbc:postgresql://localhost:5432/gestion_affectations
   DB_USER=postgres
   DB_PASSWORD=postgres
   ```

## Compilation et déploiement

Depuis la racine du projet, compiler le WAR avec Maven :

```bash
mvn clean package
```

Si le Maven Wrapper est présent, la commande équivalente est :

```bash
./mvnw clean package
```

Le fichier produit est `target/gestion-affectations.war`. Copier ce fichier dans `CATALINA_HOME/webapps/`, démarrer Tomcat, puis ouvrir :

```text
http://localhost:8080/gestion-affectations/
```

Le script `deploy.sh` automatise la compilation et la copie vers `/opt/tomcat/webapps/` sur une installation Linux correspondant à ce chemin.

## Dépendances principales

Les dépendances sont déclarées dans `pom.xml` : Hibernate ORM, pilote PostgreSQL, Jakarta Servlet API 6.0 et Jakarta JSTL 3.0. Les API Servlet sont fournies par Tomcat.
