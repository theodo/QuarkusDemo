#!/bin/sh
mvn clean package -Pnative -Dquarkus.native.container-build=true

