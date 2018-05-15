package db;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Person;

public class PersonRepositoryInDB implements PersonRepository{
	private PreparedStatement statement; 
	private Connection connection;

	public PersonRepositoryInDB() {
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
	public Person get(String personId){
		Person person = null; 
		try {
			String sql = "SELECT userid, email, firstname, lastname, length, password FROM r0623352_web3_test.person where userid = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, personId);
			ResultSet result = statement.executeQuery(); 
			result.next();
			String firstName = result.getString("firstName"); 
			String password = result.getString("password");
			String lastName = result.getString("lastname");
			String email = result.getString("email");
			int length = result.getInt("length");
			person = new Person(personId, email, firstName, lastName, length, password); 
		}catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return person;
	}
	
	@Override
	public List<Person> getAll(){
		ArrayList<Person> persons = new ArrayList<Person>();
		Person person; 
		try {
			String sql = "SELECT * FROM r0623352_web3_test.person";
			statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery(); 
			while(result.next())
			{
				String personId = result.getString("userid");
				String firstName = result.getString("firstname"); 
				String password = result.getString("password");
				String lastName = result.getString("lastname");
				String email = result.getString("email");
				int length = result.getInt("length");
				person = new Person(personId, email, firstName, lastName, length, password);
				persons.add(person);
			}
		}catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage(), e); 
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return persons;
	}

	@Override
	public void add(Person person){
		if(person == null)
		{ 
			throw new DbException("Can not add non-existing person"); 
		} 
		String sql = "INSERT INTO r0623352_web3_test.person (userid, email, firstname, lastname, length, password) VALUES (?, ?, ?, ?, ?, ?)"; 
		try { 
			statement = connection.prepareStatement(sql);
			statement.setString(1, person.getUserid());
			statement.setString(2, person.getEmail());
			statement.setString(3, person.getFirstName());
			statement.setString(4, person.getLastName());
			statement.setInt(5, person.getLength());
			statement.setString(6, person.getPassword());
			statement.executeQuery();
		} catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage()); 
		}
	}
	
	@Override
	public void update(Person person){
		if(person == null)
		{ 
			throw new DbException("Can not update non existing person"); 
		} 
		String sql = "Update r0623352_web3_test.person (userid, email, firstname, lastname, length, password) VALUES (?, ?, ?, ?, ?, ?)"; 
		try { 
			statement = connection.prepareStatement(sql);
			statement.setString(1, person.getUserid());
			statement.setString(2, person.getEmail());
			statement.setString(3, person.getFirstName());
			statement.setString(4, person.getLastName());
			statement.setInt(5, person.getLength());
			statement.setString(6, person.getPassword());
			statement.executeQuery();
		} catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage(), e); 
		}
	}
	
	@Override
	public void delete(String personId){
		//Person person; 
		try {
			String sql = "DELETE FROM r0623352_web3_test.person WHERE UserID = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, personId);
			ResultSet result = statement.executeQuery(); 
			result.next(); 
			/*String firstName = result.getString("FirstName"); 
			String password = result.getString("Password");
			String lastName = result.getString("LastName");
			String email = result.getString("Email");
			person = new Person(personId, email, firstName, lastName, password);*/
		}catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage(), e); 
		} 
	}
	
	@Override
	public List<Person> sort()
	{
		ArrayList<Person> persons = new ArrayList<Person>();
		Person person; 
		try {
			String sql = "SELECT userid, email, firstname, lastname, length, password FROM r0623352_web3_test.person ORDER BY firstname;";
			statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery(); 
			while(result.next())
			{
				String personId = result.getString("userid");
				String firstName = result.getString("firstname"); 
				String password = result.getString("password");
				String lastName = result.getString("lastname");
				String email = result.getString("email");
				int length = result.getInt("length");
				person = new Person(personId, email, firstName, lastName, length, password);
				persons.add(person);
			}
		}catch (SQLException e) 
		{ 
			throw new DbException(e.getMessage(), e); 
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return persons;
	}
	
	@Override
	protected void finalize() throws Throwable {
		connection.close();
		super.finalize();
	}
	/*4. In	the	package	ÅgdbÅh,	create	a	class	containing	a	constructor	in	which	you	connect	to	the	database	and	initialize	a	statement;	
	5. In	this	class,	add	methods	with	the	same	method	signature	as	your	inmemory	database	class,	
	but	instead	of	using	a	map	you	will	connect	to	the	real	database.	Provide:	
		a. a	method	in	which	you	add	a	given	person	to	the	database; done	
		b. a	method	to	retrieve	a	person	from	the	database,	based	on	a	given	id	done	
		c. a	method	to	retrieve	all	persons	from	the	database done	
		d. a	method	to	delete	a	person	from	the	database,	based	on	a	given	id	done	
		e. a	method	to	update	a	given	person	in	the	database	done	
	6. In	ÅgShopServiceÅh,	use	the	class	you	just	created	instead	of	the	inmemory	database	class; done	
	7. Run	your	project.	If	everything	went	right,	your	application	is	still	working,	but	the	person-data	are	permanently	stored	in	the	database.		
	8. Run	the	testclass. find a way to delete all users
	9. In the same way create a table and database for products*/
}