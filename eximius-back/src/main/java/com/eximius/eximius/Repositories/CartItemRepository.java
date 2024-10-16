package com.eximius.eximius.Repositories;

import com.eximius.eximius.Entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}

