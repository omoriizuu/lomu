public class PremiumActivity extends DateActivity {
    private double bookingFeeRate = 0.12;

    public PremiumActivity(String name, String location, double baseCost) {
        super(name, location, baseCost);
    }

    @Override
    public double calculateTotalCost() {
        return baseCost + (baseCost * bookingFeeRate);
    }

    @Override
    public void displayActivityDetails() {
        System.out.printf("[PREMIUM ACTIVITY] %s at %s\n", getName(), getLocation());
        System.out.printf("   Base Ticket Cost: ₱%.2f\n", getBaseCost());
        System.out.printf("   Total (Inc. 12%% Booking Fee): ₱%.2f\n", calculateTotalCost());
    }
}
