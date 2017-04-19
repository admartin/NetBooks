package netbooks.boundary;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.util.List;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Configuration cfg = null;

	private String templateDir = "/WEB-INF/templates";


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	public void init() {
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.25) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		cfg = new Configuration(Configuration.VERSION_2_3_25);

		// Specify the source where the template files come from.
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		// This handler outputs the stack trace information to the client, formatting it so 
		// that it will be usually well readable in the browser, and then re-throws the exception.
		//		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		// Specifies if TemplateException-s thrown by template processing are logged by FreeMarker or not. 
		//		cfg.setLogTemplateExceptions(false);
	}

	public void runTemplate(HttpServletRequest request, HttpServletResponse response) {

		Template template = null;
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());

		HttpSession sess = request.getSession(); 
		//root.put("username", sess.getAttribute("username") );

		//get books by genre -scifi
			//List<Books> scifi = BookLogicImpl.selectBooksByGenre("Sci-Fi");
			//root.put("scifi", scifi );
		//get books by genre -adventure
			//List<Books> adven = BookLogicImpl.selectBooksByGenre("Adventure");
			//root.put("adven", adven );
		//get books by genre -drama
			//List<Books> drama = BookLogicImpl.selectBooksByGenre("Drama");
			//root.put("drama", drama );
		//get books by genre -horror
			//List<Books> adven = BookLogicImpl.selectBooksByGenre("Horror");
			//root.put("horror", horror );
		//get books by genre -romance
			//List<Books> adven = BookLogicImpl.selectBooksByGenre("Romance");
			//root.put("romance", romance);
		
		//place variable detailing whether or not a user has premium
		//root.put("premium", sess.getAttribute("premium") );
		
		try {
			String templateName = "home.ftl";
			template = cfg.getTemplate(templateName );
			response.setContentType("text/html");
			Writer out = response.getWriter();
			template.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("finally")
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		boolean pass = true; //false;
		try {
			HttpSession sess = request.getSession(); 
			if(sess != null)
			{
				sess.invalidate();
				sess = request.getSession(true);
			}

			String password = (String) request.getAttribute("Password");
			String username = (String) request.getAttribute("Username");

			/*
			 * if returned userlist from getUser sql call returned a user 
			 * and if returned password from userlist equals the provided password
			 * then everything is okay
            if(!((userlist.size() < 1)&&(password.equals(userlist.get(0).getPassword()) == false)))
            {
            	sess.setAttribute("username", userlist.get(0).getUsername());
            	if(userlist.get(0).getSubscription() == 0)
            		sess.setAttribute("premium", false);
            	else
            		sess.setAttribute("premium", true);
            }
			 */
		}
		catch( Exception ce) {
			System.err.println( "Exception: " + ce );
			ce.printStackTrace();
		}

		if(pass)
		{
			runTemplate(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			login(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
