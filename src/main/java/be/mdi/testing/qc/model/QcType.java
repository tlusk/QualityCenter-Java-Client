package be.mdi.testing.qc.model;

public enum QcType {

    BUG("defect", "BUG", "defects", null),
    DEFECT("defect", "BUG", "defects", null),
    RUN("run", "RUN", "runs", null),
    RUN_STEP("run-step", "STEP", "run-steps", QcType.RUN);

    private String smallCapType;
    private String dbType;
    private String restUrlType;
    private QcType typeParent;

    QcType(String smallCapType, String dbType, String restUrlType, QcType typeParent) {
        this.smallCapType = smallCapType;
        this.dbType = dbType;
        this.restUrlType = restUrlType;
        this.typeParent = typeParent;
    }

    public String getSmallCapType() { return smallCapType; }
    public String getDbType() { return dbType; }
    public String getRestUrlType() { return restUrlType; }
    public boolean hasTypeParent() { if(typeParent == null){return false;}else{return true;} }
    public QcType getTypeParent() { return typeParent; }

    public String getEntityUrl() {
        String url = "";
        if(this.hasTypeParent()) {
            url += "/";
            url += this.getTypeParent().getRestUrlType();
        }
        url += "/";
        url += this.getRestUrlType();

        return url;
    }

}
