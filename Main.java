import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = readInt(scanner);
            switch (choice) {
                case 1:
                    int id = readIntWithPrompt(scanner, "Enter ID: ");
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    int age = readIntWithPrompt(scanner, "Enter Age: ");
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();
                    manager.addStudent(new Student(id, name, age, grade));
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    manager.viewStudents();
                    break;
                case 3:
                    int searchId = readIntWithPrompt(scanner, "Enter ID to search: ");
                    Student found = manager.searchStudentById(searchId);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    int updateId = readIntWithPrompt(scanner, "Enter ID to update: ");
                    System.out.print("Enter new Name: ");
                    String newName = scanner.nextLine();
                    int newAge = readIntWithPrompt(scanner, "Enter new Age: ");
                    System.out.print("Enter new Grade: ");
                    String newGrade = scanner.nextLine();
                    boolean updated = manager.updateStudent(updateId, newName, newAge, newGrade);
                    if (updated) {
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    int deleteId = readIntWithPrompt(scanner, "Enter ID to delete: ");
                    boolean deleted = manager.deleteStudent(deleteId);
                    if (deleted) {
                        System.out.println("Student deleted successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private static int readIntWithPrompt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return readInt(scanner);
    }
} 