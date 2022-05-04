package StudentClient;

import java.util.Scanner;

/**
 * Client application for Students.
 * Its sole method is reading (displaying to user) the grades registered.
 */

public class StudentClient {
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        // Read StudentClient.Student's ID from user input
        String studentID = null;
        System.out.print("Please enter your ID:\n");
        studentID = sc.next();

        // Create "StudentClient.Student" object
        Student user = new Student(studentID);

        // Read Subject's ID from user input
        String subjectID = null;
        System.out.print("Please enter the ID of the subject you want your grades displayed:\n");
        subjectID = sc.next();

        // Call the interface method (Invoke Remote Method)
        String grades = user.showGrades(subjectID, args);
        System.out.println("The grades for subject with ID " + subjectID + " are: " + grades);
    }
}
