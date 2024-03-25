package com.itarhitekture.controller;

import com.itarhitekture.model.User;
import com.itarhitekture.model.UserRequest;
import com.itarhitekture.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.logging.Logger;

@Path("/users")
public class UserController {

    @Inject
    UserService userService;

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<User> createUser(User user) {
        LOGGER.info("Created user: " + user);
        return userService.createUser(user)
                .onFailure().invoke(throwable -> {
                    LOGGER.severe("Error creating user: " + throwable.getMessage());
                    System.err.println("Napaka pri ustvarjanju uporabnika: " + throwable.getMessage());
                    throwable.printStackTrace();
                });
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<User> updateUser(@PathParam("id") String id, User user) {
        LOGGER.info("Updated user: " + user + id);
        user.id = new ObjectId(id);
        return userService.updateUser(user);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> deleteUser(@PathParam("id") String id) {
        LOGGER.info("Deleted user: " + id);
        return userService.deleteUser(id);
    }

    @POST
    @Path("/get-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<User> getUser(UserRequest userRequest) {
        LOGGER.info("Fetched user: " + userRequest);
        return userService.getUser(userRequest.getId());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<User>> getAllUsers() {
        LOGGER.warning("Fetched all users: ");
        return userService.getAllUsers();
    }
}