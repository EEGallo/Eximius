package com.eximius.eximius.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eximius.eximius.Entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{


}