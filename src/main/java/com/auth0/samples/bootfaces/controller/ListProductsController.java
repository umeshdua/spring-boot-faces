package com.auth0.samples.bootfaces.controller;

import com.auth0.samples.bootfaces.model.Product;
import com.auth0.samples.bootfaces.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope (value = "session")
@Component (value = "listProducts")
public class ListProductsController {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductController productController;

	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public String create() {
		return setupAndRedirectToCrudView(new Product(), false);
	}
	public String view(Product product) {
		return setupAndRedirectToCrudView(product, true);
	}
	public String edit(Product product) {
		return setupAndRedirectToCrudView(product, false);
	}
	public void delete(Product product) {
		productRepository.delete(product.getId());
	}
	private String setupAndRedirectToCrudView(Product product, boolean readOnly) {
		productController.setReadOnly(readOnly);
		productController.setProduct(product);
		return "/product/crud.xhtml?faces-redirect=true" + (product.getId() == null ? "" : "&eid=" + product.getId());
	}
}