package com.eximius.eximius.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eximius.eximius.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}