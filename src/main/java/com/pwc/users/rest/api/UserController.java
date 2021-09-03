package com.pwc.users.rest.api;
import javax.ws.rs.GET;  

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;  
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;  
@Path("/user")
public class UserController {
  
    @GET
    public Response getMsg() {
  
        String output = "Hellow : PwC " ;
  
        return Response.status(200).entity(output).build();
  
    } 
}   