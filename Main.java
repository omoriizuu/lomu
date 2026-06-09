import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<DateActivity> wishlist = new ArrayList<>();
    private static List<DateActivity> confirmedDates = new ArrayList<>();
    
    private static PartnerProfile mina = new PartnerProfile("Mina", 2500.00, "Saturday Evening");
    private static PartnerProfile yuhan = new PartnerProfile("Yuhan", 3000.00, "Saturday Evening");

    public static void main(String[] args) {
        wishlist.add(new BudgetActivity("Historical Walled City Tour", "Intramuros, Manila", 150.00));
        wishlist.add(new PremiumActivity("Deep Ocean Tunnel Experience", "Manila Ocean Park", 799.00));
        wishlist.add(new PremiumActivity("Thrills and Rides Afternoon", "Star City, Pasay/Manila", 699.00));
        wishlist.add(new BudgetActivity("Sunset Seawall Walk & Picnic", "Baywalk Roxas Boulevard", 0.00));

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("=================================================");
        System.out.println("   WELCOME TO TWO HEARTS DATE MANAGEMENT SYSTEM  ");
        System.out.println("         Dedicated to: Mina ❤ Yuhan             ");
        System.out.println("=================================================");

        while (choice != 5) {
            System.out.println("\n<--- MAIN MENU --->");
            System.out.println("1. View Current Co-Op Status & Budgets");
            System.out.println("2. Explore Manila Date Wishlist & Book a Date");
            System.out.println("3. View Confirmed Date Itinerary");
            System.out.println("4. Edit Schedules or Pool Extra Funds");
            System.out.println("5. Exit System");
            System.out.print("Select an option (1-5): ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format. Please type a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1: viewCoOpStatus(); break;
                case 2: exploreAndBookDate(scanner); break;
                case 3: viewItinerary(); break;
                case 4: coOpEditProfiles(scanner); break;
                case 5:
                    System.out.println("\nThank you for utilizing Two Hearts Manager. Have a beautiful date in Manila, Mina and Yuhan!");
                    break;
                default:
                    System.out.println("Out of bounds range. Please choose from options 1 to 5.");
            }
        }
        scanner.close();
    }

    private static void viewCoOpStatus() {
        System.out.println("\n=== CO-OP PROFILE DASHBOARD ===");
        System.out.printf("Partner 1: %s | Budget Available: ₱%.2f | Free Time: %s\n", 
                mina.getName(), mina.getAllocatedBudget(), mina.getCurrentAvailability());
        System.out.printf("Partner 2: %s | Budget Available: ₱%.2f | Free Time: %s\n", 
                yuhan.getName(), yuhan.getAllocatedBudget(), yuhan.getCurrentAvailability());
        
        double totalPooledBudget = mina.getAllocatedBudget() + yuhan.getAllocatedBudget();
        System.out.printf("Combined Spending Capacity: ₱%.2f\n", totalPooledBudget);

        if (mina.getCurrentAvailability().equalsIgnoreCase(yuhan.getCurrentAvailability())) {
            System.out.println("Status Status: Schedules Match! You are both ready for a date night.");
        } else {
            System.out.println("Status Status: Schedule conflict detected. Coordinate updates via Option 4.");
        }
    }

    private static void exploreAndBookDate(Scanner scanner) {
        System.out.println("\n=== AVAILABLE MANILA ACTIVITIES ===");
        for (int i = 0; i < wishlist.size(); i++) {
            System.out.print((i + 1) + ". ");
            wishlist.get(i).displayActivityDetails();
        }
        System.out.print("Select the number of the date activity you both want to lock in: ");
        
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < wishlist.size()) {
                DateActivity selected = wishlist.get(index);
                double costForTwo = selected.calculateTotalCost() * 2;
                double combinedBudget = mina.getAllocatedBudget() + yuhan.getAllocatedBudget();

                System.out.printf("\nTarget Activity: %s\n", selected.getName());
                System.out.printf("Calculated Total Expense for Two: ₱%.2f\n", costForTwo);

                if (combinedBudget >= costForTwo) {
                    double costPerPerson = costForTwo / 2;
                    mina.deductBudget(costPerPerson);
                    yuhan.deductBudget(costPerPerson);

                    confirmedDates.add(selected);
                    wishlist.remove(index);
                    
                    System.out.println("Success! Date booked perfectly. Funds split equally (₱" + costPerPerson + " each).");
                } else {
                    System.out.println("Insufficient funds! Choose a more economical destination or add pocket money in Option 4.");
                }
            } else {
                System.out.println("Error: That number is not on the list.");
            }
        } catch (Exception e) {
            System.out.println("Invalid numeric action selected.");
        }
    }

    private static void viewItinerary() {
        System.out.println("\n=== MINA & YUHAN'S CONFIRMED ITINERARY ===");
        if (confirmedDates.isEmpty()) {
            System.out.println("Your calendar looks blank! Go explore and add plans from the Manila catalog.");
            return;
        }
        for (DateActivity date : confirmedDates) {
            System.out.printf("❤ %s located at %s\n", date.getName(), date.getLocation());
        }
    }

    private static void coOpEditProfiles(Scanner scanner) {
        System.out.println("\n=== CO-OP EDITING SUITE ===");
        System.out.println("1. Synchronize / Change Free Time Status");
        System.out.println("2. Inject Top-Up Allowances");
        System.out.print("Select choice: ");
        
        String option = scanner.nextLine();
        if (option.equals("1")) {
            System.out.print("Enter shared availability text (e.g., Sunday Afternoon): ");
            String newSched = scanner.nextLine();
            mina.updateProfile(newSched);
            yuhan.updateProfile(newSched);
        } else if (option.equals("2")) {
            System.out.print("Enter extra budget amount to add for Mina: ₱");
            double mFund = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter extra budget amount to add for Yuhan: ₱");
            double yFund = Double.parseDouble(scanner.nextLine());
            mina.updateProfile(mFund);
            yuhan.updateProfile(yFund);
        } else {
            System.out.println("Invalid execution target.");
        }
    }
}
