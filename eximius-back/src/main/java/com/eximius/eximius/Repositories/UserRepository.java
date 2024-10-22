package com.eximius.eximius.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eximius.eximius.Entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Método para buscar un usuario por su nombre de usuario
    Optional<User> findByUserName(String userName);

    // Método para verificar si un usuario existe en la base de datos
    Boolean existsByUserName(String userName);

    // Este método ya hace lo mismo que el anterior, por lo que puedes eliminarlo
    // boolean existsByUserName(String testuser);
}
