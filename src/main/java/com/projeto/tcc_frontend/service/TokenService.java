/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.tcc_frontend.service;

import com.projeto.tcc_frontend.model.UsuarioDTO;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aluno
 */
@Service
public class TokenService {
    
    
    public SecretKey getKeySign() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public UsuarioDTO extrairClaim(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(this.getKeySign())
                .build()
                .parseSignedClaims(token)
                .getPayload();
       
        UsuarioDTO user = new UsuarioDTO();
        user.setEmail(claims.get("email", String.class));
        return user;
    }
}
