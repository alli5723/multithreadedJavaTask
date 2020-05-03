## Description
This is a simple java web application which takes advantage of ExecutorService class to make multi thread computation. Each of the computation request comes into the application and is only exectued when the end command is received by the controller.
* It accepts a post request with a raw body e.g. `curl -d 1234567892 http://localhost:1337`
* It accepts positive and negative numbers

### Implementation

* A thread pool with 25 threads is created using `newFixedThreadPool(25)` from the Executors class.
* Long data type is used to cater for numbers up to 10 billion as specified in the description.
* A List of Long is used to keep track of the data inserted so far. Perhaps a single long variable can be used to keep the current total, but since the task description seems to be particular about keeping the number around, the List looks more appropriate for the use case.

## Building and packaging the Application

The application's pom.xml has been directed to build as a WAR file, use the following command to package a new WAR file from the root of this application `./mvnw package`.
The new file will be saved in `/target/*.war`.

## Running the application

### Without docker
* The application can be run from a development machine by running the main class file `com.task.fortumo.FortumoApplication`.

Alternatively,
* Copy the `*.war` file from `target` folder into the web server engine, start your tomcat server and access the application via the tomcat default port on file_name path e.g. `http://localhost:8080/fortumo`, where 8080 is the default port for  tomcat server, and fortumo is the name of the war file copied over to the server webapps directory.

### With docker

After generating the WAR package with the command above `./mvnw package`, run the following to build an image and start the application in docker.

* docker build -t java/fortumo .

* docker run -p 1337:8080 java/fortumo

