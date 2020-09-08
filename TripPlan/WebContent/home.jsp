<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> posti = (Collection<?>) request.getAttribute("posti");

boolean bool = true;
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

<link href="https://fonts.googleapis.com/css2?family=Amiko&display=swap" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

  
    <script src=" https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>


    <script src="https://kit.fontawesome.com/410e5bdac4.js" crossorigin="anonymous"></script>

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
		var node = div.parentNode;
		div.style.display = "none";
		var mappa = document.createElement('iframe');
		var url = 'https://maps.google.com/maps?;hl=it&q=@'+x+','+y+'&ie=UTF8&t=&z=14&iwloc=B&output=embed';
		mappa.setAttribute('src', url);
		node.appendChild(mappa);
		
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

<div id="background">

<%@ include file="navbar.jsp"%>


<div id="logon">
<a  href="http:\\127.0.0.1:80\home"><img src="Images/logo.png"></a>
</div>
</div>


<div class="paragraph", id="about">

    <p style="font-size: 18px; line-height: 1.5em;">Benvenuto! Questo sito nasce con lo scopo di facilitare la creazione di itinerari di viaggio su tutta la penisola Italiana, per quanto riguarda principalmente, ma non solo, vacanze culturali.
    Attraverso un'ontologia fornita ufficialmente dall'ente per i beni culturali, È possibile su questa piattaforma scegliere tutti i punti culturali più importanti da visitare nel corso del proprio viaggio. 
    Grazie alle capacità semantiche delle ontologie.<br> È possibile scegliere i luoghi da visitare in base a quelli correlati; da ogni posto scelto si snoda un grafo con i punti di interessi simili che l'utente può poi aggiungere
    al proprio piano di visita. In pià ad ogni luoghi sono associati gli eventi che si svolgono in quel luogo,
    cosi d avere una panoramica completa su cosa ogni luogo offre e scegliere con cura cosa includere nel proprio viaggio.  
    <br><br>Quindi cosa aspetti, crea anche tu il tuo TripPlan!</p>

</div>

<div class="paragraph", id="trova">
    <p>Per prima cosa, seleziona qui la provincia, regione o comune italiano meta del tuo viaggio!</p>

        <form method="get" action="/TripPlan/Place">
          <select name="division" id="place">
            <option value="Regione" selected>Regione</option>
            <option value="Provincia">Provincia</option>
            <option value="Comune">Comune</option>
          </select>
                  <input name="city" type="text" placeholder="Cerca posto...">
    <br>
          <input type="submit" value="Cerca" id="sub">
        </form>

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
	
<script src="JS/app.js"></script>

</body>

</html>