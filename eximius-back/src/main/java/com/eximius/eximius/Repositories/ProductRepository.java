package com.eximius.eximius.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eximius.eximius.Entities.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_Id(Long categoryId);

}
