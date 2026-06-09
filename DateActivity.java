public abstract class DateActivity {
    private String name;
    private String location;
    protected double baseCost;

    public DateActivity(String name, String location, double baseCost) {
        this.name = name;
        this.location = location;
        this.baseCost = baseCost;
    }

    public abstract double calculateTotalCost();
    public abstract void displayActivityDetails();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public double getBaseCost() { return baseCost; }
    public void setBaseCost(double baseCost) { this.baseCost = baseCost; }
}
