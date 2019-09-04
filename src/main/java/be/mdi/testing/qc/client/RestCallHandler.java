package be.mdi.testing.qc.client;

import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestCallHandler {

    private final String host;
    private final String username;
    private final String password;
    private String sessionKey;

    public RestCallHandler(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public <T> T getRestData(Class<T> retType, String restUrl) {
        Invocation.Builder invocationBuilder = buildRestRequest(restUrl);
        return invocationBuilder.get().readEntity(retType);
    }

    public <T> T getRestData(GenericType<T> retType, String restUrl) {
        Invocation.Builder invocationBuilder = buildRestRequest(restUrl);
        return invocationBuilder.get().readEntity(retType);
    }

    public void login() {
        Client client = ClientBuilder.newClient();
        client.register(new HttpBasicAuthFilter(username, password));
        WebTarget webTarget = client.target(host + "/qcbin/authentication-point/authenticate");

        Response response = webTarget.request(MediaType.TEXT_PLAIN_TYPE).get();
        sessionKey = response.getHeaderString("Set-Cookie").split("=")[1].split(" ")[0];
    }

    private Invocation.Builder buildRestRequest(String restUrl) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(host + "/qcbin/" + restUrl);

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.WILDCARD_TYPE);
        return invocationBuilder.header("Cookie", "LWSSO_COOKIE_KEY=" + sessionKey);
    }
}
