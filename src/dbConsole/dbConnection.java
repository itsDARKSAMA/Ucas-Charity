package dbConsole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Abdelrahman Al-Majayda
 * @version 1.0
 * @since   2020-04-3
 * @link https://github.com/itsdarksama
 *
 */


public class dbConnection {

    private static final String SqlConn = "jdbc:sqlite:charityDB.sqlite";


    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SqlConn);
        } catch (ClassNotFoundException ex) {

            ex.printStackTrace();

        }
        return null;
    }

}
