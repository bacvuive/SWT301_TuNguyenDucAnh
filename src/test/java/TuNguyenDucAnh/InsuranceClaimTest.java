package TuNguyenDucAnh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class InsuranceClaimTest {

    private InsuranceClaim claim;

    @BeforeEach
    void setUp() {
        claim = new InsuranceClaim("C001", 1000.0);
    }

    @Test
    @DisplayName("Constructor khởi tạo đúng giá trị mặc định")
    void testConstructorInitializesValues() {
        assertEquals("C001", claim.getClaimId());
        assertEquals(1000.0, claim.getAmount());
        assertEquals("Pending", claim.getClaimStatus());
    }

    @Test
    @DisplayName("Constructor ném exception nếu amount <= 0")
    void testConstructorInvalidAmount() {
        assertThrows(IllegalArgumentException.class, () -> new InsuranceClaim("C002", 0));
    }

    @Test
    @DisplayName("Constructor ném exception nếu claimId null")
    void testConstructorNullClaimId() {
        assertThrows(IllegalArgumentException.class, () -> new InsuranceClaim(null, 1000.0));
    }

    @Test
    @DisplayName("processClaim: Pending -> Approved thành công")
    void testProcessClaimWhenPending() {
        boolean result = claim.processClaim("Approved");
        assertTrue(result);
        assertEquals("Approved", claim.getClaimStatus());
    }

    @Test
    @DisplayName("processClaim: Không đổi nếu không còn Pending")
    void testProcessClaimWhenNotPending() {
        claim.processClaim("Approved");
        boolean result = claim.processClaim("Rejected");
        assertFalse(result);
        assertEquals("Approved", claim.getClaimStatus());
    }

    @Test
    @DisplayName("processClaim: ném exception nếu newStatus null")
    void testProcessClaimNullInput() {
        assertThrows(IllegalArgumentException.class, () -> claim.processClaim(null));
    }

    @Test
    @DisplayName("calculatePayout: Approved trả 85%")
    void testCalculatePayoutApproved() {
        claim.processClaim("Approved");
        assertEquals(850.0, claim.calculatePayout(), 0.001);
    }

    @Test
    @DisplayName("calculatePayout: Không Approved trả 0")
    void testCalculatePayoutNotApproved() {
        assertEquals(0, claim.calculatePayout());
    }

    @Test
    @DisplayName("updateClaimAmount: cập nhật hợp lệ")
    void testUpdateClaimAmount() {
        claim.updateClaimAmount(2000.0);
        assertEquals(2000.0, claim.getAmount());
    }

    @Test
    @DisplayName("updateClaimAmount: ném exception nếu <= 0")
    void testUpdateClaimAmountInvalid() {
        assertThrows(IllegalArgumentException.class, () -> claim.updateClaimAmount(0));
    }

    @ParameterizedTest(name = "{index}) status={0} => payout={1}")
    @CsvSource({
            "Approved,850.0",
            "Rejected,0",
            "Pending,0"
    })
    @DisplayName("Parameterized: calculatePayout theo nhiều trạng thái")
    void testCalculatePayoutVariousStatuses(String status, double expectedPayout) {
        if (!"Pending".equals(status)) { // nếu Pending thì giữ nguyên mặc định
            claim.processClaim(status);
        }
        assertEquals(expectedPayout, claim.calculatePayout(), 0.001);
    }

    @Test
    @DisplayName("toString chứa đủ thông tin chính")
    void testToStringFormat() {
        String output = claim.toString();
        assertTrue(output.contains("InsuranceClaim"));
        assertTrue(output.contains("claimId='C001'"));
        assertTrue(output.contains("amount=1000.0"));
        assertTrue(output.contains("claimStatus='Pending'"));
    }
}
