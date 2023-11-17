package Server.Utils;

public class Connection {
    private String ipAdress;
    private int port;

    public Connection(String ipAdress, int port) {
        this.ipAdress = ipAdress;
        this.port = port;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
