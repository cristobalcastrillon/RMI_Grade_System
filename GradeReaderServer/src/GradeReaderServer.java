import java.rmi.Naming;

public class GradeReaderServer {
    public static void main(String args[]) {
        try{
            RemoteGradeReader stub = new RemoteGradeReader();
            Naming.rebind("rmi://localhost:1099", stub);
        }
        catch(Exception e){
            // TODO: Find out where this message is displayed.
            System.out.println(e);
        }
    }
}
