package domain;

public class Product {
	private String productId;
	private String description;
	private Double price;
	public Product(String productId, String description, Double price) {
		setProductId(productId);
		setDescription(description);
		setPrice(price);
	}
	
	public Product() {
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		if(productId.isEmpty()){
			throw new IllegalArgumentException("No productId given");
		}
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description.isEmpty()){
			throw new IllegalArgumentException("No description given");
		}
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		if(price < 0 || price.isNaN()){
			throw new IllegalArgumentException("No price given");
		}
		this.price = price;
	}	
}