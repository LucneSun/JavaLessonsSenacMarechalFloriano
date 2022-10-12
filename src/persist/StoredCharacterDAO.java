package persist;

import model.Product;
import model.StoredCharacter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StoredCharacterDAO extends DAO{

    public static Logger logger = LoggerFactory.getLogger(StoredCharacterDAO.class);

    public boolean save(StoredCharacter chara){
        String sql = "insert into storedcharacter (name, inventory) values (?, ?)";

        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, chara.getName());
            statement.setString(2, chara.getInventoryString());
            logger.info("Executed update: {}", sql);

            return statement.executeUpdate() != 0;

        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error trying to save character. Error: {}", e.getMessage());
        }

        return false;
    }

    public boolean checkExistence(int id){
        String sql = "select name from storedcharacter  where id = ?";
        try(Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            logger.info("Executed query: {}", sql);
            if(resultSet.next())return  true;
            else return false;

        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error validating id. Error: {}", e.getMessage());
        }
        return  false;
    }

    public String checkInventory(int id){
        String sql = "select inventory from storedcharacter  where id = ?";
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            logger.info("Executed query: {}", sql);
            if (resultSet.next()) {
                return resultSet.getString("inventory");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error("Error validating id. Error: {}", e.getMessage());
        }
        return "";
    }

    public boolean buy(String inventory, int id){
        String sql = "update storedcharacter set inventory = ? where id = ?";
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, inventory);
            statement.setInt(2, id);
            logger.info("executed inventory update: {}", sql);
            return statement.executeUpdate() != 0;
        }catch (SQLException e){e.printStackTrace(); logger.error("Error trying to buy. Error: {}", e.getMessage());}
        return  false;
    }

    public boolean updateCharacterName(int id, String name){
        String sql = "update storedcharacter set name = ? where id = ?";
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, name);
            statement.setInt(2, id);
            logger.info("Executed update: {}", sql);
            return statement.executeUpdate() != 0;
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error trying to update character name. Error: {}", e.getMessage());
        }
        return false;
    }

    public boolean deleteById(int id){
        String sql = "delete from storedcharacter where id = ?";
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            logger.info("Executed update: {}", sql);
            return statement.executeUpdate() != 0;
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error trying to delete character. Error: {}", e.getMessage());
        }
        return  false;
    }

    public List<StoredCharacter> findAll(){
        String sql = "select * from storedcharacter;";
        ArrayList<StoredCharacter> chars = new ArrayList<>();

       try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql))
       {
           logger.info("Executed query: {}", sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
               StoredCharacter storechar = new StoredCharacter(rs.getInt("id"), rs.getString("name"), rs.getString("inventory"));
               chars.add(storechar);
            }

            return chars;
       }
           catch(SQLException e){
           e.printStackTrace();
           logger.error("Error trying to find all characters. Error: {}", e.getMessage());
        }
       return null;
    }
}
