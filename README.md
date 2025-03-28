# GSB Admin

Application de gestion de l'équipe de visiteur pour l'application GSB.

## Table des matières

- [Description](#description)
- [Fonctionnalités](#fonctionnalités)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Contribuer](#contribuer)
- [Licence](#licence)
- [Auteurs](#auteurs)

## Description

L'application GSB Admin est conçue pour gérer l'équipe de visiteurs médicaux pour l'application GSB. Elle permet de gérer les utilisateurs, les sessions de connexion, et d'autres aspects administratifs.

## Fonctionnalités

- Gestion des utilisateurs
- Authentification sécurisée
- Interface utilisateur intuitive
- Gestion des sessions

## Prérequis

Avant d'installer et d'utiliser cette application, assurez-vous d'avoir les éléments suivants :

- Java JDK 8 ou supérieur
- Maven
- Hibernate
- Une base de données compatible (e.g., MySQL, PostgreSQL)

## Installation

1. Clonez le repository :

    ```sh
    git clone https://github.com/rundredoffi/gsb-admin.git
    cd gsb-admin
    ```

2. Configurez la base de données dans le fichier `hibernate.cfg.xml`.

3. Compilez le projet avec Maven :

    ```sh
    mvn clean install
    ```

## Utilisation

1. Lancez l'application :

    ```sh
    java -jar target/gsb-admin-1.0.jar
    ```

2. Connectez-vous avec vos identifiants.

3. Utilisez l'interface pour gérer les utilisateurs et les sessions.

## Contribuer

Les contributions sont les bienvenues ! Pour contribuer :

1. Forkez le projet
2. Créez votre branche feature (`git checkout -b feature/AmazingFeature`)
3. Commitez vos modifications (`git commit -m 'Add some AmazingFeature'`)
4. Pushez votre branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## Licence

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

## Auteurs

- [Rundredoffi](https://github.com/rundredoffi)
- [MarinCD](https://github.com/MarinCD)
- [MathisSP](https://github.com/MathisSP)
