package com.gramer.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gramer.entity.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:repository-context.xml")
@Transactional
public class ProductRepositoryQueryTest {

	@Autowired
	private ProductRepository repository;
	
	@After
	public void deleteAll() {
		repository.deleteAll();
	}
	
	@Test
	public void testFindByName_equal() {
		Product product1 = createProduct("product2", "2", 2);

		product1 = repository.save(product1);
		
		List<Product> products = repository.findByName("product2");
		assertThat(products.contains(product1), is(true));
	}
	
	@Test
	public void testFindByName_like() {
		Product product1 = createProduct("product3", "3", 3);

		product1 = repository.save(product1);
		
		List<Product> products = repository.findByNameLike("produ%");
		assertThat(products.contains(product1), is(true));
	}
	
	@Test
	public void testSave_multi() {
		Product product1 = createProduct("product1", "1", 1);
		Product product2 = createProduct("product2", "2", 2);
		Product product3 = createProduct("product3", "3", 2);		
		
		repository.save(Arrays.asList(product1, product2, product3));
		
		List<Product> products = repository.findByNameLike("produ%");
		assertThat(products.size(), is(3));
		assertThat(products.contains(product1), is(true));
		assertThat(products.contains(product2), is(true));
		assertThat(products.contains(product3), is(true));
	}
	
	@Test
	public void testFindByProductId() {
		Product product1 = createProduct("product1", "aa1", 1);
		Product product2 = createProduct("product2", "aa2", 2);
		Product product3 = createProduct("product3", "b3", 3);		
		
		repository.save(Arrays.asList(product1, product2, product3));
		
		List<Product> products = repository.findByProductIdLike("aa%");
		assertThat(products.size(), is(2));
		assertThat(products.contains(product1), is(true));
		assertThat(products.contains(product2), is(true));
		assertThat(products.contains(product3), is(false));
	}

	// where name and quantity
	@Test
	public void testFindByNameAndQuantity() {
		Product product1 = createProduct("product1", "aa1", 1);
		Product product2 = createProduct("product2", "aa2", 2);
		Product product3 = createProduct("product3", "b3", 3);		
		
		repository.save(Arrays.asList(product1, product2, product3));
		
		List<Product> products = repository.findByNameAndQuantity("product2", 2);
		assertThat(products.size(), is(1));
		assertThat(products.contains(product2), is(true));
	}

	private Product createProduct(String name, String productId, int quantity) {
		Product product = new Product();
		product.setName(name);
		product.setProductId(productId);
		product.setQuantity(2);
		
		return product;
	}
	
}
