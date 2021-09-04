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
import com.pwc.users.dao.Project;
import com.pwc.users.dataacess.ProjectDataAccess;

@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectController {
    private ProjectDataAccess projectDataAccess = new ProjectDataAccess(); 
	private GsonBuilder builder = new GsonBuilder(); 
    private Gson gson = builder.create() ;
	
	  @GET
	    @Path("/getAllProjects")
	    public Response getAllProjects() {
	        List<String> list = projectDataAccess.getAllProjects();
	        String json = gson.toJson(list);
	        return Response.status(200).entity(json).build();
	    } 
	    @GET
	    @Path("/getProjectsDetails")
	    public Response getProjectsDetails() {
	        List<Project> list = projectDataAccess.getProjectsDetails();
	        String json = gson.toJson(list);
	        return Response.status(200).entity(json).build();
	    } 
	    @POST
	    @Path("/assignUserToProject")
	    public Response assignUserToProject(@FormParam("json") String json) {
	    	Project project = gson.fromJson(json, Project.class);
	    	int result = projectDataAccess.addUserToProject(project);
	    	String res = result == -1 ? "Not Added" : "Added" ;
	        return Response.status(200).entity(res).build();
	    }
	    
	    @PUT
	    @Path("/updateUserProject")
	    public Response updateUserDepartment(@FormParam("json")String json) {
	    	Project project = gson.fromJson(json, Project.class);
	    	int result = projectDataAccess.updateUserProject(project);
	    	String res = result == -1 ? "Not updated" : "Updated" ;
	    	return Response.status(200).entity(res).build();
	    }
	    
	    @DELETE
	    @Path("/deleteUserOnProject")
	    public Response deleteUserOnProject(@FormParam("json") String json) {
	    	Project project = gson.fromJson(json, Project.class);
	    	int result = projectDataAccess.deleteUserFromProject(project);
	    	String res = result == -1 ? "Not Deleted" : "Deleted" ;
	        return Response.status(200).entity(res).build();
	    }
}
