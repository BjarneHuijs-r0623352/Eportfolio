

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Person;
import domain.Product;
import service.ShopServiceTest;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ShopServiceTest list = new ShopServiceTest();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException
	{
		String destination;
		String action = request.getParameter("action");
		if(action == null)
		{
			action = "";
		}
		switch(action)
		{
			case "overview":
				destination = getUserOverview(request, response);
				break;
			case "SignUp":
				destination = SignUpPerson(request, response);
				break;
			case "products":
				destination = getProductOverview(request, response);
				break;
			case "AddProduct":
				destination = AddProduct(request, response);
				break;
			case "UpdateProduct":
				destination = updateProduct(request, response);
				break;
			case "ConfirmProductUpdate":
				destination = confirmProductUpdate(request, response);
				break;
			case "DeletePerson":
				destination = deletePerson(request, response);
				break;
			case "DeletePersonConfirmed":
				destination = deletePersonConfirmed(request, response);
				break;
			case "DeleteProduct":
				destination = deleteProduct(request, response);
				break;
			case "DeleteProductConfirmed":
				destination = deleteProductConfirmed(request, response);
				break;
			case "CheckPassword":
				destination = checkPassword(request, response);
				break;
			case "PasswordConfirmed":
				destination = passwordConfirmed(request, response);
				break;
			case "SortPersons":
				destination = sortPersons(request, response);
				break;
			default:
				destination = "index.jsp";
		}
		
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}
	
	private String sortPersons(HttpServletRequest request, HttpServletResponse response) {
		String destination = "personOverview.jsp";
		request.setAttribute("allPersons", list.getPersonsSorted());
		return destination;
	}

	private String getUserOverview(HttpServletRequest request, HttpServletResponse response) {
		String destination = "personOverview.jsp";
		request.setAttribute("allPersons", list.getPersons());
		return destination;
	}
	
	private String getProductOverview(HttpServletRequest request, HttpServletResponse response) {
		String destination = "productOverview.jsp";
		request.setAttribute("allProducts", list.getProducts());
		return destination;
	}
	
	private String SignUpPerson(HttpServletRequest request, HttpServletResponse response) {
		String destination = "";
		
		String userID = request.getParameter("userid");
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String length = request.getParameter("length");
		Person person = new Person();
		List<String> error = new ArrayList<String>();
		try {
			person.setUserid(userID);
		}catch (Exception e){
			error.add(e.getMessage());
		}
		
		try {
			person.setEmail(email);;
		}catch (Exception e){
			error.add(e.getMessage());
		}
		
		try {
			person.setFirstName(firstName);
		}catch (Exception e){
			error.add(e.getMessage());
		}
		
		try {
			person.setLastName(lastName);
		}catch (Exception e){
			error.add(e.getMessage());
		}
		
		try {
			person.setLength(Integer.parseInt(length));
		}catch (Exception e){
			error.add(e.getMessage());
		}
		
		try {
			person.setPassword(password);
		}catch (Exception e){
			error.add(e.getMessage());
		}
		if(error.size() == 0){
			try{
				list.addPerson(person);
			}catch (Exception e){
				error.add(e.getMessage());
			}
			
			destination = "index.jsp";
		}
		
		if(error.size() > 0){	
			request.setAttribute("errors", error);
			request.setAttribute("ID", userID);
			request.setAttribute("Email", email);
			request.setAttribute("FirstName", firstName);
			request.setAttribute("LastName", lastName);
			request.setAttribute("Length", length);
			request.setAttribute("Password", password);
			destination = "signUp.jsp";
		}		
		
		return destination;
	}
	
	private String AddProduct(HttpServletRequest request, HttpServletResponse response) {
		String destination = "";
		
		String ProductID = request.getParameter("productid");
		String description = request.getParameter("description");
		Double price = Double.parseDouble(request.getParameter("price"));
		Product product = new Product();
		List<String> error = new ArrayList<String>();
		
		try {
			product.setProductId(ProductID);
		}catch (Exception e){
			error.add(e.getMessage());
		}
		
		try {
			product.setDescription(description);
		}catch (Exception e){
			error.add(e.getMessage());
		}
		
		try {
			product.setPrice(price);
		}catch (Exception e){
			error.add(e.getMessage());
		}
				
		if(error.size() <= 0){
			try{
				list.addProduct(product);
			}catch (Exception e){
				error.add(e.getMessage());
			}

			destination = getProductOverview(request, response);
			
		}else{
			destination = "addProduct.jsp";
			request.setAttribute("ID", ProductID);
			request.setAttribute("Description",description);
			request.setAttribute("Price", price);
			request.setAttribute("errors", error);
		}		

		return destination;
	}
	
	private String updateProduct (HttpServletRequest request, HttpServletResponse response)
	{
		String destination = "updateProduct.jsp";
		request.setAttribute("action", request.getParameter("action"));
		request.setAttribute("ID", request.getParameter("ID"));
		request.setAttribute("Description", request.getParameter("Description"));
		request.setAttribute("Price", request.getParameter("Price"));
		return destination;
	}
	
	private String confirmProductUpdate(HttpServletRequest request, HttpServletResponse response)
	{
		String destination = "";
		//list.deleteProduct(request.getParameter("oldProductId"));
		Product product = new Product(request.getParameter("productid"), request.getParameter("description"), Double.parseDouble(request.getParameter("price")));
		list.updateProduct(product);
		//destination = AddProduct(request, response);
		destination = getProductOverview(request, response);
		return destination;
	}
	
	private String deletePerson(HttpServletRequest request, HttpServletResponse response)
	{
		String destination = "deletePersonConfirmation.jsp";
		request.setAttribute("personID", request.getParameter("personID"));
		return destination;
	}
	
	private String deletePersonConfirmed(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("personID", request.getParameter("personID"));
		if(request.getParameter("submit").equals("Delete"))
		{
			list.deletePerson(request.getParameter("personID"));
		}
		String destination = getUserOverview(request, response);
		return destination;
	}
	
	private String deleteProduct(HttpServletRequest request, HttpServletResponse response)
	{
		String destination = "deleteProductConfirmation.jsp";
		request.setAttribute("productID", request.getParameter("productID"));
		return destination;
	}
	
	private String deleteProductConfirmed(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("productID", request.getParameter("productID"));
		if(request.getParameter("submit").equals("Delete"))
		{
			list.deleteProduct(request.getParameter("productID"));
		}
		String destination = getProductOverview(request, response);
		return destination;
	}
	
	private String checkPassword(HttpServletRequest request, HttpServletResponse response)
	{
		String destination = "checkPassword.jsp";
		request.setAttribute("personID", request.getParameter("personID"));
		return destination;
	}
	
	private String passwordConfirmed(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, IOException
	{
		String destination = "passwordChecked.jsp";
		String id = request.getParameter("personID");
		Person person = list.getPerson(id);
		if(person.isCorrectPassword(request.getParameter("password")))
		{
			request.setAttribute("Check", "The password is correct");
		}else
		{
			request.setAttribute("Check", "The password is NOT correct");
		}
		return destination;
	}
}
