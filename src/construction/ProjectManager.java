package construction;

import java.sql.*;
import java.util.Scanner;

public class ProjectManager {

    // Add a new project
    public static void addProject(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter project name: ");
            String name = scanner.nextLine();
            System.out.print("Enter project location: ");
            String location = scanner.nextLine();
            System.out.print("Enter project budget: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Budget must be a number.");
                scanner.next(); // Clear invalid input
                return;
            }
            double budget = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            String query = "INSERT INTO Projects (name, location, budget) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, location);
                stmt.setDouble(3, budget);
                stmt.executeUpdate();
                System.out.println("Project added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding project: " + e.getMessage());
        }
    }

    // View all projects
    public static void viewProjects(Connection conn) {
        String query = "SELECT * FROM Projects";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\nAvailable Construction Projects:");
            System.out.println("+-----+------------------------+------------------------+------------+");
            System.out.println("| ID  | Name                   | Location               | Budget     |");
            System.out.println("+-----+------------------------+------------------------+------------+");

            while (rs.next()) {
                System.out.printf("| %-3d | %-22s | %-22s | %-10.2f |\n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("location"), rs.getDouble("budget"));
            }
            System.out.println("+-----+------------------------+------------------------+------------+");
        } catch (SQLException e) {
            System.out.println("Error viewing projects: " + e.getMessage());
        }
    }

    // Delete a project
    public static void deleteProject(Connection conn, Scanner scanner) {
        System.out.print("Enter project ID to delete: ");
        int projectId = scanner.nextInt();

        String deleteQuery = "DELETE FROM Projects WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
            stmt.setInt(1, projectId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Project deleted successfully.");
            } else {
                System.out.println("No project found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting project: " + e.getMessage());
        }
    }

    // Update project details
    public static void updateProject(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter project ID to update: ");
            int projectId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new project name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new project location: ");
            String location = scanner.nextLine();
            System.out.print("Enter new project budget: ");
            double budget = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            String query = "UPDATE Projects SET name = ?, location = ?, budget = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, location);
                stmt.setDouble(3, budget);
                stmt.setInt(4, projectId);
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Project updated successfully.");
                } else {
                    System.out.println("No project found with the given ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating project: " + e.getMessage());
        }
    }
}
