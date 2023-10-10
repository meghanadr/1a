package com.in.cafe.com.in.cafe.serviceimpl;


import com.in.cafe.com.in.cafe.POJO.Product;
import com.in.cafe.com.in.cafe.dao.ProductDao;
import com.in.cafe.com.in.cafe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            if (validationAddNewProduct(requestMap, false)) {
                Product product = getProductDaoFromMap(requestMap, false);
                productDao.save(product);
                return new ResponseEntity<>("{\"message\":\"" + "Product Added Successfully." + "\"}", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{\"message\":\"" + "Invalid Data." + "\"}", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"" + "Something Went Wrong at Product services impl." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validationAddNewProduct(Map<String, String> requestMap, Boolean validateId) {
        if (requestMap.containsKey("name") && requestMap.containsKey("price") && requestMap.containsKey("description")) {

            if (validateId && requestMap.containsKey("productId")) {
                return true;
            } else if (!validateId) {


                return true;
            }
        }
        return false;
    }

    private Product getProductDaoFromMap(Map<String, String> requestMap, Boolean isAdd) {
        Product product = new Product();
        if (isAdd) {
            product.setId(Integer.parseInt(requestMap.get("productId")));
        }
        product.setName(requestMap.get("name"));
        product.setPrice(Float.parseFloat(requestMap.get("price")));
        product.setDescription(requestMap.get("description"));
        return product;
    }

    @Override
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            return new ResponseEntity<List<Product>>(productDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Product>>(new ArrayList<Product>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> updateNewProduct(Map<String, String> resquestMap) {
        try {
            if (validationAddNewProduct(resquestMap, true)) {
                Optional optional = productDao.findById(Integer.parseInt(resquestMap.get("productId")));
                if (!optional.isEmpty()) {
                    productDao.save(getProductDaoFromMap(resquestMap, true));
                    return new ResponseEntity<>("{\"message\":\"" + "you are trying update Product Successfully." + "\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"" + "you are trying update Product does not exit." + "\"}", HttpStatus.BAD_REQUEST);
                }

            } else {
                return new ResponseEntity<>("{\"message\":\"" + "Invalid Data." + "\"}", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"" + "Something Went Wrong at Product services impl." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(int productId) {
        try {
            if (productId >= 0) {
                productDao.deleteById(productId);
                return new ResponseEntity<>("{\"message\":\"" + "Product Deleted Successfully." + "\"}", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{\"message\":\"" + "Invalid Product ID." + "\"}", HttpStatus.BAD_REQUEST);

            }
        }catch(Exception ex){
                ex.printStackTrace();
            }
            return new ResponseEntity<>("{\"message\":\"" + "Something Went Wrong ." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


