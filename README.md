# Monde de développeurs

Le projet Monde de développeurs (MDD) est une application web permettant aux utilisateurs de lire/rédiger des articles liés à l'informatique. Les autres utilisateurs peuvent commenter les articles et ces derniers sont classés par thème. 

Cette application est développée dans le cadre de la formation de développeur fullstack Angular/Java d'OpenClassrooms.

## Installation et Exécution

### Frontend
1. Récupérez le code source du frontend depuis [ce repértoire Github](https://github.com/paulmarniquet/Developpez-une-application-full-stack-complete).
2. Lancez un terminal et rendez-vous dans le répertoire du frontend.
3. Exécutez la commande suivante pour installer les dépendances :
   ```
   npm install
   ```
4. Démarrez le serveur de développement en exécutant :
   ```
   ng serve
   ```
   Le frontend sera accessible à l'adresse [localhost:4200](http://localhost:4200/).

### Backend
1. Exécutez la commande suivante pour installer les dépendances :
   ```
   mvn install
   ```
2. Démarrez le serveur backend avec la commande :
   ```
   mvn spring-boot:run
   ```
   Le backend sera accessible à l'adresse [localhost:9000](http://localhost:9000/).


## Base de données

1. Créez une nouvelle base de données MySQL. L'ORM utilisé est Hibernate. Le schéma de la base de données est généré automatiquement.
2. Créez un fichier `.env` dans le répertoire du backend.
3. Ajoutez-y les informations de connexion à la base de données :
   ```
   MYSQL_USER=Utilisateur
   MYSQL_PASSWORD=MotDePasse
   MYSQL_URL=URL
   ```
   Remplacez les valeurs par vos identifiants SQL.


## Postman

Vous pouvez utiliser le fichier `mdd` qui se trouve dans le dossier ` ressources/postman` pour tester les différentes routes de l'API.

## Technologies utilisées

* Java 17
* Spring Boot
* MySQL
* Hibernate
* Maven
* Angular
* Angular Material
* Tailwind CSS

---

### Contact

Pour toute question ou commentaire, n'hésitez pas à me contacter sur mon mail : [paul.marniquet@gmail.com](mailto:paul.marniquet@email.com).

---
