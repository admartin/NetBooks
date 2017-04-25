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
 * Servlet implementation class Settings
 */
@WebServlet("/Settings")
public class Settings extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TemplateProcessor processor;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Settings() {
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
		//in account.ftl file, put Settings?opt=password ie. as the href for the waitlist link
				//which will direct to this servlet
				//generate .ftls accordingly
		
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		HttpSession sess = request.getSession();

		String option = request.getParameter("opt");
		if(option.equals("password")){
			String templateName = "password.ftl";
			root.put("username",(String)sess.getAttribute("username"));
			processor.runTemp(templateName,root,request,response);
		}
		else if(option.equals("email")){
			String templateName = "email.ftl";
			root.put("username",(String)sess.getAttribute("username"));
			processor.runTemp(templateName,root,request,response);
		}
		else if(option.equals("address")){
			String templateName = "address.ftl";
			root.put("username",(String)sess.getAttribute("username"));
			processor.runTemp(templateName,root,request,response);
		}
		else if(option.equals("subscription")){
			String templateName = "subscription.ftl";
			root.put("username",(String)sess.getAttribute("username"));
			processor.runTemp(templateName,root,request,response);
		}
		else if(option.equals("waitlist")){
			String templateName = "waitlist.ftl";
			root.put("username",(String)sess.getAttribute("username"));
			processor.runTemp(templateName,root,request,response);
		}
	}

}
