import java.sql.*;

public class DBManager {
        public Connection getConnection(){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dreamhome", "root","password");
                return conn;
            }catch(ClassNotFoundException e){
                e.printStackTrace();
                return null;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        
        }
    
}
