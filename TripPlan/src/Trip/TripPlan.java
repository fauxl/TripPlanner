package Trip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TripPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public TripPlan() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			String trip = request.getParameter("plan");

			if(trip!=null && trip.length() != 0) {
			Collection<String> list = (Collection<String>) CreateFile.Read();


			    String[] arrSplit = trip.split(";");
			    for (int i=0; i < arrSplit.length; i++)
			    {
			    	list.add(arrSplit[i]);
			      //System.out.println(arrSplit[i]);
			    } 
			    
			    if (list  != null && list.size() != 0) {
					Iterator<?> itp = list.iterator();
						System.out.print(list.size());
					while (itp.hasNext()) {					
						String beano = (String) itp.next();
						if (beano!=null) {
							if(!beano.isBlank())
						CreateFile.Write(beano);
					}

					  }
			  }}
			
			Collection<String> lista = (Collection<String>) CreateFile.Read();
			  Collection<CulturalPlace> posti = new LinkedList<CulturalPlace>();
						  
				String rimuovi = request.getParameter("rimuovi");
				Collection<String> postinew=null;
			  if(!rimuovi.equalsIgnoreCase("false")) {
					if(CreateFile.Check(rimuovi)) {
						
				 postinew = (Collection<String>) CreateFile.Delete(rimuovi);
					  if (postinew  != null && postinew .size() != 0) {
							Iterator<?> itp = postinew.iterator();
							System.out.println("here");
								
							while (itp.hasNext()) {
								String beano = (String) itp.next();
								if (beano!=null) {
								CreateFile.Write(beano);
							}

							  }
					  }
				}
					
					 if (postinew != null && postinew.size() != 0) {
							Iterator<?> ito = postinew.iterator();
							
							while (ito.hasNext()) {
								String beano = (String) ito.next();
								if (beano!=null) {
	//							System.out.println(beano);
								posti.add(Fuseki.GetPlace(beano));
//								System.out.println(Fuseki.GetPlace(beano));
								}
							}

							  }			
			  }else {
			  
			  
			  if (lista != null && lista.size() != 0) {
			Iterator<?> ito = lista.iterator();
			while (ito.hasNext()) {
				String beano = (String) ito.next();
				if (beano!=null) {
				//System.out.println(beano);
				posti.add(Fuseki.GetPlace(beano));
				//System.out.println(Fuseki.GetPlace(beano));
				}
			}

			  }			
			  }
			  request.setAttribute("posti", posti);
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/myTrip.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
