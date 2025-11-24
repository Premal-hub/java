/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Administrator
 */
public class EmployeeDao {
    
      public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3309/aj","root","");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
      public static int save(emp e)
      {
          int status=0;
          try
          {
              Connection con=EmployeeDao.getConnection();
              String sqlInsert="insert into emp(name,password,email,country) values (?,?,?,?)";
              PreparedStatement ps=con.prepareStatement(sqlInsert);
                        ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getCountry());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
          
          return status;
      }
      
      public static List<emp> getAllEmployees()
      {  List<emp> list=new ArrayList<emp>();
          try
          {
            
              Connection con=EmployeeDao.getConnection();
              String query="Select *from emp";
              PreparedStatement ps=con.prepareStatement(query);
              ResultSet rs=ps.executeQuery();
              while(rs.next())
              {
                  emp e=new emp();
                  e.setId(rs.getInt(1));
                  e.setName(rs.getString(2));
                  e.setPassword(rs.getString(3));
                  e.setEmail(rs.getString(4));
                  e.setCountry(rs.getString(5));
                  list.add(e);
                  
              }
              con.close();
          
          }catch(Exception ex)
          {ex.printStackTrace();
          }
          return list;
      }
      public static int delete(int id)
      {
            int status=0;
            try
          {
               Connection con=EmployeeDao.getConnection();
               PreparedStatement ps=con.prepareStatement("delete from emp where id=?");
               ps.setInt(1, id);
               status=ps.executeUpdate();
               
          }
      
      catch(Exception ex)
          {ex.printStackTrace();
          }
          return status;
}
        public static emp getAllEmployeeById(int id)
        {
            emp e=new emp();
              try
          {
               Connection con=EmployeeDao.getConnection();
                 PreparedStatement ps=con.prepareStatement("Select *from emp where id=?");
                 ps.setInt(1, id);
                 ResultSet rs=ps.executeQuery();
                 rs.next();
                 e.setId(rs.getInt(1));
                 e.setName(rs.getString(2));
                 e.setPassword(rs.getString(3));
                 e.setEmail(rs.getString(4));
                 e.setCountry(rs.getString(5));
               
          }
              catch(Exception ex)
          {ex.printStackTrace();
          }
              return e;
        }
        public static int update(emp e){
            int status=0;
        try
        {
             Connection con=EmployeeDao.getConnection();
             PreparedStatement ps=con.prepareStatement("update emp set name=?,password=?,email=?,country=? where id=?");
             ps.setString(1, e.getName());
             ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getCountry());
			ps.setInt(5,e.getId());
            status=ps.executeUpdate();
        }
        catch(Exception ex)
        {
        }
        return status;
        }
}
