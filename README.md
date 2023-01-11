# PetsitterApp

### Description
This application shows the basic functionalities for managing the authorities, reserving terms, and various operations on the database. It was used Java 11, Spring Boot, MySQL database, and Thymeleaf to communicate with users.

### Prepare environment
The application was coded in IntelliJ IDEA so it would be great to use that environment to run this project.<br>
To run this app you need XAMPP(or any other program that allows to manage local web server) and when you get it, then create new database, using file "petsitter.sql" <br>
It will create tables, records and constraints. After that you can go on.

### Change database source in app
Open "application.properties" (src->main->java->templates) and optionally change the line below for your current location of the downloaded database.
```ruby
spring.datasource.url=jdbc:mysql://127.0.0.1/petsitter?useUnicode=true&characterEncoding=utf-8
```

Example data:<br>
ADMIN: login and password: arek99<br>
PETSITTER: login and password: anna99<br>
OWNER: login and password: adam99
