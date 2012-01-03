package com.gramer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gramer.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	
	// query
	public List<Product> findByName(String name);
	public List<Product> findByNameLike(String name);
	public List<Product> findByProductIdLike(String productId);
	public List<Product> findByNameAndQuantity(String name, Integer quantity);
	
	// update
	@Modifying
	@Query("update Product p set p.quantity = ?1")
	public void initAllProductQuantity(Integer quantity);
}
