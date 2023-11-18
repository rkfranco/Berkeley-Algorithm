package Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.nonNull;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

    private LocalTime time = null;

    protected ClientImpl() throws RemoteException {
    }

    @Override
    public void adjustTime(LocalTime serverTime, long timeDiff) {
        long serverNano = serverTime.toNanoOfDay();
        long clientNano = getTime().toNanoOfDay();
        long localDiff = clientNano - serverNano;
        localDiff = localDiff * -1 + timeDiff + clientNano;
        setTime(LocalTime.ofNanoOfDay(localDiff));
    }

    @Override
    public LocalTime getTime() {
        return nonNull(time) ? time : LocalTime.of(0, 0, 0, 0);
    }

    public void setTime(LocalTime time) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm:ss");
        if (nonNull(time)) {
            this.time = time;
            System.out.println("Data atualizada: " + dt.format(time) + "\n");
        }
    }
}
