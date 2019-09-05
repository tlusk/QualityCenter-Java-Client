package be.mdi.testing.qc.client;

import be.mdi.testing.qc.model.entities.Defect;
import be.mdi.testing.qc.model.Domains;
import be.mdi.testing.qc.model.Projects;

public class QCRestClient {

    private RestCallHandler callHandler;

    public QCRestClient(String host, String username, String password) {
        callHandler = new RestCallHandler(host, username, password);
    }

    public void login() {
        callHandler.login();
    }

    public void logout() { callHandler.logout(); }

    public boolean isLoggedIn() { return callHandler.isLoggedIn(); }

    public Domains getDomains() {
        return callHandler.getRestData(Domains.class, "rest/domains");
    }

    public Projects getProjects(String domain) {
        return callHandler.getRestData(Projects.class, "rest/domains/" + domain + "/projects");
    }

    public Defect getDefect(String domain, String project, int defectId) {
        return callHandler.getRestData(Defect.class, "rest/domains/" + domain + "/projects/" + project + "/defects/" + defectId);
    }
}
