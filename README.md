# Projet 11 — Gestion des affectations des employés

Application web Java réalisée pour IntelliJ IDEA avec **Hibernate + PostgreSQL + JSP/Servlets**.

## Fonctionnalités
- CRUD Employé : code, nom, prénom, poste.
- CRUD Lieu : code, désignation, province.
- CRUD Affectation : employé + lieu + date.
- Recherche des employés par code ou nom.
- Relation `AFFECTER(codeemp, codelieu, date)` avec clé primaire composite.

## Prérequis
- JDK 17+
- Maven 3.9+
- PostgreSQL
- Tomcat 10.1+
- IntelliJ IDEA Ultimate recommandé pour la configuration Tomcat/Jakarta Web.

## 1. Base PostgreSQL
Créer la base :

```sql
CREATE DATABASE gestion_affectations;
```

Le fichier `database.sql` contient aussi les tables et des données d'exemple.

Par défaut l'application utilise :
- URL : `jdbc:postgresql://localhost:5432/gestion_affectations`
- utilisateur : `postgres`
- mot de passe : `postgres`

Pour changer ces valeurs, définir les variables d'environnement `DB_URL`, `DB_USER`, `DB_PASSWORD` dans la configuration d'exécution IntelliJ.

## 2. Ouvrir dans IntelliJ IDEA
1. `File > Open` puis sélectionner le dossier du projet.
2. IntelliJ détecte `pom.xml` et importe Maven.
3. Choisir JDK 17.
4. Attendre la fin du téléchargement des dépendances.

## 3. Configurer Tomcat
1. Installer/configurer Tomcat 10.1.
2. `Run > Edit Configurations > + > Tomcat Server > Local`.
3. Dans `Deployment`, ajouter l'artefact `gestion-affectations:war exploded`.
4. Context path : `/gestion-affectations`.
5. Dans `Environment`, mettre `DB_USER` et `DB_PASSWORD` selon PostgreSQL.
6. Lancer Tomcat.

URL : `http://localhost:8080/gestion-affectations/`

## Architecture

```text
src/main/java/com/example/gestion
├── model       -> Entités JPA/Hibernate
├── dao         -> Accès aux données
├── servlet     -> Contrôleurs HTTP
└── util        -> HibernateUtil

src/main/webapp
├── css
└── WEB-INF/views -> JSP
```

## Remarque
Le projet utilise `hibernate.hbm2ddl.auto=update`, donc Hibernate crée/met à jour les tables automatiquement au démarrage. Pour une production, utiliser plutôt des migrations SQL (Flyway/Liquibase) et `validate`.
