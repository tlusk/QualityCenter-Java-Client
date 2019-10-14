import be.mdi.testing.qc.model.QcStatus;
import org.junit.jupiter.api.Test;

public class QcStatusTest {

    @Test
    public void qcStatusReturnsTheStatus() {
        assert QcStatus.PASSED.getName().equals("Passed");
        assert QcStatus.BLOCKED.getName().equals("Blocked");
        assert QcStatus.NO_RUN.getName().equals("No run");
        assert QcStatus.NOT_COMPLETED.getName().equals("Not completed");
    }
}
