package Client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import static Common.Constants.*;

/*
 *   Alunos:
 *   Rodrigo Kapulka Franco
 *   Ana Carolina da Silva
 *   Gustavo Baroni Bruder
 */
public class ClientMain {
    public static void main(String[] args) {
        try {
            ClientInterface sdrmi1 = createClient(PORT_1, TIME_1, 1);
            ClientInterface sdrmi2 = createClient(PORT_2, TIME_2, 2);
            ClientInterface sdrmi3 = createClient(PORT_3, TIME_3, 3);
            ClientInterface sdrmi4 = createClient(PORT_4, TIME_4, 4);

            System.out.println(sdrmi1);
            System.out.println(sdrmi2);
            System.out.println(sdrmi3);
            System.out.println(sdrmi4 + "\n");
        } catch (Exception ex) {
            System.out.println("Houve um erro: " + ex.getMessage());
        }
    }

    private static ClientInterface createClient(int port, LocalTime time, int id) {
        try {
            Registry registry = LocateRegistry.createRegistry(port);
            ClientInterface sdrmi = new ClientImpl(id, time);
            registry.rebind(CLIENT_REGISTRY, sdrmi);
            return sdrmi;
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}