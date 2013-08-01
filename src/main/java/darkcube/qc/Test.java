package darkcube.qc;

import darkcube.qc.model.Domain;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Test {

    private static String hostname;
    private static String username;
    private static String password;

    public static void main(String[] args) {

        try {
            Properties prop = new Properties();

            //load a properties file
            prop.load(new FileInputStream("server.properties"));

            //get the property value and print it out
            hostname = prop.getProperty("hostname");
            username = prop.getProperty("username");
            password = prop.getProperty("password");

        } catch (IOException ex) {
            ex.printStackTrace();

            System.exit(-1);
        }

        String sessionKey = login();

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://" + hostname + "/qcbin/rest/domains");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.WILDCARD_TYPE);
        invocationBuilder.cookie("LWSSO_COOKIE_KEY", sessionKey);

        List<Domain> domains = invocationBuilder.get().readEntity(new GenericType<List<Domain>>() {
        });

        System.out.println(domains.get(0).getName());
    }

    public static String login() {
        Client client = ClientBuilder.newClient();
        client.register(new HttpBasicAuthFilter(username, password));
        WebTarget webTarget = client.target("http://" + hostname + "/qcbin/authentication-point/authenticate");

        Response response = webTarget.request(MediaType.TEXT_PLAIN_TYPE).get();
        return response.getHeaderString("Set-Cookie").split("=")[1].split(" ")[0];
    }
}
