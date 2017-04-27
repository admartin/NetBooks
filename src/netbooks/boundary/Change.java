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
 * Servlet implementation class Change
 */
@WebServlet("/Change")
public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TemplateProcessor processor;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Change() {
        super();
        // TODO Auto-generated constructor stub
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
		HttpSession sess = request.getSession();
		String user = (String)sess.getAttribute("username");
		if(request.getParameter("passwordbutton")!=null){
			UserLogicImpl.updatePassword(user,request.getParameter("password"));
		}
		else if(request.getParameter("addressbutton")!=null){
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			int zip = Integer.parseInt(request.getParameter("zip"));
			UserLogicImpl.updateAddress(user,address,city,state,zip);
		}
		else if(request.getParameter("subscriptionbutton")!=null){
			if (request.getParameter("optradio").equals("base")) {
				UserLogicImpl.updateSub(user, 0);
			}
			else {
				UserLogicImpl.updateSub(user, 1);
			}
		}
		else if(request.getParameter("emailbutton")!=null){
			UserLogicImpl.updateEmail(user,request.getParameter("email"));
		}
		
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
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
			sess.setAttribute("premium", true);
	    }
		else{
			subscrip = false;
			sess.setAttribute("premium", false);
		}

		root.put("premium",subscrip);
		root.put("checkedout",checkout);
		String templateName = "account.ftl";
		processor.runTemp(templateName,root,request,response);
		
	}
}