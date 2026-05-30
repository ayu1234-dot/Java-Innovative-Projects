import java.util.Scanner;

class SmartPasswordGenerator {

    public static String generatePassword(String name, String pan, String dob) {
        String part1 = name.substring(0, Math.min(3, name.length())).toUpperCase();
        String part2 = pan.substring(Math.max(0, pan.length() - 4));
        String part3 = dob.replace("/", "");
        return part1 + "@" + part2 + part3;
    }

    public static String checkStrength(String password) {
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch))
                hasUpper = true;
            else if (Character.isLowerCase(ch))
                hasLower = true;
            else if (Character.isDigit(ch))
                hasDigit = true;
            else
                hasSpecial = true;
        }

        int score = 0;
        if (hasUpper) score++;
        if (hasLower) score++;
        if (hasDigit) score++;
        if (hasSpecial) score++;

        if (password.length() >= 12 && score == 4)
            return "Strong";
        else if (password.length() >= 8 && score >= 3)
            return "Medium";
        else
            return "Weak";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Number of Users: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nUser " + i);

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter PAN: ");
            String pan = sc.nextLine();

            System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
            String dob = sc.nextLine();

            String password = generatePassword(name, pan, dob);

            System.out.println("Generated Password: " + password);
            System.out.println("Password Strength: " + checkStrength(password));
        }

        sc.close();
    }
}