package netbooks.boundary;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import netbooks.logiclayer.BookLogicImpl;
import netbooks.objectlayer.Book;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Configuration cfg = null;

	private String templateDir = "/WEB-INF/templates";


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
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
		root.put("username", sess.getAttribute("username"));
		root.put("premium", sess.getAttribute("premium"));

		String search = request.getParameter("search");
		
		//get books by searched value
		//first try title
		List<Book> byTitle = BookLogicImpl.getBooksByTitle(search);
		List<Book> byAuthor = BookLogicImpl.getBooksByAuthor(search);
		
		byTitle.addAll(byAuthor);
		
		if(byTitle.size() > 0){ 
			root.put("books",byTitle);
			try {
				String templateName = "search.ftl";
				template = cfg.getTemplate(templateName );
				response.setContentType("text/html");
				Writer out = response.getWriter();
				template.process(root, out);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}//if results
		else{
			try {
				String templateName = "notfound.ftl";
				template = cfg.getTemplate(templateName );
				response.setContentType("text/html");
				Writer out = response.getWriter();
				template.process(root, out);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}//else go to err page
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		runTemplate(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
