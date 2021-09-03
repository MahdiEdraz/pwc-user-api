package com.pwc.users.dataacess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pwc.users.dao.Department;
import com.pwc.users.dao.Project;

public class DepartmentDataAccess {
	public List<Department> getDepartmentsDetails(){
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
	public List<String> getAllDepartments(){
		 String query = "select * from department_lookup";
	    	
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
	public int insertUserToDepartment(Department department) {
		String query = " insert into department (department_name,employee_id) values (?,?) " ;
		Connection conn =MySqlConnectionPool.getConnectoin();
		int result = -1;
		try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, department.getDepartmentName());  
            ps.setInt(2, department.getEmployeeId());
            result = ps.executeUpdate();
  			MySqlConnectionPool.releaseConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int updateUserDepartment(Department department,String previousDepartment) {
		String query = " update department set department_name = ? , employee_id = ?  where employee_id= ? and department_name =?" ;
		Connection conn =MySqlConnectionPool.getConnectoin();
		int result = -1;
		try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, department.getDepartmentName());  
            ps.setInt(2, department.getEmployeeId());
            ps.setInt(3, department.getEmployeeId());
            ps.setString(4,previousDepartment );  

            result = ps.executeUpdate();
  			MySqlConnectionPool.releaseConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteUserFromDeprtment(Department department) {
		String query = " delete from department where department_name = ? and employee_id = ?" ;
		Connection conn =MySqlConnectionPool.getConnectoin();
		int result = -1;

		try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, department.getDepartmentName());  
            ps.setInt(2, department.getId()); 
           result= ps.executeUpdate();
  			MySqlConnectionPool.releaseConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String args[]) {
		DepartmentDataAccess da = new DepartmentDataAccess();
		Department d = new Department();
		d.setDepartmentName("HR");
		d.setEmployeeId(2);
		da.insertUserToDepartment(d);
	}
	
}
