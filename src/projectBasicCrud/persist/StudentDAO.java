package projectBasicCrud.persist;

import projectBasicCrud.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends DAO {
    private Connection conn;

    public boolean save(Student student){
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("insert into student(name, email, registration, sex) values(?, ?, ?, ?)");
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getRegistration());
            pstmt.setString(4, student.getSex());

            var response = pstmt.executeUpdate();
            if(response != 0)
                return Boolean.TRUE;

            return Boolean.FALSE;
        }
        catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error on save student. Error: " + e.getMessage());
            return Boolean.FALSE;
        }
        finally {
            try {
                if (conn != null)
                    conn.close();
                if (pstmt != null)
                    pstmt.close();
            }
            catch (SQLException e){
               e.printStackTrace();
               System.err.println("Error on close statement. Error: " + e.getMessage());

            }

        }

    }

    public List<Student> findAll(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{ conn = getConnection(); pstmt = conn.prepareStatement("select * from student"); rs = pstmt.executeQuery();

            var students = new ArrayList<Student>();

            while (rs.next()){
            var student = new Student();
             student.setName(rs.getString("name"));
             student.setEmail(rs.getString("email"));
             student.setSex(rs.getString("sex"));
             student.setRegistration(rs.getInt("registration"));

             students.add(student);
        }
            return  students;
        }
        catch(SQLException e){
            e.printStackTrace();
            System.err.println("Error on list students. Error: " + e.getMessage());
        }
        finally {
            try {
                if(pstmt != null)
                    pstmt.close();
                if(rs != null)
                    rs.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
                System.err.println("Error on close statement. Error: " + e.getMessage());
            }

        }
        return  null;
    }

    public Student findById(int id){
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("select * where id = ?");
            pstmt.setInt(1, id);

            var response = pstmt.executeQuery();
            Student student = new Student();

            if(resultSet.next()){
                student.setName(resultSet.getString("name"));
                student.setSex(resultSet.getString("sex"));
                student.setRegistration(resultSet.getInt("registration"));
                student.setEmail(resultSet.getString("email"));
            }

            return student;
        }
        catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error on find student. Error: " + e.getMessage());
        }
        finally {
            try {
                if (conn != null)
                    conn.close();
                if (pstmt != null)
                    pstmt.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                System.err.println("Error on close statement. Error: " + e.getMessage());

            }

        }
        return null;
    }

    public Student update(Student student){
    PreparedStatement pstmt = null;
    ResultSet resultSet = null;
        try {
        conn = getConnection();
        pstmt = conn.prepareStatement("update student set name = ?, email = ?, sex  = ?, registration = ? were name = ? and email = ? and sex = ? and registration = ?");
        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getEmail());
        pstmt.setString(3, student.getSex());
        pstmt.setInt(4, student.getRegistration());
        pstmt.setString(5, student.getName());
        pstmt.setString(6, student.getEmail());
        pstmt.setString(7, student.getSex());
        pstmt.setInt(8, student.getRegistration());

        var response = pstmt.executeQuery();
        Student student1 = new Student();

        if(resultSet.next()){
            student1.setName(resultSet.getString("name"));
            student1.setSex(resultSet.getString("sex"));
            student1.setRegistration(resultSet.getInt("registration"));
            student1.setEmail(resultSet.getString("email"));
        }

        return student1;
    }
        catch (SQLException e){
        e.printStackTrace();
        System.err.println("Error on update student. Error: " + e.getMessage());
    }
        finally {
        try {
            if (conn != null)
                conn.close();
            if (pstmt != null)
                pstmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error on close statement. Error: " + e.getMessage());

        }

    }
        return null;
    }

    public boolean deleteById(int id){
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("delete from student where id = ?");
            pstmt.setInt(1, id);


            var response = pstmt.executeUpdate();
            if(response != 0)
                return Boolean.TRUE;

            return Boolean.FALSE;
        }
        catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error on save student. Error: " + e.getMessage());
            return Boolean.FALSE;
        }
        finally {
            try {
                if (conn != null)
                    conn.close();
                if (pstmt != null)
                    pstmt.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                System.err.println("Error on close statement. Error: " + e.getMessage());

            }

        }
    }

    public boolean deleteAll(){
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement("delete from student");

            var response = pstmt.executeUpdate();
            if(response != 0)
                return Boolean.TRUE;

            return Boolean.FALSE;
        }
        catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error on save student. Error: " + e.getMessage());
            return Boolean.FALSE;
        }
        finally {
            try {
                if (conn != null)
                    conn.close();
                if (pstmt != null)
                    pstmt.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                System.err.println("Error on close statement. Error: " + e.getMessage());

            }

        }
    }
}
