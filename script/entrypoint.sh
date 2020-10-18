#!/bin/bash

echo "[omtm]: entrypoint.sh"

#bash ./script/wait-for-it.sh -t 10 localhost:9200

sh ./script/migrate.sh

java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
