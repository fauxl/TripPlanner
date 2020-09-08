<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,Trip.*"%>

<%
Collection<?> posti = (Collection<?>) request.getAttribute("posti");
Collection<?> eventi = (Collection<?>) request.getAttribute("eventi");
String location="";
String division="";

Cookie[] cookies = request.getCookies();

String cookieName = "location";
String cookieName2 = "division";

for ( int i=0; i<cookies.length; i++) {

    Cookie cookie = cookies[i];

    if (cookieName.equals(cookie.getName())){
		location = cookie.getValue();}
    else  if(cookieName2.equals(cookie.getName())){
	division = cookie.getValue();}

  }

%>

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

<script>

function addplace(trip){
	
	var expires = "";
    
        var date = new Date();
        date.setTime(date.getTime() + (10*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
 

	
console.log(trip);
var arr = [];

arr.push(trip);
var name = "triparr"
var json_str = JSON.stringify(arr);
document.cookie = name + "=" + (json_str || "")  + expires + "; path=/";
}

</script>

<script>

function chiudimini(){
	
	for (i = 0; i < modal.length; i++) {
	    modal[i].style.display = "none";
	  		}

}
function chiudi(){    
    for (i = 0; i < modal.length; i++) {
		    modal[i].style.display = "none";
		  		}	

</script>

<script>
	function caricaMappaCoordinate(div, x, y) {
		
		// node e div rappresentano il bottone "Mappa" 
		var div = document.getElementById("bottone_mappa1");
		var node = div.parentNode;
		div.style.display = "none";
		
		// Creo il frame con la mappa e assegno le proprietà
		var mappa = document.createElement('iframe');
		var url = 'https://maps.google.com/maps?;hl=it&q=@'+x+','+y+'&ie=UTF8&t=&z=14&iwloc=B&output=embed';
		mappa.setAttribute('src', url);
		mappa.style.height = "35%";
		mappa.style.width = "90%";
		mappa.style.border = "0px";
		mappa.style.margin = "4% 4% 0px 4%";
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

<body>

	<script src="{{url_for('static', filename='app.js')}}"></script>

	<div id="backgroundpages">

		<%@ include file="navbar.jsp"%>

		<div id="logopages">
			<a href="http://localhost:8080/TripPlan/home.jsp"><img src="Images/logo.png"></a>
			<h1>Area Ricerca</h1>
		</div>
	</div>


	<div id="rircerca">
		<p>Compila i campi qui sotto per effettuare una ricerca!</p>


		<form method="get" action="/TripPlan/Search">
			<div id="research">
				<input name="searchbar" id="searchBoxTitle"
					placeholder="Ricerca per Luogo o Evento...">
					
					
				<select name="disciplina"
					id="place"  >
					<option selected disabled>Disciplina</option>
					<option>Tutti</option>
					<option>Eventi</option>
					<option>Chiese</option>
					<option>Arte</option>
					<option>Archeologia</option>
					<option>Storia</option>
					<option>Etnografia</option>
					<option>Scienza</option>
					<option>Altro</option>

				</select><br>
			</div>

<div id="regione">
			<select name="division" id="place">
				<option value="Regione" selected>Regione</option>
				<option value="Provincia">Provincia</option>
				<option value="Comune">Comune</option>
			</select> <input name="city" type="text" placeholder="Cerca posto...">
</div>

			<div id="order">
				<div id="orderby">
					Visualizza in ordine: <input type="radio" name="orderby" value="ASC">
					Crescente <input type="radio" name="orderby" value="DESC"
						checked="checked"> Decrescente<br>
				</div>

				Ordina per: <input type="radio" name="order" value="Comune">
				Comune <input type="radio" name="order"
					value="Provincia"> Provincia <input type="radio"
					 name="order" value="NOME" checked="checked"> Luogo/Evento
					<input type="radio"  name="order" value="DATA_INIZIO_EVENTO" checked="checked">
					Per data inizio evento (Solo per Eventi)

			</div>
			<div id="inviodati">
				<button type="submit" name="division" id="invio">Clicca per effettuare la ricerca</button>
			</div>
		</form>

	</div>



	<%if ((eventi!=null && eventi.size() != 0) || (posti!=null && posti.size() != 0)){ %>

	<div id="risultati">


<div id="luoghi-w">

	<h3>Risultati della Ricerca:</h3>
	
	<div id="spazio_contenuti">

		<%
			int y = 0;
		if (posti != null && posti.size() != 0) {
			Iterator<?> it = posti.iterator();
			while (it.hasNext()) {
				CulturalPlace bean = (CulturalPlace) it.next();
		%>

		<div id="cultural_place">
		<a href="single?id=<%=bean.getCode()%>&disciplina=<%=bean.getTipo().replace("@it", "")%>
		&location=<%=location%>&division=<%=division%>&posto=<%=bean.getNome().replace("@it", "")%>&aggiungi=false">	<img id="foto_posto" 
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
				<a href=<%=bean.getWebsite()%>><%=bean.getWebsite()%></a> <br>
				<%
					}
				%>

			</div>

			<br> 
			
			<a onclick="addplace('<%=bean.getCode()%>')">
				<div class="button"  id="bottone_aggiungi_trip">
					<i class="fas fa-check"> </i> Aggiungi al Trip
				</div>
			</a> 
			
			<a>
				<div onclick="mostraDettagli(this)" id="bottone_dettagli"
					class="button">
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
				<div  onclick="caricaMappaCoordinate(this,<%=bean.getLati()%>,<%=bean.getLongi()%>)" id="bottone_mappa">
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
			<%if(posti!=null && posti.size() != 0){ %>
	<div id="pager">

<ul class="pagination pager" id="myPager2"></ul>
</div>	<%
			}
		%>
	</div>	
	
	<div id="eventi-w">
	
	<div id="eventi">
	
	
	<%
			int x = 0;
		if (eventi != null && eventi.size() != 0) {
			Iterator<?> it = eventi.iterator();
			while (it.hasNext()) {
				Event ev = (Event) it.next();	
		%>
		
	<div id="cultural_place">
		<a href="event?id=<%=ev.getEvento()%>">	<img id="foto_posto" 
				onerror="this.src='https://www.arciserviziocivile.it/vicenza/wp-content/uploads/sites/2/musei.jpg';"
				src=<%=	ev.getImmagine()%>> </a><br>

			<div id="descrizione_posto">
				<div id="grassetto">Nome:</div>
				<%=ev.getNome()%>
				<br>
				<div id="grassetto">Indirizzo - Provincia - Regione</div>
				<%=ev.getAddress()%>
				|
				<%=ev.getProvincia()%>
				|
				<%=ev.getRegione()%>
				<br>

				
				<div id="grassetto">Sito web:</div>
					<a href=<%=ev.getWebsite()%>><%=ev.getWebsite()%></a> 
				
			</div>

			<br> 
			
			<a>
				<div class="button" id="bottone_aggiungi_trip" onclick="addplace('<%=ev.getCode()%>')">
					<i class="fas fa-check"> </i> Aggiungi al Trip
				</div>
			</a> 
			
			<a>
				<div onclick="mostraDettagli(this)" id="bottone_dettagli"
					class="button">
					<i class="fas fa-align-left"> </i> Mostra dettagli
					<div id="dettagli_posto" class="descriz">
						<div id="testo_descrizione">
							<%=ev.getDescrizione()%>
						</div>
					</div>
				</div>
			</a> 
			
			<br> 
			
			<a>
				<div onclick="caricaMappaCoordinate(this,<%=ev.getLati()%>,<%=ev.getLongi()%>)" id="bottone_mappa">
					<i id="iconaMappa" class="fas fa-map-marker-alt"> </i> Mappa
				</div>
			</a>

		</div>

		<%
			x++;
		}
		}
		%>

				
		</div>	
			</div>	
			<%if(eventi!=null && eventi.size() != 0){ %>
		<div id="pager">

<ul class="pagination pager" id="myPager"></ul>
</div>
<%} %>
	
				</div>	
	

<%} %>
	


	<footer>
		<div id="copyright">Copyright &#169; 2020 by Gerardo De Rosa &
			Gianluca Annunziata</div>

		<div id="vertical_line"></div>

		<div id="contacts">
			<div id="contatti_testo">Contatti:</div>
			<a href="https://gerardoderosa.netlify.app/"> <img id="profili"
				src="Images/me.png"></a> <a
				href="https://g-annunziata4.netlify.app/"><img id="profili"
				src="Images/gianluca.png"></a>
		</div>
	</footer>

</body>
<script src="JS/app.js"></script>
<script src="JS/pagination.js"></script>
<script src="JS/pagination2.js"></script>

</html>