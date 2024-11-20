package construction;

import java.util.Scanner;

public class Construction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;

        do {
            System.out.println("\n-------------------------");
            System.out.println("Welcome to the Construction App");
            System.out.println("--------------------------------");
            System.out.println("1: Project");
            System.out.println("2: Employee");
            System.out.println("3: Budgets");
            System.out.println("4: Permit");
            System.out.println("5: Exit");
            System.out.print("Enter Action: ");
            int act = sc.nextInt(); 

            switch (act) { 
                case 1:
                    Project pr = new Project();
                    pr.pTransaction();
                    break;
                case 2:
                    Employee em = new Employee();
                    em.eTransaction();
                    break;
                case 3:
                    Budget bud = new Budget();
                    bud.bTransaction();
                    break;
                case 4:
                    Permit pe = new Permit();
                    pe.pTransaction();
                    break;
                case 5:
                    System.out.print("Exit Selected... type 'yes' to continue: ");
                    String resp = sc.next();
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = false; 
                    }
                    break;
                default:
                    System.out.println("Invalid Selection. Try Again:");
                    break;
            }
        } while (exit); 
        System.out.println("----------------------------------------");
        System.out.println("Welcome to Construction from Tupas");
        System.out.println("------------------------------------------");
    
    }
}