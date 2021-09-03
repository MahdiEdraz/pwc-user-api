package com.pwc.users.dataacess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.pwc.users.dao.Department;
import com.pwc.users.dao.Project;
import com.pwc.users.dao.User;
public class DataAccess {

	public DataAccess() {

	}
	public List<User> getAllUsers(){
        String query = "select * from user";
    	
		Connection conn =MySqlConnectionPool.getConnectoin();
		List<User> list = new ArrayList();
		Object a = User.class;
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ResultSet rs=ps.executeQuery();  
    		while(rs.next()){  
    			
    			User user = new User();
    			user.setId(rs.getInt("id"));
    			user.setFirstName(rs.getString("First_Name"));
    			user.setLastName(rs.getString("last_name"));
    			user.setUserType(rs.getString("User_type"));
    			list.add(user);
    		}  
			MySqlConnectionPool.releaseConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list ;
	}
	
	public List<Department> getAllDepartments(){
        String query = "select * from department";
    	
		Connection conn =MySqlConnectionPool.getConnectoin();
		List<Department> list = new ArrayList();
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ResultSet rs=ps.executeQuery();  
    		while(rs.next()){  
    			
    			Department item = new Department();
    			item.setDepartmentName(rs.getString("Department_name"));
    			item.setEmployeeId(rs.getInt("Employee_id"));
    			item.setId(rs.getInt("id"));
    			list.add(item);
    		}  
			MySqlConnectionPool.releaseConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list ;
	}
	
	public List<Project> getAllProjects(){
        String query = "select * from project";
    	
		Connection conn =MySqlConnectionPool.getConnectoin();
		List<Project> list = new ArrayList();
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ResultSet rs=ps.executeQuery();  
    		while(rs.next()){  
    			
    			Project item = new Project();
    			item.setName(rs.getString("Project_name"));
    			item.setAssignedUserId(rs.getInt("assigned_userid"));
    			item.setId(rs.getInt("id"));
    			list.add(item);
    		}  
			MySqlConnectionPool.releaseConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list ;
	}
	
	public static void main(String args[]) throws Exception {
		DataAccess da = new DataAccess();
		List<User> list = da.getAllUsers();
		List<Department> list2 = da.getAllDepartments();
		List<Project> list3 = da.getAllProjects();

		System.out.println(list);
		System.out.println(list2);
		System.out.println(list3);

	}
}
