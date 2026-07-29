/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.tcc_frontend.service;

import com.projeto.tcc_frontend.model.UsuarioBean;
import com.projeto.tcc_frontend.model.UsuarioRequestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


/**
 *
 * @author Aluno
 */
@Service
public class UsuarioService {
    
    private final RestClient client;
    
    
    public UsuarioService() {
        this.client = RestClient.builder()
                // Define a base URL que será usada em todas as requisições.
                // Depois, cada chamada só precisa informar o caminho relativo.
                .baseUrl("http://localhost:3333")
                .build();
    }
         
    public void cadastrar(UsuarioBean usuario) {
        String retorno = 
            client
                .post()
                .uri("/cadastrar")
                .body(usuario)
                .retrieve()
                .body(String.class);
    }
    
    public String login(UsuarioRequestBean usuarios){
        return client.post()
                .uri("/autenticar/login")
                .body(usuarios)
                .retrieve()
                .body(String.class);
    }
}