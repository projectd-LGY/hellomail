package com.firstmail.hellomail.dao;

import com.firstmail.hellomail.domain.ProductionData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductionDAO extends JpaRepository<ProductionData,Integer> {

}
