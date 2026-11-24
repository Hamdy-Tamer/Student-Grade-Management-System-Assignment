import java.util.Scanner;

public class Main {

    // prints students names
    public static void printStudentsNames(String[] studentNames) {
        for (int i = 0; i < studentNames.length; i++) {
            System.out.println((i + 1) + ". " + studentNames[i]);
        }
    }

    // print students' grades
    public static void printAllGrades(String[] studentNames, double[][] grades) {
        for (int i = 0; i < studentNames.length; i++) {
            System.out.println("\nStudent: " + studentNames[i]);
            for (int j = 0; j < grades[i].length; j++) {
                System.out.println("  Subject " + (j + 1) + ": " + grades[i][j]);
            }
        }
    }

    // search student by name
    public static void searchStudentByName(String[] studentNames, double[][] grades, String targetName) {
        for (int i = 0; i < studentNames.length; i++) {
            if (studentNames[i].equalsIgnoreCase(targetName.trim())) {
                System.out.println("\nStudent found: " + studentNames[i]);
                for (int j = 0; j < grades[i].length; j++) {
                    System.out.println("  Subject " + (j + 1) + ": " + grades[i][j]);
                }
                return; // Stop searching once the student is found
            }
        }
        System.out.println("\nStudent \"" + targetName + "\" not found.");
    }

    // who passed
    public static int countPassedStudents(double[][] grades) {
        int passedCount = 0;
        double passingAverage = 50.0;

        for (int i = 0; i < grades.length; i++) {
            double sum = 0;
            for (int j = 0; j < grades[i].length; j++) {
                sum += grades[i][j];
            }
            double average = sum / grades[i].length;
            if (average >= passingAverage) {
                passedCount++;
            }
        }

        return passedCount;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Array of students
        String[] studentNames = new String[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            studentNames[i] = scan.nextLine().trim();
        }

        // Array of students' grade
        double[][] grades = new double[5][3];

        for (int i = 0; i < 5; i++) {
            System.out.println("\nEntering grades for " + studentNames[i] + ":");
            for (int j = 0; j < 3; j++) {
                double grade;
                do {
                    System.out.print("  Enter grade for subject " + (j + 1) + " (0-100): ");
                    grade = scan.nextDouble();
                    if (grade < 0 || grade > 100) {
                        System.out.println("Invalid grade.");
                    }
                } while (grade < 0 || grade > 100);
                grades[i][j] = grade;
            }
        }
        scan.nextLine();

        // Main Menu
        int choice;

        do {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Show All Students names");
            System.out.println("2. Show all Students grades in each subject");
            System.out.println("3. Search Student by name");
            System.out.println("4. Count Passed Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice (0-4): ");

            if (!scan.hasNextInt()) {
                System.out.println("\nError!\nInvalid Choice\nPlease enter a number between 0 and 4.");
                scan.next(); // discard the invalid token
                choice = -1; // keep the loop running
                continue;
            }
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nStudents Name List:");
                    printStudentsNames(studentNames);
                    break;

                case 2:
                    System.out.println("Grades");
                    printAllGrades(studentNames, grades);
                    break;

                case 3:
                    scan.nextLine();
                    System.out.print("Enter student name to search: ");
                    String searchName = scan.nextLine();
                    searchStudentByName(studentNames, grades, searchName);
                    break;

                case 4:
                    int passedCount = countPassedStudents(grades);
                    System.out.println("\nNumber of passed students (average >= 50.0): " + passedCount + " out of " + studentNames.length);
                    break;

                case 0:
                    System.out.println("Exit! Goodbye");
                    break;

                default:
                    System.out.println("\nError!\nInvalid Choice\nPlease enter a number between 0 and 4.");
            }
        } while (choice != 0);
    }
}