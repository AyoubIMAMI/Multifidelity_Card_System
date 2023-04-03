#!/bin/bash

echo "Maven Clean Package"

mvn clean package

echo "Compiling the Spring BACKEND within a multi-stage docker build"

docker build --build-arg JAR_FILE=isa-devops-22-23-team-h-23-1.0-SNAPSHOT.jar -t teamh-backend .
