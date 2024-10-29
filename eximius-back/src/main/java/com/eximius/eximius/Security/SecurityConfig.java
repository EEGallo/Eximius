package com.eximius.eximius.Security;

import com.eximius.eximius.Constant.RoleConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //le indica al contenedor de spring que esta es una clase de seguridad al momento de arrancar la aplicacion
@EnableWebSecurity //indicamos que se activa la seguridad de nuestra web en nuestra apicacion y ademas esta sera una clase la cual contendra toda la configuración referente a la seguridad
public class SecurityConfig {
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }
    //Este bean se va a encargar de verificar la informacion de los usuarios que se loguearan en nuestra api
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Con este bean nos encargaremos de autenticar todas nuestras contraseñas
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Este bean incorporara el filtro de seguridad de JWT que creamos en nuestra clase anterior
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
    //este bean va a establecer una cadena de filtros de seguridad en nuestra app. Y es aqui donde determinaremos los permisos segun los roles de usuarioos para acceder a nuestra app

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://172.27.240.1:8081", "http://localhost:8081")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(false); // Permite credenciales si es necesario
            }
        };
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivar CSRF
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests

                                //acces PUBLIC
                                .requestMatchers(HttpMethod.GET, "/api/product/all", "/api/product/**", "/api/product/category/**").permitAll() // Permitir acceso sin autenticación a las peticiones GET product
                                .requestMatchers(HttpMethod.GET, "/api/category", "/api/category/{id}").permitAll() // Permitir acceso sin autenticación a las peticiones GET category
                                .requestMatchers("/api/auth/**").permitAll() // Permitir acceso sin autenticación a /api/auth/**



                                //acces USER
                                .requestMatchers(HttpMethod.POST, "/api/cart", "/api/cart/addproduct").hasAuthority(RoleConstants.USER) // Solo USER puede crear un carrito y añadir productos en él
                                .requestMatchers(HttpMethod.GET, "/api/cart/{id}").hasAuthority(RoleConstants.USER) // Solo USER puede ver el carrito
                                .requestMatchers(HttpMethod.DELETE, "/api/cart/{id}").hasAuthority(RoleConstants.USER) //Solamente USER puede eliminar el carrito



                                //acces ADMIN

                                //ADMIN > Product
                                .requestMatchers(HttpMethod.POST, "/api/product").hasAuthority(RoleConstants.ADMIN) // Solo ADMIN puede crear productos
                                .requestMatchers(HttpMethod.PUT, "/api/product/{id}").hasAuthority(RoleConstants.ADMIN) // Solo ADMIN puede actualizar productos
                                .requestMatchers(HttpMethod.DELETE, "/api/product/{id}").hasAuthority(RoleConstants.ADMIN) // Solo ADMIN puede eliminar productos

                                //ADMIN > Category
                                .requestMatchers(HttpMethod.POST, "/api/category").hasAuthority(RoleConstants.ADMIN) // Solo ADMIN puede crear productos
                                .requestMatchers(HttpMethod.PUT, "/api/category/{id}").hasAuthority(RoleConstants.ADMIN) // Solo ADMIN puede actualizar productos
                                .requestMatchers(HttpMethod.DELETE, "/api/category/{id}").hasAuthority(RoleConstants.ADMIN) // Solo ADMIN puede eliminar productos


                                //ADMIN > Carts
                                .requestMatchers(HttpMethod.GET, "/api/cart").hasAuthority(RoleConstants.ADMIN) // Solo USER puede ver el carrito


                                .anyRequest().authenticated() // Cualquier otra solicitud debe estar autenticada
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
