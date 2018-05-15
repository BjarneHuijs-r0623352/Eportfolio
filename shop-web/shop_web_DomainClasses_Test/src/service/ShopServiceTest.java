package service;

import java.util.List;

import db.PersonRepository;
import db.PersonRepositoryInDB;
import db.ProductRepository;
import db.ProductRepositoryInDB;
import domain.Person;
import domain.Product;

public class ShopServiceTest {
	private PersonRepository personRepository = new PersonRepositoryInDB();
	private ProductRepository productRepository = new ProductRepositoryInDB();

	public ShopServiceTest(){
	}
	
	public Person getPerson(String personId) {
		return getPersonRepository().get(personId);
	}

	public List<Person> getPersons() {
		return getPersonRepository().getAll();
	}

	public void addPerson(Person person) {
		getPersonRepository().add(person);
	}

	public void updatePersons(Person person) {
		getPersonRepository().update(person);
	}

	public void deletePerson(String id) {
		getPersonRepository().delete(id);
	}

	private PersonRepository getPersonRepository() {
		return personRepository;
	}
	
	
	
	public Product getProduct(String ProductId) {
		return getProductRepository().get(ProductId);
	}

	public List<Product> getProducts() {
		return getProductRepository().getAll();
	}

	public void addProduct(Product Product) {
		getProductRepository().add(Product);
	}

	public void updateProduct(Product Product) {
		getProductRepository().update(Product);
	}

	public void deleteProduct(String id) {
		getProductRepository().delete(id);
	}

	private ProductRepository getProductRepository() {
		return productRepository;
	}
	
	public List<Person> getPersonsSorted()
	{
		return getPersonRepository().sort();
	}
}
