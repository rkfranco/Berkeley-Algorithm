package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalTime;

public interface ClientInterface extends Remote {
    void adjustTime(LocalTime serverTime, long timeDiff) throws RemoteException;

    LocalTime getTime() throws RemoteException;

    void setTime(LocalTime time) throws RemoteException;
}
