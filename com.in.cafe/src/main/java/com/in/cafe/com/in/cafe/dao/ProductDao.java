package com.in.cafe.com.in.cafe.dao;

import com.in.cafe.com.in.cafe.POJO.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product , Integer> {
}
