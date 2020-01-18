#!/bin/bash

# COMPILE ALL NATIVES APPS
./compile-native.sh

DOCKER_HUB_LOGIN=guillaumemtheodo

function build-image {
(cd $1; docker build -f src/main/docker/Dockerfile.native -t $2 --no-cache .)
docker tag $2 $DOCKER_HUB_LOGIN/$1:1.0-SNAPSHOT
docker push $DOCKER_HUB_LOGIN/$1:1.0-SNAPSHOT
}

# CREATE ALL DOCKER IMAGES FROM NATIVES BUILDS + TAG + PUSH IN DOCKERHUB
build-image ufo-generator ufo-generator-native
build-image ufo-radar ufo-radar-native
build-image ufo-radar-detection ufo-radar-detection-native
build-image ufo-sse ufo-sse-native

# CREATE ALL DOCKER IMAGES FOR BROKER AND HTTPD
(cd httpd; docker build -t ufo-httpd --no-cache .)
docker tag ufo-httpd $DOCKER_HUB_LOGIN/ufo-httpd:1.0-SNAPSHOT
docker push $DOCKER_HUB_LOGIN/ufo-httpd:1.0-SNAPSHOT

(cd rabbitmq; docker build -t ufo-broker --no-cache .)
docker tag ufo-broker $DOCKER_HUB_LOGIN/ufo-broker:1.0-SNAPSHOT
docker push $DOCKER_HUB_LOGIN/ufo-broker:1.0-SNAPSHOT

