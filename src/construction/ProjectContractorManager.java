package construction;

import java.sql.*;
import java.util.Scanner;

public class ProjectContractorManager {

    // Assign contractor to project
    public static void assignContractorToProject(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter project ID to assign contractor to: ");
            int projectId = scanner.nextInt();
            System.out.print("Enter contractor ID to assign: ");
            int contractorId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String query = "INSERT INTO ProjectContractors (project_id, contractor_id) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, projectId);
                stmt.setInt(2, contractorId);
                stmt.executeUpdate();
                System.out.println("Contractor assigned to project successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error assigning contractor to project: " + e.getMessage());
        }
    }

    // Remove contractor from project
    public static void removeContractorFromProject(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter project ID to remove contractor from: ");
            int projectId = scanner.nextInt();
            System.out.print("Enter contractor ID to remove: ");
            int contractorId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String query = "DELETE FROM ProjectContractors WHERE project_id = ? AND contractor_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, projectId);
                stmt.setInt(2, contractorId);
                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Contractor removed from project successfully.");
                } else {
                    System.out.println("No such assignment found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error removing contractor from project: " + e.getMessage());
        }
    }
}
