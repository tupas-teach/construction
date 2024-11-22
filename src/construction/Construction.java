package construction;

import java.sql.*;
import java.util.Scanner;

public class Construction {

    private static final String DATABASE_URL = "jdbc:sqlite:construction.db";

    public static void main(String[] args) {
        try (Connection conn = Database.connect(); Scanner scanner = new Scanner(System.in)) {
            if (conn != null) {
                Database.createTables(conn); // Ensure tables are created
                while (true) {
                    System.out.println("\n--- Construction Management System ---");
                    System.out.println("1. Add Project");
                    System.out.println("2. View Projects");
                    System.out.println("3. Update Project");
                    System.out.println("4. Delete Project");
                    System.out.println("5. Add Contractor");
                    System.out.println("6. View Contractors");
                    System.out.println("7. Assign Contractor to Project");
                    System.out.println("8. Remove Contractor from Project");
                    System.out.println("9. Generate General Report");
                    System.out.println("10. Generate Detailed Report");
                    System.out.println("11. Exit");
                    System.out.print("Choose an option: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number between 1 and 11.");
                        scanner.next(); // Clear invalid input
                        continue;
                    }

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            ProjectManager.addProject(conn, scanner);
                            break;
                        case 2:
                            ProjectManager.viewProjects(conn);
                            break;
                        case 3:
                            ProjectManager.updateProject(conn, scanner);
                            break;
                        case 4:
                            ProjectManager.deleteProject(conn, scanner);
                            break;
                        case 5:
                            ContractorManager.addContractor(conn, scanner);
                            break;
                        case 6:
                            ContractorManager.viewContractors(conn);
                            break;
                        case 7:
                            ProjectContractorManager.assignContractorToProject(conn, scanner);
                            break;
                        case 8:
                            ProjectContractorManager.removeContractorFromProject(conn, scanner);
                            break;
                        case 9:
                            ReportGenerator.generateGeneralReport(conn);
                            break;
                        case 10:
                            ReportGenerator.generateDetailedReport(conn, scanner);
                            break;
                        case 11:
                            System.out.print("Are you sure you want to exit? (Y/N): ");
                            String exitConfirmation = scanner.nextLine();
                            if ("Y".equalsIgnoreCase(exitConfirmation)) {
                                System.out.println("Exiting the system.");
                                return;
                            }
                            break;
                        default:
                            System.out.println("Invalid choice. Please choose between 1 and 11.");
                            break;
                    }
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
}
