package netbooks.boundary;

import java.io.IOException;

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

import netbooks.objectlayer.User;
import netbooks.logiclayer.UserLogicImpl;
import netbooks.logiclayer.BookLogicImpl;
import netbooks.objectlayer.Book;

import java.util.List;

/**
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TemplateProcessor processor;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account() {
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
		HttpSession sess = request.getSession(false);
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String user = (String)sess.getAttribute("username");
		boolean subscrip;
		
		List<Book> checkout = BookLogicImpl.getCheckedOutBooks(user);

		root.put("username", user);
		User userob = UserLogicImpl.getFullUserInfo(user).get(0);
		root.put("email",userob.getEmail());
		String address = userob.getAddress() + "<br/>" + userob.getCity() + ", " + userob.getState() + " " + userob.getZipcode();
		root.put("address",address);
		root.put("waitlist",BookLogicImpl.getWaitlistProfile(userob.getUsername()));
		
		if(userob.getSubscription() == 1){
			subscrip = true;
	    }
		else{
			subscrip = false;
		}

		root.put("premium",subscrip);
		root.put("checkedout",checkout);
		String templateName = "account.ftl";
		processor.runTemp(templateName,root,request,response);
	}

}
