package construction;
import java.util.Scanner;

public class Budget {
    
    public void bTransaction() {
        Scanner sc = new Scanner(System.in);
        String response;
        do {
            System.out.println("\n--------------------------");
            System.out.println("WELCOME Permit Panel ");
            System.out.println("1: ADD Budget");
            System.out.println("2: VIEW Budget");
            System.out.println("3: UPDATE Budget");
            System.out.println("4: DELETE Budget");
            System.out.print("Selection: ");
            int action = sc.nextInt();
            Budget bud = new  Budget ();

            switch (action) {
                case 1:
                    bud.addBudget();
                    bud.viewBudget();
                    break;
                case 2:
                    bud.viewBudget();
                    break;
                case 3:
                    bud.updateBudget();
                    break;
                case 4:
                    bud.deleteBudget();
                    break;
                default:
                    System.out.println("Invalid selection.");
            }

            System.out.print("Do you want to continue? (Yes/No): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("Yes"));
    }

     public void addBudget() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Budget First Name: ");
        String fname = sc.next();
        System.out.print("Budget Last Name: ");
        String lname = sc.next();
        System.out.print("Budget Email: ");
        String email = sc.next();
        System.out.print("Budget Address: ");
        String address = sc.next();
        System.out.print("Budget Status: ");
        String status = sc.next();

        String qry = "INSERT INTO tbl_budget(b_fname, b_lname, b_email, b_address, b_status) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(qry, fname, lname, email, address, status);
    }

    public void viewBudget() {
        config conf = new config();
        String qry = "SELECT * FROM tbl_employee";
        String[] hrds = {"ID", "First Name", "Last Name", "Email", "Address", "Status"};
        String[] clmn = {"id", "fname", "lname", "email", "address", "status"};
        conf.viewRecords(qry, hrds, clmn);
    }

    public void updateBudget() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        
        while (conf.SingleValue("SELECT b_id FROM tbl_budget WHERE b_id = ?", id) == 0) {
            
        }
            System.out.print("Selected ID doesn't exist! Enter ID again: ");
            id = sc.nextInt();
        

        System.out.print("New Budget First Name: ");
        String fname = sc.next();
        System.out.print("New Budget Last Name: ");
        String lname = sc.next();
        System.out.print("New Budget Email: ");
        String email = sc.next();
        System.out.print("New Budget Address: ");
        String address = sc.next();
        System.out.print("New Budget Status: ");
        String status = sc.next();

        String qry = "UPDATE tbl_employee SET b_fname = ?, b_lname = ?, b_email = ?, b_address = ?, b_status = ? WHERE b_id = ?";
        conf.updateRecord(qry, fname, lname, email, address, status, id);
    }

    public void deleteBudget() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        while (conf.SingleValue("SELECT p_id FROM tbl_budget WHERE b_id = ?", id) == 0) {
            System.out.print("Selected ID doesn't exist! Enter ID again: ");
            id = sc.nextInt();
        }

        String qry = "DELETE FROM tbl_budget WHERE p_id = ?";
        conf.deleteRecord(qry, id);
    }

    
}

