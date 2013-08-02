import darkcube.qc.client.QCRestClient;
import darkcube.qc.model.Defect;
import darkcube.qc.model.DefectField;
import darkcube.qc.model.Domain;
import darkcube.qc.model.Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class TestRunner {

    private static String host;
    private static String username;
    private static String password;

    public static void main(String[] args) {

        try {
            Properties prop = new Properties();

            //load a properties file
            prop.load(new FileInputStream("server.properties"));

            //get the property value and print it out
            host = prop.getProperty("host");
            username = prop.getProperty("username");
            password = prop.getProperty("password");

        } catch (IOException ex) {
            ex.printStackTrace();

            System.exit(-1);
        }

        QCRestClient client = new QCRestClient(host, username, password);
        client.login();

        List<Domain> domains = client.getDomains();
        List<Project> projects = client.getProjects(domains.get(0).getName());
        Defect defect = client.getDefect(domains.get(0).getName(),projects.get(0).getName(),1);
        System.out.println(defect.getField(DefectField.NAME));
    }
}
