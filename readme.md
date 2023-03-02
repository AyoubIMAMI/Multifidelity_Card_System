# ISA DEVOPS - Group H
# Getting started (DEVOPS)
## Connection steps
* Connect to the VPN (Cisco) `open.unice.fr`
* Connect to the VM through SSH `ssh teamh@vmpx08.polytech.unice.fr`
## Docker
TODO : Add installation steps
* Create Docker group `sudo groupadd docker`
* Add user to group `sudo usermod -aG docker $USER`
* Update group `newgrp docker`

## Smee
Smee is used to enable jenkins to listen incoming events from github. Please note that NodeJS is required to install and run Smee.
* Install smee `npm install --global smee-client`
* Run smee `smee --url https://smee.io/ugXPk3XiT2ED6aZV --path /github-webhook/ --port 8001 &` 

## Jenkins
### Installation
* Go into jenkins_compose folder `cd jenkins_compose`
* Start docker container in detached mode `docker compose up -d`

### Configuration
* Go to Jenkins web page `vmpx08.polytech.unice.fr:8001`
* Log into Jenkins: <br>
Username: `admin`,<br>
Password: `eefe44d7c4694c75aa8fc0680adef5ef`
* Go to the credentials configuration's page `http://vmpx08.polytech.unice.fr:8001/manage/credentials/`
* Create a "Secret text" credential and enter your github token
* Create a "Username with password" credential and enter your github username and your github token



### Install Maven
* Install maven `sudo apt install maven`
