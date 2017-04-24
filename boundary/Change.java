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
		//in account.ftl file, put Change as the href for the waitlist link
		//which will direct to this servlet
		//generate ftl to edit waitlist
		//on the html pages to change password, etc, the forms should have action=Change
		//i think it's ok if those pages stay html but not 100% sure
		//buttons have corresponding names
		HttpSession sess = request.getSession();
		String user = (String)sess.getAttribute("username");
		if(request.getParameter("passwordbutton")!=null){
			//UserLogicImpl.setPassword(user,request.getParameter("password"));
		}
		else if(request.getParameter("addressbutton")!=null){
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zip = request.getParameter("zip");
			//UserLogicImpl.setAddress(user,address);
			//etc..
		}
		else if(request.getParameter("subscriptionbutton")!=null){
			//???
		}
		else if(request.getParameter("emailbutton")!=null){
			//UserLogicImpl.setEmail(user,request.getParameter("email"));
		}
		else{
			DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(db.build());
			String templateName = "waitlist.ftl";
			//get waitlist (List of Books) and put into root
			processor.runTemp(templateName,root,request,response);
		}
		
	}

}
