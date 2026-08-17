/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.tcc_frontend.controller;

import com.projeto.tcc_frontend.model.MatchProfissionalDTO;
import com.projeto.tcc_frontend.service.MatchService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mathe
 */
@Controller
public class MatchController {
    
    @Autowired
    private MatchService service;

    @PostMapping("/match/solicitar")
    public String solicitarMatch(
            @RequestParam Integer id_profissao,
            HttpSession session) {

        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }

        String token = (String) session.getAttribute("token");

        MatchProfissionalDTO match = new MatchProfissionalDTO();
        match.setId_profissao(id_profissao);

        service.cadastrarMatch(match, token);

        return "redirect:/tela-profissoes";
    }
 
    @GetMapping("/meus-matches")
    public String meusMatches(
            HttpSession session,
            Model model) {

        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }

        String token = (String) session.getAttribute("token");

        List<MatchProfissionalDTO> matches
                = service.listarSolicitacoes(token);

        model.addAttribute("matches", matches);

        return "meus-matches";
    }
    
    @GetMapping("/matches-aceitos")
    public String matchesAceitos(
            HttpSession session,
            Model model) {

        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }

        String token = (String) session.getAttribute("token");

        List<MatchProfissionalDTO> matches
                = service.listarMatchesAceitos(token);

        model.addAttribute("matches", matches);

        return "matches-aceitos";
    }

    @PostMapping("/match/aceitar")
    public String aceitarMatch(
            @RequestParam Integer id_match,
            HttpSession session) {

        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }

        String token = (String) session.getAttribute("token");

        service.aceitarMatch(id_match, token);

        return "redirect:/meus-matches";
    }

    @PostMapping("/match/recusar")
    public String recusarMatch(
            @RequestParam Integer id_match,
            HttpSession session) {

        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }

        String token = (String) session.getAttribute("token");

        service.recusarMatch(id_match, token);

        return "redirect:/meus-matches";
    }
}
