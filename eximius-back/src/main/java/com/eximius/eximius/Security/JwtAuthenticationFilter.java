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
    @Override                        //Solicitud entrante
    protected void doFilterInternal(HttpServletRequest request,
                                    //Respuesta saliente
                                    HttpServletResponse response,
                                    //Mecanísmo para invocar el siguiente filtro en la siguiente cadena de filtros
                                    FilterChain filterChain) throws ServletException, IOException {
        //Obtenemos los datos del token mediante el método desarrollado arriba
        String token = getRequestToken(request);
        //Validamos la información del token
        if(StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
            //Asignamos el nombre de usuario contenido con el objeto "token" y lo pasamos a nuestra variable "username"
            String username = jwtGenerator.getUsernameToJwt(token);
            //Creamos el objeto "userDetails" el cual contendrá todos los detalles de nuestro username, o sea nombre, pw y role según el método loadUserByUsername
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            //Cargamos una lista de String con los roles alojados en BD
            List<String> userRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            //Comprobamos que el usuario autenticado posea alguno de los siguientes roles alojados en BD
            if (userRoles.contains("USER") || userRoles.contains("ADMIN")){
                //Creamos el objeto UsernamePasswordAuthenticationToken el cual contendrá los detalles de autenticación del usuario
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                //Acá establecimos información adicional de la autentificación, como por ejemplo la dirección ip del usuario, o el agente de usuario para hacer la solicitud etc.
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //Establecemos el objeto anterior (autentificación del usuario) en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        //Permite que la solicitud continue hacia el siguiente filtro en la cadena de filtro.
        filterChain.doFilter(request, response);
    }
}
