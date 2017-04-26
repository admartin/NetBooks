package netbooks.boundary;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import netbooks.logiclayer.BookLogicImpl;
import netbooks.logiclayer.UserLogicImpl;
import netbooks.objectlayer.Book;
import netbooks.objectlayer.User;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TemplateProcessor processor;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		processor = new TemplateProcessor(getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			validateLogin(request,response);

	}
	
	private void validateLogin(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try{
			HttpSession sess = request.getSession();
			if(sess != null){
				sess.invalidate();
				sess = request.getSession();
			}//create new session if a user was logged in prior
			List<User> ulist = UserLogicImpl.getUserForLogin(username);	

			 //if returned userlist from getUser sql call returned a user 
			 //and if returned password from userlist equals the provided password
			 //then everything is okay
            if(!((ulist.size() < 1)&&(password.equals(ulist.get(0).getPassword()) == false)))
            {   
            	sess.setAttribute("username", ulist.get(0).getUsername());
            	if(ulist.get(0).getSubscription() == 0)
            		sess.setAttribute("premium", false);
            	else
            		sess.setAttribute("premium", true);
            	showHomePage(request, response);
            }
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void showHomePage(HttpServletRequest request, HttpServletResponse response){
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "home.ftl";
		
		HttpSession sess = request.getSession();
		
		root.put("username", sess.getAttribute("username") );

		//get books by genre -scifi
			List<Book> scifi = BookLogicImpl.getBooksByGenre("Sci-Fi");
			root.put("scifi", scifi );
		//get books by genre -adventure
			List<Book> adven = BookLogicImpl.getBooksByGenre("Adventure");
			root.put("adven", adven );
		//get books by genre -drama
			List<Book> drama = BookLogicImpl.getBooksByGenre("Drama");
			root.put("drama", drama );
		//get books by genre -horror
			List<Book> horror = BookLogicImpl.getBooksByGenre("Horror");
			root.put("horror", horror );
		//get books by genre -romance
			List<Book> romance = BookLogicImpl.getBooksByGenre("Romance");
			root.put("romance", romance);
		
		//place variable detailing whether or not a user has premium
		root.put("premium", sess.getAttribute("premium") );
		
		processor.runTemp(templateName, root, request, response);
	}
	
	public void showRegistration(HttpServletRequest request,HttpServletResponse response){
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "register.ftl";
		processor.runTemp(templateName,root,request,response);
	}

}
