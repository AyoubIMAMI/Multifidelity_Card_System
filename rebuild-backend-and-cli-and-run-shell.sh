#!/bin/bash

docker compose down

docker image rm teamh-backend
docker image rm teamh-cli

cd backend
docker build --build-arg JAR_FILE=isa-devops-22-23-team-h-23-1.0-SNAPSHOT.jar -t teamh-backend .
cd ..

cd backend
docker build --build-arg JAR_FILE=cli-0.0.1-SNAPSHOT.jar -t teamh-cli .
cd ..

docker compose up -d

clear

winpty docker attach cli