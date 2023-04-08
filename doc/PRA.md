# Problème potentiels

## Jenkins

### Très Grave

### Grave

### Pas grave
#### "file.sh not found"
  Si vous avez cette erreur alors que le fichier est au bon endroit:
  ```
  + ./build-all.sh
  /var/jenkins_home/workspace/2-23-team-h-23_cli-intoo-jenkins@tmp/durable-7332bef8/script.sh: 1: ./build-all.sh: not found
  ```
  Il faut vérifier si la première ligne du fichier est bien exactement:
  ```
  #!/bin/bash
  ```

## Artifactory
### Très Grave

### Grave
#### "401 unauthorized -> connexion refused" (Artifactory)
Il ya deux choses qui pourraient déclancher cette erreur:
1. Aller voir l'explication de [l'erreur dans la catégorie VM](#### "401 unauthorized -> connexion refused" (VM)")
2. Si cela n'a pas reglé le problème il est possible que Jenkins n'ai pas localisé le fichier `settings.xml` de maven. Alors veillez à bien vérifier que Jenkins localise se fichier et que le fichier soit bien nommé `settings.xml`.

### Pas grave

## Vm
### Très Grave

### Grave
#### "401 unauthorized -> connexion refused" (VM)
Si vous avez rajouté un service sur la VM il est possible que quand vous essayé de le contacter vous tombiez fasse à cette erreur.<br>
La solution est de remplacer `http://localhost:80XX` par l'adresse en dur: `http://134.59.213.138:80XX`

Note: Veillez à ce que le port renseigné soit bien compris entre `8000` et `8030`, c'est la plage autorisé
### Pas grave



# Procédures de redémarrage/reconstruction

## Redémarrer Jenkins et Artifactory

#### Eteindre les containers
En se connectant à la vm vous pouvez observer quels containers sont actifs avec:
````
docker ps
````
Pour fermer tout les containers il faut utiliser:
````
docker compose down
````
Pour fermer un seul containers:
````
docker stop <container-name/id>
````
Pour supprimer un ou plusieurs containers inactif:
````
docker rm <container-name/id> <container-name/id> 
````

Vérifier que tout s'est bien fermé avec `docker ps`

#### Démarrer les containers
Pour démarrer Jenkins:
````
cd ./jenkins
docker compose up
````
Pour démarrer Artifactory:
````
cd ./artifactory-oss-7.49.6
docker compose up
````

## Ajouter quelque chose à l'image Docker Jenkins
Si vous avez besoins d'ajouter une brique à l'image docker Jenkins il faut dans un premier temps modifier le `Dockerfile`, c'est le fichier qui décris l'image Docker.
````
cd ./jenkins
nano Dockerfile
````
Une fois la modification effectué il faut construire la nouvelle image:
````
cd ./jenkins
./build.sh
````
Enfin vous pouvez relancer le container avec la nouvelle image. Veuillez à bien éteindre le container précédent.
````
docker stop custom_jenkins
cd ./jenkins
docker compose up
````


# Recréer l'environnement DevOps from scratch (1h30)
### Prérequis:
L'environnement Devops a été configuré sur la vm vmpx08.polytech.unice.fr, la première chose à faire est donc d'y accéder et pour cela il faut se connecter au VPN (Cisco) open.unice.fr.
Il faut ensuite se connecter en ssh à la VM:
````
ssh teamh@vmpx08.polytech.unice.fr
zEBf7mD2aCHA8XG4
````
**Warning:** Tout le reste de l'installation se passe dans la VM

## Setup de l'environnement de la VM (10min)

### Docker (5min)

**Installation**
````
sudo apt-get update
sudo apt-get install \
ca-certificates \
curl \
gnupg \
lsb-release
````
**Configuration**
* Créer un groupe Docker ````sudo groupadd docker````
* Ajouter le user au groupe ````sudo usermod -aG docker $USER````
* Mettre à jour le groupe ````newgrp docker````

### Smee (5min)
Smee est utilisé pour permettre à Jenkins de recevoir des évènement de la part de GitHub.
A noter: Node.js est nécessaire pour utiliser Smee

**Installation de Node.js et smee**
````
sudo apt install npm nodejs
npm install --global smee-client
````
**Lancer Smee**
````
smee --url https://smee.io/ugXPk3XiT2ED6aZV --path /github-webhook/ --port 8001 &
````

## Création de l'environnement Docker (1h20)
### Jenkins (1h)
#### Installation (30min)
* Créer un dossier `Jenkins`: `mkdir Jenkins`
* Se placer dans le dossier `Jenkins`: `cd Jenkins`
* Créer le `Dockerfile` qui va décrire notre image Docker Jenkins:
```Dockerfile
# Dockerfile of the jenkins docker-image
FROM jenkins/jenkins:jdk17-preview

USER root

# Dependencies
RUN apt-get update && apt-get install -y curl tar gzip

# Maven
RUN curl -fsSL https://dlcdn.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -o apache-maven-3.6.3-bin.tar.gz
RUN tar -xzf apache-maven-3.6.3-bin.tar.gz -C /opt/
ENV PATH="/opt/apache-maven-3.6.3/bin:${PATH}"

# Docker
RUN curl -fsSL https://get.docker.com -o get-docker.sh
RUN sh get-docker.sh

# Jfrig cli
RUN curl -fL https://install-cli.jfrog.io | sh
RUN chown 1000:1000 /usr/local/bin/jf
RUN mkdir /.jfrog
RUN chmod 775 /.jfrog
RUN chown 1000:1000 /.jfrog

# Python 3
RUN apt-get install -y python3

# Socat
RUN apt-get install -y socat

USER jenkins
```

* Créer le fichier `build.sh` afin de simplifier la construction de l'image Jenkins.
```sh
#!/bin/bash
docker build -t custom_jenkins .
```

*  Modifier les permission du fichier `build.sh` afin de pouvoir l'exécuter : `sudo chmod 777 build.sh`

* Créer le fichier `docker-compose.yaml`:
```yaml
version: '3.8'
services:
  jenkins:
    image: custom_jenkins
    privileged: true
    user: root
    ports:
      - 8001:8080
    container_name: jenkins
    volumes:
      - /home/teamh/jenkins/jenkins_configuration:/var/jenkins_home
      - /var/run/docker.sock:/var/run/docker.sock
```


* Construire l'image Docker Jenkins : `./build.sh`

* Lancer le container Jenkins en mode détaché `docker compose up -d`

#### Configuration de Jenkins (30min)
**Se Login sur Jenkins**
* Aller [Jenkins web page](vmpx08.polytech.unice.fr:8001)
* Se Log sur Jenkins:
Username: `admin`
Password: `348177c474054e7795cd1282d0b05c28`

**Configurer la connexion à GitHub**
* Aller sur la page de [configuration des credentials](http://vmpx08.polytech.unice.fr:8001/manage/credentials/)
* Créer un "Secret text" credential et entrer le Github token
* Créer un "Username with password" credential et entrer votre username et token Github
* Aller sur la page de [configuration du système](http://vmpx08.polytech.unice.fr:8001/configure)
* Ajouter les credentials Github et tester la connection.

**Configurer la pipeline multibranche**
* Créer un nouveau job
* Dans le champs nom mettre : `isa-devops-22-23-team-h-23`
* Selectionner "multibranch pipeline" et cliquer sur "ok".
* Sur "branch sources" ajouter "GitHub source" et selectionner les credentials associés. Ensuite coller l'url du projet: `https://github.com/pns-isa-devops/isa-devops-22-23-team-h-23.git`



### Artifactory (20min)
#### Installation (10min)
* Télécharger la version de docker compose suivante [docker-compose 7.49.6](https://releases.jfrog.io/artifactory/bintray-artifactory/org/artifactory/oss/docker/jfrog-artifactory-oss/7.49.6/jfrog-artifactory-oss-7.49.6-compose.tar.gz):
  ````
  curl -L https://releases.jfrog.io/artifactory/bintray-artifactory/org/artifactory/oss/docker/jfrog-artifactory-oss/7.49.6/jfrog-artifactory-oss-7.49.6-compose.tar.gz
  ````
* Décompresser le .tar.gz `tar xvzf jfrog-artifactory-oss-7.49.6-compose.tar.gz`
* Ouvrir le fichier `.env` et définir le `JF_ROUTER_ENTRYPOINTS_EXTERNALPORT` à `8002`
* Toujours dans le  `.env`, définir le `ROOT_DATA_DIR` à `/home/teamh/.jfrog/artifactory`
* Lancer le fichier `config.sh` en tant que root:
    ````sudo ./config.sh````
* Choisir `no` pour tout et selectionner `derby`comme db

#### Configuration (10min)
Se login sur Artifactory
* Aller sur la [page web Artifactor](vmpx08.polytech.unice.fr:8002)
* Se Log sur Artifactory:
Username: `admin`
Password: `zEzEBf7mD2aCHA8XG4!`

Configurer les dossier Artifactory:
* cliquer sur "Welcome, admin" en haut à droite sur la page d'accueil
* Cliquer sur "Quick Repository Creation"
* Cliquer sur "Maven" et suivre les étapes
