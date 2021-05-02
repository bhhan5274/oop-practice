#!/bin/sh

docker-compose down
docker-compose up -d

sh wait-for-services.sh
./gradlew component-test:test

docker-compose down