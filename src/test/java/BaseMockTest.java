/*
 * QC REST API client
 *
 * Copyright (C) 2019  matthias.dirickx@outlook.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockserver.integration.ClientAndServer;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class BaseMockTest {

    protected ClientAndServer mockServer;

    @BeforeEach
    public void startServer() {
        mockServer = startClientAndServer(1080);
        mockServer
                .when(request("/qcbin/authentication-point/authenticate")
                        .withHeader("Authorization", "Basic YWJjOmRlZg=="))
                .respond(response()
                        .withHeader("Content-Type", "application/xml")
                        .withCookie("LWSSO_COOKIE_KEY", "akeyvalue"));

        mockServer
                .when(request("/qcbin/rest/is-authenticated")
                    .withCookie("LWSSO_COOKIE_KEY", "akeyvalue"))
                    .respond(
                        response()
                            .withBody(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                "<AuthenticationInfo>\n" +
                                "    <Username>abc</Username>\n" +
                                "</AuthenticationInfo>\t")
                            .withStatusCode(200)
                            .withHeader("Content-Type", "application/xml")
                            .withCookie("LWSO_COOKIE_KEY", "akeyvalue")
                    );
    }

    @AfterEach
    public void stopServer() {
        mockServer.stop();
    }
}
