package Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbConsole.dbConnection;

/**
 *
 * @author Abdelrahman Al-Majayda
 * @version 1.0
 * @since   2020-04-3
 * @link https://github.com/itsdarksama
 *
 */

public class loginModel {
    Connection connection;

    public loginModel() {

        try {
            this.connection = dbConnection.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected() {
        return this.connection != null;
    }

    public boolean isLogin(String user, String pass, String per) throws Exception {

        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM employeesDB where username =? and password =? and  permissions =?";

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            pr.setString(3, per);

            rs = pr.executeQuery();

            boolean bol1;
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            return false;
        }
        finally {
            {
                pr.close();
                rs.close();
            }
        }

    }

}
