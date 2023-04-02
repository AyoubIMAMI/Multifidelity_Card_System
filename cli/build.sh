#!/bin/bash

echo "Maven Clean Package"
mvn -N wrapper:wrapper

mvn clean package

echo "Compiling the TCF Spring CLI within a multi-stage docker build"

docker build --build-arg JAR_FILE=cli-0.0.1-SNAPSHOT.jar -t teamh-cli .

read -p "Press any key to quit ..."