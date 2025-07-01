package org.example;

import java.util.*;

class Student {
    String name;
    int score;
    char grade;

    public Student(String name, int score, char grade) {
        this.name = name;
        this.score = score;
        this.grade = grade;
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // create a Scanner object

        int noOfStudents = getNumberOfStudents(scanner);

        ArrayList<Student> students = new ArrayList<>();
        double totalScore = 0.0;
        Map<Character, Integer> gradeCounts = new HashMap<>();
        gradeCounts.put('A', 0);
        gradeCounts.put('B', 0);
        gradeCounts.put('C', 0);
        gradeCounts.put('D', 0);
        gradeCounts.put('F', 0);

        for(int i = 0; i < noOfStudents; i++) {
            System.out.printf("Enter name of student %d: ", i+1);
            String name = scanner.nextLine();  // read user input

            int score = getValidScore(scanner, name);
            totalScore += score; // add to total score

            char grade = determineGrade(score); // determine grade
            System.out.printf("%s got grade: %c\n\n", name, grade); // output grade
            gradeCounts.put(grade, gradeCounts.get(grade) + 1); // increment grade counter

            students.add(new Student(name, score, grade)); // add student to arraylist
        }

        if (noOfStudents > 0) {
            System.out.println("----- Class Summary -----");
            System.out.printf("Average Score: %.2f\n", totalScore / noOfStudents);
            System.out.printf("Grade Counts: A:%d B:%d C:%d D:%d F:%d\n",
                    gradeCounts.get('A'), gradeCounts.get('B'), gradeCounts.get('C'),
                    gradeCounts.get('D'), gradeCounts.get('F'));
            int highestScore = getHighestScore(students);
            ArrayList<String> topStudents = getTopStudents(students, highestScore);
            String topStudentNames = String.join(", ", topStudents);
            System.out.printf("Top Student(s): %s (%d)\n", topStudentNames, highestScore);
        } else {
            System.out.println("No students entered.");
        }

        scanner.close(); // close the scanner
    }

    // === Helper Method: Calculate grade ===
    private static char determineGrade(int score) {
        if (score >= 90) return 'A';
        else if (score >= 80) return 'B';
        else if (score >= 70) return 'C';
        else if (score >= 60) return 'D';
        else return 'F';
    }

    // === Helper Method: Find the highest score ===
    private static int getHighestScore(ArrayList<Student> students) {
        int highestScore = -1;
        for (Student s : students) {
            if (s.score > highestScore) {
                highestScore = s.score;
            }
        }
        return highestScore;
    }

    // === Helper Method: Get top student/s with the highest score ===
    private static ArrayList<String> getTopStudents(ArrayList<Student> students, int highestScore) {
        ArrayList<String> topStudents = new ArrayList<>();
        for (Student s : students) {
            if (s.score == highestScore) {
                topStudents.add(s.name);
            }
        }
        return topStudents;
    }

    // === Helper Method: Input validation for number of students ===
    private static int getNumberOfStudents(Scanner scanner) {
        int noOfStudents;
        while (true) {
            System.out.print("Enter number of students: ");
            if (scanner.hasNextInt()) {
                noOfStudents = scanner.nextInt();
                scanner.nextLine(); // consume leftover newline
                if (noOfStudents >= 0) {
                    System.out.println();
                    return noOfStudents;
                } else {
                    System.out.println("Please enter a non-negative number.");
                }
            } else {
                System.out.println("Invalid input! Please enter a whole number (integer).");
                scanner.nextLine(); // clear wrong input
            }
        }
    }

    // === Helper Method: Input validation for student score ===
    private static int getValidScore(Scanner scanner, String studentName) {
        int score;
        while (true) {
            System.out.printf("Enter score for %s: ", studentName);
            if (scanner.hasNextInt()) {
                score = scanner.nextInt();
                scanner.nextLine(); // consume the leftover newline
                if (score >= 0 && score <= 100) {
                    return score;  // return valid score
                } else {
                    System.out.println("Invalid score! Please enter a number between 0 and 100.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.nextLine();  // clear invalid input
            }
        }
    }
}