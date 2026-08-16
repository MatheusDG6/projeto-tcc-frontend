/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.tcc_frontend.service;

import com.projeto.tcc_frontend.model.MatchProfissionalDTO;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 *
 * @author mathe
 */
@Service
public class MatchService {
    
    private final RestClient client;
    
    public MatchService() {
        this.client = RestClient.builder()
                .baseUrl("http://localhost:3333")
                .build();
    }

    public String cadastrarMatch(
            MatchProfissionalDTO match,
            String token) {

        return client
                .post()
                .uri("/match/cadastrar")
                .header("Authorization", "Bearer " + token)
                .body(match)
                .retrieve()
                .body(String.class);
    }
    
     public List<MatchProfissionalDTO> listarSolicitacoes(
            String token) {

        return client
                .get()
                .uri("/match/solicitacoes")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(
                    new ParameterizedTypeReference<List<MatchProfissionalDTO>>() {}
                );
    }

    public void aceitarMatch(
            Integer id_match,
            String token) {

        client
                .put()
                .uri("/match/" + id_match + "/aceitar")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(String.class);
    }

    public void recusarMatch(
            Integer id_match,
            String token) {

        client
                .put()
                .uri("/match/" + id_match + "/recusar")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(String.class);
    }
    
    public List<MatchProfissionalDTO> listarMatchesAceitos(String token) {

        return client
                .get()
                .uri("/match/aceitos")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<List<MatchProfissionalDTO>>() {
                });
    }
}