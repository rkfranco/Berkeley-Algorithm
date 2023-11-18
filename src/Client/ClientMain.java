package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static Common.Constants.*;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(PORT_1);

            ClientInterface sdrmi1 = new ClientImpl();
            sdrmi1.setTime(TIME_1);
            registry.rebind(CLIENT_REGISTRY, sdrmi1);

            registry = LocateRegistry.createRegistry(PORT_2);

            ClientInterface sdrmi2 = new ClientImpl();
            sdrmi2.setTime(TIME_2);

            registry.rebind(CLIENT_REGISTRY, sdrmi2);

            System.out.println("Cliente " + sdrmi1 + " registrado e pronto para aceitar solicitações.");
            System.out.println("Cliente " + sdrmi2 + " registrado e pronto para aceitar solicitações.\n");
        } catch (Exception ex) {
            System.out.println("Houve um erro: " + ex.getMessage());
        }
    }
}