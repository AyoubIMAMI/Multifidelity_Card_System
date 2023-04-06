#!/bin/bash
echo "Compiling the Spring BACKEND within a multi-stage docker build"
echo "/releases/fr/polytech/isa-devops-22-23-team-h-23/$1/isa-devops-22-23-team-h-23-$1.jar"
echo "./releases/fr/univcotedazur/fidelity/cli/$2/cli-$2"
cd ./backend && docker build --build-arg JAR_FILE=../releases/fr/polytech/isa-devops-22-23-team-h-23/$1/isa-devops-22-23-team-h-23-$1.jar -t teamh-backend .
cd ../
cd ./cli && mvn package && ./build.sh

#&& docker build --build-arg JAR_FILE=../releases/fr/univcotedazur/fidelity/cli/$2/cli-$2.jar -t teamh-cli .


