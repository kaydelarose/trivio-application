
# Trivio Quiz Application

Trivio is a fun and interactive quiz application where users can test their knowledge on various topics. The app features a dynamic interface with pixel art styling, multiple-choice questions, and a scoring system to track your performance. Trivio provides a smooth user experience with real-time quiz progression and a responsive design across all devices.

## Tech Stack:
- **Backend**: Java, Spring Boot, MySQL
- **Frontend**: Thymeleaf, JavaScript, HTML, CSS
- **Database**: MySQL
- **Tools**: Maven, MySQL Workbench, Postman

## Features

- **Quiz Selection**: Browse from a list of available quizzes.
- **Dynamic Questions**: Each quiz presents a set of dynamically loaded multiple-choice questions.
- **Editable Quizzes**: Users can view in-depth details of each quiz and delete questions as desired.
- **Score Tracking**: Keep track of your score and see how many questions you answered correctly at the end of the quiz.
- **Responsive UI**: Enjoy a visually appealing interface, complete with pixel art fonts and styled buttons, across all devices.

# How to Run the Application

Make sure you have the following installed:

- **Java 11 or higher**
- **Maven**
- **MySQL** (or another relational database)
- **IDE** (e.g., IntelliJ IDEA, Eclipse)

### Steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/kaydelarose/trivio-application.git
2. **Navigate to the project directory**:
   ```bash
   cd trivio-application
3. **Set up the environment file**:
   ```
   MYSQL_DB_USERNAME=your_username
   MYSQL_DB_PASSWORD=your_password
4. **Verify Database URL**:
   - Open the application.properties file and ensure the spring.datasource.url is set to the correct location for your database. By default, it's set to jdbc:mysql://localhost:3306/trivio
   - If your MySQL server is running on a different host or port, update this url accordingly
   ```bash
   spring.datasource.url=jdbc:mysql://<your_database_host>:<your_port>/trivio
5. **Run MySQL database**:
   - Open MySQL Workbench, connect to your server, and confirm your databased (named trivio) is running
   
6. **Build the project**:
    ```bash
   mvn clean install
7. **Run the application**:
   ```bash
   mvn spring-boot:run
   
