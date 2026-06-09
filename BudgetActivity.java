public class BudgetActivity extends DateActivity {
    private double estimatedSnackExpense = 250.00;

    public BudgetActivity(String name, String location, double baseCost) {
        super(name, location, baseCost);
    }

    @Override
    public double calculateTotalCost() {
        return baseCost + estimatedSnackExpense;
    }

    @Override
    public void displayActivityDetails() {
        System.out.printf("[BUDGET/CULTURAL ACTIVITY] %s at %s\n", getName(), getLocation());
        System.out.printf("   Base Entry Fee: ₱%.2f\n", getBaseCost());
        System.out.printf("   Total (Inc. ₱%.2f Street Food Allowance): ₱%.2f\n", estimatedSnackExpense, calculateTotalCost());
    }
}
