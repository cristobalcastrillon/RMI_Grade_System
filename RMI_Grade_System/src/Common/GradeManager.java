package Common;

public interface GradeManager extends java.rmi.Remote {
    public void enterGrades(String studentID, String subjectID, String grade) throws java.rmi.RemoteException;
    public void deleteGrades(String studentID, String subjectID) throws java.rmi.RemoteException;
    public void updateGrades(String studentID, String subjectID, String grade) throws java.rmi.RemoteException;
}
