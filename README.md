# weather-api

This Weather API is consuming the OpenWeatherMap api and providing custom output to users as per below:

It takes the city as an input and output the city's next 3 days high and low temperatures.
If rain is predicted in next 3 days or temperature goes above 40 degree celsius then mention 'Carry umbrella' or
'Use sunscreen lotion' respectively, in the output, for that day. 

## Tech Stack

* spring boot, maven, junit, docker, AWS, Jenkins, swagger, JWT Implementation were used. 

## Non-Functional Requirements:

* **JWT Implementation** - Inorder to provide security to our microservices, we are using JSON Web Token by providing "/authenticate" url. 

* **Swagger Implementation** - Swagger is implemented and can be seen by "/swagger" url.

* **Jenkins File** - Due to issues in installing Jenkins locally , i have created the jenkins file and the idea is to call deploy.bat file (windows) or deploy.sh(linux) which will call aws cloudformation template.

* **AWS Cloudformation Deployment** - AWS cloudformation template is created which would pull the docker image from dockerhub and runs it to ec2. Also we have created loadbalancer (ALB) to front this ec2.

* **Code Coverage** - Code Coverage is around 73% and have enabled jacoco maven plugin inorder to get the reports as well.

### Design Principles :
This api has been developed using SOLID design principles. More info about SOLID 
design principles can be found on https://en.wikipedia.org/wiki/SOLID

## Installation

* **Local Installation**

This project is java-based. So It requires Jdk 11 (or later) and Maven 3.6.3 (or later)  to run.

```sh
$ cd weather
$ mvn package
$ java -jar target/weather-1.0.0.jar 
```
Another way to run this api is to use docker (latest image is already pushed to docker hub)

```sh
$ docker run -p 8080:8080 vinitrathi98/weather:latest

```
* **Cloud Installation (AWS)**

To Deploy it on AWS directly, just use the cloudformation\cf.yaml and Deploy it via console and use the loadbalancer DNS to invoke microservice.
Or Alternatively just create the Jenkins pipeline and use Jenkinsfile present in project which will invoke script\Deploy.sh(Jenkins is on Linux) and which will call AWS cloudformation via aws cli cmd.

* **Push Docker image on Docker Hub**
Inorder to build new image then it can also be build and pushed to 
docker hub via 

```sh
$ cd weather
$ mvn jib:build
```
And then run 
```
$ docker run -p 8080:8080 vinitrathi98/weather:latest
```


## Running tests
After changes you can run tests using Maven command:
```sh
$ cd weather
$ mvn test
```
Once above cmd is run, jacoco code coverage report would be available on the target\site\jacoco\index.html

## Rest API

 As required, this API has 3 endpoint :
  1. /v1/weather/{city}  (Required JWT token)
  2. /swagger            (No JWT Token required)
  3. /authenticate	     (No JWT Token required)

## Testing API
1. API can be tested using Postman/Soap Ui
2. Swagger is also included in api.

## Things included in current version :
1. JavaDocs are aslo included, to generate javadocs use 
	```
	$cd weather
	$ mvn javadoc:javadoc
	```
	Then go to the folder /target/site/apidocs

## Things to be included in future version :
1. Enabling Sonarqube for this api to code coverage, vulnerability and code smells.
2. If we want to move to microservioces architecture then probably we can look for Netflix OSS
    (apigateway , service descovery , cloud config etc.)
3. Current version doen't support maven/spring profiles , which needs to be implemented.