package Trip;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public GetEvent() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			String id = request.getParameter("id");
		System.out.println(id);
			request.setAttribute("evento", Fuseki.GetEvent(id));
			

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SingoloEvento.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
