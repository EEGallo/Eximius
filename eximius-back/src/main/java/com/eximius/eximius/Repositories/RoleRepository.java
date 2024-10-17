package com.eximius.eximius.Repositories;


import com.eximius.eximius.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //Metodo para buscar un role por su nombre en nuestra DB
    Optional<Role> findByName(String name);
}
