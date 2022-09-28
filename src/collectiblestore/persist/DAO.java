package collectiblestore.persist;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    public DAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
            System.err.println("Class not found. Error: " + cnfe.getMessage());
        }
    }

    protected Connection getConnection() throws SQLException {
        String DATABASE_URL = "jdbc:mysql://localhost/collectiblestore";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(DATABASE_URL, user, password);
    }

    public static void main(){
        
    }
}
