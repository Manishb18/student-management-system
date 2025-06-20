import java.util.ArrayList;
import java.sql.*;

public class StudentManager {
    private ArrayList<Student> students;
    private static final String JDBC_URL = "jdbc:postgresql://db.mdurxgrzgcrzqogllcuh.supabase.co:5432/postgres?user=postgres&password=Manipspk@18"; 

    public StudentManager() {
        students = new ArrayList<>();
        loadFromDatabase();
    }

    public void addStudent(Student student) {
        students.add(student);
        insertStudentToDatabase(student);
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public Student searchStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean updateStudent(int id, String newName, int newAge, String newGrade) {
        Student s = searchStudentById(id);
        if (s != null) {
            s.setName(newName);
            s.setAge(newAge);
            s.setGrade(newGrade);
            updateStudentInDatabase(s);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        Student s = searchStudentById(id);
        if (s != null) {
            students.remove(s);
            deleteStudentFromDatabase(id);
            return true;
        }
        return false;
    }

    private void loadFromDatabase() {
        students.clear();
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, age, grade FROM students")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String grade = rs.getString("grade");
                students.add(new Student(id, name, age, grade));
            }
        } catch (SQLException e) {
            System.out.println("Error loading students from DB: " + e.getMessage());
        }
    }

    private void insertStudentToDatabase(Student student) {
        String sql = "INSERT INTO students (id, name, age, grade) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getGrade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }

    private void updateStudentInDatabase(Student student) {
        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getGrade());
            pstmt.setInt(4, student.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    private void deleteStudentFromDatabase(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
} 