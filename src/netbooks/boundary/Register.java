package netbooks.boundary;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netbooks.logiclayer.UserLogicImpl;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
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
		boolean valid = true;
		String[] info = new String[10];
		info[0] = request.getParameter("display_name");
		info[1] = request.getParameter("password");
		info[2] = request.getParameter("email");
		info[3] = request.getParameter("first_name");
		info[4] = request.getParameter("last_name");
		info[5] = request.getParameter("birth_date");
		info[6] = request.getParameter("address");
		info[7] = request.getParameter("city");
		info[8] = request.getParameter("state");
		info[9] = request.getParameter("zip");
		String subscription = request.getParameter("optradio");

		int subscrip;
		
		if(subscription.equals("base")){
			subscrip = 0;
		}
		else{
			subscrip = 1;
		}

		if(UserLogicImpl.getUserForLogin(info[0]) != null){
			response.sendRedirect("register.html");
		}
		else{
			UserLogicImpl.createUser(info[0],info[1],info[3],info[4],info[5],info[6],info[7],info[8],Integer.parseInt(info[9]),subscrip,info[2]);
			response.sendRedirect("index.html");
		}
		 

	}

}
