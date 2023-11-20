package Server;

import Server.Utils.Connection;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Common.Constants.*;

public class ServerMain {
    public static void main(String[] args) {
        List<Connection> connections = List.of(
                new Connection(SERVER_IP, PORT_1),
                new Connection(SERVER_IP, PORT_2),
                new Connection(SERVER_IP, PORT_3),
                new Connection(SERVER_IP, PORT_4));

        Server server = new Server(SERVER_TIME, connections);
        List<LocalTime> times = new ArrayList<>(server.sendTimeRequests());

        System.out.println("-----SERVIDOR-----");
        System.out.println("Horario do servidor: \n" + DT_FORMATTER.format(server.getServerTime()) + "\n");
        System.out.println("Horarios dos clientes: \n" + times.stream().map(DT_FORMATTER::format).collect(Collectors.joining("\n")) + "\n");

        times.add(server.getServerTime());

        long totalSumDiff = times.stream()
                .map(server::getTimeDiff)
                .mapToLong(Long::longValue)
                .sum();

        long averageDiff = totalSumDiff / times.size();

        System.out.println("Diferenca media de tempo em nano segundos: " + averageDiff);
        System.out.println("------------------\n");

        server.sendDiffToClients(averageDiff);
    }
}