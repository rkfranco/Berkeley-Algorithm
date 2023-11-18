package Server;

import Server.Utils.Connection;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Common.Constants.*;

public class ServerMain {
    public static void main(String[] args) {
        List<Connection> connections = new ArrayList<>();
        connections.add(new Connection(SERVER_IP, PORT_1));
        connections.add(new Connection(SERVER_IP, PORT_2));

        Server server = new Server(SERVER_TIME, connections);
        List<LocalTime> times = server.sendTimeRequests();

        System.out.println("Horario do servidor: \n" + DT_FORMATTER.format(server.getServerTime()));
        System.out.println("Horarios dos clientes: \n" + times.stream().map(DT_FORMATTER::format).collect(Collectors.joining("\n")));

        long totalSumDiff = times.stream()
                .map(server::getTimeDiff)
                .mapToLong(Long::longValue)
                .sum();

        long averageDiff = totalSumDiff / times.size();

        server.sendDiffToClients(averageDiff);
    }
}