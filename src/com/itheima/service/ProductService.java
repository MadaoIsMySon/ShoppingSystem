package com.itheima.service;

import java.util.List;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;

public class ProductService {

	public List<Product> findAll() {
		ProductDao Dao = new ProductDao();
		List<Product> ProductList = Dao.findAll();
		return ProductList;
	}
	
}
