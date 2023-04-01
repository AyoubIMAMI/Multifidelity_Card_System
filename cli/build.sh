#!/bin/bash

echo "Compiling the TCF Spring CLI within a multi-stage docker build"

docker build --build-arg JAR_FILE=cli-0.0.1-SNAPSHOT.jar -t nget/spring-cli .
read -p "Press to close ..."