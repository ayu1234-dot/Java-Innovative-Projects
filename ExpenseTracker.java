import java.io.*;
import java.time.LocalDate;
import java.util.*;

class Expense implements Serializable {
    double amount;
    String category;
    String date;

    Expense(double amount, String category, String date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String toString() {
        return "Amount: ₹" + amount + " | Category: " + category + " | Date: " + date;
    }
}

public class ExpenseTracker {
    static ArrayList<Expense> expenses = new ArrayList<>();
    static final String FILE_NAME = "expenses.dat";

    static void addExpense(Scanner sc) {
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        expenses.add(new Expense(amount, category, date));
        System.out.println("Expense Added Successfully.");
    }

    static void displayExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No Expenses Found.");
            return;
        }

        System.out.println("\nAll Expenses:");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    static void monthlyReport() {
        Map<String, Double> report = new HashMap<>();

        for (Expense e : expenses) {
            String month = e.date.substring(0, 7);
            report.put(month, report.getOrDefault(month, 0.0) + e.amount);
        }

        System.out.println("\nMonthly Expense Report:");
        for (String month : report.keySet()) {
            System.out.println(month + " : ₹" + report.get(month));
        }
    }

    static void highestCategory() {
        Map<String, Double> categoryTotal = new HashMap<>();

        for (Expense e : expenses) {
            categoryTotal.put(e.category,
                    categoryTotal.getOrDefault(e.category, 0.0) + e.amount);
        }

        String highestCategory = "";
        double max = 0;

        for (String category : categoryTotal.keySet()) {
            if (categoryTotal.get(category) > max) {
                max = categoryTotal.get(category);
                highestCategory = category;
            }
        }

        if (!highestCategory.isEmpty()) {
            System.out.println("Highest Expense Category: "
                    + highestCategory + " (₹" + max + ")");
        }
    }

    static void saveExpenses() {
        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(FILE_NAME));

            out.writeObject(expenses);
            out.close();

            System.out.println("Expenses Saved Successfully.");
        } catch (Exception e) {
            System.out.println("Error Saving Data.");
        }
    }

    static void loadExpenses() {
        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(FILE_NAME));

            expenses = (ArrayList<Expense>) in.readObject();
            in.close();

            System.out.println("Expenses Loaded Successfully.");
        } catch (Exception e) {
            System.out.println("No Previous Data Found.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        loadExpenses();

        while (true) {
            System.out.println("\n===== PERSONAL EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. Display Expenses");
            System.out.println("3. Monthly Report");
            System.out.println("4. Highest Expense Category");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addExpense(sc);
                    break;

                case 2:
                    displayExpenses();
                    break;

                case 3:
                    monthlyReport();
                    break;

                case 4:
                    highestCategory();
                    break;

                case 5:
                    saveExpenses();
                    break;

                case 6:
                    saveExpenses();
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}