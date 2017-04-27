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

import netbooks.logiclayer.BookLogicImpl;
import netbooks.objectlayer.Book;


/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/PopulateSummary")
public class PopulateSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulateSummary() {
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
			List<Book> books = new ArrayList<Book>();
			books = BookLogicImpl.getBooksById(Integer.parseInt(request.getParameter("id")));
			for(int i = 0;  i  < books.size(); i++){
				System.out.println(books.get(i).getDesr());
			}
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(books, new TypeToken<List<Book>>() {}.getType());
			
			JsonArray json = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(json);
		
	}


}
