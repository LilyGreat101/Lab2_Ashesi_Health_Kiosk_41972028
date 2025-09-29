import java.util.Scanner;
public class HealthKiosk {
    public static void main(String[] args) {
        // Task 0
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Ashesi Health Kiosks");


        // Task 1
        System.out.println("Enter service code (P/L/T/C): ");

        String serviceCode = sc.nextLine();
        char code;
        code = Character.toUpperCase(serviceCode.charAt(0));

        switch (code) {
            case 'P' ->  System.out.println("Go to: Pharmacy Desk");
            case 'L' ->  System.out.println("Go to: Lab Desk");
            case 'T' ->  System.out.println("Go to: Triage Desk");
            case 'C' ->  System.out.println("Go to: Counseling Desk");
            default -> System.out.println("Invalid service code");
        }

        // Task 2
        System.out.print("Enter health metric (1 for BMI, 2 for Dosage round-up, 3 for trig helper): ");
        int healthMetric = sc.nextInt();

        double bmi = 0;
        int dosage = 0;
        double angle = 0;
        double bmiRound = 0;

        if (healthMetric == 1) {
            System.out.print("Enter weight in kg: ");
            double weight = sc.nextDouble();
            System.out.print("Enter height in m: ");
            double height = sc.nextDouble();

            bmi = weight/Math.pow(height,2);
            bmiRound = Math.round(bmi*10)/10.0;

            System.out.println("BMI: " + bmiRound + " Category: ");

            if (bmiRound < 18.5) {
                System.out.println("Underweight");
            } else if (bmiRound <= 24.9) {
                System.out.println("Normal");
            } else if (bmiRound <= 29.9) {
                System.out.println("Overweight");
            } else {
                System.out.println("Obese");
            }
        } else if (healthMetric == 2) {
            System.out.print("Enter required dosage in mg: ");
            double tablet = sc.nextDouble();
            dosage = (int) Math.ceil(tablet / 250.0);
            System.out.println("Number of dosage: " + dosage);
        } else if (healthMetric == 3) {
            System.out.print("Enter angle in degrees: ");
            angle = sc.nextDouble();
            double radians = Math.toRadians(angle);

            double sinVal = Math.round(Math.sin(radians) * 1000) / 1000.0;
            double cosVal = Math.round(Math.cos(radians) * 1000) / 1000.0;

            System.out.printf("sin(%f) = .3f", angle, sinVal);
            System.out.printf("cos(%f) = .3f", angle, cosVal);
        } else {
            System.out.println("Invalid health metric option");
        }

        // Task 3
        char randomCharacter = (char)('A' + (int)(Math.random() * 26));

        StringBuilder digit = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int number = 3 + (int)(Math.random() * 7);
            digit.append(number);
        }

        String shortCode = randomCharacter + digit.toString();

        if (shortCode.length() != 5) {
            System.out.println("Invalid length");
        } else if (!Character.isLetter(shortCode.charAt(0))) {
            System.out.println("Invalid: first char must be a letter");
        } else if (!(Character.isDigit(shortCode.charAt(1)) &&
                Character.isDigit(shortCode.charAt(2)) &&
                Character.isDigit(shortCode.charAt(3)) &&
                Character.isDigit(shortCode.charAt(4)))) {
            System.out.println("Invalid: last 4 must be digits");
        } else {
            System.out.println("ID OK");
        }

        // Task 4
        System.out.print("Enter your first name: ");
        String firstName = sc.next();

        char firstChar = Character.toUpperCase(firstName.charAt(0));

        char shiftedChar = (char) ('A' + (firstChar - 'A' + 2) % 26);

        String lastTwo = shortCode.substring(shortCode.length() - 2);

        int metricValue = 0;
        if (healthMetric == 1) {
            metricValue = (int) Math.round(bmi);
        } else if (healthMetric == 2) {
            metricValue = dosage;
        } else if (healthMetric == 3) {
            metricValue = (int) Math.round(Math.sin(Math.toRadians(angle)) * 100);
        }

        String codeFormat = shiftedChar + lastTwo + "-" + metricValue;
        System.out.println("Display Code: " + codeFormat);

        // Task 5
        String summary = switch (Character.toUpperCase(code)) {
            case 'P' -> "PHARMACY | shortCode=" + shortCode + " | Code=" + codeFormat;
            case 'L' -> "LAB | shortCode=" + shortCode + " | Code=" + codeFormat;
            case 'T' -> "TRIAGE | shortCode=" + shortCode + " | BMI=" + bmiRound + " | Code=" + codeFormat;
            case 'C' -> "COUNSELING | shortCode=" + shortCode + " | Code=" + codeFormat;
            default -> "Invalid service, no summary available.";
        };

        System.out.println(summary);

        sc.close();
    }

}
