package com.utn.elbuensaborbackend.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.squareup.okhttp.*;
import com.squareup.okhttp.RequestBody;
import com.utn.elbuensaborbackend.dtos.Auth0UsuarioDTO;
import com.utn.elbuensaborbackend.dtos.RolDTO;
import com.utn.elbuensaborbackend.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/auth0")
public class Auth0Controller {

    @Value("${AUTH0_DOMAIN}")
    private String domain;

    @Value("${AUTH0_API_CLIENT_ID}")
    private String apiClientID;

    @Value("${AUTH0_API_CLIENT_SECRET}")
    private String apiClientSecret;

    @Value("${AUTH0_AUDIENCE}")
    private String apiAudience;
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios(){
        try {
            String token = getTokenApi();
            String url = "https://" + domain + "/api/v2/users";

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);

            List<UsuarioDTO> usuarios = new ArrayList<>();
            for (JsonNode userNode : jsonNode) {
                String email = userNode.get("email").asText();
                String usuarioId = userNode.get("user_id").asText();

                List<RolDTO> roles = getRolesUsuario(usuarioId);

                //UsuarioDTO usuarioDTO = new UsuarioDTO(email, roles.get(0));
                //usuarios.add(usuarioDTO);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("usuarios/roles/{id}")
    public List<RolDTO> getRolesUsuario(@PathVariable("id") String usuarioId){
        try{
            String token = getTokenApi();
            String encodedUsuarioId = URLEncoder.encode(usuarioId, StandardCharsets.UTF_8)
                    .replace("|", "%7C");
            String url = "https://" + domain + "/api/v2/users/" + encodedUsuarioId + "/roles";

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            RolDTO[] rolesUsuarios = mapper.readValue(responseBody, RolDTO[].class);

            return Arrays.asList(rolesUsuarios);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    @PostMapping("/token")
    public String getTokenApi(){
        try{
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            String requestBody = "grant_type=client_credentials&client_id=" + apiClientID +
                    "&client_secret=" + apiClientSecret +
                    "&audience=" + apiAudience;

            RequestBody body = RequestBody.create(mediaType, requestBody);

            Request request = new Request.Builder()
                    .url("https://" + domain + "/oauth/token")
                    .post(body)
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);
            String token = jsonNode.get("access_token").asText();
            return token;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<String> create(@org.springframework.web.bind.annotation.RequestBody Auth0UsuarioDTO newUser){
        try {
            String token = getTokenApi();
            String url = "https://" + domain + "/api/v2/users";

            JsonObject userObject = new JsonObject();
            userObject.addProperty("connection", "Username-Password-Authentication");
            userObject.addProperty("email", newUser.getEmail());
            userObject.addProperty("password", newUser.getClave());

            String requestBody = userObject.toString();

            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            HttpStatus httpStatus = HttpStatus.valueOf(response.code());

            return ResponseEntity.status(httpStatus).body(responseBody);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear un Usuario");
        }
    }

    @PostMapping("/usuarios/roles/{rolId}")
    public ResponseEntity<String> assignUserToRole(@PathVariable String rolId, @org.springframework.web.bind.annotation.RequestBody Map<String, List<String>> requestBody) {
        try {
            String token = getTokenApi();
            String url = "https://" + domain + "/api/v2/roles/" + rolId + "/users";

            OkHttpClient cliente = new OkHttpClient();

            List<String> usuarios = requestBody.get("users");

            JsonObject requestBodyObject = new JsonObject();
            JsonArray usersArray = new JsonArray();

            for (String usuario : usuarios) {
                usersArray.add(usuario);
            }

            requestBodyObject.add("users", usersArray);

            String requestBodyString = requestBodyObject.toString();

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBodyString);

            Request request = new Request.Builder()
                    .url(url)
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .post(body)
                    .build();

            Response response = cliente.newCall(request).execute();
            String responseBody = response.body().string();

            HttpStatus httpStatus = HttpStatus.valueOf(response.code());
            return ResponseEntity.status(httpStatus).body(responseBody);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al asignar el Usuario al Rol.");
        }
    }

    @DeleteMapping("/usuarios/roles/{usuarioId}")
    public ResponseEntity<String> deleteRolesFromUser(@PathVariable String usuarioId, @org.springframework.web.bind.annotation.RequestBody Map<String, List<String>> requestBody) {
        try {
            String token = getTokenApi();
            String encodedUserId = URLEncoder.encode(usuarioId, StandardCharsets.UTF_8).replace("|", "%7C");
            String url = "https://" + domain + "/api/v2/users/" + encodedUserId + "/roles";

            List<String> roles = requestBody.get("roles");

            JsonObject requestBodyObject = new JsonObject();
            JsonArray rolesArray = new JsonArray();

            for (String rol : roles) {
                rolesArray.add(rol);
            }

            requestBodyObject.add("roles", rolesArray);

            String requestBodyString = requestBodyObject.toString();

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBodyString);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .delete(body)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            HttpStatus httpStatus = HttpStatus.valueOf(response.code());
            return ResponseEntity.status(httpStatus).body(responseBody);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar un rol del usuario");
        }
    }

}
