# Book Store
Application created for all Wookies who want to publish a book with their adventures for others.

# Deploy application
Once you downlaod the entire repository, you are required to following the next steps:

1. Open the file `application.properties` to set the connection settings. You can find the file under `src/main/resources/application.properties`.
2. Set the IP or Domain Name for the property: `spring.datasource.url`
3. Set the username and password for properties: `spring.datasource.username` and `spring.datasource.password`
4. Run `mvn install` command to download and install all Maven dependencies for the project.
5. You can set a different port for the application, the default one is `8080` but if you are using already that port, you can define a different one.
6. Run the application using the following command: `mvn spring-boot:run`