import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private static final String URL =
        "jdbc:sqlite:C:/data/employee.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC"); // ★ 重要

            Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS employee (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "name TEXT," +
                         "department TEXT," +
                         "join_date TEXT)";
            stmt.execute(sql);

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // ★ これが無いのが原因だった
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
