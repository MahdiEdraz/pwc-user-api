package com.pwc.users.dataacess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pwc.users.dao.Project;
import com.pwc.users.dao.User;

public class ProjectDataAccess {
	public List<Project> getProjectsDetails(){
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
	
	public List<String> getAllProjects(){
		 String query = "select * from project_lookup";
	    	
			Connection conn =MySqlConnectionPool.getConnectoin();
			List<String> list = new ArrayList();
			try(PreparedStatement ps = conn.prepareStatement(query)){
				ResultSet rs=ps.executeQuery();  
	    		while(rs.next()){  
	    			
	    		
	    			list.add(rs.getString(2));
	    		}  
				MySqlConnectionPool.releaseConnection(conn);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			return list ;
	}
	public int updateUserProject(Project project,String perviousProject) {
		String query = " update project set project_name = ? , assigned_userid = ?  where project_name = ? and assigned_userid = ?" ;
		Connection conn =MySqlConnectionPool.getConnectoin();
		int result = -1;
		try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, project.getName());  
            ps.setInt(2, project.getAssignedUserId());
            ps.setString(3, perviousProject);
            ps.setInt(4, project.getAssignedUserId());

            result = ps.executeUpdate();
  			MySqlConnectionPool.releaseConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteUserFromProject(Project project) {
		String query = " delete from project where project_Name = ? and assigned_userid = ?" ;
		Connection conn =MySqlConnectionPool.getConnectoin();
		int result = -1;
		try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, project.getName());  
            ps.setInt(2, project.getAssignedUserId());  
            result = ps.executeUpdate();
  			MySqlConnectionPool.releaseConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public static void main(String args[]) {
		ProjectDataAccess da = new ProjectDataAccess();
		Project p = new Project();
		p.setName("Dev");
		p.setAssignedUserId(2);
		da.updateUserProject(p, "BlockChain");
		System.out.println();
	}
}
