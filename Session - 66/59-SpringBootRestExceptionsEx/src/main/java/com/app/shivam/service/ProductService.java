package com.app.shivam.service;

import org.springframework.stereotype.Service;

import com.app.shivam.entity.Product;
import com.app.shivam.exception.ProductNotFoundException;

@Service
public class ProductService {

	public Product getOneProductById(Integer id) {
		if(id==150)
			return new Product(id, "DUMMY");
		else 
			throw new ProductNotFoundException("PRODUCT '"+id+"' NOT EXIST");
	}

}
