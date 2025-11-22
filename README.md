## 1.Introduction 
This project presents STM App, a mobile application designed to support students in organising study tasks, managing course schedules,
and monitoring overall learning progress.
The system is implemented using Android Kotlin, leverages Jetpack Compose for UI development, and adopts the MVVM architectural pattern,
ensuring a clear separation between UI, logic, and data layers.

## 2.Key Features 
Email-based login system, including a “Remember Me” option.
Task management module, supporting task creation, completion, and automatic removal of outdated entries.
Course scheduling module, allowing users to record course names, weekly schedules, and time periods.
Progress tracking system, which automatically calculates overall study progress based on task completion.
Local persistence using Room, enabling offline operation.
Modern UI implemented entirely with Jetpack Compose and Material 3.

## 3.System Architecture 
STM App follows a Model–View–ViewModel (MVVM) architecture combined with the Repository pattern to ensure modularity, maintainability, and scalability.

1.Architecture Diagram 
UI (Jetpack Compose)
      StateFlow
ViewModel (Logic Layer)
      Repository Calls
Repository (Data Abstraction Layer)
      DAO Access
Room Database (Local Persistence)

2.Component Descriptions 
View (Compose UI)
Collects StateFlow data from ViewModel
Displays real-time UI updates
Sends user actions to the ViewModel

ViewModel Layer
Key responsibilities:
Maintain UI state using StateFlow
Execute logic via coroutines
Communicate with repositories
Handle progress computation and input validation

Repository Layer
Encapsulates data operations
Serves as an abstraction over Room DAO
Reduces coupling between ViewModel and Database

Room Database
Stores task and course records
Ensures data consistency
Provides DAO interfaces for CRUD operations

## 4.Key Implementation Highlights 
1.Task Progress Calculation
2.Automatic Removal of Expired Tasks 
3.State Management Using StateFlow 

## 5.Project Structure 
app/
 ├─ data/                  # Database Layer
 │   ├─ AppDatabase.kt
 │   ├─ TaskDao.kt
 │   ├─ CourseDao.kt
 │   ├─ TaskEntity.kt
 │   └─ CourseEntity.kt
 │
 ├─ repository/            # Repository Layer
 │   ├─ TaskRepository.kt
 │   └─ CourseRepository.kt
 │
 ├─ viewmodel/             # MVVM Logic
 │   ├─ LoginViewModel.kt
 │   ├─ TaskViewModel.kt
 │   └─ CourseViewModel.kt
 │
 ├─ ui/                    # Jetpack Compose UI
 │   ├─ LoginScreen.kt
 │   ├─ MainMenuScreen.kt
 │   ├─ AddTaskScreen.kt
 │   ├─ AddCourseScreen.kt
 │   └─ ProgressScreen.kt
 │
 └─ navigation/            # Navigation Graph
     └─ NavGraph.kt

## 6.Steps 操作步骤
Clone this repository
Open the project in Android Studio
Sync Gradle
Run the application on an emulator or physical device

## 7. Conclusion 
STM App demonstrates how mobile applications can integrate task management, scheduling, and progress tracking within a unified system.
By employing MVVM, Jetpack Compose, and Room, the project provides a clean, structured, and scalable solution suitable for academic study 
and practical development.

