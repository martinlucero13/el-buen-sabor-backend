package com.utn.elbuensaborbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Auth0RolDTO {

    private String id;
    private String name;
    private String description;
}