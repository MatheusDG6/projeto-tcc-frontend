/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.tcc_frontend.controller;

import com.projeto.tcc_frontend.model.ProfissaoDTO;
import com.projeto.tcc_frontend.model.UsuarioDTO;
import com.projeto.tcc_frontend.service.ProfissaoService;
import com.projeto.tcc_frontend.service.TokenService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Aluno
 */
@Controller
public class ProfissaoController {
    
    @Autowired
    private ProfissaoService service;
    
    @Autowired
    private TokenService tokenService;
    
    @GetMapping("/tela-profissoes")
    public String telaProfissoes(HttpSession session, Model model) {
        
        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }
        
        List<ProfissaoDTO> profissoes = service.listarProfissoes();
        model.addAttribute("profissoes", profissoes);
        
        return "tela-profissoes";
    }
    
    @GetMapping("/solicitar-match/{id}")
    public String telaSolicitarMatch(
            @PathVariable Integer id,
            HttpSession session,
            Model model) {

        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }

        List<ProfissaoDTO> profissoes = service.listarProfissoes();
        ProfissaoDTO profissaoSelecionada = null;

        for (ProfissaoDTO profissao : profissoes) {

            if (profissao.getId_profissao().equals(id)) {
                profissaoSelecionada = profissao;
                break;
            }
        }

        if (profissaoSelecionada == null) {
            return "redirect:/tela-profissoes";
        }

        model.addAttribute("profissao", profissaoSelecionada);
        return "solicitar-match";
    }
    
    @GetMapping("/cadastro-profissao")
    public String telaCadastroProfissao(HttpSession session) {

        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }

        return "cadastro-profissao";
    }
    
    @PostMapping("/profissao/cadastrar")
    public String cadastrarProfissao(@ModelAttribute ProfissaoDTO profissao, HttpSession session) {

        if (session.getAttribute("token") == null) {
            return "redirect:/login";
        }
        
        String token = (String) session.getAttribute("token");
        
        System.out.println("===== TOKEN NA SESSION =====");
        System.out.println("TOKEN: " + token);
        System.out.println("============================");
        
        
        UsuarioDTO usuario = tokenService.extrairClaim(token);
        
        System.out.println("===== USUARIO DA PROFISSAO =====");

        if (usuario != null) {
            System.out.println("ID USUARIO: " + usuario.getId_usuario());
            System.out.println("NOME: " + usuario.getNome());
            System.out.println("EMAIL: " + usuario.getEmail());
            System.out.println("ROLE: " + usuario.getRole());
        } else {
            System.out.println("USUARIO: NULL");
        }

        System.out.println("================================");
        
        profissao.setId_usuario(usuario.getId_usuario());
        service.cadastrarProfissao(profissao);

        return "redirect:/tela-profissoes";
    }
}