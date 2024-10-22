package com.eximius.eximius.Repositories;

import com.eximius.eximius.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
