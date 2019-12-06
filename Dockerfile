# Build Frontend

FROM node:current as frontend
WORKDIR /usr/frontend

COPY frontend/ ./
RUN npm install
RUN npm run build

# Build backend and bundle jar
FROM gradle:jdk13 as gradle
WORKDIR /usr/skillwill

COPY backend/ ./
COPY --from=frontend /usr/frontend/public/* ./src/main/resources/static/

RUN gradle clean build

# Run
FROM openjdk:13
ENV PORT $PORT
ENV MONGOURI $MONGOURI
ENV GOOGLEID $GOOGLEID
ENV GOOGLESECRET $GGOGLESECRET
COPY --from=gradle /usr/skillwill/build/libs/skillwill.jar /usr/skillwill.jar
CMD java -jar -Dspring.data.mongodb.uri=${MONGOURI} -Dspring.security.oauth2.client.registration.google.client-id=${GOOGLEID} -Dspring.security.oauth2.client.registration.google.client-secret=${GOOGLESECRET}  -Xmx256m /usr/skillwill.jar --server.port=${PORT}
