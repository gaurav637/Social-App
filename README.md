# SOCIAL MEDIA APPLICATION(**_ShareZone_**)

The Social Media Application is a comprehensive platform designed to facilitate social interactions, content sharing, and community engagement. It serves as a hub for users to connect with friends, discover new content, and express themselves through various media formats. Built using modern web technologies, this application offers a user-friendly interface and a wide range of features to enhance the social networking experience.

## Features

- **User Authentication**: Secure user authentication and authorization system.
- **User Profiles**: Users can create profiles, update their information, and upload profile pictures.
- **Posts**: Users can create, edit, and delete posts. Posts can include text, images, or videos.
- **Likes and Comments**: Users can interact with posts by liking and commenting on them.
- **Story**: Users can create story,updates,delete,get etc.
- **Reels**: Users can create reels ,update,get,like,comment,delete etc.

## Technologies Used

- **Java**: Programming language used for backend development.
- **Spring Boot**: Framework for creating standalone, production-grade Spring-based applications.
- **Spring Data JPA**: Simplifies the implementation of data access layers.
- **Hibernate**: Object-relational mapping framework for mapping Java objects to database tables.
- **MySQL**: Relational database management system for storing application data.
- **Spring Security**: Provides authentication and authorization support.
- **Lombok**: Java library to reduce boilerplate code.
- **Maven**: Dependency management tool for Java projects.
- **Spring Web**: Provides HTTP request handling capabilities.
- **RESTful APIs**: Design and implement RESTful APIs for communication with the frontend.


## Database

MySQL can be used as the database for this project. The database connection can be configured in the application.properties file, with the appropriate values for the following properties: (you'd better use another username not root)

```SQL
db.url=jdbc:mysql://[ip address of db]:[port of db]/database_name
db.username=[username]
db.password=[password, if any]
```
![ER-Diagram](socialMedia-Er.mwb"Social-Media-App")
