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

public enum QcRunStepField {

    ACTUAL("actual"),
    ATTACHMENT("attachment"),
    BPT_FACET_TYPE("bpt-facet-type"),
    BPT_PATH("bpt-path"),
    BPTA_CONDITION("bpta-condition"),
    COMPONENT_DATA("component-data"),
    DESCRIPTION("description"),
    DESSTEP_ID("desstep-id"),
    EXECUTION_DATE("execution-date"),
    EXECUTION_TIME("execution-time"),
    EXPECTED("expected"),
    EXTENDED_REFERENCE("extended-reference"),
    ID("id"),
    LEVEL("level"),
    LINE_NO("line-no"),
    OBJ_ID("obj-id"),
    PARENT_ID("parent-id"),
    PATH("path"),
    REL_OBJ_ID("rel-obj-id"),
    RUN_ID("run-id"),
    STATUS("status"),
    STEP_NAME("step-name"),
    STEP_ORDER("step-order"),
    TEST_ID("test-id"),
    USER_01("user-01"),
    USER_02("user-02"),
    USER_03("user-03"),
    USER_04("user-04"),
    USER_05("user-05"),
    USER_06("user-06");

    private String name;

    QcRunStepField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
