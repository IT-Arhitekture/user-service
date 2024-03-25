package com.itarhitekture.service;

import com.itarhitekture.model.User;
import com.itarhitekture.rabbitmq.MessageProducer;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class UserService implements ReactivePanacheMongoRepository<User> {


    @Inject
    MessageProducer messageProducer;

    public Uni<User> createUser(User user) {
        messageProducer.sendMessage("Created new user !");
        return persist(user).map(ignore -> user);
    }

    public Uni<User> updateUser(User user) {
        messageProducer.sendMessage("Updated user: " + user);
        return findById(user.id)
                .onItem().ifNotNull().transformToUni(dbUser -> {
                    dbUser.username = user.username;
                    dbUser.password = user.password;
                    return update(dbUser).map(ignore -> dbUser);
                });
    }

    public Uni<Void> deleteUser(String id) {
        ObjectId objectId = new ObjectId(id);
        messageProducer.sendMessage("Deleted user: " + id);
        return findById(objectId)
                .onItem().ifNotNull().transformToUni(user -> user.delete());
    }

    public Uni<User> getUser(String id) {
        messageProducer.sendMessage("Fetched user: " + id);
        ObjectId objectId = new ObjectId(id);
        return findById(objectId);
    }

    public Uni<List<User>> getAllUsers() {
        messageProducer.sendMessage("Fetched all users !");
        return listAll();
    }
}