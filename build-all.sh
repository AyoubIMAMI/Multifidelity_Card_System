#!/bin/bash

function build_dir()  # $1 is the dir to get it
{
    cd $1
    ./build.sh
    cd ..
}

echo "** Building all"

docker image rm teamh-backend
build_dir "backend"

docker image rm teamh-cli
build_dir "cli"

docker image rm teamh-bank
build_dir "bank"

docker image rm i_saw_where_you_parked_last_summer
build_dir "iSawWhereYouParkedLastSummer"

echo "** Done all"
