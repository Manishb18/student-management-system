import java.util.ArrayList;
import java.io.*;

public class StudentManager {
    private ArrayList<Student> students;
    private static final String FILE_NAME = "students.dat";

    public StudentManager() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
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
            saveToFile();
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        Student s = searchStudentById(id);
        if (s != null) {
            students.remove(s);
            saveToFile();
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            students = new ArrayList<>();
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                students = (ArrayList<Student>) obj;
            } else {
                students = new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            students = new ArrayList<>();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
} 