<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> posti = (Collection<?>) request.getAttribute("posti");
	boolean bool = true;
	
	String tripfound = "";


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

<body>


	<div id="backgroundpages">

		<%@ include file="navbar.jsp"%>

		<div id="logopages">
			<a href="http:\\127.0.0.1:80\home"><img src="Images/logo.png"></a>
			<h1>Viaggio Consigliato</h1>
		</div>
	</div>
	
		<%if (posti!=null && posti.size() != 0){ %>
	
	<div id="contenuti">
		<h3>Ecco il piano elaborato per te:</h3>
		<p>Puoi aggiungere tutto al piano cliccando il tasto a piè di pagina, oppure aggiungere singolarmente i luoghi.

	<div id="spazio_contenuti">


		<%
			int y = 0;
		if (posti != null && posti.size() != 0) {
			Iterator<?> it = posti.iterator();
			while (it.hasNext()) {
				CulturalPlace bean = (CulturalPlace) it.next();
				tripfound =  tripfound +";"+bean.getCode();
		%>

		<div id="cultural_place">
		<a href="single?id=<%=bean.getCode()%>&posto=<%=bean.getNome().replace("@it", "")%>">		<img id="foto_posto"
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

			<a>
				<div onclick="mostraDettagli(this)" id="bottone_dettagli"
					class="btn">
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
					<i id="iconaMappa" class="fas fa-map-marker-alt"> </i> Mostra la mappa
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
<div id ="addall">
<a  href="plan?plan=<%=tripfound%>&rimuovi=false" >
	 <button type="submit" id="alltrip">
	 Clicca per aggiungere tutto al TripPlan
	</button>
	</a>
</div>
<%} %>


</div>
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