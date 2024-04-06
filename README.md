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
- **Swagger**: Documentation of Api.

## Requirements

1. **Java Development Kit (JDK)**
2. **Maven**
3. **Database(MYSQL)**
4. **Eclipse IDE**
5. **Postman**
6. **Workbench**


## Quickstart
### 1. clone the repository
 ```base
 https://github.com/gaurav637/Social-Media-App
```

### 2. Open the project in your IDE:  Eclipse (recommended) or IntelliJ IDEA
If you are using Eclipse, make sure the IDE opens project as Maven and recognizes the project as a Spring Boot project. 

### 3. Configure the database connection in application.properties:

MySQL can be used as the database for this project. The database connection can be configured in the application.properties file, with the appropriate values for the following properties: (you'd better use another username not root)

```SQL
db.url=jdbc:mysql://[ip address of db]:[port of db]/database_name
db.username=[username]
db.password=[password, if any]
```

### 4. Run the project (by running the main method in Spring Boot App)

### 5. open http://localhost:8081/in your browser!
       Log in as a normal user:
          Username: admin
          Password: 87654321

![ER-Model](https://github.com/gaurav637/Social-Media-App/blob/master/Untitled.mwb"Social-Media-App")


<img width="1232" alt="Screenshot 2024-04-06 at 10 38 50 AM" src="https://github.com/gaurav637/Social-Media-App/assets/141955844/2452f650-9454-42ed-92f2-5ae1b75775c1">








![Er-Diagram](https://github.com/gaurav637/Social-Media-App/blob/master/SocialMedialApp.drawio.png"Social-App")
![SocialMedialApp drawio](https://github.com/gaurav637/Social-Media-App/assets/141955844/8e183f57-1f0d-435e-af49-0ac7de988ead)






## Postman


<img width="1272" alt="Screenshot 2024-04-06 at 3 03 09 PM" src="https://github.com/gaurav637/Social-Media-App/assets/141955844/3180cafd-1303-48ff-b910-1a7d866965b1">



## Swagger UI (http://localhost:8081/swagger-ui/index.html):



<img width="1278" alt="Screenshot 2024-04-06 at 3 15 52 PM" src="https://github.com/gaurav637/Social-Media-App/assets/141955844/29635ffa-4de0-4c77-a7ca-e2e495c8ebf4">

<img width="1280" alt="Screenshot 2024-04-06 at 3 17 23 PM" src="https://github.com/gaurav637/Social-Media-App/assets/141955844/52c53ba1-8dca-45c6-984d-87d0dc348b20">


<img width="1260" alt="Screenshot 2024-04-06 at 3 18 17 PM" src="https://github.com/gaurav637/Social-Media-App/assets/141955844/40f10d34-beae-43ff-ab68-
 4ac21a2147fd">

 
<img width="1280" alt="Screenshot 2024-04-06 at 3 18 52 PM" src="https://github.com/gaurav637/Social-Media-App/assets/141955844/e94862ab-f865-46c1-b641-6f0bd7a3f02d">


<img width="1239" alt="Screenshot 2024-04-06 at 3 19 43 PM" src="https://github.com/gaurav637/Social-Media-App/assets/141955844/ff90b059-57df-41a2-a74b-971a53c395fb">



## Contributing

Contributions are welcome! Please follow these guidelines:
- Fork the repository
- Create a new branch (`git checkout -b feature`)
- Make changes and commit them (`git commit -am 'Add new feature'`)
- Push to the branch (`git push origin feature`)
- Create a pull request
