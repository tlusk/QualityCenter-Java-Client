package darkcube.qc.client;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import java.net.HttpURLConnection;

public class QCRestClient {

    private String serverAddress;
    private String username;
    private String password;

    public QCRestClient(String serverAddress, String username, String password) {
        this.serverAddress = serverAddress;
        this.username = username;
        this.password = password;
    }

    public boolean login() {
        return false;
    }
}
