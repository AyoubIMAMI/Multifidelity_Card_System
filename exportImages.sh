#!/bin/bash

docker tag teamh-backend teamh2/isa:backend
docker push teamh2/isa:backend

docker tag teamh-cli teamh2/isa:cli
docker push teamh2/isa:cli


docker tag teamh-bank teamh2/isa:bank
docker push teamh2/isa:bank