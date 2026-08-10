/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.tcc_frontend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Aluno
 */
@Controller
public class ProfissaoController {
    
    @GetMapping("/tela-profissoes")
    public String telaProfissoes(HttpSession session) {
        
        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }
        
        return "tela-profissoes";
    }
    
    @GetMapping("/cadastro-profissao")
    public String telaCadastroProfissao(HttpSession session) {

        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }

        return "cadastro-profissao";
    }
}
