package db;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Person;

public class PersonRepositoryInMemory implements PersonRepository {
	private Map<String, Person> persons = new HashMap<String, Person>();
	
	public PersonRepositoryInMemory () throws NoSuchAlgorithmException, IOException {
		Person administrator = new Person("admin", "admin@ucll.be", "Ad", "Ministrator", 183, "t");
		add(administrator);
	}
	
	@Override
	public Person get(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		return persons.get(personId);
	}
	
	@Override
	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());	
	}

	@Override
	public void add(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		if (persons.containsKey(person.getUserid())) {
			throw new IllegalArgumentException("User already exists");
		}
		persons.put(person.getUserid(), person);
	}
	
	@Override
	public void update(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		if(!persons.containsKey(person.getUserid())){
			throw new IllegalArgumentException("No person found");
		}
		persons.put(person.getUserid(), person);
	}
	
	@Override
	public void delete(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		persons.remove(personId);
	}

	@Override
	public List<Person> sort() {
		// TODO Auto-generated method stub
		return null;
	}
}
