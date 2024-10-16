package com.eximius.eximius.Repositories;

import com.eximius.eximius.Entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
