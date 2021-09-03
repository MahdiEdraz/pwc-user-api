package com.pwc.users.dataacess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pwc.users.dao.Project;
import com.pwc.users.dao.User;

public class UserDataAccess {
	public List<User> getAllUsers(){
        String query = "select * from user";
    	
		Connection conn =MySqlConnectionPool.getConnectoin();
		List<User> list = new ArrayList();
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
	
	public void updateUser(User user) {
		String query = " update user set first_name = ? , last_name = ? , user_type= ? where id = ?" ;
		Connection conn =MySqlConnectionPool.getConnectoin();
		try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, user.getFirstName());  
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUserType());
            ps.executeUpdate();
  			MySqlConnectionPool.releaseConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteUser(int id) {
		String query = " delete from user where id = ?" ;
		Connection conn =MySqlConnectionPool.getConnectoin();
		try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, id);  
            ps.executeUpdate();
  			MySqlConnectionPool.releaseConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
