# ISA DEVOPS - Group H
# Getting started (DEVOPS)
## Connection steps
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
* Create the Jenkins `docker-compose.yaml` file
```yaml
version: '3.8'
services:
  jenkins:
    image: jenkins/jenkins:lts
    privileged: true
    user: root
    ports:
      - 8001:8080
    container_name: jenkins
    volumes:
      - /home/teamh/jenkins_compose/jenkins_configuration:/var/jenkins_home
      - /var/run/docker.sock:/var/run/docker.sock
```
* Start the Jenkins docker container in detached mode `docker compose up -d`

### Configuration
* Go to Jenkins web page `vmpx08.polytech.unice.fr:8001`
* Log into Jenkins: <br>
Username: `admin`<br>
Password: `eefe44d7c4694c75aa8fc0680adef5ef`
* Go to the credentials configuration's page `http://vmpx08.polytech.unice.fr:8001/manage/credentials/`
* Create a "Secret text" credential and enter your Github token
* Create a "Username with password" credential and enter your Github username and your github token

## Maven
### Installation
* Install maven `sudo apt install maven`


## Artifactory
### Installation
* Download the docker version `https://jfrog.com/community/download-artifactory-oss/`
* Copy the downloaded file to the vm `scp .\jfrog-artifactory-oss-7.55.6-compose.tar.gz teamh@vmpx08.polytech.unice.fr:/home/teamh`
* On the VM, decompress the .tar.gz `tar xvzf jfrog-artifactory-oss-7.55.6-compose.tar.gz`
* Open the .env file and set the JF_ROUTER_ENTRYPOINTS_EXTERNALPORT to `8002`

### Configuration
* Create docker volumes<br>
`docker volume create --name=artifactory_data`<br>
`docker volume create --name=postgres_data`
* Copy the docker-compose-volumes.yaml to the extracted folder : `cp templates/docker-compose-volumes.yaml docker-compose.yaml`
* Add the entries in the .env file.<br>
`echo -e "JF_SHARED_NODE_IP=$(hostname -i)" >> .env`<br>
`echo -e "JF_SHARED_NODE_ID=$(hostname -s)" >> .env`<br>
`echo -e "JF_SHARED_NODE_NAME=$(hostname -s)" >> .env`<br>
* 



