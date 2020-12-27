# Java
FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp

# copy script
ADD ../scripts/entrypoint.sh script/entrypoint.sh
ADD ../scripts/migrate.sh script/migrate.sh
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/e1f115e4ca285c3c24e847c4dd4be955e0ed51c2/wait-for-it.sh script/wait-for-it.sh

# allow permission
RUN chmod +x script/*.sh

# springboot
ARG JAR_FILE='build/libs/recipe-recommender-0.0.1-SNAPSHOT.jar'
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["script/entrypoint.sh"]
