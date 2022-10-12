package persist;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.DocFlavor;

public class ProductDAO extends DAO{

    private static Logger logger = LoggerFactory.getLogger(ProductDAO.class);

    public boolean save(Product product) {
        boolean succes = false;
        Date now = new Date();
        String sql = "Insert into product (name, quantity, type) values (?, ?, ?)";

        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setInt(2, product.getStockQuantity());
            statement.setString(3, product.getType());

            if(statement.executeUpdate() != 0)
                succes = true;

        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error trying to save product. Error: {}", e.getMessage());
        }
        return  succes;
    }

    public boolean update(Product product) {
        boolean succes = false;
        String sql = "Update product set name = ?, quantity = ?, price = ?, type = ? where id = ?";

        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setInt(2, product.getStockQuantity());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getType());
            statement.setInt(5, product.getId());

            if(statement.executeUpdate() != 0)
                succes = true;

        }catch (SQLException e){
            e.printStackTrace();
           logger.error("Error trying to update product by id. Error: {}", e.getMessage());
        }

        return succes;
    }

    public boolean deleteById(int id) {
        String sql = "Delete from product where id = ?";
        boolean succes = false;
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            if(statement.executeUpdate() != 0)
                succes = true;

        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error trying to delete product by id. Error: {}", e.getMessage());
        }
        return succes;
    }

    public boolean deleteAll() {
        boolean succes = false;
        String sql = "Delete * from product";

        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            if(statement.executeUpdate() != 0)
                succes = true;

        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error trying to delete product. Error: {}", e.getMessage());
        }

        return succes;
    }

    public boolean deleteAllByIdList(List<Product> products){
        boolean succes = false;
        String sql = "delete * from product were id in (?)";
        String sqlIn = products.stream().map(product -> String.valueOf(product.getId())).collect(Collectors.joining(",", "(", ")"));
        sql = sql.replace("(?)", sqlIn);

       try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

           succes = true;
       }
       catch (SQLException e){
           e.printStackTrace();
           logger.error("Error trying to delete products by id list. Error: {}", e.getMessage());
       }
        return succes;
    }

    public Product findById(int id){
        Product product = null;
        String sql = "select * from product where id = ?";
        Date now = new Date();

        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try(ResultSet rs = statement.executeQuery()) {
                if (rs.next()){
                    product = setProduct(rs);
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error finding product by id. Error: {}", e.getMessage());
        }
        return product;
    }

    public List<Product> findAll(){
        ArrayList<Product> products = new ArrayList<Product>();
        String sql = "select * from product";

        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            logger.info("Executed query: {}", sql);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    products.add(setProduct(rs));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error("Error on list products. Error: {}", e);
            return new ArrayList<>();
        }
        return products;
    }

    public List<Product> findAllStore(){
        ArrayList<Product> products = new ArrayList<Product>();
        String sql = "select * from product where quantity >= 1";

        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            logger.info("Executed query: {}", sql);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    products.add(setProduct(rs));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error("Error on list products. Error: {}", e);
            return new ArrayList<>();
        }
        return products;
    }

    public boolean productExists(int id){
        String sql = "select name from product where id = ?";
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            logger.info("Executed query: {}", sql);
            ResultSet rs = statement.executeQuery();
            if(rs.next())return  true;
            else return false;

        } catch(SQLException e){e.printStackTrace(); logger.error("Error trying to check product existence. Error: {}", e.getMessage());}
        return  false;
    }

    public int getStock(int id){
        String sql = "select quantity from product where id = ?";
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            logger.info("executed quantity query: {}", sql);
            if (rs.next()) return  rs.getInt("quantity"); else return 0;
        }catch (SQLException e){e.printStackTrace(); logger.error("Error trying to update stock. Error: {}", e.getMessage());}
        return 0;
    }

    public boolean updateStock(int newStock, int id){
        String sql = "update product set quantity = ? where id = ?";
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, newStock);
            statement.setInt(2, id);
            logger.info("executed update: {}", sql);
            return statement.executeUpdate() != 0;
        }catch (SQLException e){e.printStackTrace(); logger.error("Error trying to update stock. Error: {}", e.getMessage());}
        return false;
    }

    public double price(int id){
        String sql = "select price from product where id = ?";
        try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            logger.info("select product price query: {}", sql);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return rs.getDouble("price");
            }
            return 0.0;
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error trying to select item price. Error: {}", e.getMessage());
        }
        return 0.0;
    }

    private Product setProduct(ResultSet rs) throws  SQLException{
        Product product = new Product(rs.getInt("id") ,rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price"), rs.getString("type"));
        product.setRegisterDate(rs.getDate("registerdate"));
        return  product;
    }

}
