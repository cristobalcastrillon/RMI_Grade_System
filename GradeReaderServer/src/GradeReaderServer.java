import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GradeReaderServer {
    public static void main(String args[]) {
        try{

            RemoteGradeReader remoteGradeReader = new RemoteGradeReader();

            Registry registry = LocateRegistry.createRegistry(1888);

            registry.rebind("GradeReader", remoteGradeReader);

            System.out.println("Server is running...");
        }
        catch(Exception e){
            // TODO: Find out where this message is displayed.
            System.out.println(e);
        }
    }
}
