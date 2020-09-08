package Trip;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public GetPlan() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		

			int prenotazione = Integer.parseInt(request.getParameter("pren"));
			String servizi = request.getParameter("servizi");
			String discipline = request.getParameter("disciplina");
			String servizi2= request.getParameter("servizi2");
			String discipline2 = request.getParameter("disciplina2");
			String division = request.getParameter("div");
			String location = request.getParameter("loca");


			int number = Integer.parseInt(request.getParameter("number"));

			System.out.println(location);
			System.out.println(division);

			System.out.println(discipline2);
			
			
			request.setAttribute("posti",  Fuseki.GetTripPlan(prenotazione, servizi, servizi2,  discipline,
	    			discipline2,number,location, division));

					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Trip.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
