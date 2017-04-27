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
import netbooks.objectlayer.Book;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TemplateProcessor processor;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOut() {
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
int id = Integer.parseInt(request.getParameter("book_id"));
		
		HttpSession sess = request.getSession();
		String username = (String)sess.getAttribute("username");
		BookLogicImpl.checkOutBook(username, id);
		System.out.println("i'm in checkout");
		
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "home.ftl";
		root.put("username", sess.getAttribute("username") );
		if((Boolean)sess.getAttribute("premium")){
			root.put("premium",true);
		}
		else{
			root.put("premium",false);
		}

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
		
		processor.runTemp(templateName, root, request, response);
	}

}
