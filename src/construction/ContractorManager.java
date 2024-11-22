package construction;

import java.sql.*;
import java.util.Scanner;

public class ContractorManager {

    // Add a new contractor
    public static void addContractor(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter contractor name: ");
            String name = scanner.nextLine();

            String query = "INSERT INTO Contractors (name) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.executeUpdate();
                System.out.println("Contractor added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding contractor: " + e.getMessage());
        }
    }

    // View all contractors
    public static void viewContractors(Connection conn) {
        String query = "SELECT * FROM Contractors";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\nAvailable Contractors:");
            System.out.println("+-----+------------------------+");
            System.out.println("| ID  | Name                   |");
            System.out.println("+-----+------------------------+");

            while (rs.next()) {
                System.out.printf("| %-3d | %-22s |\n", rs.getInt("id"), rs.getString("name"));
            }
            System.out.println("+-----+------------------------+");
        } catch (SQLException e) {
            System.out.println("Error viewing contractors: " + e.getMessage());
        }
    }
}
