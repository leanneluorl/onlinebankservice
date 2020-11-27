

import java.sql.*;

/**
 *
 * @author www.luv2code.com
 */
public class JDBCdemo {

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "root";
        String pass = "password";

        try {
            // 1. Get a connection to database
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dreamhome", user, pass);

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from staff");

            // 4. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("Lname") + ", " + myRs.getString("Fname"));
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }    

}
