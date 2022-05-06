import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GradeManagerServer {
    public static void main(String[] args) {
        try{
            RemoteGradeManager remoteGradeManager = new RemoteGradeManager();

            Registry registry = LocateRegistry.createRegistry(1889);

            registry.rebind("GradeManager", remoteGradeManager);
            System.out.println("Server is running...");
        }
        catch(Exception e){
            // TODO: Find out where this message is displayed.
            System.out.println(e);
        }
    }
}
