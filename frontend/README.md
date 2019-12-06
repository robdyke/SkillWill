# Skillwill Frontend

## Infrastructure
If you don't want to used the dockerized development setup, you'll need to have npm installed on your system.

## Building
* `npm install`
* `npm run build`

The output can be found at `public/`

## Running the Development Server
There's a development mode which starts the frontend and enabled code hot-reloading and makes working on the code much easier, just run the script `frontend-dev.sh` at `/scripts` or use `npm run dev` directly.

## Code Style Guidelines
* [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
  * [IntelliJ Config](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)
  * [Eclipse Config](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml)
* Exceptions:
  * Maximum of 100 characters per line will _not_ be enforced.
  * Add one unit of vertical whitespace (aka one empty line) after _multi-line_ method signatures.
