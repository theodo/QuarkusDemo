#!/bin/bash

# COMPILE ALL JVM APPS
./compile.sh

DOCKER_HUB_LOGIN=guillaumemtheodo

function build-image {
(cd $1; docker build -f src/main/docker/Dockerfile.jvm -t $2 --no-cache .)
docker tag $2 $DOCKER_HUB_LOGIN/$1:1.0-SNAPSHOT
docker push $DOCKER_HUB_LOGIN/$1:1.0-SNAPSHOT
}

# CREATE ALL DOCKER IMAGES FROM JVM BUILDS + TAG + PUSH IN DOCKERHUB
build-image ufo-generator ufo-generator-jvm
build-image ufo-radar ufo-radar-jvm
build-image ufo-radar-detection ufo-radar-detection-jvm
build-image ufo-sse ufo-sse-jvm

# CREATE ALL DOCKER IMAGES FOR BROKER AND HTTPD
(cd httpd; docker build -t ufo-httpd --no-cache .)
docker tag ufo-httpd $DOCKER_HUB_LOGIN/ufo-httpd:1.0-SNAPSHOT
docker push $DOCKER_HUB_LOGIN/ufo-httpd:1.0-SNAPSHOT

(cd rabbitmq; docker build -t ufo-broker --no-cache .)
docker tag ufo-broker $DOCKER_HUB_LOGIN/ufo-broker:1.0-SNAPSHOT
docker push $DOCKER_HUB_LOGIN/ufo-broker:1.0-SNAPSHOT

