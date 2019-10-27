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
package be.mdi.testing.qc.client;

import static be.mdi.testing.qc.PropertyManager.getProperty;

public class StaticQCRestClient {

    private static StaticQCRestClient staticQcRestClient;
    private QCRestClient qcc;

    private StaticQCRestClient() {
        qcc = new QCRestClient(
                getProperty("qcclient.host"),
                getProperty("qcclient.username"),
                getProperty("qcclient.password"));
    }

    public static synchronized QCRestClient getQcClient() {
        if(staticQcRestClient == null) {
            synchronized(StaticQCRestClient.class) {
                if(staticQcRestClient == null) {
                    staticQcRestClient = new StaticQCRestClient();
                }
            }
        }

        return staticQcRestClient.qcc;
    }
}
