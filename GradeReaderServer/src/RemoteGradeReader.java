import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteGradeReader extends UnicastRemoteObject implements Common.GradeReader {
    public RemoteGradeReader() throws RemoteException {
        super();
    }

    public String readGrades(String studentID, String subjectID) throws RemoteException {
        String grades = null;
        boolean combinationFound = false;
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Grades.csv"));
            String row;
            try{
                while((row = csvReader.readLine()) != null){
                    String[] data = row.split(",");
                    if(data[0] == studentID && data[1] == subjectID){
                        combinationFound = true;
                        grades = data[2];
                    }
                }
                if(!combinationFound){
                    throw new RemoteException("The student ID or subject ID were not found.");
                }
                csvReader.close();
            }
            catch(IOException e){
                throw new RemoteException();
            }
        }
        catch(FileNotFoundException e){
            throw new RemoteException("The grades file has not been found.");
        }
        return grades;
    }
}
