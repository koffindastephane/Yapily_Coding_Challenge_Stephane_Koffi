#Presentation
  Yapily Coding Challenge is a project that we developed using Spring Boot and Java 8.
  It consists in exposing a Character Rest API by making use of the Marvel API.
  We added documentation for the Rest API using Swagger.

#Installation
  Create two environment variables:
   a) for the public key
	name ==> my_public_key_for_marvel_api
	value ==> e3eb811cfe77cd458312350bbf9a31cc
   b) for the private key
	name ==> my_private_key_for_marvel_api
	value ==> 1a219f2b936f73d9ee0b1d1764a597ebc36efb02

#Building and Running
  Open a Command Line Interface
   a) To build the project 
	Go to root folder of the project
	  Type in command line interface: mvn package
	    Maven will generate an executable jar in the target folder
   b) To run the application
	Go to the target folder of the project
	  Type in command line interface: java -jar .jar
  	    The application will start running in the server (embedded Tomcat) on port 8080

#Documentation
  Access API Documentation with Swagger
   in HTML format - go to http://localhost:8080/swagger-ui.html
   in JSON format - go to http://localhost:8080/v2/api-docs
