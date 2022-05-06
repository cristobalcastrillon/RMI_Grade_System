import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteGradeReader extends UnicastRemoteObject implements GradeReader {
    public RemoteGradeReader() throws RemoteException {
        super();
    }

    public String readGrades(String studentID, String subjectID) throws RemoteException {
        String grades = null;
        boolean combinationFound = false;
        try{

            BufferedReader csvReader = new BufferedReader(new FileReader("/Users/cristobalcastrilonbalcazar/Dev/RMI_Grade_System" + "/Grades.csv"));

            System.out.println("Reading grades...");

            String row;
            try{
                while((row = csvReader.readLine()) != null){
                    String[] data = row.split(",");

                    if(data[0].equals(studentID) && data[1].equals(subjectID)){
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
                throw new RemoteException("Data handling has not been completed.");
            }
        }
        catch(FileNotFoundException e){
            throw new RemoteException("The grades file has not been found.");
        }
        return grades;
    }
}
