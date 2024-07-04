package com.project.springbootrabbitmq.dto;

import lombok.Data;

//Class for serialization and deserialization
@Data
public class User {
    public int id;
    private String firstName;
    private String lastName;
}