import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteGradeManager extends UnicastRemoteObject implements GradeManager {

    private String csString = null;

    public RemoteGradeManager() throws RemoteException {
        super();
        System.out.println("B");
    }

    public void enterGrades(String studentID, String subjectID, String grade) throws RemoteException {

        boolean success = false;
        while (!success) {
            try {
                csString = stringConcatHelper(studentID, subjectID, grade);
                Files.write(Paths.get(System.getProperty("user.dir") + "/Grades.csv"), csString.getBytes(), StandardOpenOption.APPEND);
                success = true;
            } catch (IOException e) {

                File gradesFile = new File(System.getProperty("user.dir") + "/Grades.csv");
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

            BufferedReader csvReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Grades.csv"));

            try {

                String row;
                String[][] fileCopy = null;
                int rowCounter = 0;

                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    if (data[0] == studentID && data[1] == subjectID) {
                        combinationFound = true;
                    } else {
                        fileCopy[rowCounter++] = data;
                    }
                }

                if (!combinationFound) {
                    throw new RemoteException("The student ID or subject ID were not found.");
                }

                csvReader.close();

                // Delete file
                File gradesFile = new File(System.getProperty("user.dir") + "/Grades.csv");
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

            BufferedReader csvReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Grades.csv"));

            try {

                String row;
                String[][] fileCopy = null;
                int rowCounter = 0;

                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    if (data[0] == studentID && data[1] == subjectID) {
                        combinationFound = true;
                        data[2] = grade;
                    }
                    fileCopy[rowCounter++] = data;
                }

                if (!combinationFound) {
                    throw new RemoteException("The student ID or subject ID were not found.");
                }
                csvReader.close();

                // Delete file
                File gradesFile = new File(System.getProperty("user.dir") + "/Grades.csv");
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

    private String stringConcatHelper(String studentID, String subjectID, String grade) {
        return studentID + "," + subjectID + "," + grade;
    }

    private void writeCopyFile(String[][] fileCopy) throws IOException {
        FileWriter csvWriter = new FileWriter("Grades.csv");
        for (String[] row : fileCopy) {
            for (String field : row) {
                csvWriter.append(field);
                csvWriter.append(",");
            }
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }
}
