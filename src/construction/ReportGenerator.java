package construction;

import java.sql.*;
import java.util.Scanner;

public class ReportGenerator {

    // Generate a general report for projects
    public static void generateGeneralReport(Connection conn) {
        String query = "SELECT p.id, p.name, p.location, p.budget, COUNT(pc.contractor_id) AS num_contractors " +
                       "FROM Projects p LEFT JOIN ProjectContractors pc ON p.id = pc.project_id " +
                       "GROUP BY p.id";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\nGeneral Report: Projects and Assigned Contractors");
            System.out.println("+-----+------------------------+------------------------+------------+----------------+");
            System.out.println("| ID  | Name                   | Location               | Budget     | Num Contractors |");
            System.out.println("+-----+------------------------+------------------------+------------+----------------+");

            while (rs.next()) {
                System.out.printf("| %-3d | %-22s | %-22s | %-10.2f | %-15d |\n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("location"), rs.getDouble("budget"),
                        rs.getInt("num_contractors"));
            }
            System.out.println("+-----+------------------------+------------------------+------------+----------------+");
        } catch (SQLException e) {
            System.out.println("Error generating general report: " + e.getMessage());
        }
    }

    // Generate a detailed report for contractors and their projects
    public static void generateDetailedReport(Connection conn, Scanner scanner) {
        System.out.print("Enter contractor ID for detailed report: ");
        int contractorId = scanner.nextInt();
        String query = "SELECT p.id, p.name, p.location, p.budget " +
                       "FROM Projects p JOIN ProjectContractors pc ON p.id = pc.project_id " +
                       "WHERE pc.contractor_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, contractorId);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("\nDetailed Report: Projects for Contractor ID " + contractorId);
                System.out.println("+-----+------------------------+------------------------+------------+");
                System.out.println("| ID  | Name                   | Location               | Budget     |");
                System.out.println("+-----+------------------------+------------------------+------------+");

                while (rs.next()) {
                    System.out.printf("| %-3d | %-22s | %-22s | %-10.2f |\n",
                            rs.getInt("id"), rs.getString("name"),
                            rs.getString("location"), rs.getDouble("budget"));
                }
                System.out.println("+-----+------------------------+------------------------+------------+");
            }
        } catch (SQLException e) {
            System.out.println("Error generating detailed report: " + e.getMessage());
        }
    }
}
