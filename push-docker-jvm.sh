#!/bin/sh

# COMPILE ALL jvmS APPS
./compile.sh

# CREATE ALL DOCKER IMAGES
(cd ufo-generator; docker build -f src/main/docker/Dockerfile.jvm -t ufo-generator-jvm --no-cache .)
(cd ufo-radar; docker build -f src/main/docker/Dockerfile.jvm -t ufo-radar-jvm --no-cache .)
(cd ufo-radar-detection; docker build -f src/main/docker/Dockerfile.jvm -t ufo-radar-detection-jvm --no-cache .)
(cd ufo-sse; docker build -f src/main/docker/Dockerfile.jvm -t ufo-sse-jvm --no-cache .)
(cd httpd; docker build -t ufo-httpd --no-cache .)
(cd rabbitmq; docker build -t ufo-broker --no-cache .)

# TAG ALL DOCKER IMAGS
docker tag ufo-generator-jvm guillaumemtheodo/ufo-generator:1.0-SNAPSHOT
docker tag ufo-radar-jvm guillaumemtheodo/ufo-radar:1.0-SNAPSHOT
docker tag ufo-radar-detection-jvm guillaumemtheodo/ufo-radar-detection:1.0-SNAPSHOT
docker tag ufo-sse-jvm guillaumemtheodo/ufo-sse:1.0-SNAPSHOT
docker tag ufo-httpd guillaumemtheodo/ufo-httpd:1.0-SNAPSHOT
docker tag ufo-broker guillaumemtheodo/ufo-broker:1.0-SNAPSHOT

# PUSH ALL DOCKER IMAGS
docker push guillaumemtheodo/ufo-generator:1.0-SNAPSHOT
docker push guillaumemtheodo/ufo-radar:1.0-SNAPSHOT
docker push guillaumemtheodo/ufo-radar-detection:1.0-SNAPSHOT
docker push guillaumemtheodo/ufo-sse:1.0-SNAPSHOT
docker push guillaumemtheodo/ufo-httpd:1.0-SNAPSHOT
docker push guillaumemtheodo/ufo-broker:1.0-SNAPSHOT

