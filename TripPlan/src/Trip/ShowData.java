package Trip;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowData extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ShowData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {	
			//CreateFile.Create();
			
			String division = request.getParameter("division");
			String city = request.getParameter("city");
			String id = request.getParameter("id");

			
			System.out.println(division);
		
			request.setAttribute("posti", Fuseki.GetPlaces(division,city));
			request.setAttribute("citta", Fuseki.GetInfoCity(city));
			CreateFile.Write(id);

			
			request.setAttribute("location", city);
			request.setAttribute("division", division);
			
			Cookie cookie1 = new Cookie("location", city);
			Cookie cookie2 = new Cookie("division", division);

			cookie1.setMaxAge(60 * 60);			
			response.addCookie(cookie1);
			cookie2.setMaxAge(60 * 60);			
			response.addCookie(cookie2);
			

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Place.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
