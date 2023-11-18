package Server;

import Server.Utils.Connection;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static Common.Constants.*;

public class ServerMain {
    public static void main(String[] args) {
        List<Connection> connections = new ArrayList<>();
        connections.add(new Connection(SERVER_IP, PORT_1));
        connections.add(new Connection(SERVER_IP, PORT_1));

        Server server = new Server(SERVER_TIME, connections);
        List<LocalTime> times = server.sendTimeRequests();

        System.out.println(server.getServerTime());
        times.forEach(System.out::println);

        long totalSumDiff = times.stream()
                .map(server::getTimeDiff)
                .mapToLong(Long::longValue)
                .sum();

        long averageDiff = totalSumDiff / times.size();

        server.sendDiffToClients(averageDiff);
    }
}