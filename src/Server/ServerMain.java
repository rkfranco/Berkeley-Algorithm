package Server;

import Server.Utils.Connection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    public static void main(String[] args) {
        List<Connection> connections = new ArrayList<>();
        connections.add(new Connection("127.0.0.2", 2000));
        connections.add(new Connection("127.0.0.3", 2500));

        boolean teste = true;

        Server server = teste ? new Server(
                LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 00, 00, 00)), connections) :
                new Server(connections);
        List<LocalTime> times = server.sendTimeRequests();

        System.out.println(server.getServerTime());
        times.forEach(System.out::println);


        long totalTimeSum = server.getServerTime().toLocalTime().toEpochSecond(LocalDate.now(), ZoneOffset.UTC);
        for (LocalTime time : times) {
            totalTimeSum += time.toEpochSecond(LocalDate.now(), ZoneOffset.UTC);
        }
        long media = totalTimeSum / (times.size() + 1);

        server.setNewClientsTime(LocalDateTime.ofEpochSecond(media, 0, ZoneOffset.UTC));
    }
}