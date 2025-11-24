package jdbc_java;

import java.util.*;
import java.sql.*;

public class JDBC_Java {
    public static Connection connectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/aryan","root",""
            );
            return con;
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void getAll(){
        Connection con = connectDB();
        if(con == null) {
            return;
        }
        
        try{
            String query = "SELECT * FROM employee;";
            PreparedStatement psmt = con.prepareStatement(query);
            
            //SELECT
            ResultSet rs = psmt.executeQuery();
            
            while(rs.next()){
                System.out.print(rs.getString("id"));
                System.out.print(rs.getString("name"));
                System.out.print(rs.getString("address"));
                System.out.print(rs.getString("phone"));
                System.out.print(rs.getString("designation"));
                System.out.print("\n");
            }

        } catch(SQLException e){
            
        }
    }
    
        
    public static void insert(){
        
        Connection con = connectDB();
        if(con == null) {
            return;
        }
        
        String name, address, phone, designation;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter name: ");
        name = sc.nextLine();
        
        System.out.println("Enter address: ");
        address = sc.nextLine();
        
        System.out.println("Enter phone: ");
        phone = sc.nextLine();
        
        System.out.println("Enter designation: ");
        designation = sc.nextLine();

        if(invalid(name, address, phone, designation)) {
            System.out.println("enter atleast data");
            return;
        }
        
        try{
            String query = "INSERT INTO employee(name, address, phone, designation) VALUES(?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, name);
            psmt.setString(2, address);
            psmt.setString(3, phone);
            psmt.setString(4, designation);
            
            int status = psmt.executeUpdate();
            
            if(status > 0 ) {
                System.out.println("Inserted!");
                getAll();
            } else {
                System.out.println("Not Inserted!");
            }
            
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void updateById(){
        Connection con = connectDB();
        if(con == null) {
            return;
        }
        
        int id;
        String name, designation, address, phone;
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        
        System.out.println("Enter id: ");
        id = sc1.nextInt();
        
        System.out.println("Enter name: ");
        name = sc.nextLine();
        
        System.out.println("Enter address: ");
        address = sc.nextLine();
        
        System.out.println("Enter phone: ");
        phone = sc.nextLine();
        
        System.out.println("Enter designation: ");
        designation = sc.nextLine();
        
        if(invalid(name, address, phone, designation)) {
            System.out.println("enter atleast data");
            return;
        }
        
        try{
            
            String query = "UPDATE employee set name=?, address=?, phone=?, designation=? WHERE id = ?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, name);
            psmt.setString(2, address);
            psmt.setString(3, phone);
            psmt.setString(4, designation);
            psmt.setInt(5, id);
            
            int status = psmt.executeUpdate();
            
            if(status > 0) {
                System.out.println("Updated");
                getAll();
            } else {
                System.out.println("Not Updated");
            }
            
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void deleteById(){
        Connection con = connectDB();
        if(con == null) {
            return;
        }
        
        int id;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter id: ");
        id = sc.nextInt();
        
        try{
            
            String query = "DELETE from employee WHERE id = ?";
            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setInt(1, id);
            
            int status = psmt.executeUpdate();
            if(status > 0) {
                System.out.println("Deleted");
                getAll();
            } else {
                System.out.println("Not Deleted");
            }
            
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }   
    }
    
    public static boolean invalid(String name, String address, String phone, String designation) {
        return (name.trim().equals("")) &&
                (address.trim().equals("")) &&
                (phone.trim().equals("")) &&
                (designation.trim().equals(""));
    }
    
    public static void main(String[] args) {
        int user = 1;
        Scanner sc = new Scanner(System.in);
        
        while(user != 0) {
            System.out.println("1. Select");
            System.out.println("2. Insert");
            System.out.println("3. Update by Id");
            System.out.println("4. Delete by Id");
            System.out.println("0 for exit.");
            
            System.out.println("Enter your choice");
            user = sc.nextInt();
            
            switch(user) {
                case 1:
                    getAll();
                    break;
                case 2:
                    insert();
                    break;
                case 3:
                    updateById();
                    break;
                case 4:
                    deleteById();
                    break;
                default:
                    System.out.println("Enter between 1 to 4");
            }
            
        }
        
    }
    
}
