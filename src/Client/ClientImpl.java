package Client;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.nonNull;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

    private LocalDateTime time = null;

    protected ClientImpl() throws RemoteException {
    }

    protected ClientImpl(int port) throws RemoteException {
        super(port);
    }

    protected ClientImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void setTime(LocalDateTime time) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        if (nonNull(time)) this.time = time;
        System.out.println("Data atualizada: " + dt.format(time) + "\n");
    }

    @Override
    public LocalTime getTime() {
        if (nonNull(time)) return time.toLocalTime();
        return LocalTime.of(0, 0, 0, 0);
    }

    private LocalTime getTimeDiff(LocalTime time1, LocalTime time2) {
        return time1.isAfter(time2) ?
                getLocalTimeDiff(time1, time2) :
                getLocalTimeDiff(time2, time1);
    }

    private LocalTime getLocalTimeDiff(LocalTime time1, LocalTime time2) {
        return LocalTime.of(
                time1.getHour() - time2.getHour(),
                time1.getMinute() - time2.getMinute(),
                time1.getSecond() - time2.getSecond());
    }
}
