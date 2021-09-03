package com.pwc.users.rest.api;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.pwc.users.dao.User;
import com.pwc.users.dataacess.ProjectDataAccess;
import com.pwc.users.dataacess.UserDataAccess;  
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
	private GsonBuilder builder = new GsonBuilder(); 
	private Gson gson = builder.create() ;
    private UserDataAccess userDataAccess = new UserDataAccess(); 

    @GET
    @Path("/getAllUsers")
    public Response getAllUsers() {
        List<User> users = userDataAccess.getAllUsers();
        String json = gson.toJson(users);
        return Response.status(200).entity(json).build();
    } 
    
    @POST
    @Path("/createUser/{user}")
    public Response createUser(@PathParam("user") String userJson) {
    	User user = gson.fromJson(userJson, User.class);
    	int result = userDataAccess.insertUser(user);
    	String res = result == -1 ? "Not Added" : "Added" ;
        return Response.status(200).entity(res).build();
    }
    
    @PUT
    @Path("/updateUser/{user}")
    public Response updateUser(@PathParam("user") String userJson) {
    	User user = gson.fromJson(userJson, User.class);
    	int result = userDataAccess.updateUser(user);
    	String res = result == -1 ? "Not updated" : "Updated" ;
    	return Response.status(200).entity(res).build();
    }
    @DELETE
    @Path("/deleteUser/{id}")
    public Response deleteUser(@PathParam("id") int id) {
    	int result = userDataAccess.deleteUser(id);
    	String res = result == -1 ? "Not Deleted" : "Deleted" ;
        return Response.status(200).entity(res).build();
    }
    
  
    
    
}   