package com.utn.elbuensaborbackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSecurityTestController {

    @GetMapping("/public")
    public ResponseEntity<?> publicEndpoint() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("{ \\\"message\\\": \\\"All good. You do not need to be authenticated.\\\"}");
    }

    @GetMapping("/private")
    public ResponseEntity<?> privateEndpoint() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("{ \\\"message\\\": \\\"All good. You can see this because you are authenticated.\\\"}");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('read:admin')")
    public ResponseEntity<?> adminEndpoint() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("{ \\\"message\\\": \\\"All good. You can see this because you are an Admin.\\\"}");
    }

}
