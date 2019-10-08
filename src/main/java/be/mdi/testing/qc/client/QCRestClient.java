package be.mdi.testing.qc.client;

import be.mdi.testing.qc.model.entities.Defect;
import be.mdi.testing.qc.model.Domains;
import be.mdi.testing.qc.model.Projects;
import be.mdi.testing.qc.model.entities.QcEntity;
import be.mdi.testing.qc.model.fields.DefectField;

public class QCRestClient {

    private RestCallHandler callHandler;

    public QCRestClient(String host, String username, String password) {
        callHandler = new RestCallHandler(host, username, password);
    }

    // Overhead & General items like logging in

    public QCRestClient login() {
        callHandler.login();
        return this;
    }

    public void logout() { callHandler.logout(); }

    public boolean isLoggedIn() { return callHandler.isLoggedIn(); }

    // Generic items
    public Domains getDomains() {
        return callHandler.getRestData(Domains.class, "rest/domains");
    }

    public Domains getDomainsWithProjects() {
        return callHandler.getRestData(Domains.class, "rest/domains?include-projects-info=y");
    }

    public Projects getProjects(String domain) {
        return callHandler.getRestData(Projects.class, "rest/domains/" + domain + "/projects");
    }

    // Defect
    public Defect getDefect(String domain, String project, int defectId) {
        return callHandler.getRestData(
                Defect.class,
                "rest/domains/" + domain + "/projects/" + project + "/defects/" + defectId);
    }

    public void postDefect(String domain, String project, Defect defect) {
        callHandler.postRestData(
                defect,
                "rest/domains/" + domain + "/projects/" + project + "/defects/");
    }

    public void putDefect(String domain, String project, Defect defect) {
        callHandler.putRestData(
                defect,
                "rest/domains/" + domain +
                        "/projects/" + project +
                        "/defects/" + defect.getField(DefectField.BUG_ID));
    }

    // Create new run with result for specific test instance
    // in first instance --> assume that if multiple are returned, must take the first one.
    // Just to be robust at the start - we must manage this more elegantly.
}
