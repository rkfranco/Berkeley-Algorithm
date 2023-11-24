package Server;

import Client.ClientInterface;
import Server.Utils.Connection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static Common.Constants.CLIENT_REGISTRY;

public class Server {
    private final List<ClientInterface> clients;
    private final LocalTime serverTime;

    public Server(LocalTime time, List<Connection> connections) {
        this.serverTime = time;
        this.clients = getClients(connections);
    }

    public Server(List<Connection> connections) {
        this.serverTime = LocalTime.now();
        this.clients = getClients(connections);
    }

    public LocalTime getServerTime() {
        return serverTime;
    }

    public long getTimeDiff(LocalTime client) {
        return client.toNanoOfDay() - getServerTime().toNanoOfDay();
    }

    public void sendDiffToClients(long timeDiff) {
        try {
            for (ClientInterface client : clients) {
                client.adjustTime(getServerTime(), timeDiff);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LocalTime> sendTimeRequests() {
        return clients.stream()
                .map(this::getClientTime)
                .collect(Collectors.toList());
    }

    private List<ClientInterface> getClients(List<Connection> connections) {
        return connections.stream()
                .map(this::getRmiRegistry)
                .map(this::getLookup)
                .collect(Collectors.toList());
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
            return (ClientInterface) registry.lookup(CLIENT_REGISTRY);
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
}
