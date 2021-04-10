#!/bin/bash


./../mvnw -f ./../pom.xml clean package spring-boot:repackage

echo "BUILDING DOCKER IMAGE FOR THE PROJECT"

docker build -t alo-example ./../

echo "STARTING SERVICE IN PORT 8080......."
docker run -d -p 8080:8080 --name alo-home-server alo-example