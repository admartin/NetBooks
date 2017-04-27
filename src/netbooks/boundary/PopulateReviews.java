package netbooks.boundary;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import netbooks.logiclayer.ReviewLogicImpl;
import netbooks.objectlayer.Review;


/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/PopulateReviews")
public class PopulateReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulateReviews() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Review> reviews = new ArrayList<Review>();
			reviews = ReviewLogicImpl.selectReviews(Integer.parseInt(request.getParameter("id")));
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(reviews, new TypeToken<List<Review>>() {}.getType());
			
			JsonArray json = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(json);
		
	}


}
