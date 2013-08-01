package darkcube.qc;

import darkcube.qc.model.Defect;
import darkcube.qc.model.Domain;
import darkcube.qc.model.Project;
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

    private static String sessionKey;

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

        sessionKey = login();
        List<Domain> domains = getDomains();
        List<Project> projects = getProjects(domains.get(0).getName());
        Defect defect = getDefect(domains.get(0).getName(),projects.get(0).getName(),1);
        System.out.println(defect.getFields().get(0).getName());
    }



    public static List<Domain> getDomains() {
        return getRestData(new GenericType<List<Domain>>(){}, "rest/domains");
    }

    public static List<Project> getProjects(String domain) {
        return getRestData(new GenericType<List<Project>>(){}, "rest/domains/" + domain + "/projects");
    }

    public static Defect getDefect(String domain, String project, int defectId) {
        return getRestData(Defect.class, "rest/domains/" + domain + "/projects/" + project + "/defects/" + defectId);
    }

    public static <T> T getRestData(Class<T> retType, String restUrl) {
        Invocation.Builder invocationBuilder = buildRestRequest(restUrl);
        return invocationBuilder.get().readEntity(retType);
    }

    public static <T> T getRestData(GenericType<T> retType, String restUrl) {
        Invocation.Builder invocationBuilder = buildRestRequest(restUrl);
        return invocationBuilder.get().readEntity(retType);
    }

    public static String login() {
        Client client = ClientBuilder.newClient();
        client.register(new HttpBasicAuthFilter(username, password));
        WebTarget webTarget = client.target("http://" + hostname + "/qcbin/authentication-point/authenticate");

        Response response = webTarget.request(MediaType.TEXT_PLAIN_TYPE).get();
        return response.getHeaderString("Set-Cookie").split("=")[1].split(" ")[0];
    }

    private static Invocation.Builder buildRestRequest(String restUrl) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://" + hostname + "/qcbin/" + restUrl);

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.WILDCARD_TYPE);
        return invocationBuilder.cookie("LWSSO_COOKIE_KEY", sessionKey);
    }


}
