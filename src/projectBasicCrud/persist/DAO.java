package projectBasicCrud.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: João Lucas de Araújo
 * @since: 07/09/2022
 * @version: 1.0
 * @description: Class responsible for opening the data base
 */

public class DAO {
    public DAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
            System.err.println("Class not found. Error: " + cnfe.getMessage());
        }
    }

    protected Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost/db01", "root", "");
    }

    public static void main(String[] args){
        var dao = new DAO();

        try {
            dao.getConnection();
            System.out.println("Conexão aberta");
        }
        catch (SQLException e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
}
