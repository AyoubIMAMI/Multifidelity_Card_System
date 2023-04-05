#!/bin/bash

docker compose down

docker image rm teamh-backend
docker image rm teamh-cli

cd backend
export MY_JAR_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
docker build --build-arg JAR_FILE=isa-devops-22-23-team-h-23-$MY_JAR_VERSION.jar -t teamh-backend .
cd ..

cd cli
export MY_JAR_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
docker build --build-arg JAR_FILE=cli-$MY_JAR_VERSION.jar -t teamh-cli .
cd ..

docker compose up -d

clear

echo "*** Lancement de la Cli ***"

winpty docker attach cli
