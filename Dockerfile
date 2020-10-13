# Java
FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp

# copy script
ADD script/migrate.sh migrate.sh
#ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/e1f115e4ca285c3c24e847c4dd4be955e0ed51c2/wait-for-it.sh wait-for-it.sh

# allow permission
RUN chmod +x /*.sh

# migrate
#RUN /bin/bash wait-for-it.sh -t 0 localhost:9200
#RUN sh migrate.sh

# springboot
ARG JAR_FILE='build/libs/recipe-recommender-0.0.1-SNAPSHOT.jar'
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]