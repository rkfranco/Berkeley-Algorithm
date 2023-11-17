package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(2000);

            ClientInterface sdrmi1 = new ClientImpl();
            sdrmi1.setTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 45, 0, 0)));
            registry.rebind("ClientImpl", sdrmi1);

            registry = LocateRegistry.createRegistry(2500);

            ClientInterface sdrmi2 = new ClientImpl();
            sdrmi2.setTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30, 0, 0)));

            registry.rebind("ClientImpl", sdrmi2);

            System.out.println("Cliente\\Servidor " + sdrmi1 + " registrado e pronto para aceitar solicitações.");
            System.out.println("Cliente\\Servidor " + sdrmi2 + " registrado e pronto para aceitar solicitações.\n");
        } catch (Exception ex) {
            System.out.println("Houve um erro: " + ex.getMessage());
        }
    }
}