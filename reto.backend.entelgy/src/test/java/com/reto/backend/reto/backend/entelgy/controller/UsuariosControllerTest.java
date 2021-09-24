package com.reto.backend.reto.backend.entelgy.controller;

import com.reto.backend.reto.backend.entelgy.models.ResponseUsuarios;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class UsuariosControllerTest {
    @InjectMocks
    UsuariosController usuariosController;

    @BeforeAll
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void usuariosControllerTest(){
        ResponseEntity<ResponseUsuarios> response = usuariosController.usuarios();
        assertTrue(response!=null?true:false);
    }
}