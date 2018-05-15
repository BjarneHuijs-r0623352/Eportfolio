package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Product;

public class ProductRepositoryInDB implements ProductRepository{
	private PreparedStatement statement; 
	private Connection connection;

	public ProductRepositoryInDB() {
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51516/1TX35";
		Properties properties = new Properties(); 
		properties.setProperty("user", "r0623352"); 
		properties.setProperty("password", "Stevusx12"); 
		properties.setProperty("ssl", "true"); 
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory"); 
		
		try { 
			Class.forName("org.postgresql.Driver"); 
			connection = DriverManager.getConnection(url, properties); 
		}catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage(), e);
		} catch (ClassNotFoundException e) 
		{ 
			throw new DbException(e.getMessage(), e); 
		}
	}

	@Override
	public Product get(String productId){
		Product product; 
		try {
			String sql = "SELECT * FROM r0623352_web3_test.product WHERE productid = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, productId);
			ResultSet result = statement.executeQuery(); 
			result.next(); 
			String productID  = result.getString("productid"); 
			String description = result.getString("description");
			Double price = result.getDouble("price");
			product = new Product(productID, description, price); 
		}catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage(), e); 
		} 
		return product;
	}
	
	@Override
	public List<Product> getAll(){
		ArrayList<Product> products = new ArrayList<Product>();
		Product product; 
		try {
			String sql = "SELECT * FROM r0623352_web3_test.product";
			statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery(); 
			while(result.next())
			{
				String productID  = result.getString("productid"); 
				String description = result.getString("description");
				Double price = result.getDouble("price");
				product = new Product(productID, description, price); 
				products.add(product);
			}
		}catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage(), e); 
		} 
		return products;
	}

	@Override
	public void add(Product product){
		if(product == null)
		{ 
			throw new DbException("Can not add non-existing product"); 
		} 
		String sql = "INSERT INTO r0623352_web3_test.product (productid, description, price) VALUES (?, ?, ?)"; 
		try { 
			statement = connection.prepareStatement(sql);
			statement.setString(1, product.getProductId());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			statement.executeQuery();
		} catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage(), e); 
		}
	}
	
	@Override
	public void update(Product product){
		if(product == null)
		{ 
			throw new DbException("Can not update non existing product"); 
		} 
		String sql = "UPDATE r0623352_web3_test.product SET productid = ?, description = ?, price = ? WHERE productid = ?;"; 
		try { 
			statement = connection.prepareStatement(sql);
			statement.setString(1, product.getProductId());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			statement.setString(4, product.getProductId());
			statement.executeUpdate();
		} catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage(), e); 
		}
	}
	
	@Override
	public void delete(String productId){
		//Product product; 
		try {
			String sql = "DELETE FROM r0623352_web3_test.product WHERE productid = ?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, productId);
			ResultSet result = statement.executeQuery(); 
			result.next(); 
		}catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage(), e); 
		} 
	}
	
	@Override
	protected void finalize() throws Throwable {
		connection.close();
		super.finalize();
	}
}	