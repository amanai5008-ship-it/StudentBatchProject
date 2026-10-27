import java.sql.*;

public class StudentBatch {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "Dolly9827+";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO student (id, name, marks) VALUES (?, ?, ?)")) {

            for (int i = 1; i <= 5; i++) {
                ps.setInt(1, 100 + i);
                ps.setString(2, "Student" + i);
                ps.setInt(3, 80 + i);
                ps.addBatch();
            }

            int[] result = ps.executeBatch();
            System.out.println("Records processed: " + result.length);

        } catch (BatchUpdateException e) {
            System.out.println("Batch error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}