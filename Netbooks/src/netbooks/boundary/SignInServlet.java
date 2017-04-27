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

import netbooks.logiclayer.UserLogicImpl;
import netbooks.objectlayer.User;
import netbooks.objectlayer.Book;
import netbooks.logiclayer.BookLogicImpl;

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
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if(request.getParameter("login") != null){
					validateLogin(request,response);
			}//if came here by login
			else if(request.getParameter("user") != null){
				showHomePage(request,response,request.getParameter("user"));
			}//if came here by homepage link

	}
	
	private void validateLogin(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try{
			HttpSession sess = request.getSession();
			if(sess != null){
				sess.invalidate();
				sess = request.getSession(true);
			}//create new session if a user was logged in prior
			synchronized(sess){
			//check if pass is valid
				List<User> ulist = UserLogicImpl.getUserForLogin(username);	
				if(ulist == null || !password.equals(ulist.get(0).getPassword())){
					DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(db.build());
					root.put("error","Invalid login information.");
					String templateName = "error.ftl";
					root.put("error","Invalid login.");
					processor.runTemp(templateName,root,request,response);
				}
				else{
					sess.setAttribute("username",username);
	            	if(ulist.get(0).getSubscription() == 0)
	            		sess.setAttribute("premium", false);
	            	else
	            		sess.setAttribute("premium", true);
	            	}
					showHomePage(request,response,username);
				}		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void showHomePage(HttpServletRequest request, HttpServletResponse response,String username){
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "home.ftl";
		HttpSession sess = request.getSession(false);
		
		
		root.put("username", username);
		if((Boolean)sess.getAttribute("premium")){
			root.put("premium",true);
		}
		else{
			root.put("premium",false);
		}

		//get books by genre -scifi
		List<Book> scifi = BookLogicImpl.getBooksByGenre("Sci-Fi");
		for(int i =0; i < scifi.size(); i++){
			System.out.println(scifi.get(i).getID());
		}
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
		
		processor.runTemp(templateName, root, request, response);
	}
	

}
