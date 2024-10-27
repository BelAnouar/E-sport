# 🎮 E-Sports Tournament Management System

## 🚀 Overview
Welcome to our E-Sports Tournament Management System! This robust Java application is designed to streamline the organization of e-sports events, making tournament management a breeze. Whether you're managing small local tournaments or large-scale competitions, our system has got you covered.

## 🌟 Key Features
- 🎯 **Player Management**: Register, update, and track player profiles
- 👥 **Team Organization**: Create and manage teams with ease
- 🏆 **Tournament Control**: Set up and run tournaments seamlessly
- ⏱️ **Smart Duration Calculation**: Intelligent estimation of tournament duration
- 📊 **Real-time Status Tracking**: Monitor tournament progress (PLANNED, IN_PROGRESS, COMPLETED, CANCELLED)

## 🛠️ Technical Stack
- ☕ Java 8
- 🌱 Spring Framework (Core)
- 📦 H2 Database
- 🔧 Maven
- 🧪 JUnit & Mockito
- 📊 JaCoCo
- 📝 SLF4J

## 📁 Project Structure
```
src/
├── main/
│   ├── java/org.esport/
│   │   ├── Model/
│   │   │   ├── enums/
│   │   │   ├── Equipe.java
│   │   │   ├── Jeu.java
│   │   │   ├── Joueur.java
│   │   │   └── Tournoi.java
│   │   ├── Repository/
│   │   │   ├── base/
│   │   │   ├── impl/
│   │   │   ├── GameRepository.java
│   │   │   ├── PlayerRepository.java
│   │   │   ├── TeamRepository.java
│   │   │   └── TournamentRepository.java
│   │   ├── Service/
│   │   │   ├── base/
│   │   │   ├── impl/
│   │   │   ├── GameService.java
│   │   │   ├── PlayerService.java
│   │   │   ├── TeamService.java
│   │   │   └── TournamentService.java
│   │   ├── util/
│   │   └── Main.java
│   └── resources/
│       │   └── applicationContext.xml
└── test/
    └── java/
```

## 🚀 Quick Start

### Prerequisites
- JDK 8
- Maven 3.x
- Git

### Installation
```bash
# Clone the repository
git clone [repository-url]

# Navigate to project directory
cd esports-tournament-manager

# Build the project
mvn clean install

# Run the application
java -jar target/esports-tournament-manager.jar
```

## 💡 Smart Features

### Intelligent Tournament Duration Calculator
Our system offers two calculation methods:
```java
// Basic Calculation
Duration = (Teams × Average Match Duration) + Break Time

// Advanced Calculation
Duration = (Teams × Average Match Duration × Game Difficulty) + Break Time + Ceremonies
```

### Repository Layer Features
- 🎮 `GameRepository`: Game data management
- 👤 `PlayerRepository`: Player profile handling
- 👥 `TeamRepository`: Team organization
- 🏆 `TournamentRepository`: Tournament operations

### Service Layer Capabilities
- 🎮 `GameService`: Game logic implementation
- 👤 `PlayerService`: Player management operations
- 👥 `TeamService`: Team coordination
- 🏆 `TournamentService`: Tournament workflow management

## 🧪 Testing
```bash
# Run tests with coverage
mvn test

# Generate coverage report
mvn jacoco:report
```
Coverage reports available at: `target/site/jacoco/index.html`

## 📝 Configuration
All configurations are XML-based:
- `applicationContext.xml`: Spring configuration
- `persistence.xml`: JPA/H2 database settings

## ⚠️ Important Notes
- XML-based Spring configuration (no annotations)
- SLF4J logging (no System.out.println())
- Standard Java project (not Spring Boot)
- JPA/Hibernate validations

## 🤝 Contributing
1. 🔄 Create a feature branch
2. ✨ Make your changes
3. 📤 Submit a pull request
4. 🔗 Link your JIRA tickets

## 🔒 Restricted Features
The following Spring annotations are not used:
- ❌ @Service
- ❌ @Repository
- ❌ @Component
- ❌ @SpringBootApplication
- ❌ @Autowired
- ❌ @ComponentScan

