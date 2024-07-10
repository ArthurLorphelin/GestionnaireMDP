# GestionnaireMDP
Petite application Android de gestionnaire de mots de passe en Java.

L'application permet, après s'être authentifié grâce a des identifiants codés en dur, de gérer ses comptes et mots de passe stockés dans une base de données locale.
L'application permet d'ajouter, de modifier et de supprimer des comptes.
Après authentification, l'application présente la liste complète des comptes dans la BDD (SQLite), avec la possibilité d'en trouver un (ou plusieurs) en particulier grâce à une barre de recherche.
Je me suis aussi aumsé a coder quelques petites spécificités : 
- lorsque le nom du compte, le username ou le password ne sont pas rentrés lors de l'ajout/modification d'un compte, les champs en question s'affichent en rouge et un message d'erreur apparaît
- les mots de passe sont initialement masqués dans les détails des comptes, mais en cliquant sur l'icône "oeil", celui-ci devient visible, et il est possible de les remarsqer en cliquant sur l'icône "oeil barré"
- les mots de passe peuvent directement être copiés dans le presse-papier du téléphone Android depuis l'application.
