package com.dbp.backendtourplus.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dbp.backendtourplus.user.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    @Lazy
    private UserService userService;

    public String extractUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    public String generateToken(UserDetails data) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60 * 10);

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(data.getUsername())
                .withClaim("role", data.getAuthorities().iterator().next().getAuthority())
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);
    }

    public void validateToken(String token, String userEmail) throws AuthenticationException {
        JWT.require(Algorithm.HMAC256(secret)).build().verify(token);

        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(
                userDetails, token, userDetails.getAuthorities()));
        SecurityContextHolder.setContext(context);
    }
}
