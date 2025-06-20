# Student Management System (Java CLI)

A simple in-memory Student Management System using Java and CLI, now with persistent storage using serialization.

## Features
- Add, view, search, update, and delete students
- All data is stored in memory and saved to a file (`students.dat`) using Java serialization
- Data is loaded from the file on startup and saved after every change
- Handles invalid user input and file I/O errors gracefully

## Files
- `Student.java`: Student class (Serializable)
- `StudentManager.java`: Manages students and file I/O
- `Main.java`: CLI entry point with error handling
- `students.dat`: Data file (created automatically)

## How to Compile and Run

1. Open a terminal in this directory.
2. Compile all Java files:
   ```
   javac *.java
   ```
3. Run the program:
   ```
   java Main
   ```

## Requirements
- Java 8 or higher

---
This is a simple educational project. Data is persisted using Java's standard serialization mechanism. 