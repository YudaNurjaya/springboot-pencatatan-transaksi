package com.enigma.livecodeecommerce.repository;

import com.enigma.livecodeecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
