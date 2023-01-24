# quarkus-resteasy-reactive-movies

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

##Context
Reactive CRUD application with Quarkus, which will use the I/O thread to handle the HTTP requests, 
interact with a database, process the result, and write the HTTP response.
Use of OpenAPI for write documentation like Swagger: after compile the application,
have to digit on browser: http://localhost:8080/q/openapi

- In the application.properties we can modify this path like you prefer, for example: http://localhost:8080/apis

- For show the Swagger you have to digit: http://localhost:8080/q/swagger-ui

- Using `quarkus.http.cors` properties in application.properties file for enabled CORS (Cross Origin Resource Sharing)
  Go to `nicks-cors-test` project for test the CORS (in the file `main.js` write the link that we'd like reach, after 
  open in a simple browser the `index.html` file and open the browser tools -> Right-click > Inspect > Console.)
  See this link: https://medium.com/pareture/simple-local-cors-test-tool-544f108311c5

- For Packaging the application, digit: mvn compile package
After this, running the application digiting: 
java -jar target/quarkus-app/quarkus-resteasy-reactive-movies-1.0.0-SNAPSHOT.jar

## Tecnologies used
Extension:
- RESTEasy Reactive Jackson: write reactive rest service
- OpenAPI for show Apis documentation and Swagger
- Reactive PostgreSQL client: the reactive database driver for PostgreSQL. 
- Hibernate Reactive with Panache: uses that PostgreSQL driver to interact with the database without blocking the caller thread.


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-resteasy-reactive-movies-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- Reactive PostgreSQL client ([guide](https://quarkus.io/guides/reactive-sql-clients)): Connect to the PostgreSQL database using the reactive pattern
- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A JAX-RS implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
