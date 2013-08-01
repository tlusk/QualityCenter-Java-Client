package darkcube.qc.model;

public enum DefectField {
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
    TO_MAIL("to-mail");

    private String name;

    DefectField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
