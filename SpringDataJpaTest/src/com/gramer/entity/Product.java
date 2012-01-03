package com.gramer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter @Getter
public class Product {
	
	@Id @GeneratedValue(generator="seq")
	@GenericGenerator(name="seq", strategy = "increment")
	private Long id;
	
	private String name;
	
	@NotNull
	private String productId;
	
	@NotNull
	private Integer quantity;

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", productId=" + productId + "]";
	}
	
}
