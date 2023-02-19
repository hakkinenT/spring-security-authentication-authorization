package com.hakkinenT.authentication.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hakkinenT.authentication.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Filtro executado apenas uma vez por requisição

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        
        // obtem o cabeçalho Authorization da requisição        
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // verifica se o cabeçalho é nulo ou se não há nenhum prefixo do tipo Bearer
        //retorna 403 se for true
        if (authHeader == null || !authHeader.startsWith("Bearer ", 0)) {
            // invoca o proximo filtro da cadeia, passando a requisição e a resposta
            // se o filtro é o último na cadeia, o resource final da cadeia é invocado
            filterChain.doFilter(request, response);
            return;
        }

        //extrai o jwt do cabeçalho Authorization
        jwt = authHeader.substring(7);
        // extrai o email do usuário
        userEmail = jwtService.extractUsername(jwt);
        

        //autentica o usuário
        // retorna 403 se for falso
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);

    }

}
