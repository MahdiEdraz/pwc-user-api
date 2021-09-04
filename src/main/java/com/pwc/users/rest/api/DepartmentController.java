package com.pwc.users.rest.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pwc.users.dao.Department;
import com.pwc.users.dao.User;
import com.pwc.users.dataacess.DepartmentDataAccess;
@Path("/department")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentController {
	
    private DepartmentDataAccess departmentDataAccess = new DepartmentDataAccess(); 
    private GsonBuilder builder = new GsonBuilder(); 
	private Gson gson = builder.create() ;
	
	  @GET
	    @Path("/getAllDepartments")
	    public Response getAllDepartments() {
	        List<String> list = departmentDataAccess.getAllDepartments();
	        String json = gson.toJson(list);
	        return Response.status(200).entity(json).build();
	    } 
	    @GET
	    @Path("/getDepartmentsDetails")
	    public Response getDepartmentsDetails() {
	        List<Department> list = departmentDataAccess.getDepartmentsDetails();
	        String json = gson.toJson(list);
	        return Response.status(200).entity(json).build();
	    } 
	    
	    @POST
	    @Path("/assignUserToDepartment")
	    public Response assignUserToDepartment(@FormParam("json") String userJson) {
	    	Department department = gson.fromJson(userJson, Department.class);
	    	int result = departmentDataAccess.insertUserToDepartment(department);
	    	String res = result == -1 ? "Not Added" : "Added" ;
	        return Response.status(200).entity(res).build();
	    }
	    
	    @PUT
	    @Path("/updateUserDepartment")
	    public Response updateUserDepartment(@FormParam("json") String json) {
	    	Department department = gson.fromJson(json, Department.class);
	    	int result = departmentDataAccess.updateUserDepartment(department);
	    	String res = result == -1 ? "Not updated" : "Updated" ;
	    	return Response.status(200).entity(res).build();
	    }
	    
	    @POST
	    @Path("/deleteUserOnDepartment")
	    public Response deleteUserOnDepartment(@FormParam("json") String json) {
	    	Department department = gson.fromJson(json, Department.class);
	    	int result = departmentDataAccess.deleteUserFromDeprtment(department);
	    	String res = result == -1 ? "Not Deleted" : "Deleted" ;
	        return Response.status(200).entity(res).build();
	    }
	   
	    
}
