package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Product;

public class ProductRepositoryInMemory implements ProductRepository {
	private Map<String, Product> products = new HashMap<String, Product>();
	
	public ProductRepositoryInMemory () {
		Product test = new Product("test", "test", 50.0);
		Product prod = new Product("prod", "prod", 100.0);
		add(test);
		add(prod);
	}
	
	@Override
	public Product get(String productId){
		if(productId == null){
			throw new IllegalArgumentException("No id given");
		}
		return products.get(productId);
	}
	
	@Override
	public List<Product> getAll(){
		return new ArrayList<Product>(products.values());	
	}

	@Override
	public void add(Product product){
		if(product == null){
			throw new IllegalArgumentException("No Product given");
		}
		if (products.containsKey(product.getProductId())) {
			throw new IllegalArgumentException("Product already exists");
		}
		products.put(product.getProductId(), product);
	}
	
	@Override
	public void update(Product product){
		if(product == null){
			throw new IllegalArgumentException("No Product given");
		}
		if(!products.containsKey(product.getProductId())){
			throw new IllegalArgumentException("No Product found");
		}
		products.put(product.getProductId(), product);
	}
	
	@Override
	public void delete(String productId){
		if(productId == null){
			throw new IllegalArgumentException("No id given");
		}
		products.remove(productId);
	}
}