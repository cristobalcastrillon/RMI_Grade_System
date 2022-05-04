package Server;

import java.rmi.Naming;

public class GradeManagerServer {
    public static void main(String args[]) {
        try{
            RemoteGradeManager stub = new RemoteGradeManager();
            Naming.rebind("rmi://localhost:1099", stub);
        }
        catch(Exception e){
            // TODO: Find out where this message is displayed.
            System.out.println(e);
        }
    }
}
