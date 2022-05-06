import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GradeReaderServer {
    public static void main(String args[]) {
        try{
            System.setProperty("java.rmi.server.hostname", "127.0.1.1");
            System.out.println("A");

            RemoteGradeReader remoteGradeReader = new RemoteGradeReader();
            System.out.println("C");

            Registry registry = LocateRegistry.getRegistry("127.0.1.1", 9101);
            System.out.println("E");

            registry.rebind("GradeReader", remoteGradeReader);
            System.out.println("F");
        }
        catch(Exception e){
            // TODO: Find out where this message is displayed.
            System.out.println(e);
        }
    }
}
