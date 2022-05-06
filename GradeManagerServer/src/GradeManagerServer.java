import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GradeManagerServer {
    public static void main(String[] args) {
        try{
            System.setProperty("java.rmi.server.hostname", "127.0.1.1");
            System.out.println("A");

            RemoteGradeManager remoteGradeManager = new RemoteGradeManager();
            System.out.println("C");

            Registry registry = LocateRegistry.getRegistry("127.0.1.1", 9100);
            System.out.println("E");

            registry.rebind("GradeManager", remoteGradeManager);
            System.out.println("F");
        }
        catch(Exception e){
            // TODO: Find out where this message is displayed.
            System.out.println(e);
        }
    }
}
