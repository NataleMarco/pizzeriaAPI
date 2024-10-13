package com.marquitos.pizzeria.web.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //1 validar que sea un header Authorization valido.
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("Paso 1 - Verificando Auth Header");
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            System.out.println("Paso 1 - Error verificando Auth Header");
            return;
        }
        System.out.println("Paso 1 - Auth header verificado con exito");

        //2 Validar que el JWT sea valido.

        String jwt = authHeader.split(" ")[1].trim();
        System.out.println("Paso 2 - Verificando el jwt");
        if (!this.jwtUtil.isValid(jwt)){
            filterChain.doFilter(request, response);
            System.out.println("Paso 2 - jwt no valido");
            return;
        }

        System.out.println("Paso 2 - jwt valido");

        //3 Cargar el usuario del UserDetailsService.

        System.out.println("Paso 3 - Descubriendo el nombre");
        String username = this.jwtUtil.getUsername(jwt);
        System.out.println("Paso 3 - Buscando en la base de datos");
        User user = (User) this.userDetailsService.loadUserByUsername(username);
        System.out.println("Paso 3 - agregando al usuario");

        //4 Cargar el usuario en el contexto de seguridad.
        System.out.println("Paso 4 - cargando el usuario al contexto de seguridad - 1 ");

        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),user.getAuthorities());

        System.out.println("Paso 4 - cargando el usuario al contexto de seguridad - 2");

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println(authenticationToken);
        filterChain.doFilter(request, response);

    }
}
