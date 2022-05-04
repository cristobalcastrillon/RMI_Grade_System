public interface GradeReader extends java.rmi.Remote {
    public String readGrades(String studentID, String subjectID) throws java.rmi.RemoteException;
}
