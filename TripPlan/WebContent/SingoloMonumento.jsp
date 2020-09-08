<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,Trip.*"%>

<%
	CulturalPlace beano = (CulturalPlace) request.getAttribute("posto");
Collection<?> eventi = (Collection<?>) request.getAttribute("eventi");
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
		mappa.style.height = "63%";
		mappa.style.width = "95%";
		mappa.style.border = "0px";
		mappa.style.margin = "3% 2.5% 0px 2.5%";
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
<script>
function apriluoghi(){
	var dettagli = document.getElementById("luoghi-wrap");
	
	if(dettagli.style.display == "inline"){
		dettagli.style.display = "none";
		}
	else{ 
		dettagli.style.display = "inline" 
		}
	}
	
function aprieventi(){
	var dettagli = document.getElementById("eventi-wrap");
	
	if(dettagli.style.display == "inline"){
		dettagli.style.display = "none";
		}
	else{ 
		dettagli.style.display = "inline" 
		}
	}
</script>

<body
	onload="caricaMappaCoordinate(this,<%=beano.getLati()%>,<%=beano.getLongi()%>);">

	<div id="backgroundpages">

		<%@ include file="navbar.jsp"%>

		<div id="logopages">
			<a href="http:\\127.0.0.1:80\home"><img src="Images/logo.png"></a>
			<h1><%=beano.getNome()%></h1>
		</div>
	</div>

	<div id="spazio_contenuti"
		style="width: 100%; margin: auto; margin-top: 1%; margin-bottom: 3%;">

		<div id="cultural_place_singolo">

			<div id="parte_sinistra">
				<div id="immagine">
					<img id="foto_posto1"
						onerror="this.src='https://www.arciserviziocivile.it/vicenza/wp-content/uploads/sites/2/musei.jpg';"
						src=<%=beano.getImmagine()%>> <br>
				</div>
				<div id="descrizione_posto">
					<div id="grassetto">Nome:</div>
					<%=beano.getNome()%>

					<br>
					<div id="grassetto">Disciplina:</div>
					<%=beano.getTipo()%>
					<br>
					<div id="grassetto">Indirizzo</div>
					<%=beano.getAddress()%>
					<br>
					<div id="grassetto">Provincia</div>
					<%=beano.getProvincia()%>
					<br>
					<div id="grassetto">Regione</div>
					<%=beano.getRegione()%><br>

					<%
						if (!(beano.getWebsite().isEmpty() || beano.getWebsite().isBlank())) {
					%>
					<div id="grassetto">Sito web:</div>
					<a href=<%=beano.getWebsite()%>><%=beano.getWebsite()%></a> <br>
					<%
						}
					%>

				</div>

			</div>

			<div id="linea_verticale"></div>

			<div id="parte_centrale">
				<div id="dettagli1">
					<div id="dettagli_posto1" class="descriz">
						<div id="testo_descrizione1">
							<div id="grassetto">Descrizione:</div>
							<br>
							<%=beano.getDescrizione()%>
						</div>
					</div>
				</div>
			</div>

			<div id="linea_verticale"></div>

			<div id="parte_destra" style="width: 32%;">
				<a>
					<div id="bottone_mappa1"></div>
				</a> <br> <a
					href="single?location=<%=location%>&division=<%=division%>&id=<%=beano.getCode()%>
				&posto=<%=beano.getNome()%>&aggiungi=<%=beano.getCode()%>&disciplina=<%=beano.getTipo()%>">
					<div class="button" id="bottone_aggiungi_trip1">
						<i class="fas fa-check"> </i> Aggiungi al Trip
					</div>
				</a>
				<br>
				<div class="button" id="bottone_show_places"
					onclick="apriluoghi()">
					<i class="far fa-eye"> </i> Luoghi Correlati
				</div>
				<div class="button" id="bottone_show_events" onclick="aprieventi()">
					<i class="far fa-eye"> </i> Eventi Connessi
				</div>
			</div>

		</div>
	</div>




	<%
		if ((eventi != null && eventi.size() != 0) || (posti != null && posti.size() != 0)) {
	%>

	<div id="container">

		<div id="eventi-wrap">
			<%
				if (eventi != null && eventi.size() != 0) {
			%>
			<h3>Eventi Connessi:</h3>


			<div id="eventi">


				<%
					int y = 0;
				if (eventi != null && eventi.size() != 0) {
					Iterator<?> it = eventi.iterator();
					while (it.hasNext()) {
						Event ev = (Event) it.next();
				%>

				<div id="cultural_place">
					<a href="event?id=<%=ev.getEvento()%>"> <img id="foto_posto"
						onerror="this.src='https://www.arciserviziocivile.it/vicenza/wp-content/uploads/sites/2/musei.jpg';"
						src=<%=ev.getImmagine()%>>
					</a><br>

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
						<a href=<%=ev.getLuogo()%>><%=ev.getLuogo()%></a> <br>

					</div>

					<br> <a>
						<div class="button" id="bottone_aggiungi_trip"
							onclick="ShowTrip()">
							<i class="fas fa-check"> </i> Aggiungi al Trip
						</div>
					</a> <a>
						<div class="button" onclick="mostraDettagli(this)"
							id="bottone_dettagli" class="btn">
							<i class="fas fa-align-left"> </i> Mostra dettagli
							<div id="dettagli_posto" class="descriz">
								<div id="testo_descrizione">
									<%=ev.getDescrizione()%>
								</div>
							</div>
						</div>
					</a> <br> <a>
						<div class="button"
							onclick="caricaMappaCoordinate(this,<%=ev.getLati()%>,<%=ev.getLongi()%>)"
							id="bottone_mappa">
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
			<%
				}
			%>
		</div>
		<div id="luoghi-wrap">
			<%
				if (posti != null && posti.size() != 0) {
			%>
			<h3>Luoghi correlati:</h3>

			<div id="luoghi">

				<%
					int x = 0;
				if (posti != null && posti.size() != 0) {
					Iterator<?> it = posti.iterator();
					while (it.hasNext()) {
						CulturalPlace bean = (CulturalPlace) it.next();
				%>

				<div id="cultural_place">
					<a
						href="single?id=<%=bean.getCode()%>&disciplina=<%=bean.getTipo().replace("@it", "")%>
		&location=<%=location%>&division=<%=division%>&aggiungi=false&posto=<%=bean.getNome().replace("@it", "")%>">
						<img id="foto_posto"
						onerror="this.src='https://www.arciserviziocivile.it/vicenza/wp-content/uploads/sites/2/musei.jpg';"
						src=<%=bean.getImmagine()%>>
					</a><br>

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

					<br> <a
						href="single?location=<%=location%>&division=<%=division%>&id=<%=beano.getCode()%>
				&posto=<%=beano.getNome()%>&aggiungi=<%=bean.getCode()%>&disciplina=<%=beano.getTipo()%>">
						<div class="button" id="bottone_aggiungi_trip">
							<i class="fas fa-check"> </i> Aggiungi al Trip
						</div>
					</a> <a>
						<div onclick="mostraDettagli(this)" id="bottone_dettagli"
							class="button">
							<i class="fas fa-align-left"> </i> Mostra dettagli
							<div id="dettagli_posto" class="descriz">
								<div id="testo_descrizione">
									<%=bean.getDescrizione()%>
								</div>
							</div>
						</div>
					</a> <br> <a>
						<div
							onclick="caricaMappaCoordinate(this,<%=bean.getLati()%>,<%=bean.getLongi()%>)"
							id="bottone_mappa">
							<i id="iconaMappa" class="fas fa-map-marker-alt"> </i> Mappa
						</div>
					</a>

				</div>

				<%
					x++;
				}
				}
				%>

				<%
					if (x % 3 == 0) {
				%>
				<br>
				<%
					}
				%>
			</div>



			<div id="pager">

				<ul class="pagination pager" id="myPager2"></ul>
			</div>
			<%
				}
			%>
		</div>
	</div>
	<%
		}
	%>


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
<script src="JS/pagination2.js"></script>


</html>