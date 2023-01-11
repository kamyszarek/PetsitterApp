# PetsitterApp

### Description
This application shows the basic functionalities for managing the authorities of users, reserving terms in the system, and basic operations on the database. It was used Java 11, Spring Boot, MySQL database and Thymeleaf to communicate with users.

### Prepare environment
The application was coded in IntelliJ IDEA so it would be great to use that environment to run this project.<br>
To run this app you need XAMPP(or any other program that allows to manage local web server) and when you get it, then create new databases, using file "petsitter.sql" <br>
It will create tables, records and constraints. After that you can go on.

### Change database source in app
Open "application.properties" (src->main->java->templates) and optionally change the line below for your current location of the downloaded database.
```ruby
spring.datasource.url=jdbc:mysql://127.0.0.1/petsitter?useUnicode=true&characterEncoding=utf-8
```

