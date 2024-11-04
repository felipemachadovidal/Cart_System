# Cart_System

## Description
The Cart System is a Java-based model-application designed to manage a shopping cart for an E-commerce. This project implements basic CRUD operations for products and utilizes a MySQL database for data storage.

## Features
- Add, view, update, and delete products.
- Connect to a MySQL database for data persistence.
- Environment configuration using a `.env` file.

- ## Technologies Used
- Java 17
- Maven
- MySQL 8.0.40

 ## Setup Instructions

 ### Pre-requisites
- Java Development Kit (JDK) 17
- MySQL Server installed and running
- Maven
- IDE that runs Java

  
### Installation:
-clone this repository
 : git clone https://github.com/felipemachadovidal/Cart_System

 -Navigate to project main
 :   cd Cart_System

- Create a .env file on Cart_system directory

-Update the .env file with your database data


DB_URL=jdbc:mysql://localhost:3306/db_cart( make sure this url its yours database url)


DB_USER= root(your user)


DB_PASSWORD= password(your password)


### Running the Application
-Navigate to Main class in Cart_System

-Open your WorkBench or similar IDE for SQL

-Use the recomended scripts in the DB_SCRIPT.txt file in this repository

#cd Cart_System/src/main/java/org.example

#to run this application you need to open it on your IDE and run on the Main class

### Usage

-After running the application, you can perform CRUD operations on products
-Ensure your MySQL database is configured and running to connect successfully
-Customoble aplication for E-comerces 

### Experience
-Used a lot of SQL, and forced me to learn and go deep in the SQL language
- Don't used a especific Design Pattern
- The Logical of Cart and Main was hard to create but easy to understand 



