package Trip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetPlace extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public GetPlace() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
						
			
			String id = request.getParameter("id");
			String posto = request.getParameter("posto");
			String discipline = request.getParameter("disciplina");
			String location = request.getParameter("location");
			String division = request.getParameter("division");
			String aggiungi = request.getParameter("aggiungi");

			if(!aggiungi.equalsIgnoreCase("false")) {
				if(CreateFile.Check(aggiungi)) {
			CreateFile.Write(aggiungi);}
			}
		
			System.out.println(discipline);
			request.setAttribute("posto", Fuseki.GetPlace(id));
			request.setAttribute("posti", Fuseki.GetCorrelati(posto, discipline, location, division));
			request.setAttribute("eventi", Fuseki.GetEventsQuery(posto));

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SingoloMonumento.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
