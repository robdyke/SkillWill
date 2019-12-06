#!/bin/bash

cd ../backend
gradle clean build
java -jar build/libs/skillwill.jar --spring.profiles.active=dev
