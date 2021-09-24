package com.reto.backend.reto.backend.entelgy.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reto.backend.reto.backend.entelgy.models.ResponseUsuarios;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Value("${entelgy.end_point}")
    private String URL;

    @GetMapping("/")
    public ResponseEntity<ResponseUsuarios> usuarios (){
        RestTemplate restTemplate = new RestTemplate();
        ResponseUsuarios responseUsuarios = null;
        HttpStatus httpStatus;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<ResponseUsuarios> responseData  = restTemplate.exchange(URL, HttpMethod.GET, entity, ResponseUsuarios.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode body = (mapper.valueToTree(responseData)).get("body");

            responseUsuarios = mapper.reader().forType(ResponseUsuarios.class).readValue(body);
            return new ResponseEntity(responseUsuarios, HttpStatus.OK);
        }catch (Exception exception){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(responseUsuarios, httpStatus);
    }


}
