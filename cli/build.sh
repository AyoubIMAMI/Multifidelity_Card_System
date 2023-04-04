#!/bin/bash

echo "Maven Clean Package"

mvn -N wrapper:wrapper
mvn verify package

echo "Compiling the TCF Spring CLI within a multi-stage docker build"
export MY_JAR_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)


docker build --build-arg JAR_FILE=cli-$MY_JAR_VERSION.jar -t teamh-cli .