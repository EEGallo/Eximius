package com.eximius.eximius.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eximius.eximius.Entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    //Metodo para poder buscar un usuario mediante su nombre

    Optional<User> findByUserName(String  username);

    //Metodo para poder verificar si un usuario existe en nuestra base de datos
    Boolean existsByUsername(String username);

}