package com.in.cafe.com.in.cafe.restimpl;


import com.in.cafe.com.in.cafe.POJO.Product;
import com.in.cafe.com.in.cafe.rest.ProductRest;
import com.in.cafe.com.in.cafe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProductRestImpl implements ProductRest {

    @Autowired
    ProductService productService;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            return productService.addNewProduct(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"" + "Something Went Wrong." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public  ResponseEntity<List<Product>> getAllProduct() {
        try {
                return  productService.getAllProduct();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new  ResponseEntity<List<Product>>(new ArrayList<Product>() ,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateNewProduct(Map<String, String> requestMap) {
        try {
            return productService.updateNewProduct(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"" + "Something Went Wrong." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(int productId) {
        try {
            return productService.deleteProduct(productId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"" + "Something Went Wrong." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }




