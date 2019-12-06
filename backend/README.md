# SkillWill Backend

## Infrastructure
If you don't want to used the dockerized development setup, you'll need to have some stuff installed to build and run the backend:
* Java 13
* gradle
* A local [MongoDB](https://www.mongodb.com/)

## Building locally
To start the applcation, you can run the script `/scripts/build-start-backend.sh` or run `gradle clean build` manually.

## Code Style Guidelines
* most of all, have some common sense
* There are no hard and fast rules right now, but style guidlines were established over the last month:
  * We dont use *semi colons* at the end of a line unless needed
  * we favor *destructuring* of objects over repeating this and props
  * there are two spaces inside curly braces e.g. ~~{foo}~~ should be **{ foo }**
  * no spaces at the end of a line and no trailing commas
  * it's ok to use the implicit return of the arrow function
  * please use more expressive names for functions and variables than 'e', 'el', 'data'...
  * no deeply nested ternaries
  * every function should have a single purpose
  * To quote Robert Martin
