package be.mdi.testing.qc.client;

import be.mdi.testing.qc.model.entities.Defect;
import be.mdi.testing.qc.model.Domain;
import be.mdi.testing.qc.model.Project;

import javax.ws.rs.core.GenericType;
import java.util.List;

public class QCRestClient {

    private RestCallHandler callHandler;

    public QCRestClient(String host, String username, String password) {
        callHandler = new RestCallHandler(host, username, password);
    }

    public void login() {
        callHandler.login();
    }

    public List<Domain> getDomains() {
        return callHandler.getRestData(new GenericType<List<Domain>>() {}, "rest/domains");
    }

    public List<Project> getProjects(String domain) {
        return callHandler.getRestData(new GenericType<List<Project>>(){}, "rest/domains/" + domain + "/projects");
    }

    public Defect getDefect(String domain, String project, int defectId) {
        return callHandler.getRestData(Defect.class, "rest/domains/" + domain + "/projects/" + project + "/defects/" + defectId);
    }
}
