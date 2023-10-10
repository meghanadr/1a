package com.in.cafe.com.in.cafe.service;

import com.in.cafe.com.in.cafe.POJO.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {

    public ResponseEntity<String> addNewProduct (Map<String,String> resquestMap);

    public  ResponseEntity<List<Product>>getAllProduct ();

    public ResponseEntity<String> updateNewProduct(Map<String,String> resquestMap);

    public ResponseEntity<String> deleteProduct(int productId);
}
