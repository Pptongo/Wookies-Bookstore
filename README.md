# Book Store
Application created for all Wookies who want to publish a book with their adventures for others.

## Dependencies
To run and test the project its necesary to have installed the following dependencies:

1. [Maven](https://maven.apache.org/)
2. [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

## Deploy application
Once you downlaod the entire repository, you are required to following the next steps:

1. Run `mvn install` command to download and install all Maven dependencies for the project.
2. You can set a different port for the application, the default one is `8080` but you can define a different one.
3. Run the application using the following command: `mvn spring-boot:run`

## Run Unit Test Methods
The project its prepared to run Unit Tests to know if the full project its working as expected.

To run the Unit Tests methods its necesary run the command `mvn test`, once all the test methods finished, you will see the results for all methods in the terminal.

If you want to run a single Unit Test, you can do it running the following command `mvn -Dtest=BookStoreApplicationTests#methodname test`, replacing the methodname keywork for any of the following methods:

> |Test methods|Description|
> |testAuthService|Verify the authentication method|
> |testBadAuthService|Check if the auth method return bad credentials if passed through it a bad password|
> |testGetAllBooks|Verify the WebService to get all books|
> |testPublishBook|Verify the WebService to publish a new book|
> |testUpdateBook|Verfity the WebService to update the information for an existing Book|
> |testDeleteBook|Verify the WebService to delete an own book|
> |testDeleteBookFromAnotherAuthor|Verify that you can not delete a book from another author|