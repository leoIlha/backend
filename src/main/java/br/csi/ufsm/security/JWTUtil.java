package br.csi.ufsm.security;

import br.csi.ufsm.model.Funcionario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    public static final long TEMPO_VIDA = Duration.ofSeconds(10000).toMillis(); //voltar para 40

    public String geraToken(Funcionario funcionario){
        System.out.println("JWT UTIL:"+funcionario.getCargo());
        final Map<String,Object> claims = new HashMap<>();
        claims.put("sub",funcionario.getEmail());
        claims.put("permissoes:",funcionario.getCargo());

        return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis()+this.TEMPO_VIDA))
                .signWith(SignatureAlgorithm.HS256,"poow2").compact();

    }

    public String getUsernameToken(String token){
        if(token!=null){
            return this.parseToken(token).getSubject();
        }
        else{
            return null;
        }
    }

    public boolean isTokenExpirado(String token){
        return this.parseToken(token).getExpiration().before(new Date());
    }
    public Claims parseToken(String token){
        return Jwts.parser().setSigningKey("poow2").parseClaimsJws(token.replace("Bearer",""))
                .getBody();
    }
}
