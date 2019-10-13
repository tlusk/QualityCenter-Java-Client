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
package be.mdi.testing.qc.model.fields;

public enum QcRunField {

    TESTCYCL_ID("testcycl-id"),
    CYCLE_ID("cycle-id"),
    TEST_ID("test-id"),
    TEST_CONFIG_ID("test-config-id"),
    ID("id"),
    RUN_NAME("run-name"),
    EXECUTION_DATE("execution-date"),
    EXECUTION_TIME("execution-time"),
    HAS_VTC("has-vtc"),
    HOST("host"),
    STATUS("status"),
    DURATION("duration"),
    DRAFT("draft"),
    TESTER_NAME("tester-name"),
    ITERS_PARAMS_VALUES("iters-params-values"),
    ITERS_SUM_STATUS("iters-sum-status"),
    PATH("path"),
    USER_01("user-01"),
    USER_02("user-02"),
    USER_03("user-03"),
    USER_04("user-04"),
    USER_05("user-05"),
    USER_06("user-06"),
    USER_07("user-07"),
    USER_08("user-08"),
    USER_09("user-09"),
    USER_10("user-10"),
    USER_11("user-11"),
    USER_12("user-12"),
    TEST_VERSION("test-version"),
    ATTACHMENT("attachment"),
    RUN_VER_STAMP("run-ver-stamp"),
    VTS("vts"),
    CYCLE("cycle"),
    TEST_INSTANCE("test-instance"),
    OS_NAME("os-name"),
    OS_SP("os-sp"),
    OS_BUILD("os-build"),
    VC_LOKEDBY("vc-lokedby"),
    VC_STATUS("vc-status"),
    VC_VERSION("vc-version"),
    OS_CONFIG("os-config"),
    ASSIGN_RCYC("assign-rcyc"),
    BPTA_CHANGE_DETECTED("bpta-change-detected"),
    BPTA_CHANGE_AWARENESS("bpta-change-awareness"),
    VC_VERSION_NUMBER("vc-version-number"),
    PINNED_BASELINE("pinned-baseline"),
    BPT_STRUCTURE("bpt-structure"),
    STATE("state"),
    COMMENTS("comments"),
    SUBTYPE_ID("subtype-id"),
    TEXT_SYNC("text-sync"),
    ENVIRONMENT("environment"),
    BUILD_REVISION("build-revision"),
    DETAIL("detail"),
    JENKINS_URL("jenkins-url"),
    JENKINS_JOB_NAME("jenkins-job-name"),
    RESULTS_FILES_NETWORK_PATH("results-files-network-path"),
    LAST_MODIFIED("last-modified");


    private String name;

    QcRunField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
