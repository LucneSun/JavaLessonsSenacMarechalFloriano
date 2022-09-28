package collectiblestore.persist;

import collectiblestore.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDAO extends DAO{
    private Connection connection;

    public boolean save(Product product) {
        PreparedStatement pstmt = null;
        Date now = new Date();

        try {
            connection = getConnection();

            String sql = "Insert into product (name, quantity, type) values (?, ?, ?)";

            pstmt = connection.prepareStatement(sql);


            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getStockQuantity());
            pstmt.setString(3, product.getType());

            if(pstmt.executeUpdate() != 0)
                return  Boolean.TRUE;
            return Boolean.FALSE;

        }catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error on save product. Error: " + e.getMessage());
            return  Boolean.FALSE;
        }
        finally {
            try{
                if(connection != null) connection.close();
                if(pstmt != null) pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public boolean update(Product product, int id) {
        PreparedStatement pstmt = null;

        try {
            connection = getConnection();

            String sql = "Update product set name = ?, quantity = ?, type = ? where id = ?";

            pstmt = connection.prepareStatement(sql);


            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getStockQuantity());
            pstmt.setString(3, product.getType());
            pstmt.setInt(4, id);

            if(pstmt.executeUpdate() != 0)
                return  Boolean.TRUE;
            return Boolean.FALSE;

        }catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error on update product. Error: " + e.getMessage());
            return  Boolean.FALSE;
        }
        finally {
            try{
                if(connection != null) connection.close();
                if(pstmt != null) pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public boolean deleteById(int id) {
        PreparedStatement pstmt = null;

        try {
            connection = getConnection();

            String sql = "Delete * from product where id = ?";

            pstmt = connection.prepareStatement(sql);


            pstmt.setInt(1, id);

            if(pstmt.executeUpdate() != 0)
                return  Boolean.TRUE;
            return Boolean.FALSE;

        }catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error on delete product. Error: " + e.getMessage());
            return  Boolean.FALSE;
        }
        finally {
            try{
                if(connection != null) connection.close();
                if(pstmt != null) pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public boolean deleteAll() {
        PreparedStatement pstmt = null;

        try {
            connection = getConnection();

            String sql = "Delete * from product";

            pstmt = connection.prepareStatement(sql);

            if(pstmt.executeUpdate() != 0)
                return  Boolean.TRUE;
            return Boolean.FALSE;

        }catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error on delete all products. Error: " + e.getMessage());
            return  Boolean.FALSE;
        }
        finally {
            try{
                if(connection != null) connection.close();
                if(pstmt != null) pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

    public Product findById(int id){
        PreparedStatement pstmt = null;
        Date now = new Date();

        try {
            connection = getConnection();

            String sql = "select * from product where id = ?";

            pstmt = connection.prepareStatement(sql);


            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                Product product = new Product(rs.getString("name"), rs.getInt("quantity"), rs.getString("type"));
                product.setRegisterDate(rs.getDate("registerdate"));
                return product;
            }
            return  null;

        }catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error on select product by id. Error: " + e.getMessage());
            return null;
        }
        finally {
            try{
                if(connection != null) connection.close();
                if(pstmt != null) pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }
    public List<Product>  findAll(){

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection();

            String sql = "select * from product";

            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();

            ArrayList<Product> products = new ArrayList<Product>();
            while (rs.next()){
                Product product = new Product(rs.getString("name"), rs.getInt("quantity"), rs.getString("type"));
                product.setRegisterDate(rs.getDate("registerdate"));
                products.add(product);
            }

            return products;

        }catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error on list products. Error: " + e.getMessage());
            return  null;
        }
        finally {
            try{
                if(connection != null) connection.close();
                if(pstmt != null) pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
                System.err.println("Error on close statements. Error: " + e.getMessage());
            }
        }
    }

}
