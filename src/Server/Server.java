package Server;

import Client.ClientInterface;
import Server.Utils.Connection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Server {

    final String CLIENT = "ClientImpl";
    private final LocalDateTime serverTime;
    private final List<ClientInterface> clients;

    public Server(LocalDateTime time, List<Connection> connections) {
        this.serverTime = time;
        this.clients = getClients(connections);
    }

    public Server(List<Connection> connections) {
        this.serverTime = LocalDateTime.now();
        this.clients = getClients(connections);
    }

    private List<ClientInterface> getClients(List<Connection> connections) {
        return connections.stream()
                .map(this::getRmiRegistry)
                .map(this::getLookup)
                .toList();
    }

    public void setNewClientsTime(LocalDateTime newTime) {
        clients.forEach(c -> {
            try {
                c.setTime(newTime);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<LocalTime> sendTimeRequests() {
        return clients.stream()
                .map(this::getClientTime)
                .toList();
    }

    private LocalTime getClientTime(ClientInterface client) {
        try {
            return client.getTime();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private ClientInterface getLookup(Registry registry) {
        try {
            return (ClientInterface) registry.lookup(CLIENT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private Registry getRmiRegistry(Connection c) {
        try {
            return LocateRegistry.getRegistry(c.getIpAdress(), c.getPort());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LocalDateTime getServerTime() {
        return serverTime;
    }
}
