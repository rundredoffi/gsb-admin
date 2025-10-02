# Configuration de la base de données

## Sécurité

Pour des raisons de sécurité, les identifiants de base de données ne sont pas inclus dans le code source.

## Configuration requise

1. Copiez le fichier `src/resources/db.properties.example` vers `src/resources/db.properties`
2. Modifiez le fichier `db.properties` avec vos vraies informations de connexion :
   - `db.url` : URL de votre base de données
   - `db.username` : Nom d'utilisateur
   - `db.password` : Mot de passe
   - `db.driver` : Driver JDBC

## Exemple de configuration

```properties
db.url=jdbc:mysql://localhost:3306/gsb_admin
db.username=votre_utilisateur
db.password=votre_mot_de_passe
db.driver=com.mysql.cj.jdbc.Driver
```

⚠️ **Important** : Le fichier `db.properties` est dans le `.gitignore` et ne doit jamais être commité dans le repository.