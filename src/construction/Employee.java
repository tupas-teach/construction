package construction;

import java.util.Scanner;
class Employee {

    public void eTransaction() {
        Scanner sc = new Scanner(System.in);
        String response;
        do {
            System.out.println("\n--------------------------");
            System.out.println("WELCOME Permit Panel ");
            System.out.println("\n___________________________");
            System.out.println("1: ADD Employee");
            System.out.println("2: VIEW Employee");
            System.out.println("3: UPDATE Employee");
            System.out.println("4: DELETE Employee");
            System.out.print("Selection: ");
            int action = sc.nextInt();
            Employee em = new Employee();

            switch (action) {
                case 1:
                    em.addEmployee();
                     em.viewEmployee();
                    break;
                case 2:
                    em.viewEmployee();
                    break;
                case 3:
                     em.viewEmployee();
                    em.updateEmployee();
                     em.viewEmployee();
                    break;
                case 4:
                     em.viewEmployee();
                    em.deleteEmployee();
                     em.viewEmployee();
                    break;
                default:
                    System.out.println("Invalid selection.");
            }

            System.out.print("Do you want to continue? (Yes/No): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("Yes"));
    }

     public void addEmployee() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Employee First Name: ");
        String fname = sc.next();
        System.out.print("Employee Last Name: ");
        String lname = sc.next();
        System.out.print("Employee Email: ");
        String email = sc.next();
        System.out.print("Employee Address: ");
        String address = sc.next();
        System.out.print("Employee Status: ");
        String status = sc.next();

        String qry = "INSERT INTO tbl_employee(e_fname, e_lname, e_email, e_address, e_status) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(qry, fname, lname, email, address, status);
    }

    public void viewEmployee() {
        config conf = new config();
        String qry = "SELECT * FROM tbl_employee";
        String[] hrds = {"ID", "First Name", "Last Name", "Email", "Address", "Status"};
        String[] clmn = {"id", "fname", "lname", "email", "address", "status"};

        conf.viewRecords(qry, hrds, clmn);
    }

    public void updateEmployee() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        
        while (conf.SingleValue("SELECT e_id FROM tbl_employee WHERE e_id = ?", id) == 0) {
            System.out.print("Selected ID doesn't exist! Enter ID again: ");
            id = sc.nextInt();
        }

        System.out.print("New Employee First Name: ");
        String fname = sc.next();
        System.out.print("New Employee Last Name: ");
        String lname = sc.next();
        System.out.print("New Employee Email: ");
        String email = sc.next();
        System.out.print("New Employee Address: ");
        String address = sc.next();
        System.out.print("New Employee Status: ");
        String status = sc.next();

        String qry = "UPDATE tbl_employee SET e_fname = ?, e_lname = ?, e_email = ?, e_address = ?, e_status = ? WHERE e_id = ?";
        conf.updateRecord(qry, fname, lname, email, address, status, id);
    }

    public void deleteEmployee() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        while (conf.SingleValue("SELECT p_id FROM tbl_project WHERE p_id = ?", id) == 0) {
            System.out.print("Selected ID doesn't exist! Enter ID again: ");
            id = sc.nextInt();
        }

        String qry = "DELETE FROM tbl_project WHERE p_id = ?";
        conf.deleteRecord(qry, id);
    }

    
}
