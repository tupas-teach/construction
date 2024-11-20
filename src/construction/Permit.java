package construction;

import java.util.Scanner;

class Permit {
     
     public void pTransaction() {
        Scanner sc = new Scanner(System.in);
        String response;
        do {
            System.out.println("--------------------------");
            System.out.println("WELCOME Permit Panel ");
            System.out.println("--------------------------");
            System.out.println("1: ADD Permit");
            System.out.println("2: VIEW Permit");
            System.out.println("3: UPDATE Permit");
            System.out.println("4: DELETE Permit");
            System.out.print("Selection: ");
            int action = sc.nextInt();
            Permit pr = new Permit ();

            switch (action) {
                case 1:
                    pr.addPermit();
                    pr.viewPermit();
                    break;
                case 2:
                    pr.viewPermit();
                    break;
                case 3:
                    pr.viewPermit();
                    updatePermit();
                    pr.viewPermit();
                    break;
                case 4:
                    pr.viewPermit();
                    pr.deletePermit();
                    pr.viewPermit();
                    break;
                default:
                    System.out.println("Invalid selection.");
            }

            System.out.print("Do you want to continue? (Yes/No): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("Yes"));
    }

     public void addPermit() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println(" ID:");
        String  id = sc.next();
        System.out.print("Permit First Name: ");
        String fname = sc.next();
        System.out.print("Permit stock: ");
        String stock = sc.next();
        System.out.print("Permit Email: ");
        String email = sc.next();
        System.out.print("Permit Address: ");
        String address = sc.next();
        System.out.print("Permit Status: ");
        String status = sc.next();

        String qry = "INSERT INTO tbl_permit(p_id, p_fname, p_lname, p_email, p_address, p_status) VALUES (?, ?, ?, ?, ?,?)";
        conf.addRecord(qry,id, fname, stock, email, address, status);
    }

    public void viewPermit() {
        config conf = new config();
        String qry = "SELECT * FROM tbl_permit";
        String[] hrds = {"ID", "First Name", "Last Name", "Email", "Address", "Status"};
        String[] clmn = {"id", "fname", "stock", "email", "address", "status"};

        conf.viewRecords(qry, hrds, clmn);
    }

    public void updatePermit() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        
        while (conf.SingleValue("SELECT p_id FROM tbl_permit WHERE p_id = ?", id) == 0) {
            System.out.print("Selected ID doesn't exist! Enter ID again: ");
            id = sc.nextInt();
        }

        System.out.print("New Permit First Name: ");
        String fname = sc.next();
        System.out.print("New Permit Stock: ");
        String lname = sc.next();
        System.out.print("New Permit Email: ");
        String email = sc.next();
        System.out.print("New Permit Address: ");
        String address = sc.next();
        System.out.print("New Permit Status: ");
        String status = sc.next();

        String qry = "UPDATE tbl_permit SET  p_fname = ?, p_stock = ?, p_email = ?, p_address = ?, p_status = ? WHERE p_id = ?";
        conf.updateRecord(qry, fname, lname, email, address, status, id);
    }

    public void deletePermit() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        while (conf.SingleValue("SELECT p_id FROM tbl_permit WHERE p_id = ?", id) == 0) {
            System.out.print("Selected ID doesn't exist! Enter ID again: ");
            id = sc.nextInt();
        }

        String qry = "DELETE FROM tbl_Permit WHERE p_id = ?";
        conf.deleteRecord(qry, id);
    }

    
}

