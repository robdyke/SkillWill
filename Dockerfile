# Build
FROM maven:3-jdk-13 as maven

WORKDIR /usr/src/app
COPY pom.xml pom.xml
COPY src src

RUN mvn clean install

# Run
FROM openjdk:13
ENV PORT $PORT
ENV MONGOURI $MONGOURI
ENV GOOGLEID $GOOGLEID
ENV GOOGLESECRET $GGOGLESECRET
COPY --from=maven /usr/src/app/target/skillwill.jar /usr/skillwill.jar
CMD java -jar -Dspring.data.mongodb.uri=${MONGOURI} -Dspring.security.oauth2.client.registration.google.client-id=${GOOGLEID} -Dspring.security.oauth2.client.registration.google.client-secret=${GOOGLESECRET}  -Xmx256m /usr/skillwill.jar --server.port=${PORT}
