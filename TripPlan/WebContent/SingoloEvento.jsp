<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,Trip.*"%>

<%
Event bean = (Event) request.getAttribute("evento");

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
		mappa.style.height = "75%";
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

<body onload="caricaMappaCoordinate(this,<%=bean.getLati()%>,<%=bean.getLongi()%>);" >

	<script src="{{url_for('static', filename='app.js')}}"></script>

	<div id="backgroundpages">

		<%@ include file="navbar.jsp"%>

		<div id="logopages">
			<a href="http:\\127.0.0.1:80\home"><img src="Images/logo.png"></a>
			<h1><%=bean.getNome()%></h1>
		</div>
	</div>

	<div id="spazio_contenuti"
		style="width: 100%; margin: auto; margin-top: 1x%; margin-bottom: 3%;">

		<div id="cultural_place_singolo">

			<div id="parte_sinistra">
						<div id ="immagine">
			
				<img id="foto_posto1"
					onerror="this.src='https://www.arciserviziocivile.it/vicenza/wp-content/uploads/sites/2/musei.jpg';"
					src=<%=bean.getImmagine()%>> <br>
</div>
				<div id="descrizione_posto">
					<div id="grassetto">Nome:</div>
					<%=bean.getNome()%>

					<br>
					<div id="grassetto">Indirizzo</div>
					<%=bean.getAddress()%>
					<br>
					<div id="grassetto">Provincia</div>
					<%=bean.getProvincia()%>
					<br>
					<div id="grassetto">Regione</div>
					<%=bean.getRegione()%><br> 
					<div id="grassetto">Data Inizio evento</div>
					<%=bean.getDatainizio()%><br> 
					<div id="grassetto">Data Fine evento</div>
					<%=bean.getDatafine()%><br> 
					<div id="grassetto">Luogo evento</div>
					<%=bean.getLuogo()%><br> 

					<%
						if (!(bean.getWebsite().isEmpty() || bean.getWebsite().isBlank())) {
					%>
					<div id="grassetto">Sito web:</div>
					<a href=<%=bean.getWebsite()%>><%=bean.getWebsite()%></a> <br>
					<%
						}
					%>

				</div>
				
					
			</div>

			<div id="linea_verticale"></div>

			<div id="parte_centrale">
					<div  id="dettagli1">
						<div id="dettagli_posto1" class="descriz">
							<div id="testo_descrizione1">
									<div id="grassetto">Descrizione:</div>
									<br>
								<%=bean.getDescrizione()%>
							</div>
						</div>
					</div>
			</div>

			<div id="linea_verticale"></div>

			<div id="parte_destra">
				<a>
					<div
						id="bottone_mappa1">
					</div>
				</a> <br> <a>
					<div id="bottone_aggiungi_trip1">
						<i class="fas fa-check"> </i> Aggiungi al Trip
					</div>
				</a>
			</div>

		</div>
	</div>



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