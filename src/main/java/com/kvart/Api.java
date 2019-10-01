package com.kvart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class Api {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = UsersDao.getInstance().getAllUsers();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(users);
        return Response
                .status(Response.Status.OK)
                .entity(result)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createNewUser(@FormParam("name") String name,
                                  @FormParam("age") int age) {
        User newUser = new User(UsersDao.getInstance().genereteId(), name, age);
        UsersDao.getInstance().addUser(newUser);
        return Response
                .status(Response.Status.OK)
                .entity("User created")
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateUser (@PathParam("id") int id, @FormParam("name") String name,
                                @FormParam("age") int age) {
        User newUser = new User(id, name, age);
        UsersDao.getInstance().updateUser(newUser);
        return Response
                .status(Response.Status.OK)
                .entity("User update")
                .build();

    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser (@PathParam("id") int id) {
        UsersDao.getInstance().deleteUser(id);
        return Response
                .status(Response.Status.OK)
                .entity("User delete")
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") int id) {
        String result = null;
        try {
            List<User> users = UsersDao.getInstance().getAllUsers();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            result = gson.toJson(users.get(id));
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No such user")
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(result)
                .build();
    }
}
