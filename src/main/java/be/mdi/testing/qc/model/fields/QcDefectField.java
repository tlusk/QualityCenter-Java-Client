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

public enum QcDefectField {
    ACTUAL_FIX_TIME("actual-fix-time"),
    ATTACHMENT("attachment"),
    BUG_ID("id"),
    BUG_VER_STAMP("bug-ver-stamp"),
    CLOSING_VERSION("closing-version"),
    CLOSING_DATE("closing-date"),
    CYCLE_ID("cycle-id"),
    CYCLE_REFERENCE("cycle-reference"),
    DESCRIPTION("description"),
    DETECTED_BY("detected-by"),
    DETECTED_IN_RCYC("detected-in-rcyc"),
    DETECTED_IN_REL("detected-in-rel"),
    DETECTION_VERSION("detection-version"),
    DETECTION_DATE("creation-time"),
    DEV_COMMENTS("dev-comments"),
    ESTIMATED_FIX_TIME("estimated-fix-time"),
    EXTENDED_REFERENCE("extended-reference"),
    LAST_MODIFIED("last-modified"),
    NAME("name"),
    PLANNED_CLOSING_VER("planned-closing-ver"),
    PRIORITY("priority"),
    PROJECT("project"),
    REPRODUCIBLE("reproducible"),
    REQUEST_ID("request-id"),
    REQUEST_NOTE("request-note"),
    REQUEST_TYPE("request-type"),
    REQUEST_SERVER("request-server"),
    RESPONSIBLE("owner"),
    RUN_REFERENCE("run-reference"),
    SEVERITY("severity"),
    STATUS("status"),
    STEP_REFERENCE("step-reference"),
    SUBJECT("subject"),
    TARGET_RCYC("target-rcyc"),
    TARGET_REL("target-rel"),
    TEST_REFERENCE("test-reference"),
    TO_MAIL("to-mail"),
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
    USER_13("user-13"),
    USER_14("user-14"),
    USER_15("user-15"),
    USER_16("user-16"),
    USER_17("user-17"),
    USER_18("user-18"),
    USER_19("user-19"),
    USER_20("user-20"),
    USER_21("user-21"),
    USER_22("user-22"),
    USER_23("user-23"),
    USER_24("user-24");


    private String name;

    QcDefectField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
