package com.eximius.eximius.Controllers;

import com.eximius.eximius.DTO.DtoAuthResponse;
import com.eximius.eximius.DTO.DtoLogin;
import com.eximius.eximius.DTO.DtoRegister;
import com.eximius.eximius.Entities.Role;
import com.eximius.eximius.Entities.User;
import com.eximius.eximius.Repositories.RoleRepository;
import com.eximius.eximius.Repositories.UserRepository;
import com.eximius.eximius.Security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/auth")
public class RestControllerAuth {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private JwtGenerator jwtGenerator;

    @Autowired
    public RestControllerAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
    }

    //Metodo para poder registar usuarios con role "USER"
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody DtoRegister dtoRegister) {
        if (userRepository.existsByUserName(dtoRegister.getUsername())){
            return new ResponseEntity<>("The user already exists, try another one", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(dtoRegister.getUsername());
        user.setPassword(passwordEncoder.encode(dtoRegister.getPassword()));
        Role role = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        return new ResponseEntity<>("Successful user registration", HttpStatus.OK);
    }


    //Metodo para poder registar usuarios con role "ADMIN"
    @PostMapping("registerAdm")
    public ResponseEntity<String> registerAdmin(@RequestBody DtoRegister dtoRegister) {
        if (userRepository.existsByUserName(dtoRegister.getUsername())){
            return new ResponseEntity<>("The user already exists, try another one", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(dtoRegister.getUsername());
        user.setPassword(passwordEncoder.encode(dtoRegister.getPassword()));
        Role role = roleRepository.findByName("ADMIN").get();
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        return new ResponseEntity<>("Successful admin registration", HttpStatus.OK);
    }

    //Metodo para poder logear un usuario y obtener un token
    @PostMapping("login")
    public ResponseEntity<DtoAuthResponse> login(@RequestBody DtoLogin dtoLogin) {
        // Autenticación del usuario
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dtoLogin.getUsername(), dtoLogin.getPassword()));

        // Establecer el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generar el token JWT
        String token = jwtGenerator.generateToken(authentication);

        // Obtener el nombre de usuario autenticado desde 'authentication'
        String username = authentication.getName();

        // Recuperar el usuario desde la base de datos
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener los roles del usuario
        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());

        // Retornar la respuesta con el token y los roles
        return new ResponseEntity<>(new DtoAuthResponse(token, roles), HttpStatus.OK);
    }
}
