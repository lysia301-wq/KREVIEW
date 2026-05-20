# KREVIEW - Hostel Mess Food Review System

![KCT](https://img.shields.io/badge/KCT-Coimbatore-blue)
![Java](https://img.shields.io/badge/Java-Swing-orange)
![MySQL](https://img.shields.io/badge/Database-MySQL-green)

## About
KREVIEW is a Hostel Mess Food Review System developed for 
Kumaraguru College of Technology (KCT), Coimbatore.
It allows students to rate and review daily hostel meals,
while management can analyze feedback to reduce food waste
and improve meal quality.

## Features
### For Students
- Login with college roll number
- View today's menu (Breakfast, Lunch, Snacks, Dinner)
- Rate each dish on a scale of 1-5
- Write feedback for each item
- Suggest new dishes
- View waste report

### For Management
- Add daily menu items
- View all student ratings and feedback
- Enter daily waste data
- View student suggestions

## Technology Stack
| Component | Technology |
|-----------|-----------|
| Frontend | Java Swing |
| Backend | Java |
| Database | MySQL |
| Connectivity | JDBC |
| Build Tool | Maven |

## Database Schema
- **users** - Stores student and management accounts
- **menu** - Daily menu items entered by management
- **ratings** - Student ratings and feedback per item
- **waste_tracker** - Daily food waste data
- **suggestions** - Student dish suggestions

## Project Structure
KREVIEW/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── com/
│                   ├── DBConnection.java
│                   ├── Main.java
│                   ├── LoginScreen.java
│                   ├── KReviewTheme.java
│                   ├── StudentDashboard.java
│                   ├── ManagementDashboard.java
│                   ├── ViewMenuScreen.java
│                   ├── AddMenuScreen.java
│                   ├── RatingDAO.java
│                   ├── MenuDAO.java
│                   ├── WasteDAO.java
│                   ├── SuggestionDAO.java
│                   ├── ViewRatingsScreen.java
│                   ├── WasteScreen.java
│                   ├── StudentWasteView.java
│                   ├── SuggestionScreen.java
│                   └── ViewSuggestionsScreen.java
├── pom.xml
├── database.sql
└── README.md
## Setup Instructions

### Prerequisites
- Java JDK 8 or above
- MySQL Server
- Maven
- VS Code or any Java IDE

### Database Setup
1. Open MySQL Workbench
2. Run the `database.sql` file
3. This creates all required tables

### Configuration
Open `DBConnection.java` and update:
```java
static final String URL = 
    "jdbc:mysql://localhost:3306/trafficdb";
static final String USER = "root";
static final String PASSWORD = "your_password";
```

### Running the Project
```bash
mvn clean install -DskipTests
mvn exec:java -Dexec.mainClass="com.com.Main" -DskipTests
```

### Test Credentials
| Role | Roll No | Password |
|------|---------|----------|
| Student | 21CSE001 | 1234 |
| Management | MESS001 | 1234 |

## Developed By
- Kumaraguru College of Technology
- Department of Computer Science
- Database Connectivity Project

