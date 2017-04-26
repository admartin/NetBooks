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
import netbooks.logiclayer.ReviewLogicImpl;
import netbooks.objectlayer.Book;

/**
 * Servlet implementation class AddReview
 */
@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	TemplateProcessor processor;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReview() {
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
		String review = request.getParameter("review");
		int bookid = Integer.parseInt(request.getParameter("book_id_review"));
		//added hidden input val to review form with name book_id_review
		HttpSession sess = request.getSession();
		
		//createAddReview needs to not have id as a param, as there's no way to get id until it's added to the DB
		ReviewLogicImpl.createReview(0,0,review,bookid,(String)sess.getAttribute("username"));
		//there's also no way to get a rating from home.ftl as of yet
		
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
