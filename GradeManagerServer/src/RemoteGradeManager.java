import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RemoteGradeManager extends UnicastRemoteObject implements GradeManager {

    private String csString = null;

    public RemoteGradeManager() throws RemoteException {
        super();
    }

    public void enterGrades(String studentID, String subjectID, String grade) throws RemoteException {

        boolean success = false;
        while (!success) {
            try {
                csString = stringConcatHelper(studentID, subjectID, grade);
                Files.write(Paths.get("/Users/cristobalcastrilonbalcazar/Dev/RMI_Grade_System" + "/Grades.csv"), csString.getBytes(), StandardOpenOption.APPEND);
                success = true;
            } catch (IOException e) {
                File gradesFile = new File("/Users/cristobalcastrilonbalcazar/Dev/RMI_Grade_System" + "/Grades.csv");
                try {
                    gradesFile.createNewFile();
                } catch (IOException ioE) {
                    throw new RemoteException("File handling has gone wrong.");
                }
            }
        }
    }

    public void deleteGrades(String studentID, String subjectID) throws RemoteException {

        boolean combinationFound = false;
        try {

            BufferedReader csvReader = new BufferedReader(new FileReader("/Users/cristobalcastrilonbalcazar/Dev/RMI_Grade_System" + "/Grades.csv"));

            try {

                String row;
                ArrayList<String[]> fileCopy = new ArrayList<>();

                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    if (data[0].equals(studentID) && data[1].equals(subjectID)) {
                        combinationFound = true;
                    } else {
                        fileCopy.add(data);
                    }
                }

                if (!combinationFound) {
                    throw new RemoteException("The student ID or subject ID were not found.");
                }

                csvReader.close();

                // Delete file
                File gradesFile = new File("/Users/cristobalcastrilonbalcazar/Dev/RMI_Grade_System" + "/Grades.csv");
                if (!gradesFile.delete())
                    throw new IOException();

                // Write copy file
                writeCopyFile(fileCopy);
            } catch (IOException e) {
                throw new RemoteException("File handling has gone wrong.");
            }
        } catch (FileNotFoundException e) {
            throw new RemoteException("The grades file has not been found.");
        }
    }

    public void updateGrades(String studentID, String subjectID, String grade) throws RemoteException {

        boolean combinationFound = false;
        try {

            BufferedReader csvReader = new BufferedReader(new FileReader("/Users/cristobalcastrilonbalcazar/Dev/RMI_Grade_System" + "/Grades.csv"));

            try {

                String row;
                ArrayList<String[]> fileCopy = new ArrayList<>();

                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    if (data[0].equals(studentID) && data[1].equals(subjectID)) {
                        combinationFound = true;
                        data[2] = grade;
                    }
                    fileCopy.add(data);
                }

                if (!combinationFound) {
                    throw new RemoteException("The student ID or subject ID were not found.");
                }
                csvReader.close();

                File gradesFile = new File("/Users/cristobalcastrilonbalcazar/Dev/RMI_Grade_System" + "/Grades.csv");
                if (!gradesFile.delete())
                    throw new IOException();

                writeCopyFile(fileCopy);
            } catch (Exception e) {
                throw new RemoteException("File handling has gone wrong.");
            }
        } catch (FileNotFoundException e) {
            throw new RemoteException("The grades file has not been found.");
        }
    }

    private String stringConcatHelper(String studentID, String subjectID, String grade) {
        return studentID + "," + subjectID + "," + grade;
    }

    private void writeCopyFile(ArrayList<String[]> fileCopy) throws IOException {
        FileWriter csvWriter = new FileWriter("/Users/cristobalcastrilonbalcazar/Dev/RMI_Grade_System/Grades.csv");

        for(Integer i = 0; i < fileCopy.size(); i++){
            String[] row = fileCopy.get(i);
            for(Integer j = 0; j < row.length; j++){
                csvWriter.append(row[j]);
                if(j != row.length - 1)
                    csvWriter.append(",");
            }
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
