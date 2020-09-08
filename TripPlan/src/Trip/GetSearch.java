package Trip;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Action;


public class GetSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public GetSearch() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String name = request.getParameter("searchbar");
	
			if (name != null) {
				System.out.print(name);
			
			String discipline = request.getParameter("disciplina");
			String division = request.getParameter("division");
			String city = request.getParameter("city");
			String orderby = request.getParameter("orderby");
			String criterio = request.getParameter("order");

	
			Cookie[] cookies = request.getCookies();

			String cookieName = "location";
			String cookieName2 = "division";

			for ( int i=0; i<cookies.length; i++) {

			    Cookie cookie = cookies[i];

			    if (cookieName.equals(cookie.getName())){
			    	cookie.setMaxAge(-1);}
			    else  if(cookieName2.equals(cookie.getName())){
			    	cookie.setMaxAge(-1);}

			  }
			
			Cookie cookie1 = new Cookie("location", city);
			Cookie cookie2 = new Cookie("division", division);

			cookie1.setMaxAge(60 * 60);			
			response.addCookie(cookie1);
			cookie2.setMaxAge(60 * 60);			
			response.addCookie(cookie2);
			
			
			if(discipline.equalsIgnoreCase("eventi")) {
				request.setAttribute("eventi", Fuseki.GetEventsSearch(name,discipline,division,city,orderby,criterio));
				System.out.println("lol");
			} else
				request.setAttribute("posti", Fuseki.GetSearch(name,discipline,division,city,orderby,criterio));

				} else {
					
				}
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Ricerca.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
