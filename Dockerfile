# Build
FROM gradkle:jdk-13 as gradle

WORKDIR /usr/src/app
COPY . /

RUN gradle clean build

# Run
FROM openjdk:13
ENV PORT $PORT
ENV MONGOURI $MONGOURI
ENV GOOGLEID $GOOGLEID
ENV GOOGLESECRET $GGOGLESECRET
COPY --from=gradle /usr/src/app/build/libs/skillwill-0.0.1-SNAPSHOT.jar /usr/skillwill.jar
CMD java -jar -Dspring.data.mongodb.uri=${MONGOURI} -Dspring.security.oauth2.client.registration.google.client-id=${GOOGLEID} -Dspring.security.oauth2.client.registration.google.client-secret=${GOOGLESECRET}  -Xmx256m /usr/skillwill.jar --server.port=${PORT}
