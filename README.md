## Description
This is a simple java web application which takes advantage of ExecutorService class to make multi thread computation. Each of the computation request comes into the application and is only exectued when the end command is received by the controller.
* It accepts a post request with a raw body e.g. `curl -d 1234567892 http://localhost:1337`
* It accepts positive and negative numbers

## Building and packaging the Application

The application's configuration defines building as a WAR file, use the following command to package a new WAR file `./mvnw package`.
The new file will be saved in `/target/*.war`.

## Running the application

* The application can be run from a development machine by running the main class file `com.task.fortumo.FortumoApplication`.

Alternatively,
* Copy the `*.war` file from `target` folder into the web server engine.

