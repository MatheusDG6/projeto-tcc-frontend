/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.tcc_frontend.controller;

import com.projeto.tcc_frontend.model.UsuarioDTO;
import com.projeto.tcc_frontend.model.UsuarioRequestBean;
import com.projeto.tcc_frontend.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author mathe
 */
@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/cadastrar")
    public String telaCadastro(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "cadastrar";
    }

    @PostMapping("/cadastrar")
    public String mandarRegistro(@ModelAttribute UsuarioDTO usuario ) {
        service.cadastrar(usuario);
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String telaLogin(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuario) {
        UsuarioRequestBean user = new UsuarioRequestBean();
        return service.login(user);
    }
}