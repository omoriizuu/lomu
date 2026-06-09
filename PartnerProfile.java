public class PartnerProfile {
    private String name;
    private double allocatedBudget;
    private String currentAvailability;

    public PartnerProfile(String name, double allocatedBudget, String currentAvailability) {
        this.name = name;
        this.allocatedBudget = allocatedBudget;
        this.currentAvailability = currentAvailability;
    }

    public void updateProfile(String newAvailability) {
        this.currentAvailability = newAvailability;
        System.out.println(">> " + name + "'s availability successfully shared and updated to: " + newAvailability);
    }

    public void updateProfile(double addedBudget) {
        if (addedBudget > 0) {
            this.allocatedBudget += addedBudget;
            System.out.printf(">> Co-op Budget Updated! Added ₱%.2f to %s's balance.\n", addedBudget, name);
        }
    }

    public String getName() { return name; }
    public double getAllocatedBudget() { return allocatedBudget; }
    public void deductBudget(double amount) { this.allocatedBudget -= amount; }
    public String getCurrentAvailability() { return currentAvailability; }
}
