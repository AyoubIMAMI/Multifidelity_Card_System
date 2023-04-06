Ce Jenkinsfile décrit une pipeline Jenkins qui permet de construire, tester, déployer et publier les artefacts des projets "backend" et "cli". La pipeline dispose de deux branches principales "Develop" et "main".

Voici le découpage de la pipeline pour chaque branche :

## Branche Develop :

- **config workspace:** Configuration de l'environnement Jenkins et vérification si le commit est généré par le plugin "maven-release-plugin" ou "Jenkins". Si c'est le cas, la pipeline sort et n'exécute pas les autres étapes. Ensuite, le fichier settings.xml est copié dans le dossier .m2 et les permissions sont modifiées.
- **Export backend and cli:** Pour chaque projet backend et cli, la pipeline exécute les étapes suivantes :
  - **Test backend/cli :** Si la branche est "Develop", la pipeline lance la commande mvn test pour tester le code.
  - **Building backend/cli:** La pipeline lance la commande mvn clean package pour construire le projet.
  - **Deploy backend/cli :** Si la branche est "Develop", la pipeline lance la commande mvn deploy pour déployer le projet.
## Branche main :

- **config workspace:** Configuration de l'environnement Jenkins et vérification si le commit est généré par le plugin "maven-release-plugin" ou "Jenkins". Si c'est le cas, la pipeline sort et n'exécute pas les autres étapes. Ensuite, le fichier settings.xml est copié dans le dossier .m2 et les permissions sont modifiées.
- **Export backend and cli:** Pour chaque projet backend et cli, la pipeline exécute l'étape suivante :
  - **Prepare and Perform release backend/cli:** La pipeline vérifie si des modifications n'ont pas été poussées dans la branche courante et, si tel est le cas, applique les modifications. Ensuite, la pipeline exécute les commandes mvn clean verify, mvn release:prepare, mvn release:perform pour préparer et publier la release.
- **Pull ltest artifacts:** Si la branche est "main", la pipeline crée le dossier "releases" et télécharge les artefacts de la dernière version de chaque projet à partir d'un artifactory en utilisant la commande jf rt dl.
