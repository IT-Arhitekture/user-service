package com.itarhitekture.model;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

public class User extends ReactivePanacheMongoEntity {
    public String username;
    public String password;

}