package com.eximius.eximius.Security;

import jakarta.persistence.Column;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/*La funcion de esta clase ser[a validar la información del token y si esto es exitoso,
establecera la autenticacion de un usuario en la solicitud o en el contexto de seguridad de nuestra aplicacion*/
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtGenerator jwtGenerator;

    /*Con el siguiente método extraemos el token JWT de la cabecera de nuestra petición HTTP("Authoritation")
    * luego lo validará y finalmente lo retornará*/
    private String getRequestToken(HttpServletRequest request){
        String bearerToken = request.getHeader( "Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            //Acá si se encuentra el token JWT, se devuelve una subcadena de BearerToken que comienza despues de los primeros 7 caracteres hasta el final de la cadena
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getRequestToken(request);

        if (StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            try {
                String username = jwtGenerator.getUsernameToJwt(token);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                List<String> userRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

                if (userRoles.contains("USER") || userRoles.contains("ADMIN")) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (Exception e) {
                // Manejar excepciones si el token es inválido o ha expirado
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token or user not found");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

}
