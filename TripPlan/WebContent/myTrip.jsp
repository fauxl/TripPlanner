<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,Trip.*"%>

<%
	Collection<?> posti = (Collection<?>) request.getAttribute("posti");
String location = "";
String division = "";

Cookie[] cookies = request.getCookies();

String cookieName = "location";
String cookieName2 = "division";

for (int i = 0; i < cookies.length; i++) {

	Cookie cookie = cookies[i];

	if (cookieName.equals(cookie.getName())) {
		location = cookie.getValue();
	} else if (cookieName2.equals(cookie.getName())) {
		division = cookie.getValue();
	}

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

function chiudimini(){
	
	for (i = 0; i < modal.length; i++) {
	    modal[i].style.display = "none";
	  		}

}
function chiudi(){    
    for (i = 0; i < modal.length; i++) {
		    modal[i].style.display = "none";
		  		}	
}
</script>

<script>
	function caricaMappaCoordinate(div, x, y) {
		
		// node e div rappresentano il bottone "Mappa" 
		var div = document.getElementById("map");
		var node = div.parentNode;
		div.style.display = "none";
		
		// Creo il frame con la mappa e assegno le proprietà
		var mappa = document.createElement('iframe');
		var url = 'https://maps.google.com/maps?;hl=it&q=@'+x+','+y+'&ie=UTF8&t=&z=14&iwloc=B&output=embed';
		mappa.setAttribute('src', url_trip);
		mappa.style.height = "67%";
		mappa.style.width = "90%";
		mappa.style.border = "0px";
		mappa.style.margin = "-2% 5% 0px 5%";
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

<body onload="caricaMappaTrip()";>


	<div id="backgroundpages">

		<%@ include file="navbar.jsp"%>

		<div id="logopages">
			<a href="http:\\127.0.0.1:80\home"><img src="Images/logo.png"></a>
			<h1>TripPlan</h1>
		</div>
	</div>

	<div id=contenuti>
		<h3 style="margin-top: -25px;">Il tuo Trip:</h3>

		<div id="spazio_contenuti1" style="margin-top:0px; margin-bottom: -15px;">

			<%
				if (posti != null && posti.size() != 0) {
				Iterator<?> it = posti.iterator();
				while (it.hasNext()) {
					CulturalPlace bean = (CulturalPlace) it.next();
			%>

			<a style="color: black;" href="#mappa_posto">
				<div id="cultural_place_trip" class="cards" style="cursor: pointer;">
					<a
						href="single?id=<%=bean.getCode()%>&disciplina=<%=bean.getTipo().replace("@it", "")%>
		&location=<%=location%>&division=<%=division%>&posto=<%=bean.getNome().replace("@it", "")%>&aggiungi=false">

						<img id="foto_posto" style="width:100%;"
						onerror="this.src='https://www.arciserviziocivile.it/vicenza/wp-content/uploads/sites/2/musei.jpg';"
						src=<%=bean.getImmagine()%>>
					</a><br>
					<div id="descrizione_posto2">
						<div class="name"><%=bean.getNome()%></div>
						<br>
						<%=bean.getAddress()%>
					</div>
					<br> 
					<a href="javascript:aggiornaMappa(<%=bean.getLati()%>,<%=bean.getLongi()%>);"> 
						<div id="bottone_mappa"
							onclick="aggiornaNome('<%=bean.getNome()%>');">
							<i id="iconaMappa" class="fas fa-map-marker-alt"> </i> Mostra
							sulla mappa
						</div>
					</a>
					<div id="elimina_trip" style="color: red;">
						<a href="plan?rimuovi=<%=bean.getCode()%>"> <i
							class="fas fa-times-circle"> </i> Elimina dal Trip
						</a>
					</div>
				</div>
			</a>
			<%
				}
			}
			%>

		</div>
		<div id="pager">

			<ul class="pagination pager" id="myPager"></ul>
		</div>
	</div>

	<a href="#spazio_contenuti1">
		<div id="map"></div>
		<div id="luogo_trip" style="margin-top: -1%;">Scegli un posto da visualizzare</div>
	</a>

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
<script src="JS/pagination3.js"></script>


<script type="text/javascript">

window.onload = function caricaMappaTrip() {
	// node e div rappresentano il bottone "Mappa" 
	var node = document.getElementById("map");
	var spazioMappa = node.parentNode;
	node.style.display = "none";
	
	// Creo il frame con la mappa e assegno le proprietà
	var mappa = document.createElement('iframe');
	var url = 'https://maps.google.com/maps?;hl=it&q=@&ie=UTF8&t=&z=14&iwloc=B&output=embed';
	mappa.setAttribute('src', url);
	mappa.id = "mappa_posto";
	mappa.style.height = "70%";
	mappa.style.width = "88%";
	mappa.style.border = "0px";
	mappa.style.margin = "-3% 6% 2% 6%";
	mappa.style.borderRadius = "8px";
	mappa.style.boxShadow = "0px 1px 7px #A9A9A9";
	mappa.style.zIndex = "1";
	
	spazioMappa.appendChild(mappa);

	
}	


function aggiornaMappa(x,y){

	var mappa_posto = document.getElementById("mappa_posto");
	var url_posto = 'https://maps.google.com/maps?;hl=it&q=@'+x+','+y+'&ie=UTF8&t=&z=14&iwloc=B&output=embed';
	
	mappa_posto.setAttribute('src', url_posto);
	
	}
	
function aggiornaNome(k){
	var nome_posto = document.getElementById("luogo_trip");
	nome_posto.textContent = k;
	}

</script>

</html>