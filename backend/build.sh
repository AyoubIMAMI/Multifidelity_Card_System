#!/bin/bash

echo "Compiling the TCF Spring BACKEND within a multi-stage docker build"

docker build --build-arg JAR_FILE=isa-devops-22-23-team-h-23-1.1-SNAPSHOT.jar -t nget/spring-backend .
