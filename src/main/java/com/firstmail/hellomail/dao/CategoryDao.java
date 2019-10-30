package com.firstmail.hellomail.dao;

import com.firstmail.hellomail.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {

}