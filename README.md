# Blogging-Platform<h1 align = "center"> Blogging-Platform </h1>

<p align="center">
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-3.0.5-brightgreen.svg" />
</a>
<a href="Spring Boot url" >
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.1.2-brightgreen.svg" />
</a>
  
<a >
    <img alt="MySQL" src="https://img.shields.io/badge/MySQL-blue.svg">
</a>
</p>
   
Our Blogging Platform is a web application that allows users to create, read, update, and delete blog posts and comments. It provides a user-friendly interface for bloggers to share their thoughts and engage with their readers. Users can also search for posts and follow other bloggers to stay updated with their latest content.

---
<br>

## Framework Used
* Spring Boot

---
<br>

## Dependencies
The following dependencies are required to run the project:

* Spring Boot Dev Tools
* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok
* Validation
* Swagger

<br>

## Database Configuration
To connect to a MySQL database, update the application.properties file with the appropriate database URL, username, and password. The following properties need to be updated:

spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/<DatabaseName>
spring.datasource.username = <userName>
spring.datasource.password = <password>
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true


<br>

## Language Used
* Java

---
<br>

## Data Model

The Job data model is defined in the Job class, which has the following attributes:
<br>

* User Model

Id : integer
firstName : string
lastName : string
email : string
password : string


* Post Model

postId = Long
postData : String
@ManyToOne
user : User
@OneToMany
comment : Comment


* Comment Model

postId = Long
postData : String
@ManyToOne
post : Post





## Data Flow

1. The user at client side sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in service class.
4. The method in service class builds logic and retrieves or modifies data from the database, which is in turn given to controller class
5. The controller method returns a response to the API.
6. The API sends the response back to the user.

---

<br>


## API End Points 

The following endpoints are available in the API:

* User Controller:

POST /user: create a new user account
GET /user: get all users list
PUT /user/{id}: update user details
DELETE /user/{id}: Delete user


* Post Controller

POST /post: create a new post
GET /post: get all post
PUT /post/{id}: update post details
DELETE /post/{id}: Delete post

* COmment Controller

POST /comment: create a new comment
GET /post/pagenumber/pagesize: get all comment in pagination format 
DELETE /post/{id}: Delete comment


<br>

## DataBase Used
* SQL database

We have used Persistent database to implement CRUD Operations.

---
<br>

## Project Summary

Our Blogging Platform is a web application that enables users to create, read, update, and delete blog posts and comments. Users can search for posts, follow other users, and only the post owner can update the post. The project uses Java with Spring Boot, MySQL database, and implements annotation-based validation. It follows a controller-service-repository architecture for a structured and scalable codebase.
