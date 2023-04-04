# ISA DEVOPS - Group H
# Getting started (DEVOPS)
## Connection to the virtual machine
* Connect to the VPN (Cisco) `open.unice.fr`
* Connect to the VM through SSH `ssh teamh@vmpx08.polytech.unice.fr`

## Docker
### Installation 
* `sudo apt-get update`
* ```shell
  sudo apt-get install \
  ca-certificates \
  curl \
  gnupg \
  lsb-release
  ```
  
### Configuration
* Create Docker group `sudo groupadd docker`
* Add user to group `sudo usermod -aG docker $USER`
* Update group `newgrp docker`

## Smee
Smee is used to enable jenkins to listen incoming events from github. Please note that NodeJS is required to install and run Smee.
* Install smee `npm install --global smee-client`
* Run smee `smee --url https://smee.io/ugXPk3XiT2ED6aZV --path /github-webhook/ --port 8001 &` 

## Jenkins
### Installation
* Create a `Jenkins` foder : `mkdir Jenkins`
* cd to the `Jenkins` folder : `cd Jenkins`
* Create the Jenkins `Dockerdile` file
```Dockerfile
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

* Create the Jenkins `build.sh` to simplify the build of the Jenkins image.
```sh
#!/bin/bash
docker build -t custom_jenkins .
```

* Make the `build.sh` executable : `sudo chmod 777 build.sh`

* Create the Jenkins `docker-compose.yaml` file
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


* Build of the custom Jenkins image : `./build.sh`

* Start the Jenkins docker container in detached mode `docker compose up -d`

### Configuration
Login to Jenkins
* Go to [Jenkins web page](vmpx08.polytech.unice.fr:8001)
* Log into Jenkins: <br>
Username: `admin`<br>
Password: `348177c474054e7795cd1282d0b05c28`

Setting up a GitHub connection
* Go to the [credentials configuration's page](http://vmpx08.polytech.unice.fr:8001/manage/credentials/)
* Create a "Secret text" credential and enter your Github token
* Create a "Username with password" credential and enter your Github username and your github token
* Go to system [configuration's page](http://vmpx08.polytech.unice.fr:8001/configure)
* Add your Github credentilals and test the connection.

Configure a mulibranch pipeline
* Create a new Job
* In the name field enter the name of the git project : `isa-devops-22-23-team-h-23`
* Select a Multibranches Pipeline and click on "ok".
* On branch sources add a GitHub source, select your credentials and paste the project Repository HTTPS URL : `https://github.com/pns-isa-devops/isa-devops-22-23-team-h-23.git`



## Artifactory
### Installation
* Download the [docker-compose 7.49.6](https://releases.jfrog.io/artifactory/bintray-artifactory/org/artifactory/oss/docker/jfrog-artifactory-oss/7.49.6/jfrog-artifactory-oss-7.49.6-compose.tar.gz) version.
Note that if you do not download this version, I will not help you, because NO ONE help me for 2 weeks
* On the VM, decompress the .tar.gz `tar xvzf jfrog-artifactory-oss-7.49.6-compose.tar.gz`
* Open the .env file and set the JF_ROUTER_ENTRYPOINTS_EXTERNALPORT to `8002`
* Open the .env file and set the ROOT_DATA_DIR to `/home/teamh/.jfrog/artifactory`
* Run the config.sh file as root. `sudo ./config.sh`
* Say no to everything and select derby as database

### Configuration
Login to Artifactory
* Go to [Artifactory web page](vmpx08.polytech.unice.fr:8002)
* Log into Artifactory: <br>
Username: `admin`<br>
Password: `zEzEBf7mD2aCHA8XG4!`<br>

Setting up Artifactory repos
* Click on "Welcome, admin" at the top right of the website homepage
* Click on "Quick Repository Creation"
* Click on "Maven" and follow the steps


## Problème sur jenkins
Si vous avez cette erreur alors que le fichier est au bon endroit:
```
+ ./build-all.sh
/var/jenkins_home/workspace/2-23-team-h-23_cli-intoo-jenkins@tmp/durable-7332bef8/script.sh: 1: ./build-all.sh: not found
```
Il faut vérifier si la première ligne du fichier est bien exactement:
```
#!/bin/bash
```
