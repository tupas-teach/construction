package construction;

import java.util.Scanner;

public class Project {
   

    public void pTransaction() {
        Scanner sc = new Scanner(System.in);
        String response;
        do {
            System.out.println("--------------------------");
            System.out.println("APP Project");
            System.out.println("--------------------------");
            System.out.println("1: Add Project");
            System.out.println("2: View Project");
            System.out.println("3: Update Project");
            System.out.println("4: Delete Project");
            System.out.println("Selection:");
            int act = sc.nextInt();
            Project pr = new Project();
            switch (act) {
                case 1:
                   pr. addProject();
                  pr.  viewProject();
                    break;
                case 2:
                   pr. viewProject();
                    break;
                case 3:
                    pr. viewProject();
                    updateProject();
                    pr. viewProject();
                    break;
                case 4:
                   pr.  viewProject();
                   pr. deleteProject();
                    pr. viewProject();
                    break;
                default:
                    System.out.println("Invalid selection.");
                    break;
            }
            System.out.println("Do you want to continue? (Yes/No):");
            response = sc.next();
        } while (response.equalsIgnoreCase("Yes"));
    }

    public void addProject() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println(" ID:");
        String  id = sc.next();
        System.out.print("Project FirstName: ");
        String fname = sc.next();
        System.out.print("Project LastName: ");
        String lname = sc.next();
        System.out.print("Project Email: ");
        String email = sc.next();
        System.out.print("Project Address: ");
        String address = sc.next();
        System.out.print("Project Status: ");
        String status = sc.next();

        String qry = "INSERT INTO tbl_project(p_id,p_fname, p_lname, p_email, p_address, p_status) VALUES (?, ?, ?, ?, ?,?)";
        conf.addRecord(qry,id, fname, lname, email, address, status);
    }

    public void viewProject() {
        config conf = new config();
        String qry = "SELECT * FROM tbl_project";
        String[] hrds = {"ID", "FirstName", "LastName", "Email", "Address", "Status"};
        String[] clmn = {"id", "fname", "lname", "email", "address", "status"};
        conf.viewRecords(qry, hrds, clmn);
    }

    public void updateProject() {
        config conf = new config();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        
        while (conf.SingleValue("SELECT p_id FROM tbl_project WHERE p_id = ?", id) == 0) {
            System.out.print("Selected ID doesn't exist! Enter ID again: ");
            id = sc.nextInt();
        }

        System.out.print("New Project First Name: ");
        String fname = sc.next();
        System.out.print("New Project Last Name: ");
        String lname = sc.next();
        System.out.print("New Project Email: ");
        String email = sc.next();
        System.out.print("New Project Address: ");
        String address = sc.next();
        System.out.print("New Project Status: ");
        String status = sc.next();

        String qry = "UPDATE tbl_project SET p_fname = ?, p_lname = ?, p_email = ?, p_address = ?, p_status = ? WHERE p_id = ?";
        conf.updateRecord(qry, fname, lname, email, address, status, id);
    }

    public void deleteProject() {
        config conf = new config();
        Scanner sc = new Scanner(System.in);
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


