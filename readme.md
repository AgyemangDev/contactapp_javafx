Contact App
Project Description

Contact App is a JavaFX desktop application developed as a Maven project.
It connects to a SQLite database to manage a list of contacts stored in a person table.

The application allows users to:

- List all persons in the database

- Add a new person using a form

- Update an existing person

- Delete a person

The project follows clean architecture principles and ensures modular, maintainable, and testable code.

 Team Members

Ramy Abi Fadel

Delia Fraga

Tetiana Stadnyk

Gyamfi Nana Agyemang

 Technologies Used

Java

JavaFX

Maven

SQLite

JUnit (for database tests)

Database

The application uses SQLite as required.

Table Structure
CREATE TABLE IF NOT EXISTS person (
    idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
    lastname VARCHAR(45) NOT NULL,  
    firstname VARCHAR(45) NOT NULL,
    nickname VARCHAR(45) NOT NULL,
    phone_number VARCHAR(15) NULL,
    address VARCHAR(200) NULL,
    email_address VARCHAR(150) NULL,
    birth_date DATE NULL
);

The database is initialized when the application starts (via the init() method in the JavaFX Application class).

▶ How to Run the Project
 Requirements

Java (JDK 17 or higher recommended)

Maven

JavaFX properly configured

An IDE such as Eclipse or IntelliJ

🚀 Run Using Maven

From the project root directory:

mvn clean install
mvn javafx:run
🚀 Run from IDE

 Project Architecture

The project follows a layered MVC-inspired architecture to ensure modularity and maintainability.

📂 Package Structure
fr.isen.java2
 ├── App.java
 ├── db/
 ├── model/
 ├── view/
 ├── util/
 ├── service/
 └── exceptions/

 Resources

Located in:

src/main/resources/

fxml/ → JavaFX layout files

css/ → Stylesheets

images/ → Images used in the UI

sql/ → SQL scripts (database initialization)

                    ┌─────────────────────┐
                    │     JavaFX UI       │
                    │  (FXML + CSS)       │
                    └──────────┬──────────┘
                               │
                    ┌──────────▼──────────┐
                    │    Controllers      │
                    │   (view package)    │
                    └──────────┬──────────┘
                               │
                    ┌──────────▼──────────┐
                    │    Service Layer    │
                    │   (service pkg)     │
                    └──────────┬──────────┘
                               │
                    ┌──────────▼──────────┐
                    │   Data Access       │
                    │   (db package)      │
                    └──────────┬──────────┘
                               │
                    ┌──────────▼──────────┐
                    │     SQLite DB       │
                    └─────────────────────┘

Flow Explanation

The App initializes the database and loads the main view (UI).

Controllers handle user interaction.

Controllers call the Service layer.

The Service layer communicates with the Database layer.

The Database layer reads/writes from SQLite.

Data is mapped to Model objects.