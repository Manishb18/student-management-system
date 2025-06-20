# Student Management System (Java CLI)

A simple command-line Student Management System in Java, now using PostgreSQL (Supabase) for persistent storage via JDBC.

## Features

- Add, view, search, update, and delete students
- Data is stored in a PostgreSQL database (Supabase) using JDBC
- Handles invalid user input and database errors gracefully

## Files

- `Student.java`: Student class (Serializable)
- `StudentManager.java`: Manages students and database I/O (JDBC)
- `Main.java`: CLI entry point with error handling

## Database Setup

1. **Create a Supabase project** at [supabase.com](https://supabase.com/).
2. **Create the `students` table** using the SQL editor:
   ```sql
   CREATE TABLE students (
     id INTEGER PRIMARY KEY,
     name VARCHAR(255),
     age INTEGER,
     grade VARCHAR(10)
   );
   ```
3. **Get your JDBC connection string** from the Supabase dashboard and update it in `StudentManager.java`.

## Requirements

- Java 8 or higher
- [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/download.html) (e.g., `postgresql-42.7.7.jar`)
- Internet connection (for Supabase)

## How to Compile and Run

1. Place the JDBC driver `.jar` in your project directory.
2. Open a terminal in this directory.
3. Compile all Java files:
   ```powershell
   javac -cp ".;postgresql-42.7.7.jar" *.java
   ```
   (On Mac/Linux, use `:` instead of `;`)
4. Run the program:
   ```powershell
   java -cp ".;postgresql-42.7.7.jar" Main
   ```
   (On Mac/Linux, use `:` instead of `;`)

## Notes

- All student data is now stored in your Supabase PostgreSQL database.
- Update your database credentials in `StudentManager.java` as needed.
- The old file-based persistence (`students.dat`) is no longer used.

---

This is a simple educational project. Data is now persisted using a cloud database for better scalability and reliability.