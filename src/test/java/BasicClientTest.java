import be.mdi.testing.qc.client.QCRestClient;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class BasicClientTest {

    private ClientAndServer mockServer;

    @Test
    public void theClientCanLogIn() {
        mockServer = startClientAndServer(1080);
        mockServer
                .when(request("/qcbin/authentication-point/authenticate")
                        .withHeader("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("LWSSO_COOKIE_KEY", "akeyvalue"));

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "username", "password");

        qcc.login();

        assert qcc.isLoggedIn() == true;


        mockServer.stop();
    }

    @Test
    public void theClientCanLogInAndLogOut() {
        mockServer = startClientAndServer(1080);
        mockServer
                .when(request("/qcbin/authentication-point/authenticate")
                        .withHeader("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("LWSSO_COOKIE_KEY", "akeyvalue"));

        mockServer
                .when(request("/qcbin/authentication-point/logout")
                        .withCookie("LWSSO_COOKIE_KEY", "akeyvalue"))
                .respond(response());

        QCRestClient qcc = new QCRestClient("http://127.0.0.1:1080", "username", "password");

        qcc.login();
        qcc.logout();

        assert qcc.isLoggedIn() == false;


        mockServer.stop();
    }
}
