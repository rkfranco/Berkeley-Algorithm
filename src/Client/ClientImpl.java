package Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

import static Common.Constants.DT_FORMATTER;
import static java.util.Objects.nonNull;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

    private LocalTime time = null;
    private int id;

    protected ClientImpl(int id, LocalTime time) throws RemoteException {
        this.time = time;
        this.id = id;
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
        if (nonNull(time)) {
            this.time = time;
            System.out.println("[Cliente " + getId() + "]: Data atualizada: " + DT_FORMATTER.format(time) + "\n");
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[Cliente " + getId() + "]: criado com sucesso | Hora: " + DT_FORMATTER.format(getTime());
    }
}
