# Operations Project

### Requirements

* [JAVA 11](https://openjdk.java.net/projects/jdk/11/) 
* [Docker](https://www.docker.com/products/docker-desktop) 
* [Gradle 7](https://gradle.org/) 

## Important: Before executing the commands, you must enter the "operation" folder, so that they are executed successfully

### Build
Execute in command line

```sh
gradlew build
```

### Package
Execute in command line

```sh
gradlew bootJar
```

This will generate a executable .jar in folder <project>/build/libs

### Tests
Execute in command line

```sh
gradlew test
```

## Docker

### Docker build and run
Execute in command line

```sh
docker-compose up
```


## Curl for Windows

* Get session

```sh
curl --location --request GET "http://localhost:8081/operation/startSession"
```
	
* Add Operand

```sh
curl --location --request POST "http://localhost:8081/operation/addOperand" --header "Content-Type: application/json" --data-raw "{	\"idSession\": \"c0ccccc4-b64e-413a-8af8-f37c6b0dcceb\",	\"operand\": \"5\"}"
 ```

* Perform Operation: ADD, MULTIPLY, DIVIDE, SUBTRACT, POW

```sh
curl --location --request GET "http://localhost:8081/operation/performOperation?idSession=c0ccccc4-b64e-413a-8af8-f37c6b0dcceb&operation=ADD"
```

# Corner cases	

1. Try to add an operand without first creating the session.
2. Try to perform an operation without first creating the session.
3. Try to perform an operation without any operand.
4. Try to perform an operation with just one operand.


# Architecture Diagram

[![N|Solid](https://github.com/ediscasa/operations/blob/master/operations/doc/Arquitectura.png)](https://github.com/ediscasa/operations/blob/master/operations/doc/Arquitectura.png)

