package domain;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private int length;
	
	public Person(String personId, String email, String firstName, String lastName, int length, String password) throws NoSuchAlgorithmException, IOException 
	{
		setUserid(personId);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setLength(length);
	}

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty() || userid == null){
			throw new IllegalArgumentException("No userid given");
		}
		this.userid = userid;
	}

	public void setEmail(String email) {
		if(email.isEmpty() || email == null){
			throw new IllegalArgumentException("No email given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email;
	}

	
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean isCorrectPassword(String password) throws NoSuchAlgorithmException, IOException {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		return this.getPassword().equals(password);
	}

	public void setPassword(String password) throws NoSuchAlgorithmException, IOException {
		if(password.isEmpty() || password == null){
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty() || firstName == null){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty() || lastName == null){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}

	public void setLength(int length) {
		if(length <= 0 || length > 250)
		{
			throw new IllegalArgumentException("Impossible length given");
		}
		this.length = length;		
	}
	
	public int getLength() {
		return this.length;
	}
	
	//string to byte array conversion
	//String salt = new BigInteger(1, seed).toString(16); 
	//byte[] seed = salt.getBytes("UTF-8");
}
