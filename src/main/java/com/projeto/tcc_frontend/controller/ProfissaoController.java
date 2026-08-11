/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.tcc_frontend.controller;

import com.projeto.tcc_frontend.model.ProfissaoDTO;
import com.projeto.tcc_frontend.model.UsuarioDTO;
import com.projeto.tcc_frontend.service.ProfissaoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Aluno
 */
@Controller
public class ProfissaoController {
    
    @Autowired
    private ProfissaoService service;
    
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
    
    @PostMapping("/profissao/cadastrar")
    public String cadastrarProfissao(@ModelAttribute ProfissaoDTO profissao, UsuarioDTO usuario, HttpSession session) {

        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }
        
        System.out.println("asda");
        System.out.println(usuario.getId_usuario());
        System.out.println(usuario.getNome());

        service.cadastrarProfissao(profissao, usuario);

        return "redirect:/tela-profissoes";
    }
}
