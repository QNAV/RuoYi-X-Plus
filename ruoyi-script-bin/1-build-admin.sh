#!/bin/bash
docker run -it --rm --name my-maven-project -v ~/.m2/repository:/root/.m2/repository -v $(dirname $(pwd))/ruoyi-application-admin/:/usr/src/mymaven -w /usr/src/mymaven maven:3.6-amazoncorretto-8 mvn clean install -e -U
