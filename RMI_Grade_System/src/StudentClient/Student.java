package StudentClient;

import java.rmi.Naming;

public class Student {

    private String studentID;

    public Student(){
        this.studentID = null;
    }

    public Student(String id){
        this.studentID = id;
    }

    /**
     * @param subjectID: id given by the user as the StudentClient.Student ID.
     * @param args: host IP address and port combination.
     * @return response: String containing user requested grades.
     */
    public String showGrades(String subjectID, String args[]){
        // First, let's implement reading just one grade for one subject.
        String response = null;
        try {
            // TODO: Bind 'StudentClient.GradeManager' name with its respective remote object.
            GradeReader studentGradeReader = (GradeReader) Naming.lookup("rmi://" +
                    args[0] + "/" + "GradeReader");

            response = studentGradeReader.readGrades(this.studentID, subjectID);
        }
        catch (Exception e){
            System.err.println("An error has occurred, please try again later.");
        }
        return response;
    }
}
