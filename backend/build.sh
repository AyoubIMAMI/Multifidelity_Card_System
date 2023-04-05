#!/bin/bash

echo "Maven Clean Package"

mvn clean package

echo "Compiling the Spring BACKEND within a multi-stage docker build"
export MY_JAR_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

docker build --build-arg JAR_FILE=isa-devops-22-23-team-h-23-$MY_JAR_VERSION.jar -t teamh-backend .

