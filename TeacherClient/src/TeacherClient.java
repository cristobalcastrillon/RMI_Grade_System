import java.rmi.Naming;
import java.util.Objects;
import java.util.Scanner;

/**
 * Client application for Teachers.
 * Its methods are registering grades for a StudentClient.Student's Course;
 * deleting a grade for a StudentClient.Student's Course;
 * and modifying a grade for a StudentClient.Student's Course.
 */

public class TeacherClient {
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        System.out.print("Welcome.");

        String option = "0";
        while(!Objects.equals(option, "4")){
            System.out.println(("What do you want to do?"));
            System.out.print("1. Enter a new grade.\n" +
                    "2. Update an existing grade.\n" +
                    "3. Delete a grade.\n" +
                    "4. Exit");
            option = sc.next();
            try {
                GradeManager gradeManager = (GradeManager) Naming.lookup("rmi://" +
                        args[0] + "/" + "GradeManager");
                switch (option) {
                    // No regex for this input (academic exercise).
                    case "1":
                        enterGradesUserInterface(gradeManager, sc);
                        break;
                    case "2":
                        updateGradesUserInterface(gradeManager, sc);
                        break;
                    case "3":
                        deleteGradesUserInterface(gradeManager, sc);
                        break;
                    case "4":
                        break;
                }
            }
            catch(Exception e){
                System.err.println("An error has occurred, please try again later.");
            }
        }
        System.exit(0);
    }

    public static void enterGradesUserInterface(GradeManager gradeManager, Scanner sc) throws java.rmi.RemoteException {

        System.out.println("Please enter the student's ID: ");
        String studentID = sc.next();

        System.out.println("Please enter the subject's ID: ");
        String subjectID = sc.next();

        System.out.println("Please enter the grades for subject " + subjectID + " of student " + studentID);
        String grades = sc.next();

        gradeManager.enterGrades(studentID, subjectID, grades);
    }

    public static void deleteGradesUserInterface(GradeManager gradeManager, Scanner sc) throws java.rmi.RemoteException {

        System.out.println("Please enter the student's ID: ");
        String studentID = sc.next();

        System.out.println("Please enter the subject's ID: ");
        String subjectID = sc.next();

        gradeManager.deleteGrades(studentID, subjectID);
    }

    public static void updateGradesUserInterface(GradeManager gradeManager, Scanner sc) throws java.rmi.RemoteException {

        System.out.println("Please enter the student's ID: ");
        String studentID = sc.next();

        System.out.println("Please enter the subject's ID: ");
        String subjectID = sc.next();

        System.out.println("Please enter the new grades for subject " + subjectID + " of student " + studentID);
        String grades = sc.next();

        gradeManager.updateGrades(studentID, subjectID, grades);
    }
}