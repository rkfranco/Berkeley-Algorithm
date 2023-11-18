package Common;

import Client.ClientImpl;

import java.time.LocalTime;

public class Constants {
    public static final String SERVER_IP = "localhost";
    public static final int PORT_1 = 4500;
    public static final int PORT_2 = 4501;

    public static final String CLIENT_REGISTRY = ClientImpl.class.getSimpleName();

    public static final LocalTime SERVER_TIME = LocalTime.of(16, 0, 0, 0);
    public static final LocalTime TIME_1 = LocalTime.of(15, 45, 0, 0);
    public static final LocalTime TIME_2 = LocalTime.of(15, 30, 0, 0);

}
