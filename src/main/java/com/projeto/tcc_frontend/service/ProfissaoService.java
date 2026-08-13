/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.tcc_frontend.service;

import com.projeto.tcc_frontend.model.ProfissaoDTO;
import com.projeto.tcc_frontend.model.UsuarioDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 *
 * @author mathe
 */
@Service
public class ProfissaoService {
    
    private final RestClient client;

    public ProfissaoService() {
        this.client = RestClient.builder()
                .baseUrl("http://localhost:3333")
                .build();
    }
    
    public void cadastrarProfissao(ProfissaoDTO profissao) {
        
    client
        .post()
        .uri("/profissional/cadastrar")
        .body(profissao)
        .retrieve()
        .body(String.class);
    }
    
    public List<ProfissaoDTO> listarProfissoes() {

        return client
                .get()
                .uri("/profissional/listar")
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<List<ProfissaoDTO>>() {});
    }
}
