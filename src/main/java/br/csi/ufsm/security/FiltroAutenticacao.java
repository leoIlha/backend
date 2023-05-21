package br.csi.ufsm.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FiltroAutenticacao extends OncePerRequestFilter {

    @Autowired private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String url = request.getRequestURI();
        System.out.println("filtro requisição:"+ url);

        System.out.println("passando aqui");
        try{

            if(!url.contains("login")){
               // System.out.println("passou login");
                String token = request.getHeader("Authorization");
                System.out.println("Token:"+token);
                String username = new JWTUtil().getUsernameToken(token);
                System.out.println("username"+username);
                System.out.println("Token expirado?"+new JWTUtil().isTokenExpirado(token));

                if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null){

                    UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
                    System.out.println("FILTRO AUTENTICAÇÃO:"+userDetails.getAuthorities());
                    if(! new JWTUtil().isTokenExpirado(token)){
                        UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                        authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authtoken);
                    }

                }

            }

        }catch (ExpiredJwtException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token Expirado");
        }catch (AccessDeniedException e){
            System.out.println("caiu no AccessDanieExcption");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expirado");
        }
        filterChain.doFilter(request,response);
    }
}
