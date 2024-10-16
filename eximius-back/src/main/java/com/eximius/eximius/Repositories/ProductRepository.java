package com.eximius.eximius.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eximius.eximius.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
