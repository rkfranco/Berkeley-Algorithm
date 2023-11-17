package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface ClientInterface extends Remote {
    public void setTime(LocalDateTime time) throws RemoteException;

    public LocalTime getTime() throws RemoteException;
}
