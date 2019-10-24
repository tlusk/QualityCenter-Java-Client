package be.mdi.testing.qc.model.composits;

public interface QcCommitable {
    void commit();
    void update();
    void get(int id);
}
