/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.tcc_frontend.service;

import com.projeto.tcc_frontend.model.UsuarioBean;
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
     
    //public String logar(UsuarioBean usuario) {
        // Inicia a construção de uma requisição POST.
    //    return service.post()
                // Define o caminho relativo ao endpoint de autenticação.
                // A URL final será "http://localhost:3333/auth/logar".
    //            .uri("/autenticar/logar")
                // Define o corpo da requisição como o DTO de login.
                // O Spring converte automaticamente este objeto para JSON.
    //            .body(usuario)
                // Dispara a requisição e obtém a resposta do servidor.
    //            .retrieve()
                // Lê o corpo da resposta e converte para String.
                // Use outro DTO aqui se a API retornar um objeto JSON complexo.
    //            .body(String.class); 
    //}
        
    public void cadastrar(UsuarioBean usuario) {
        String retorno = 
            client
                .post()
                .uri("/cadastrar")
                .body(usuario)
                .retrieve()
                .body(String.class);
    }
}