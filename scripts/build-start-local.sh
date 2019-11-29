#!/bin/bash

cd ..

gradle clean build
java -jar build/libs/skillwill-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
