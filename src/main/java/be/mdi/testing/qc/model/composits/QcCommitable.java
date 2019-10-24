package be.mdi.testing.qc.model.composits;

public interface QcCommitable {
    void commit(String host, String username, String password);
    void update();
}
