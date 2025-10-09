package TuNguyenDucAnh;

public class InsuranceClaim {
    private String claimId;
    private double amount;
    private String claimStatus; // "Pending", "Approved", "Rejected"

    public InsuranceClaim(String id, double claimAmount) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Claim ID cannot be null or empty");
        }
        if (claimAmount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.claimId = id;
        this.amount = claimAmount;
        this.claimStatus = "Pending";
    }

    public String getClaimId() { return claimId; }
    public double getAmount() { return amount; }
    public String getClaimStatus() { return claimStatus; }

    /** Chỉ cho phép đổi trạng thái khi hiện tại là Pending; trạng thái mới phải là Approved/Rejected */
    public boolean processClaim(String newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        if ("Pending".equals(claimStatus)) {
            if (!("Approved".equals(newStatus) || "Rejected".equals(newStatus))) {
                throw new IllegalArgumentException("Invalid status: " + newStatus);
            }
            this.claimStatus = newStatus;
            return true;
        }
        return false;
    }

    /** Payout = 85% amount nếu Approved; ngược lại 0 */
    public double calculatePayout() {
        if ("Approved".equals(claimStatus)) {
            return amount * 0.85;
        }
        return 0;
    }

    /** Cập nhật amount > 0 */
    public void updateClaimAmount(double newAmount) {
        if (newAmount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.amount = newAmount;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{" +
                "claimId='" + claimId + '\'' +
                ", amount=" + amount +
                ", claimStatus='" + claimStatus + '\'' +
                '}';
    }
}
