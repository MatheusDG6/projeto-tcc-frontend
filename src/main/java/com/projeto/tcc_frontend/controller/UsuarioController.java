/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.tcc_frontend.controller;

import com.projeto.tcc_frontend.model.UsuarioBean;
import com.projeto.tcc_frontend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mathe
 */
@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @PostMapping("/cadastrar")
    public String mandarRegistro(@ModelAttribute UsuarioBean usuario ) {
//        ser.cadastrar(usuario);
        service.cadastrar(usuario);
        return "cadastrar";
    }
}