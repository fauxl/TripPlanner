<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> posti = (Collection<?>) request.getAttribute("posti");
   String location= (String) request.getAttribute("location");
   String division= (String) request.getAttribute("division");

	City city = (City) request.getAttribute("citta");


%>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,Trip.*"%>

<html>
<head>
<title>TripPlanner</title>

<meta name="author" content="Gerardo De Rosa">
<meta name="keywords" content="De Rosa, Gerardo, MetatraderToBinance">
<meta name="description" content="MetatraderToBinance application">
<meta name="viewport"
	content="initial-scale=1,user-scalable=no,maximum-scale=1,width=device-width" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="https://fonts.googleapis.com/css2?family=Amiko&display=swap"
	rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>


<script src=" https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>


<script src="https://kit.fontawesome.com/410e5bdac4.js"
	crossorigin="anonymous"></script>

<link rel='stylesheet' type='text/css' href='style.css' />
<link rel="shortcut icon" href="Images/logo.png" type="image/png">
<link rel="icon" href="Images/logo.png" type="image/png">


</head>


<script type="text/javascript">
function caricaMappaCoordinat(div, x) {
	// node e div rappresentano il bottone "Mappa" 
	var div = document.getElementById("bottone_mappa1");
	var node = div.parentNode;
	div.style.display = "none";
	
	// Creo il frame con la mappa e assegno le proprietà
	var mappa = document.createElement('iframe');
	var url = 'https://maps.google.com/maps?;hl=it&q=@'+ x +'&ie=UTF8&t=&z=9&iwloc=B&output=embed';
	mappa.setAttribute('src', url);
	mappa.style.height = "45%";
	mappa.style.width = "92%";
	mappa.style.border = "0px";
	mappa.style.margin = "1% 4% 0px 4%";
	mappa.style.borderRadius = "8px";
	mappa.style.boxShadow = "0px 1px 6px #A9A9A9";
	
	node.appendChild(mappa);
}
</script>
<script>

	function caricaMappaCoordinate(div, x, y) {
		
		// node e div rappresentano il bottone "Mappa" 
		var node = div.parentNode;
		div.style.display = "none";
		
		// Creo il frame con la mappa e assegno le proprietà
		var mappa = document.createElement('iframe');
		var url = 'https://maps.google.com/maps?;hl=it&q=@'+x+','+y+'&ie=UTF8&t=&z=14&iwloc=B&output=embed';
		mappa.setAttribute('src', url);
		mappa.style.height = "240px";
		mappa.style.width = "100%";
		mappa.style.border = "0px";
		mappa.style.margin = "7px 0px 0px 0px";
	
		mappa.style.borderRadius = "8px";
		mappa.style.boxShadow = "0px 1px 6px #A9A9A9";
		
		node.appendChild(mappa);
	}
	
	function mostraDettagli(div){
		var dettagli = div.querySelector(".descriz");
		
		if(dettagli.style.display == "inline"){
			dettagli.style.display = "none";
			}
		else{ 
			dettagli.style.display = "inline" 
			}
		}
	
	
	function caricaMappaIndirizzo(div, x){
		var node = div.parentNode;
		div.style.display = "none";
		var mappa = document.createElement('iframe');
		var url = x;
		mappa.setAttribute('src', url);
		node.appendChild(mappa);
	}
	
</script>



<body onload="caricaMappaCoordinat(this,'<%=location%>')">

	<script src="{{url_for('static', filename='app.js')}}"></script>


	<div id="backgroundpages">

		<%@ include file="navbar.jsp"%>

		
		
		   <script>
			function mostra(id) {
			 y = "for"+id
			 c = "for"+(id-1)
				console.log(p);
			 var g =  document.getElementById("triplan");
				 var x = document.getElementById(y);
				 var p = document.getElementById(c);
				 var h =  document.getElementById("fortuna");

					x.style.display = "block";
					 h.style.margin ="4% 0% 0% 0%";

					 g.style.display = "none";
				 p.style.display = "none";

			console.log(y);
				
			}
		</script>
		
		 <script>
			function mostra(id) {
			 y = "for"+id
			 c = "for"+(id-1)
			 q = "for"+(eval(id)+1)
			 
				console.log(q);
			 var g =  document.getElementById("triplan");
				 var x = document.getElementById(y);
				 var h =  document.getElementById("fortuna");
				 var i =  document.getElementById(q);
				 var p = document.getElementById(c);

					x.style.display = "block";
					 h.style.margin ="4% 0% 0% 0%";
					 g.style.display = "none";
				 p.style.display = "none";
					i.style.display = "none";
				
			}
		</script>
		
		 <script>
			function remove(id) {
				
			
			 y = "for"+id
			 q = "for"+(eval(id)+1)
			 
				console.log(q);
				 var x = document.getElementById(y);
				 var i =  document.getElementById(q);
					if(id==0){
						 var g =  document.getElementById("triplan");
						 var h =  document.getElementById("fortuna");

						 i.style.display = "none";
						 g.style.display = "block";					 
						 h.style.margin ="0% 0% 0% 0%";

				}
					x.style.display = "block";
					i.style.display = "none";
				
			}
		</script>

		<div id="logopages">
			<a href="http:\\127.0.0.1:80\home"><img src="Images/logo.png"></a>
			<h1><%=location%></h1>
		</div>
	</div>
	
	<% if(city!=null){ %>

		<div id="cultural_place_singolo">

			<div id="parte_sinistra">
			<div id="bandiera">
				<img id="foto_posto1"
					onerror="this.src='https://www.arciserviziocivile.it/vicenza/wp-content/uploads/sites/2/musei.jpg';"
					src=<%=city.getImmagine()%>> <br>
</div>

			<br>
				<div id="descrizione_posto">
					<div id="grassetto">Nome:</div>
					<%=location%>

					<br>
		

					<br>

					<%
						if (!(city.getWebsite().isEmpty() || city.getWebsite().isBlank())) {
					%>
					<div id="grassetto">Sito web:</div>
					<a href=<%=city.getWebsite()%>><%=city.getWebsite()%></a> <br><br>
					
					<%
						}
					%>

				</div>
			</div>

			<div id="linea_verticale"></div>

			<div id="parte_restante">
					<div  id="dettagli1">
						<div id="dettagli_posto1" class="descriz">
							<div id="testo_descrizione1">
									<div id="grassetto">Descrizione:</div>
								<%=city.getDescrizione()%>
							</div>
												<div class="button" id="bottone_mappa1">
							
						</div>
					</div>
			</div>


	</div>

		</div>
	
	<%
						}
					%>
	<br>
	<div id="triplan">
	
	<p id="labelt">Clicca su "Crea il tuo Trip Plan" per iniziare ad aggiungere luoghi da visitare al tuo TripPlan!<br><br>
	Oppure clicca su "Mi sento Fortunato", se vuoi farti consigliare da noi, in base alle tue esigenze, un Trip Plan!</p>
	
	<a href="http://localhost:8080/TripPlan/Search"><button>Cerca un posto</button></a>
	<button onclick="mostra('1')">Mi sento Fortunato</button>
	
	</div>
	
	<div id="fortuna">
	
	  <form method="get" action="/TripPlan/Trip">

	
<div class="domande" id="for1"><p id="des">Saresti disponibile a prenotare la tua visita?   </p>
	<p id="check">
	    <label><input type="checkbox" class="radio" value="1" name="pren" />Si</label>
	    	    <label><input type="checkbox" class="radio" value="2" name="pren" />No</label>
	    	   <label><input type="checkbox" class="radio" value="3" name="pren" />Indifferente</label>
	    	    
	    </p>

	<p id="next"  onclick="remove('0')">Previous</p>
	<p id="next"  onclick="mostra('2')">Next</p>
	</div>	
		
<div class="domande" id="for2"><p id="des">Quale servizio ti piacerebbe venisse offerto? </p>
	<p id="check">
	    <select name="servizi"
					id="disc"  >
					<option selected disabled>Disciplina</option>
					<option>Tutti</option>
					<option>Archivio</option>
					<option>Fototeca</option>
					<option>Percorsi segnalati</option>
					<option>Laboratori</option>
				

				</select>
		   <select name="servizi2"
					id="disc"  >
					<option selected disabled>Disciplina</option>
					<option>Tutti</option>
					<option>Archivio</option>
					<option>Fototeca</option>
					<option>Percorsi segnalati</option>
					<option>Laboratori</option>
				

				</select>		
	    </p>
	    
	<p id="next"  onclick="remove('1')">Previous</p>
	<p id="next"  onclick="mostra('3')">Next</p>
	</div>	
	
<div class="domande" id="for3"><p id="des">Quali sono le discipline artistiche che più ti intrigrano tra queste (Scegline due differenti)? </p>
	<p id="check">
	    <select name="disciplina"
					id="disc"  >
					<option selected disabled>Disciplina</option>
					<option>Tutti</option>
					<option>Arte</option>
					<option>Archeologia</option>
					<option>Storia</option>
					<option>Etnografia</option>
					<option>Scienza</option>
					<option>Altro</option>

				</select>
				
		 <select name="disciplina2"
					id="disc"  >
					<option selected disabled>Disciplina</option>
					<option>Tutti</option>
					<option>Arte</option>
					<option>Archeologia</option>
					<option>Storia</option>
					<option>Etnografia</option>
					<option>Scienza</option>
					<option>Altro</option>

				</select>		
	    </p>
	    
	<p id="next"  onclick="remove('2')">Previous</p>
	<p id="next"  onclick="mostra('4')">Next</p>
	</div>
			
<div class="domande" id="for4"><p id="des">Quanti luoghi culturali vorresti visitare durante la tua vacanza (Formato Numerico)? </p>
	<p id="check">
	    <label><input type="text"  name="number" /></label>
	    </p>

	<p id="next"  onclick="remove('3')">Previous</p>
	
	<label id="td"><input type="text"  name="loca" value=<%=location %> /></label>
	<button type="submit" name="div" value=<%=division%>
	 id="fortunato">Clicca per ottenere i risultati</button>
	
	</div>
		
	</form>
	
	</div>
	
	
	<%if (posti!=null && posti.size() != 0){ %>
	
	<div id="contenuti">
		<h3>Luoghi suggeriti:</h3>

	<div id="spazio_contenuti">

		<%
			int y = 0;
		if (posti != null && posti.size() != 0) {
			Iterator<?> it = posti.iterator();
			while (it.hasNext()) {
				CulturalPlace bean = (CulturalPlace) it.next();
			bean.getTipo();
		%>

		<div id="cultural_place">
		<a href="single?id=<%=bean.getCode()%>&disciplina=<%=bean.getTipo().replace("@it", "")%>
		&location=<%=location%>&division=<%=division%>&aggiungi=false&posto=<%=bean.getNome().replace("@it", "")%>"><img id="foto_posto" 
				onerror="this.src='https://www.arciserviziocivile.it/vicenza/wp-content/uploads/sites/2/musei.jpg';"
				src=<%=bean.getImmagine()%>> </a><br>

			<div id="descrizione_posto">
				<div id="grassetto">Nome:</div>
				<%=bean.getNome()%>
				<br>
				<div id="grassetto">Indirizzo - Provincia - Regione</div>
				<%=bean.getAddress()%>
				|
				<%=bean.getProvincia()%>
				|
				<%=bean.getRegione()%>
				<br>

				<%
					if (!(bean.getWebsite().isEmpty() || bean.getWebsite().isBlank())) {
				%>
				<div id="grassetto">Sito web:</div>
				<a href=<%=bean.getWebsite()%>><%=bean.getWebsite()%></a> <br><br>
				<%
					}
				%>

			</div>

			<br> 
			
			<a href="Place?city=<%=location%>&division=<%=division%>&id=<%=bean.getCode()%>">
				<div class="button" id="bottone_aggiungi_trip" onclick="addplace('<%=bean.getCode()%>')">
					<i class="fas fa-check"> </i> Aggiungi al Trip
				</div>
			</a> 
			
			<a>
				<div class="button" onclick="mostraDettagli(this)" id="bottone_dettagli">
					<i class="fas fa-align-left"> </i> Mostra dettagli
					<div id="dettagli_posto" class="descriz">
						<div id="testo_descrizione">
							<%=bean.getDescrizione()%>
						</div>
					</div>
				</div>
			</a> 
			
			<br> 
			
			<a>
				<div onclick="caricaMappaCoordinate(this,<%=bean.getLati()%>,<%=bean.getLongi()%>)" id="bottone_mappa">
					<i id="iconaMappa" class="fas fa-map-marker-alt"> </i> Mappa
				</div>
			</a>

		</div>

		<%
			y++;
		}
		}
		%>

		<%
			if (y % 3 == 0) {
		%>
		<br>
		<%
			}
		%>
	
	</div>

	<div id="pager">

<ul class="pagination pager" id="myPager"></ul>
</div>

<%} %>


</div>

	<footer>
		<div id="copyright">Copyright &#169; 2020 by Gerardo De Rosa &
			Gianluca Annunziata</div>
			
		<div id="vertical_line"></div>
		
		<div id="contacts">
			<div id="contatti_testo"> Contatti: </div> 
			<a href="https://gerardoderosa.netlify.app/"> <img id="profili"
				src="Images/me.png"></a> <a
				href="https://g-annunziata4.netlify.app/"><img id="profili"
				src="Images/gianluca.png"></a>
		</div>
	</footer>

</body>
<script src="JS/app.js"></script>
<script src="JS/pagination.js"></script>


</html>