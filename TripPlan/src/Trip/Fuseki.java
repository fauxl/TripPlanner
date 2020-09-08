package Trip;

import java.net.URL;
import java.util.List; 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.lang.Number;

import org.apache.jena.query.*;
import org.apache.jena.query.QueryExecution; 
import org.apache.jena.query.QueryExecutionFactory; 
import org.apache.jena.query.QueryFactory; 
import org.apache.jena.query.QuerySolution; 
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.function.library.print;


public class Fuseki { 

   // public static void main(final String[] args) throws Exception { 
    	
    	public static Collection<CulturalPlace> GetPlaces(String division, String city) throws Exception{

        String queryString=
        		
        		
         "prefix cis:<http://dati.beniculturali.it/cis/>" + 
         "prefix l0:<https://w3id.org/italia/onto/l0/>" + 
         "prefix clvapit: <https://w3id.org/italia/onto/CLV/>" + 
         "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
         "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
         "prefix foaf: <http://xmlns.com/foaf/0.1/>" +
         "prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>" +
         "prefix accessCondition:<https://w3id.org/italia/onto/AccessCondition/>"+
         "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
         "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
         "prefix potapit:<https://w3id.org/italia/onto/POT/>"+

         
         "select * where {" + 
         " select distinct ?s " + 
         " ?pic" + 
         " ?NOME" + 
         " ?Descrizione" + 
         " ?Identifier" + 
         " ?Latitudine" + 
         " ?Longitudine" + 
         " ?Disciplina" + 
         " ?Indirizzo" + 
         " ?Codice_postale" + 
         " ?Comune" + 
         " ?Regione" + 
         " ?Provincia" + 
         " ?Prenotazioni" + 
         " ?Orari_di_apertura" + 
         " ?Telefono" + 
         " ?Fax" + 
         " ?Email" + 
         " ?WebSite" + 
         " ?Biglietti" + 
         " ?Servizi" + 
         " where {" + 
         "  graph <http://dati.beniculturali.it/mibact/luoghi> {" + 
         "		?s rdf:type cis:CulturalInstituteOrSite ;" + 
         "      cis:institutionalCISName ?NOME ." + 
         "   optional { ?s foaf:depiction ?pic}" + 
         "   optional { ?s l0:description ?Descrizione }" + 
         "   optional { ?s l0:identifier ?Identifier }" + 
         "   optional { ?s geo:lat ?Latitudine }" + 
         "   optional { ?s geo:long ?Longitudine }" + 
         "   optional { ?s cis:hasDiscipline [l0:name ?Disciplina] }" + 
         "   optional {" + 
         "    ?s cis:hasSite [cis:siteAddress ?address ] ." + 
         "    optional { ?address clvapit:fullAddress ?Indirizzo }" + 
         "    optional { ?address clvapit:postCode ?Codice_postale }" + 
         "    optional { ?address clvapit:hasCity [rdfs:label ?Comune] }" + 
         "    optional { ?address clvapit:hasProvince [rdfs:label ?Provincia] }" + 
         "    optional { ?address clvapit:hasRegion [rdfs:label ?Regione]}" + 
         "   }" + 
         "FILTER regex(?"+division+", \""+city+"\")" +

         "   optional {?s accessCondition:hasAccessCondition [rdf:type accessCondition:Booking ;" + 
         "                                                    rdfs:label ?Prenotazioni] }" + 
         "   optional {?s accessCondition:hasAccessCondition [rdf:type accessCondition:OpeningHoursSpecification ;" + 
         "                                                    l0:description ?Orari_di_apertura ] }" + 
         "   optional {" + 
         "    ?s smapit:hasOnlineContactPoint ?contactPoint ." + 
         "    optional { ?contactPoint smapit:hasTelephone [smapit:hasTelephoneType <https://w3id.org/italia/controlled-vocabulary/classifications-for-public-services/channel/031> ;" + 
         "                                                  smapit:telephoneNumber ?Telefono] }" + 
         "    optional { ?contactPoint smapit:hasTelephone [smapit:hasTelephoneType <https://w3id.org/italia/controlled-vocabulary/classifications-for-public-services/channel/033> ;" + 
         "                                                  smapit:telephoneNumber ?Fax] }" + 
         "    optional { ?contactPoint smapit:hasEmail [smapit:emailAddress ?Email] }" + 
         "    optional { ?contactPoint smapit:hasWebSite [smapit:URL ?WebSite] }    " + 
         "   }  " + 
         "   optional {" + 
         "    ?s potapit:hasTicket ?ticket ." + 
         "    ?offer potapit:includes ?ticket ;" + 
         "           potapit:hasPriceSpecification [potapit:hasCurrencyValue ?Biglietti]" + 
         "   }" + 
         "   optional { ?s cis:providesService [l0:name ?Servizi] }" + 
         "  }" + 
         " }" + 
         " order by ?s" + 
         "}limit 40";

  	  // now creating query object
        Query query = QueryFactory.create(queryString);
        // initializing queryExecution factory with remote service.
        // **this actually was the main problem I couldn't figure out.**
        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dati.beniculturali.it/sparql", query);


    	boolean bool = false;
    	Collection<CulturalPlace> posto = new LinkedList<CulturalPlace>();
        Collection<QuerySolution> filtered = new ArrayList<QuerySolution>();

       try {

            ResultSet results = qexec.execSelect();
            ResultSet results2 = qexec.execSelect();
            QuerySolution j = results2.next();
        	
       	 for (; results.hasNext();) {
         	QuerySolution i = results.next();
    		
    		if(bool == false) {
    			filtered.add(i);
        		bool = true;
    		}
    		
             	QuerySolution v = results2.next();
             
            	if (!i.get("Identifier").toString().equalsIgnoreCase(v.get("Identifier").toString()))
            	{
            	
            		filtered.add(v);
            	
            		
            	}
               //	System.out.println(filtered.size());

       	 }

        }
        catch (Exception e) {
     		System.out.print(e);
		} 

       
       
       
       //     	System.out.println(filtered.size());

            	Iterator<?> it = filtered.iterator();
            	
         	while (it.hasNext() ) {
         		QuerySolution i = (QuerySolution) it.next();
         
            	CulturalPlace place = new CulturalPlace();
                if(i.get("NOME")!=null) {
            	place.setNome(i.get("NOME").toString().replace("\\", "").replace("@it", ""));}
                if(i.get("Indirizzo")!=null) {
            	place.setAddress(i.get("Indirizzo").toString());}
                if(i.get("Identifier")!=null) {
                	place.setCode(i.get("Identifier").toString());}
                if(i.get("Disciplina")!=null) {
                	place.setTipo(i.get("Disciplina").toString());}
                if(i.get("Comune")!=null) {
                place.setComune(i.get("Comune").toString());}
                if(i.get("Regione")!=null) {
            	place.setRegione(i.get("Regione").toString());}
            	if(i.get("Provincia")!=null) {
            	place.setProvincia(i.get("Provincia").toString());}
            	if(i.get("Longitudine")!=null) {
            	place.setLongi(Float.parseFloat((i.get("Longitudine")).toString()));}
            	if(i.get("Latitudine")!=null) {
            	place.setLati(Float.parseFloat((i.get("Latitudine")).toString()));}
            	if(i.get("WebSite")!=null) {
            		place.setWebsite(i.get("WebSite").toString());}
            	if(i.get("Descrizione")!=null) {
            	place.setDescrizione(i.get("Descrizione").toString());}
            	if(i.get("pic")!=null) {
            		place.setImmagine(i.get("pic").toString());}
	
            	if(place!=null) {
            	posto.add(place);} 	
            	System.out.print(place.toString()+"\n");
            	System.out.print(posto.size());
            // Result processing is done here.

            }            

            qexec.close();
            return posto;
        
        }
 

    	public static Collection<CulturalPlace> GetPlacesQuery() throws Exception{

            String queryString=
            		
            		
             "prefix cis:<http://dati.beniculturali.it/cis/>" + 
             "prefix l0:<https://w3id.org/italia/onto/l0/>" + 
             "prefix clvapit: <https://w3id.org/italia/onto/CLV/>" + 
             "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
             "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
             "prefix foaf: <http://xmlns.com/foaf/0.1/>" +
             "prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>" +
             "prefix accessCondition:<https://w3id.org/italia/onto/AccessCondition/>"+
             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
             "prefix potapit:<https://w3id.org/italia/onto/POT/>"+

             
             "select * where {" + 
             " select distinct ?s" + 
             " ?pic" + 
             " ?NOME" + 
             " ?Descrizione" + 
             " ?Identifier" + 
             " ?Latitudine" + 
             " ?Longitudine" + 
             " ?Disciplina" + 
             " ?Indirizzo" + 
             " ?Codice_postale" + 
             " ?Comune" + 
             " ?Regione" + 
             " ?Provincia" + 
             " ?Prenotazioni" + 
             " ?WebSite" + 
             " where {" + 
             "  graph <http://dati.beniculturali.it/mibact/luoghi> {" + 
             "?s rdf:type cis:CulturalInstituteOrSite ;" + 
             "      cis:institutionalCISName ?NOME ." + 
             "   optional { ?s foaf:depiction ?pic}" + 
             "   optional { ?s l0:description ?Descrizione }" + 
             "   optional { ?s l0:identifier ?Identifier }" + 
             "   optional { ?s geo:lat ?Latitudine }" + 
             "   optional { ?s geo:long ?Longitudine }" + 
             "   optional { ?s cis:hasDiscipline [l0:name ?Disciplina] }" + 
             "   optional {" + 
             "    ?s cis:hasSite [cis:siteAddress ?address ] ." + 
             "    optional { ?address clvapit:fullAddress ?Indirizzo }" + 
             "    optional { ?address clvapit:postCode ?Codice_postale }" + 
             "    optional { ?address clvapit:hasCity [rdfs:label ?Comune] }" + 
             "    optional { ?address clvapit:hasProvince [rdfs:label ?Provincia] }" + 
             "    optional { ?address clvapit:hasRegion [rdfs:label ?Regione]}" + 
             "   }" + 
             "   optional {?s accessCondition:hasAccessCondition [rdf:type accessCondition:Booking ;" + 
             "                                                    rdfs:label ?Prenotazioni] }" + 
             "   optional {?s accessCondition:hasAccessCondition [rdf:type accessCondition:OpeningHoursSpecification ;" + 
             "                                                    l0:description ?Orari_di_apertura ] }" + 
             "   optional {" + 
             "    ?s smapit:hasOnlineContactPoint ?contactPoint ." + 
             "    optional { ?contactPoint smapit:hasWebSite [smapit:URL ?WebSite] }  " + 
             "   }   " + 
          
             "     FILTER(str(?Regione) = \"Campania\")" + 
             "    FILTER(str(?Disciplina) = \"Arte\")" + 
             "    FILTER(str(?Prenotazioni) = \"Ingresso libero\")" + 
           
             "  }" + 
             " }" + 
             " order by ?s" + 
             "}" + 
             "limit 10";

            // now creating query object
            Query query = QueryFactory.create(queryString);
            // initializing queryExecution factory with remote service.
            // **this actually was the main problem I couldn't figure out.**
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dati.beniculturali.it/sparql", query);

            //after it goes standard query execution and result processing which can
            // be found in almost any Jena/SPARQL tutorial.
            try {
            	Collection<CulturalPlace> posto = new LinkedList<CulturalPlace>();

                ResultSet results = qexec.execSelect();
            	
                for (; results.hasNext();) {
                	
                	QuerySolution i = results.next();
                	CulturalPlace place = new CulturalPlace();
                    if(i.get("NOME")!=null) {
                	place.setNome(i.get("NOME").toString());}
                    if(i.get("Indirizzo")!=null) {
                	place.setAddress(i.get("Indirizzo").toString());}
                    if(i.get("Identifier")!=null) {
                    	place.setCode(i.get("Identifier").toString());}
                    if(i.get("Comune")!=null) {
                    place.setComune(i.get("Comune").toString());}
                    if(i.get("Regione")!=null) {
                	place.setRegione(i.get("Regione").toString());}
                	if(i.get("Provincia")!=null) {
                	place.setProvincia(i.get("Provincia").toString());}
                	if(i.get("Longitudine")!=null) {
                	place.setLongi(Float.parseFloat((i.get("Longitudine")).toString()));}
                	if(i.get("Latitudine")!=null) {
                	place.setLati(Float.parseFloat((i.get("Latitudine")).toString()));}
                	if(i.get("WebSite")!=null) {
                		place.setWebsite(i.get("WebSite").toString());}
                	if(i.get("Descrizione")!=null) {
                	place.setDescrizione(i.get("Descrizione").toString());}
                	if(i.get("pic")!=null) {
                		place.setImmagine(i.get("pic").toString());}
    	
                	if(place!=null) {
                	posto.add(place);} 	
                	System.out.print(place.toString()+"\n");
                	System.out.print(posto.size());
                // Result processing is done here.

                }            

                qexec.close();
                return posto;
            
            }
            catch (Exception e) {
         		System.out.print(e);
        		return null;

    		} 
            
        }
    	
    	public static CulturalPlace GetPlace(String id) throws Exception{
    		 String queryString=
             		
             		
    	             "prefix cis:<http://dati.beniculturali.it/cis/>" + 
    	             "prefix l0:<https://w3id.org/italia/onto/l0/>" + 
    	             "prefix clvapit: <https://w3id.org/italia/onto/CLV/>" + 
    	             "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
    	             "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
    	             "prefix foaf: <http://xmlns.com/foaf/0.1/>" +
    	             "prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>" +
    	             "prefix accessCondition:<https://w3id.org/italia/onto/AccessCondition/>"+
    	             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
    	             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
    	             "prefix potapit:<https://w3id.org/italia/onto/POT/>"+

    	             
    	             "select * where {" + 
    	             " select distinct ?s" + 
    	             " ?pic" + 
    	             " ?NOME" + 
    	             " ?Descrizione" + 
    	             " ?Identifier" + 
    	             " ?Latitudine" + 
    	             " ?Longitudine" + 
    	             " ?Disciplina" + 
    	             " ?Indirizzo" + 
    	             " ?Codice_postale" + 
    	             " ?Comune" + 
    	             " ?Regione" + 
    	             " ?Provincia" + 
    	             " ?Prenotazioni" + 
    	             " ?WebSite" + 
    	             " where {" + 
    	             "  graph <http://dati.beniculturali.it/mibact/luoghi> {" + 
    	             "?s rdf:type cis:CulturalInstituteOrSite ;" + 
    	             "      cis:institutionalCISName ?NOME ." + 
    	             "   optional { ?s foaf:depiction ?pic}" + 
    	             "   optional { ?s l0:description ?Descrizione }" + 
    	             "   optional { ?s l0:identifier ?Identifier }" + 
    	             "   optional { ?s geo:lat ?Latitudine }" + 
    	             "   optional { ?s geo:long ?Longitudine }" + 
    	             "   optional { ?s cis:hasDiscipline [l0:name ?Disciplina] }" + 
    	             "   optional {" + 
    	             "    ?s cis:hasSite [cis:siteAddress ?address ] ." + 
    	             "    optional { ?address clvapit:fullAddress ?Indirizzo }" + 
    	             "    optional { ?address clvapit:postCode ?Codice_postale }" + 
    	             "    optional { ?address clvapit:hasCity [rdfs:label ?Comune] }" + 
    	             "    optional { ?address clvapit:hasProvince [rdfs:label ?Provincia] }" + 
    	             "    optional { ?address clvapit:hasRegion [rdfs:label ?Regione]}" + 
    	             "   }" + 
    	             "   optional {?s accessCondition:hasAccessCondition [rdf:type accessCondition:Booking ;" + 
    	             "                                                    rdfs:label ?Prenotazioni] }" + 
    	             "   optional {?s accessCondition:hasAccessCondition [rdf:type accessCondition:OpeningHoursSpecification ;" + 
    	             "                                                    l0:description ?Orari_di_apertura ] }" + 
    	             "   optional {" + 
    	             "    ?s smapit:hasOnlineContactPoint ?contactPoint ." + 
    	             "    optional { ?contactPoint smapit:hasWebSite [smapit:URL ?WebSite] }  " + 
    	             "   }   " + 
    	          
    	             "     FILTER(str(?Identifier) = \""+id+"\")" + 
    	            
    	           
    	             "  }" + 
    	             " }" + 
    	             " order by ?s" + 
    	             "}" + 
    	             "limit 10";

            // now creating query object
            Query query = QueryFactory.create(queryString);
            // initializing queryExecution factory with remote service.
            // **this actually was the main problem I couldn't figure out.**
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dati.beniculturali.it/sparql", query);

            //after it goes standard query execution and result processing which can
            // be found in almost any Jena/SPARQL tutorial.
            try {

                ResultSet results = qexec.execSelect();
            	
                	
                	QuerySolution i = results.next();
                	CulturalPlace place = new CulturalPlace();
                    if(i.get("NOME")!=null) {
                	place.setNome(i.get("NOME").toString().replace("\\", "").replace("@it", ""));}
                    if(i.get("Indirizzo")!=null) {
                	place.setAddress(i.get("Indirizzo").toString());}
                    if(i.get("Identifier")!=null) {
                    	place.setCode(i.get("Identifier").toString());}
                    if(i.get("Comune")!=null) {
                    place.setComune(i.get("Comune").toString());}
                    if(i.get("Regione")!=null) {
                	place.setRegione(i.get("Regione").toString());}
                	if(i.get("Provincia")!=null) {
                	place.setProvincia(i.get("Provincia").toString());}
                	if(i.get("Longitudine")!=null) {
                	place.setLongi(Float.parseFloat((i.get("Longitudine")).toString()));}
                	if(i.get("Latitudine")!=null) {
                	place.setLati(Float.parseFloat((i.get("Latitudine")).toString()));}
                	if(i.get("WebSite")!=null) {
                		place.setWebsite(i.get("WebSite").toString());}
                	if(i.get("Descrizione")!=null) {
                	place.setDescrizione(i.get("Descrizione").toString());}
                	if(i.get("Disciplina")!=null) {
                    	place.setTipo(i.get("Disciplina").toString());}
                	if(i.get("pic")!=null) {
                		place.setImmagine(i.get("pic").toString());}
    	
                	 	
                	System.out.print(place.toString()+"\n");
                // Result processing is done here.
                    return place;

                       
            
            }
            catch (Exception e) {
         		System.out.print(e);
        		return null;

    		} 
            
        } 

    	public static City GetInfoCity(String id) throws Exception{
    		
           	System.out.print(id);

   		 String queryString=
            		
   				 "prefix foaf: <http://xmlns.com/foaf/0.1/>" +
   	    	       "prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>" +
   					"prefix dbo: <http://dbpedia.org/ontology/>"	+ 
   	    	       
   	             "select distinct" + 
   	             
   	             "?Comuni" + 
   	             "?abs" + 
   	  
   	             "?pic" + 
   	             "?lat" + 
   	             "?long" + 
   	             "?site" + 
   	             " where {" + 
   	             "?Comuni a dbo:PopulatedPlace" + 
   	             
   	             
   	             " FILTER regex(?Comuni, \"/"+id.replace(" ", "_")+"$\")" + 
   	             
   	             "optional { ?Comuni dbo:abstract ?abs }" + 
   	             " FILTER langMatches( lang(?abs), \"IT\" )" + 
   	         
   	        
   	             "optional { ?Comuni foaf:depiction ?pic }" + 
   	             "optional { ?Comuni geo:lat ?lat }" + 
   	             "optional { ?Comuni geo:long ?long }" + 
   	             "optional { ?Comuni foaf:homepage ?site }" + 
   
   	             "}"; 

           // now creating query object
           Query query = QueryFactory.create(queryString);
           // initializing queryExecution factory with remote service.
           // **this actually was the main problem I couldn't figure out.**
           QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql/", query);

           //after it goes standard query execution and result processing which can
           // be found in almost any Jena/SPARQL tutorial.
           try {

               ResultSet results = qexec.execSelect();
           	
               	
               	QuerySolution i = results.next();
               	City place = new City();
            

  
               	if(i.get("long")!=null) {
               	place.setLongi(Float.parseFloat((i.get("long")).toString().replace("^^http://www.w3.org/2001/XMLSchema#float", "")));}
               	if(i.get("lat")!=null) {
               	place.setLati(Float.parseFloat((i.get("lat")).toString().replace("^^http://www.w3.org/2001/XMLSchema#float", "")));}
               	if(i.get("site")!=null) {
               		place.setWebsite(i.get("site").toString());}
               	if(i.get("abs")!=null) {
               	place.setDescrizione(i.get("abs").toString());}
               	if(i.get("pic")!=null) {
               		place.setImmagine(i.get("pic").toString());}
               	 	
               	System.out.print(place.toString()+"\n");
               // Result processing is done here.
                   return place;

                      
           
           }
           catch (Exception e) {
        		System.out.print(e);
       		return null;

   		} 
           
       } 

    	public static Collection<CulturalPlace> GetSearch(String name, String discipline, String division, String city,
    			String orderby, String criterio ) throws Exception{
    		 String boo =   "  }" ;
    		 
    		if(!discipline.equalsIgnoreCase("tutti")) {
  	             boo="FILTER regex(?Disciplina, \""+discipline+"\") }";

                 } 
    		  
    		  
   	         

    		String 
    		queryString=
             		
    				 "prefix cis:<http://dati.beniculturali.it/cis/>" + 
    			             "prefix l0:<https://w3id.org/italia/onto/l0/>" + 
    			             "prefix clvapit: <https://w3id.org/italia/onto/CLV/>" + 
    			             "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
    			             "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
    			             "prefix foaf: <http://xmlns.com/foaf/0.1/>" +
    			             "prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>" +
    			             "prefix accessCondition:<https://w3id.org/italia/onto/AccessCondition/>"+
    			             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
    			             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
    			             "prefix potapit:<https://w3id.org/italia/onto/POT/>"+

    			             
    			             "select * where {" + 
    			             " select distinct ?s" + 
    			             " ?pic" + 
    			             " ?NOME" +     
    			             " ?Identifier" + 
    			             " ?Disciplina" + 
    			             " ?Comune" + 
    			             " ?Regione" + 
    			             " ?Provincia" + 
    			             " where {" + 
    			             "  graph <http://dati.beniculturali.it/mibact/luoghi> {" + 
    			             "?s rdf:type cis:CulturalInstituteOrSite ;" + 
    			             "      cis:institutionalCISName ?NOME ." + 
    			             "   optional { ?s foaf:depiction ?pic}" + 
    			             "   optional { ?s l0:identifier ?Identifier }" + 
    			             "   optional { ?s geo:lat ?Latitudine }" + 
    			             "   optional { ?s geo:long ?Longitudine }" + 
    			             "   optional { ?s cis:hasDiscipline [l0:name ?Disciplina] }" + 
    			             "   optional {" + 
    			             
    			             "    ?s cis:hasSite [cis:siteAddress ?address ] ." + 			   
    			             "    optional { ?address clvapit:hasCity [rdfs:label ?Comune] }" + 
    			             "    optional { ?address clvapit:hasProvince [rdfs:label ?Provincia] }" + 
    			             "    optional { ?address clvapit:hasRegion [rdfs:label ?Regione]}" + 
    			             "   }" +    			    
    			          
   	                             
   	                            " FILTER regex(?NOME, \""+name+"\", \"i\")" +   
   	                        "FILTER regex(?"+division+", \""+city+"\")" +
   	                        	boo+  	                       
    			             " }" + 
    			             " order by"+orderby+" (?"+criterio+ ")"+
    			             "}";
    		
   		 
    	  // now creating query object
        Query query = QueryFactory.create(queryString);
        // initializing queryExecution factory with remote service.
        // **this actually was the main problem I couldn't figure out.**
        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dati.beniculturali.it/sparql", query);

        //after it goes standard query execution and result processing which can
        // be found in almost any Jena/SPARQL tutorial.
        try {
        	Collection<CulturalPlace> posto = new LinkedList<CulturalPlace>();

            ResultSet results = qexec.execSelect();
        	
            for (; results.hasNext();) {
            	
            	QuerySolution i = results.next();
            	CulturalPlace place = new CulturalPlace();
                if(i.get("NOME")!=null) {
            	place.setNome(i.get("NOME").toString().replace("@it", "").replace("\\", ""));}
                
                if(i.get("Identifier")!=null) {
                	place.setCode(i.get("Identifier").toString());}
                if(i.get("Comune")!=null) {
                place.setComune(i.get("Comune").toString());}
                if(i.get("Regione")!=null) {
            	place.setRegione(i.get("Regione").toString());}
            	if(i.get("Provincia")!=null) {
            	place.setProvincia(i.get("Provincia").toString());}
            	if(i.get("pic")!=null) {
            		place.setImmagine(i.get("pic").toString());}
            	   if(i.get("Disciplina")!=null) {
                   	place.setTipo(i.get("Disciplina").toString());}
            	   if(i.get("Longitudine")!=null) {
                   	place.setLongi(Float.parseFloat(i.get("Longitudine").toString()));}
                   if(i.get("Latitudine")!=null) {
                   	place.setLati(Float.parseFloat(i.get("Latitudine").toString()));}
                   
                   
            	if(place!=null) {
            	posto.add(place);} 	
            	System.out.print(place.toString()+"\n");
            	System.out.print(posto.size());
            // Result processing is done here.

            }            

            qexec.close();
            return posto;
        
        }
        catch (Exception e) {
     		System.out.print(e);
    		return null;

		} 
        
    }
  	
    	
    	public static Collection<Event> GetEventsQuery(String posto) throws Exception{

            String queryString=
            			
      				 "prefix cis:<http://dati.beniculturali.it/cis/>" + 
      				 "prefix dc:<http://purl.org/dc/elements/1.1/>" + 
      				 "prefix l0:<https://w3id.org/italia/onto/l0/>" + 
      				 "prefix TI:<https://w3id.org/italia/onto/TI/>" + 
      				 "prefix SM:<https://w3id.org/italia/onto/SM/>" + 
      				 "prefix POT:<https://w3id.org/italia/onto/POT/>" + 
      				 "prefix AC:<https://w3id.org/italia/onto/AccessCondition/>" + 
      				 "prefix CLV:<https://w3id.org/italia/onto/CLV/>" + 
      				 "prefix geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>" + 
      				 "prefix foaf:<http://xmlns.com/foaf/0.1/>" + 
      				 "prefix xsd:<http://www.w3.org/2001/XMLSchema>" + 


      				 "select distinct (?s as ?EVENTO) ?CATEGORIA ?NOME" + 
      				 "?DESCRIZIONE_IT " + 
      				 "?DATA_INIZIO_EVENTO" + 
      				 "?DATA_FINE_EVENTO" + 
      				 "?sitename" + 
      				 "?VIA ?NUMERO_CIVICO ?CAP ?Comune ?Provincia ?Regione" + 
      				 "?LATITUDINE ?LONGITUDINE ?IMMAGINE" + 
      				 " where {" + 
      				 
      				 " ?s a cis:CulturalEvent optional " + 
      				 " { ?s dc:type ?CATEGORIA } optional " + 
      				 " { ?s l0:name ?NOME } optional" + 
      				 " { ?s l0:description ?DESCRIZIONE_IT"
      				 + " filter langMatches( lang(?DESCRIZIONE_IT), \"it\" )} optional" + 
      				 " { ?s TI:atTime/TI:startTime ?DATA_INIZIO_EVENTO } optional " + 
      				 " { ?s TI:atTime/TI:endTime ?DATA_FINE_EVENTO } optional " + 

      				 
      				 "   { ?s cis:isHostedBySite/l0:name  ?sitename} optional" + 
      				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasStreetToponym/CLV:officialStreetName ?VIA } optional " + 
      				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasCity/l0:name ?Comune } optional" + 
      				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasProvince/l0:name ?Provincia } optional " + 
      				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasRegion/l0:name ?Regione } optional " + 
      				 " { ?s geo:lat ?LATITUDINE } optional" + 
      				 " { ?s geo:long ?LONGITUDINE } optional " + 
      				 " { ?s foaf:depiction ?IMMAGINE }" + 
       	             " FILTER regex(?sitename, \""+posto+"\")" + 

      				 
      				 "} limit 10"; 


            // now creating query object
            Query query = QueryFactory.create(queryString);
            // initializing queryExecution factory with remote service.
            // **this actually was the main problem I couldn't figure out.**
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dati.beniculturali.it/sparql", query);

            //after it goes standard query execution and result processing which can
            // be found in almost any Jena/SPARQL tutorial.
            try {
            	Collection<Event> events = new LinkedList<Event>();

                ResultSet results = qexec.execSelect();
            	
                for (; results.hasNext();) {
                	
                	QuerySolution i = results.next();
                	Event place = new Event();
                    if(i.get("NOME")!=null) {
                	place.setNome(i.get("NOME").toString());}
                    if(i.get("VIA")!=null) {
                	place.setAddress(i.get("VIA").toString());}
                    if(i.get("EVENTO")!=null) {
                    	place.setEvento(i.get("EVENTO").toString());}
                    if(i.get("sitename")!=null) {
                    	place.setLuogo(i.get("sitename").toString());}  
                    if(i.get("Comune")!=null) {
                    place.setComune(i.get("Comune").toString());}
                    if(i.get("Regione")!=null) {
                	place.setRegione(i.get("Regione").toString());}
                	if(i.get("Provincia")!=null) {
                	place.setProvincia(i.get("Provincia").toString());}
                	if(i.get("LONGITUDINE")!=null) {
                	place.setLongi(Float.parseFloat((i.get("LONGITUDINE")).toString()));}
                	if(i.get("LATITUDINE")!=null) {
                	place.setLati(Float.parseFloat((i.get("LATITUDINE")).toString()));}
                	if(i.get("SITO_WEB ")!=null) {
                		place.setWebsite(i.get("SITO_WEB ").toString());}
                	if(i.get("DESCRIZIONE_IT")!=null) {
                	place.setDescrizione(i.get("DESCRIZIONE_IT").toString());}
                	if(i.get("?IMMAGINE")!=null) {
                		place.setImmagine(i.get("?IMMAGINE").toString());}
                	if(i.get("DATA_INIZIO_EVENTO")!=null) {
                    	place.setDatainizio(i.get("DATA_INIZIO_EVENTO").toString());}
                    	if(i.get("?DATA_FINE_EVENTO")!=null) {
                    		place.setDatafine(i.get("?DATA_FINE_EVENTO").toString());}
                	
    	
                	if(place!=null) {
                	events.add(place);} 	
                	System.out.print(place.toString()+"\n");
                	System.out.print(events.size());
                // Result processing is done here.

                }            

                qexec.close();
                return events;
            
            }
            catch (Exception e) {
         		System.out.print(e);
        		return null;

    		} 
            
        }


    	public static Collection<Event> GetEventsSearch(String name, String discipline, String division, String city,
    			String orderby, String criterio ) throws Exception{

            String queryString=
            			
      				 "prefix cis:<http://dati.beniculturali.it/cis/>" + 
      				 "prefix dc:<http://purl.org/dc/elements/1.1/>" + 
      				 "prefix l0:<https://w3id.org/italia/onto/l0/>" + 
      				 "prefix TI:<https://w3id.org/italia/onto/TI/>" + 
      				 "prefix SM:<https://w3id.org/italia/onto/SM/>" + 
      				 "prefix POT:<https://w3id.org/italia/onto/POT/>" + 
      				 "prefix AC:<https://w3id.org/italia/onto/AccessCondition/>" + 
      				 "prefix CLV:<https://w3id.org/italia/onto/CLV/>" + 
      				 "prefix geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>" + 
      				 "prefix foaf:<http://xmlns.com/foaf/0.1/>" + 
      				 "prefix xsd:<http://www.w3.org/2001/XMLSchema>" + 


      				 "select distinct (?s as ?EVENTO) ?CATEGORIA ?NOME" + 
      				 "?DESCRIZIONE_IT " + 
      				 "?DATA_INIZIO_EVENTO" + 
      				 "?DATA_FINE_EVENTO" + 
      				 "?sitename" + 
      				 "?VIA ?NUMERO_CIVICO ?CAP ?Comune ?Provincia ?Regione" + 
      				 "?LATITUDINE ?LONGITUDINE ?IMMAGINE" + 
      				 " where {" + 
      				 
      				 " ?s a cis:CulturalEvent optional " + 
      				 " { ?s dc:type ?CATEGORIA } optional " + 
      				 " { ?s l0:name ?NOME } optional" + 
      				 " { ?s l0:description ?DESCRIZIONE_IT"
      				 + " filter langMatches( lang(?DESCRIZIONE_IT), \"it\" )} optional" + 
      				 " { ?s TI:atTime/TI:startTime ?DATA_INIZIO_EVENTO } optional " + 
      				 " { ?s TI:atTime/TI:endTime ?DATA_FINE_EVENTO } optional " + 

      				 
      				 "   { ?s cis:isHostedBySite/l0:name  ?sitename} optional" + 
      				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasStreetToponym/CLV:officialStreetName ?VIA } optional " + 
      				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasCity/l0:name ?Comune } optional" + 
      				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasProvince/l0:name ?Provincia } optional " + 
      				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasRegion/l0:name ?Regione } optional " + 
      				 " { ?s geo:lat ?LATITUDINE } optional" + 
      				 " { ?s geo:long ?LONGITUDINE } optional " + 
      				 " { ?s foaf:depiction ?IMMAGINE }" + 

   	          " FILTER regex(?NOME, \""+name+"\", \"i\")" +
   	               "FILTER regex(?"+division+", \""+city+"\")" +
   	             //"FILTER regex(?Disciplina, \""+discipline+"\")" +

      				 
      				 "}order by"+orderby+" (?"+criterio+ ")";
 


            // now creating query object
            Query query = QueryFactory.create(queryString);
            // initializing queryExecution factory with remote service.
            // **this actually was the main problem I couldn't figure out.**
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dati.beniculturali.it/sparql", query);

            //after it goes standard query execution and result processing which can
            // be found in almost any Jena/SPARQL tutorial.
            try {
            	Collection<Event> events = new LinkedList<Event>();

                ResultSet results = qexec.execSelect();
            	
                for (; results.hasNext();) {
                	
                	QuerySolution i = results.next();
                	Event place = new Event();
                    if(i.get("NOME")!=null) {
                	place.setNome(i.get("NOME").toString().replace("@it", "").replace("\\", ""));}
                    if(i.get("VIA")!=null) {
                	place.setAddress(i.get("VIA").toString());}
                    if(i.get("EVENTO")!=null) {
                    	place.setEvento(i.get("EVENTO").toString());}
                    if(i.get("sitename")!=null) {
                    	place.setLuogo(i.get("sitename").toString());}  
                    if(i.get("Comune")!=null) {
                    place.setComune(i.get("Comune").toString());}
                    if(i.get("Regione")!=null) {
                	place.setRegione(i.get("Regione").toString());}
                	if(i.get("Provincia")!=null) {
                	place.setProvincia(i.get("Provincia").toString());}
                	if(i.get("LONGITUDINE")!=null) {
                	place.setLongi(Float.parseFloat((i.get("LONGITUDINE")).toString()));}
                	if(i.get("LATITUDINE")!=null) {
                	place.setLati(Float.parseFloat((i.get("LATITUDINE")).toString()));}
                	if(i.get("SITO_WEB ")!=null) {
                		place.setWebsite(i.get("SITO_WEB ").toString());}
                	if(i.get("DESCRIZIONE_IT")!=null) {
                	place.setDescrizione(i.get("DESCRIZIONE_IT").toString());}
                	if(i.get("?IMMAGINE")!=null) {
                		place.setImmagine(i.get("?IMMAGINE").toString());}
                	if(i.get("DATA_INIZIO_EVENTO")!=null) {
                    	place.setDatainizio(i.get("DATA_INIZIO_EVENTO").toString());}
                    	if(i.get("?DATA_FINE_EVENTO")!=null) {
                    		place.setDatafine(i.get("?DATA_FINE_EVENTO").toString());}
                	
    	
                	if(place!=null) {
                	events.add(place);} 	
                	System.out.print(place.toString()+"\n");
                	System.out.print(events.size());
                // Result processing is done here.

                }            

                qexec.close();
                return events;
            
            }
            catch (Exception e) {
         		System.out.print(e);
        		return null;

    		} 
            
        }

    	public static Event GetEvent(String id) throws Exception{

            String queryString=
            			
            		 "prefix cis:<http://dati.beniculturali.it/cis/>" + 
              				 "prefix dc:<http://purl.org/dc/elements/1.1/>" + 
              				 "prefix l0:<https://w3id.org/italia/onto/l0/>" + 
              				 "prefix TI:<https://w3id.org/italia/onto/TI/>" + 
              				 "prefix SM:<https://w3id.org/italia/onto/SM/>" + 
              				 "prefix POT:<https://w3id.org/italia/onto/POT/>" + 
              				 "prefix AC:<https://w3id.org/italia/onto/AccessCondition/>" + 
              				 "prefix CLV:<https://w3id.org/italia/onto/CLV/>" + 
              				 "prefix geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>" + 
              				 "prefix foaf:<http://xmlns.com/foaf/0.1/>" + 
              				 "prefix xsd:<http://www.w3.org/2001/XMLSchema>" + 


              				 "select distinct (?s as ?EVENTO) ?CATEGORIA ?NOME" + 
              				 "?DESCRIZIONE_IT " + 
              				 "?DATA_INIZIO_EVENTO" + 
              				 "?DATA_FINE_EVENTO" + 
              				 "?sitename ?SITO_WEB" + 
              				 "?VIA ?NUMERO_CIVICO ?CAP ?Comune ?Provincia ?Regione" + 
              				 "?LATITUDINE ?LONGITUDINE ?IMMAGINE" + 
              				 " where {" + 
              				 
              				 " ?s a cis:CulturalEvent optional " + 
              				 " { ?s dc:type ?CATEGORIA } optional " + 
              				 " { ?s l0:name ?NOME } optional" + 
              				 " { ?s l0:description ?DESCRIZIONE_IT"
              				 + " filter langMatches( lang(?DESCRIZIONE_IT), \"it\" )} optional" + 
              				 " { ?s TI:atTime/TI:startTime ?DATA_INIZIO_EVENTO } optional " + 
              				 " { ?s TI:atTime/TI:endTime ?DATA_FINE_EVENTO } optional " + 
              				"{ ?s SM:hasOnlineContactPoint ?cp1  filter (!contains(str(?cp1),\"Biglietteria\") && !contains(str(?cp1),\"Prenotazioni\")) optional"+
              			  "{ ?cp1 SM:hasWebSite/SM:URL ?SITO_WEB }} optional "+
              				 
              				 "   { ?s cis:isHostedBySite/l0:name  ?sitename} optional" + 
              				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasStreetToponym/CLV:officialStreetName ?VIA } optional " + 
              				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasCity/l0:name ?Comune } optional" + 
              				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasProvince/l0:name ?Provincia } optional " + 
              				 " { ?s cis:isHostedBySite/cis:siteAddress/CLV:hasRegion/l0:name ?Regione } optional " + 
              				 " { ?s geo:lat ?LATITUDINE } optional" + 
              				 " { ?s geo:long ?LONGITUDINE } optional " + 
              				 " { ?s foaf:depiction ?IMMAGINE }" + 
              				 
              				 //FILTER(str(?s) = "http://dati.beniculturali.it/mibact/eventi/resource/CulturalEvent/131125")
              				 
       	             " FILTER (str(?s)=\""+id+"\")" + 

      				 
      				 "} "; 


            // now creating query object
            Query query = QueryFactory.create(queryString);
            // initializing queryExecution factory with remote service.
            // **this actually was the main problem I couldn't figure out.**
            QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dati.beniculturali.it/sparql", query);

            //after it goes standard query execution and result processing which can
            // be found in almost any Jena/SPARQL tutorial.
            try {

                ResultSet results = qexec.execSelect();
       
                QuerySolution i = results.next();
                	Event place = new Event();
                    if(i.get("NOME")!=null) {
                	place.setNome(i.get("NOME").toString().replace("@it", ""));}
                    if(i.get("VIA")!=null) {
                	place.setAddress(i.get("VIA").toString());}
                    if(i.get("EVENTO")!=null) {
                    	place.setEvento(i.get("EVENTO").toString());}
                    if(i.get("sitename")!=null) {
                    	place.setLuogo(i.get("sitename").toString());}  
                    if(i.get("Comune")!=null) {
                    place.setComune(i.get("Comune").toString());}
                    if(i.get("Regione")!=null) {
                	place.setRegione(i.get("Regione").toString());}
                	if(i.get("Provincia")!=null) {
                	place.setProvincia(i.get("Provincia").toString());}
                	if(i.get("LONGITUDINE")!=null) {
                	place.setLongi(Float.parseFloat((i.get("LONGITUDINE")).toString()));}
                	if(i.get("LATITUDINE")!=null) {
                	place.setLati(Float.parseFloat((i.get("LATITUDINE")).toString()));}
                	if(i.get("SITO_WEB")!=null) {
                		place.setWebsite(i.get("SITO_WEB").toString());}
                	if(i.get("DESCRIZIONE_IT")!=null) {
                	place.setDescrizione(i.get("DESCRIZIONE_IT").toString());}
                	if(i.get("?IMMAGINE")!=null) {
                		place.setImmagine(i.get("?IMMAGINE").toString());}
                	if(i.get("DATA_INIZIO_EVENTO")!=null) {
                    	place.setDatainizio(i.get("DATA_INIZIO_EVENTO").toString().replace("+02:00^^xsd:dateTime", "").replace("+01:00^^xsd:dateTime", ""));}
                    	if(i.get("?DATA_FINE_EVENTO")!=null) {
                    		place.setDatafine(i.get("?DATA_FINE_EVENTO").toString().replace("+02:00^^xsd:dateTime", "").replace("+01:00^^xsd:dateTime", ""));}
                	
                   System.out.print(place.toString()+"\n");

                   qexec.close();
                   return place;
                	
            }
            catch (Exception e) {
         		System.out.print(e);
        		return null;

    		} 
            
        }

    	public static Collection<CulturalPlace> GetTripPlan( int prenotazione, String servizi, String servizi2, String discipline,
    			String discipline2, int number, String location, String division ) throws Exception{
    		
    		String ser = " " ;
    		 String dis =   " " ;
    		 String dis2 =   "  }" ;
    		 String pren=  "FILTER regex(?Prenotazioni, \"Ingresso libero\")";
    		 
    		if(!(discipline.equalsIgnoreCase("tutti")&&discipline2.equalsIgnoreCase("tutti"))) {
    			
    			if(discipline.equalsIgnoreCase("tutti")) {
  	             dis="FILTER regex(?Disciplina, \""+discipline2+"\",\"i\") ";

    			} else if (discipline2.equalsIgnoreCase("tutti")) {
     	            
    				dis="FILTER regex(?Disciplina, \""+discipline+"\",\"i\") ";   				
    				
    			} else {
    				 
    				dis="FILTER regex(?Disciplina, \""+discipline+"|"+discipline2+"\",\"i\")";
    				
    			}
                 } 
    		
    		if(prenotazione == 1) {
    			pren = "FILTER regex(?Prenotazioni, \"Prenotazione obbligatoria\",\"i\")";
    			
    		} else if(prenotazione == 3) {
    			
    			pren = " ";
    		}
    		
    		if(!(servizi.equalsIgnoreCase("tutti")&&servizi2.equalsIgnoreCase("tutti"))) {
    			
    			if(servizi.equalsIgnoreCase("tutti")) {
  	             ser="FILTER regex(?Servizi, \""+servizi2+"\",\"i\") ";

    			} else if (servizi2.equalsIgnoreCase("tutti")) {
     	            
    				ser="FILTER regex(?Servizi, \""+servizi+"\",\"i\") ";   				
    				
    			} else {
    				 
    				ser="FILTER regex(?Servizi, \""+servizi+"|"+servizi2+"\",\"i\")";
    				
    			}
                 } 
    
    		
    		  
   	         

    		String 
    		queryString=
             		
    				"prefix cis:<http://dati.beniculturali.it/cis/>" + 
    	    	             "prefix l0:<https://w3id.org/italia/onto/l0/>" + 
    	    	             "prefix clvapit: <https://w3id.org/italia/onto/CLV/>" + 
    	    	             "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
    	    	             "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
    	    	             "prefix foaf: <http://xmlns.com/foaf/0.1/>" +
    	    	             "prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>" +
    	    	             "prefix accessCondition:<https://w3id.org/italia/onto/AccessCondition/>"+
    	    	             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
    	    	             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
    	    	             "prefix potapit:<https://w3id.org/italia/onto/POT/>"+

    	    	             
    	    	             "select * where {" + 
    	    	             " select distinct ?s" + 
    	    	             " ?pic" + 
    	    	             " ?NOME" + 
    	    	             " ?Descrizione" + 
    	    	             " ?Identifier" + 
    	    	             " ?Latitudine" + 
    	    	             " ?Longitudine" + 
    	    	             " ?Disciplina" + 
    	    	             " ?Indirizzo" + 
    	    	             " ?Codice_postale" + 
    	    	             " ?Comune" + 
    	    	             " ?Regione" + 
    	    	             " ?Provincia" + 
    	    	             " ?Prenotazioni" + 
    	    	             " ?WebSite "+  	    	           
    	    	             "?Servizi" + 
    	    	             
    	    	             " where {" + 
    	    	             "  graph <http://dati.beniculturali.it/mibact/luoghi> {" + 
    	    	             "?s rdf:type cis:CulturalInstituteOrSite ;" + 
    	    	             "      cis:institutionalCISName ?NOME ." + 
    	    	             "   optional { ?s foaf:depiction ?pic}" + 
    	    	             "   optional { ?s l0:description ?Descrizione }" + 
    	    	             "filter langMatches( lang(?Descrizione), \"it\" )"+
    	    	             "   optional { ?s l0:identifier ?Identifier }" + 
    	    	             "   optional { ?s geo:lat ?Latitudine }" + 
    	    	             "   optional { ?s geo:long ?Longitudine }" + 
    	    	             "   optional { ?s cis:hasDiscipline [l0:name ?Disciplina] }" + 
    	    	             "optional { ?s cis:providesService [l0:name ?Servizi] }"+
    	    	             "   optional {" + 
    	    	             "    ?s cis:hasSite [cis:siteAddress ?address ] ." + 
    	    	             "    optional { ?address clvapit:fullAddress ?Indirizzo }" + 
    	    	             "    optional { ?address clvapit:postCode ?Codice_postale }" + 
    	    	             "    optional { ?address clvapit:hasCity [rdfs:label ?Comune] }" + 
    	    	             "    optional { ?address clvapit:hasProvince [rdfs:label ?Provincia] }" + 
    	    	             "    optional { ?address clvapit:hasRegion [rdfs:label ?Regione]}" + 
    	    	             "   }" + 
    	    	             "   optional {?s accessCondition:hasAccessCondition [rdf:type accessCondition:Booking ;" + 
    	    	             "                                                    rdfs:label ?Prenotazioni] }" +     			            		    			          
    	    	             "FILTER regex(?"+division+", \""+location+"\",\"i\")" +
   	                        pren+
   	                        ser+
   	                         dis+  	                       
    			            dis2+
    			            
   	                        	" }" + 
    			          
    			             "}";
    		
   
   		 
    		
    		
    	  // now creating query object
        Query query = QueryFactory.create(queryString);
        // initializing queryExecution factory with remote service.
        // **this actually was the main problem I couldn't figure out.**
        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dati.beniculturali.it/sparql", query);

        //after it goes standard query execution and result processing which can
        // be found in almost any Jena/SPARQL tutorial.
        
    	int l = 0;    

    	boolean bool = false;
    	Collection<CulturalPlace> posto = new LinkedList<CulturalPlace>();
        Collection<QuerySolution> filtered = new ArrayList<QuerySolution>();

        try {

            ResultSet results = qexec.execSelect();
            ResultSet results2 = qexec.execSelect();
            QuerySolution j = results2.next();
        	
       	 for (; results.hasNext();) {
         	QuerySolution i = results.next();
    		
    		if(bool == false) {
    			filtered.add(i);
        		bool = true;
    		}
    		
             	QuerySolution v = results2.next();
             
            	if (!i.get("Identifier").toString().equalsIgnoreCase(v.get("Identifier").toString()))
            	{
            	
            		filtered.add(v);
            	
            		
            	}
               //	System.out.println(filtered.size());

       	 }

        }
        catch (Exception e) {
     		System.out.print(e);
		} 
        
       	System.out.println(filtered.size());

       	Iterator<?> it = filtered.iterator();
       	
    	while (it.hasNext() && l<number) {
    		QuerySolution i = (QuerySolution) it.next();
    			l=l+1;

            	CulturalPlace place = new CulturalPlace();
                if(i.get("NOME")!=null) {
            	place.setNome(i.get("NOME").toString().replace("@it", "").replace("\\", ""));}
                
                if(i.get("Identifier")!=null) {
                	place.setCode(i.get("Identifier").toString());}
                if(i.get("Comune")!=null) {
                place.setComune(i.get("Comune").toString());}
                if(i.get("Regione")!=null) {
            	place.setRegione(i.get("Regione").toString());}
                if(i.get("Descrizione")!=null) {
                	place.setDescrizione(i.get("Descrizione").toString());}
                if(i.get("Longitudine")!=null) {
                	place.setLongi(Float.parseFloat(i.get("Longitudine").toString()));}
                if(i.get("Latitudine")!=null) {
                	place.setLati(Float.parseFloat(i.get("Latitudine").toString()));}
                if(i.get("Disciplina")!=null) {
                	place.setTipo(i.get("Disciplina").toString());}
            	if(i.get("Provincia")!=null) {
            	place.setProvincia(i.get("Provincia").toString());}
            	if(i.get("pic")!=null) {
            		place.setImmagine(i.get("pic").toString());}
	
            	if(place!=null) {
            	posto.add(place);} 	
            	//System.out.print(place.toString()+"\n");
            	//System.out.print(posto.size());
            // Result processing is done here.

            		
        	
       	}
        
               

            qexec.close();
            return posto;
        
      
        
    }

    	public static Collection<CulturalPlace> GetCorrelati( String nome, String discipline,
    			String location, String division ) throws Exception{
    		
    	 

    		String queryString=
             		 				
    				"prefix cis:<http://dati.beniculturali.it/cis/>" + 
    	    	             "prefix l0:<https://w3id.org/italia/onto/l0/>" + 
    	    	             "prefix clvapit: <https://w3id.org/italia/onto/CLV/>" + 
    	    	             "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
    	    	             "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
    	    	             "prefix foaf: <http://xmlns.com/foaf/0.1/>" +
    	    	             "prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>" +
    	    	             "prefix accessCondition:<https://w3id.org/italia/onto/AccessCondition/>"+
    	    	             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
    	    	             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
    	    	             "prefix potapit:<https://w3id.org/italia/onto/POT/>"+

    	    	             
    	    	             " select * where {" + 
    	    	             " select distinct ?s" + 
    	    	             " ?pic" + 
    	    	             " ?NOME" + 
    	    	             " ?Descrizione" + 
    	    	             " ?Identifier" + 
    	    	             " ?Latitudine" + 
    	    	             " ?Longitudine" + 
    	    	             " ?Disciplina" + 
    	    	             " ?Indirizzo" + 
    	    	             " ?Codice_postale" + 
    	    	             " ?Comune" + 
    	    	             " ?Regione" + 
    	    	             " ?Provincia" + 
    	    	             " ?Prenotazioni" + 
    	    	             " ?WebSite "+  	    	           
    	    	             "?Servizi" + 
    	    	             
    	    	             " where {" + 
    	    	             "  graph <http://dati.beniculturali.it/mibact/luoghi> {" + 
    	    	             "?s rdf:type cis:CulturalInstituteOrSite ;" + 
    	    	             "      cis:institutionalCISName ?NOME ." + 
    	    	             "   optional { ?s foaf:depiction ?pic}" + 
    	    	             "   optional { ?s l0:description ?Descrizione }" + 
    	    	             "filter langMatches( lang(?Descrizione), \"it\" )"+
    	    	             "   optional { ?s l0:identifier ?Identifier }" + 
    	    	             "   optional { ?s geo:lat ?Latitudine }" + 
    	    	             "   optional { ?s geo:long ?Longitudine }" + 
    	    	             "   optional { ?s cis:hasDiscipline [l0:name ?Disciplina] }" + 
    	    	             "optional { ?s cis:providesService [l0:name ?Servizi] }"+
    	    	             "   optional {" + 
    	    	             "    ?s cis:hasSite [cis:siteAddress ?address ] ." + 
    	    	             "    optional { ?address clvapit:fullAddress ?Indirizzo }" + 
    	    	             "    optional { ?address clvapit:postCode ?Codice_postale }" + 
    	    	             "    optional { ?address clvapit:hasCity [rdfs:label ?Comune] }" + 
    	    	             "    optional { ?address clvapit:hasProvince [rdfs:label ?Provincia] }" + 
    	    	             "    optional { ?address clvapit:hasRegion [rdfs:label ?Regione]}" + 
    	    	             "   }" + 
    	    	             "   optional {?s accessCondition:hasAccessCondition [rdf:type accessCondition:Booking ;" + 
    	    	             "                                                    rdfs:label ?Prenotazioni] }" +     			            		    			          
    	    	             "FILTER regex(?"+division+", \""+location+"\",\"i\")" +
   	                      
						"FILTER regex(?Disciplina, \""+discipline+"\",\"i\") }"	 +                      
						
    	    	               "  FILTER (!regex(?NOME, \""+nome+"\",\"i\")) ."+ 
    	    	            
    			            
   	                        	" }" + 
    			          
    			             "}";
    		
    		String queryString2=
		 				
    				"prefix cis:<http://dati.beniculturali.it/cis/>" + 
    	    	             "prefix l0:<https://w3id.org/italia/onto/l0/>" + 
    	    	             "prefix clvapit: <https://w3id.org/italia/onto/CLV/>" + 
    	    	             "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
    	    	             "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
    	    	             "prefix foaf: <http://xmlns.com/foaf/0.1/>" +
    	    	             "prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>" +
    	    	             "prefix accessCondition:<https://w3id.org/italia/onto/AccessCondition/>"+
    	    	             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
    	    	             "prefix smapit:<https://w3id.org/italia/onto/SM/>"+
    	    	             "prefix potapit:<https://w3id.org/italia/onto/POT/>"+

    	    	             
    	    	             " select * where {" + 
    	    	             " select distinct ?s" + 
    	    	             " ?pic" + 
    	    	             " ?NOME" + 
    	    	             " ?Descrizione" + 
    	    	             " ?Identifier" + 
    	    	             " ?Latitudine" + 
    	    	             " ?Longitudine" + 
    	    	             " ?Disciplina" + 
    	    	             " ?Indirizzo" + 
    	    	             " ?Codice_postale" + 
    	    	             " ?Comune" + 
    	    	             " ?Regione" + 
    	    	             " ?Provincia" + 
    	    	             " ?Prenotazioni" + 
    	    	             " ?WebSite "+  	    	           
    	    	             "?Servizi" + 
    	    	             
    	    	             " where {" + 
    	    	             "  graph <http://dati.beniculturali.it/mibact/luoghi> {" + 
    	    	             "?s rdf:type cis:CulturalInstituteOrSite ;" + 
    	    	             "      cis:institutionalCISName ?NOME ." + 
    	    	             "   optional { ?s foaf:depiction ?pic}" + 
    	    	             "   optional { ?s l0:description ?Descrizione }" + 
    	    	             "filter langMatches( lang(?Descrizione), \"it\" )"+
    	    	             "   optional { ?s l0:identifier ?Identifier }" + 
    	    	             "   optional { ?s geo:lat ?Latitudine }" + 
    	    	             "   optional { ?s geo:long ?Longitudine }" + 
    	    	             "   optional { ?s cis:hasDiscipline [l0:name ?Disciplina] }" + 
    	    	             "optional { ?s cis:providesService [l0:name ?Servizi] }"+
    	    	             "   optional {" + 
    	    	             "    ?s cis:hasSite [cis:siteAddress ?address ] ." + 
    	    	             "    optional { ?address clvapit:fullAddress ?Indirizzo }" + 
    	    	             "    optional { ?address clvapit:postCode ?Codice_postale }" + 
    	    	             "    optional { ?address clvapit:hasCity [rdfs:label ?Comune] }" + 
    	    	             "    optional { ?address clvapit:hasProvince [rdfs:label ?Provincia] }" + 
    	    	             "    optional { ?address clvapit:hasRegion [rdfs:label ?Regione]}" + 
    	    	             "   }" + 
    	    	             "   optional {?s accessCondition:hasAccessCondition [rdf:type accessCondition:Booking ;" + 
    	    	             "                                                    rdfs:label ?Prenotazioni] }" +     			            		    			          
    	    	             "FILTER regex(?"+division+", \""+location+"\",\"i\")" +
    	    	             "FILTER contains(?Descrizione, \""+nome+"\")" +
  	    	               "  FILTER (!regex(?NOME, \""+nome+"\",\"i\")) ."+ 


    			            
   	                        	" }" + 
    			          
    			             " }}";
   		 
    		
    		
    	  // now creating query object
        Query query = QueryFactory.create(queryString);
        // initializing queryExecution factory with remote service.
        // **this actually was the main problem I couldn't figure out.**
        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dati.beniculturali.it/sparql", query);

        //after it goes standard query execution and result processing which can
        // be found in almost any Jena/SPARQL tutorial.
        Query query2 = QueryFactory.create(queryString2);
        // initializing queryExecution factory with remote service.
        // **this actually was the main problem I couldn't figure out.**
        QueryExecution qexec2 = QueryExecutionFactory.sparqlService("http://dati.beniculturali.it/sparql", query2);


    	boolean bool = false;
    	Collection<CulturalPlace> posto = new LinkedList<CulturalPlace>();
        Collection<QuerySolution> filtered = new ArrayList<QuerySolution>();

       try {

            ResultSet results = qexec.execSelect();
            ResultSet results2 = qexec.execSelect();
            QuerySolution j = results2.next();
        	
       	 for (; results.hasNext();) {
         	QuerySolution i = results.next();
    		
    		if(bool == false) {
    			filtered.add(i);
        		bool = true;
    		}
    		
             	QuerySolution v = results2.next();
             
            	if (!i.get("Identifier").toString().equalsIgnoreCase(v.get("Identifier").toString()))
            	{
            	
            		filtered.add(v);
            	
            		
            	}
               //	System.out.println(filtered.size());

       	 }

        }
        catch (Exception e) {
     		System.out.print(e);
		} 
        
        try {

            ResultSet results = qexec2.execSelect();
            ResultSet results2 = qexec2.execSelect();
            QuerySolution j = results2.next();
        	
       	 for (; results.hasNext();) {
         	QuerySolution i = results.next();
    		
    		if(bool == false) {
    			filtered.add(i);
        		bool = true;
    		}
    		
             	QuerySolution v = results2.next();
             
            	if (!i.get("Identifier").toString().equalsIgnoreCase(v.get("Identifier").toString()))
            	{
            	
            		filtered.add(v);
            	
            		
            	}
               //	System.out.println(filtered.size());

       	 }

        }
        catch (Exception e) {
     		System.out.print(e);
		}
        
  //     	System.out.println(filtered.size());

       	Iterator<?> it = filtered.iterator();
       	
    	while (it.hasNext() ) {
    		QuerySolution i = (QuerySolution) it.next();
    	
            	CulturalPlace place = new CulturalPlace();
                if(i.get("NOME")!=null) {
            	place.setNome(i.get("NOME").toString().replace("@it", "").replace("\\", ""));}
                
                if(i.get("Identifier")!=null) {
                	place.setCode(i.get("Identifier").toString());}
                if(i.get("Comune")!=null) {
                place.setComune(i.get("Comune").toString());}
                if(i.get("Regione")!=null) {
            	place.setRegione(i.get("Regione").toString());}
                if(i.get("Descrizione")!=null) {
                	place.setDescrizione(i.get("Descrizione").toString());}
                if(i.get("Longitudine")!=null) {
                	place.setLongi(Float.parseFloat(i.get("Longitudine").toString()));}
                if(i.get("Latitudine")!=null) {
                	place.setLati(Float.parseFloat(i.get("Latitudine").toString()));}
                if(i.get("Disciplina")!=null) {
                	place.setTipo(i.get("Disciplina").toString());}
            	if(i.get("Provincia")!=null) {
            	place.setProvincia(i.get("Provincia").toString());}
            	if(i.get("pic")!=null) {
            		place.setImmagine(i.get("pic").toString());}
	
            	if(place!=null) {
            	posto.add(place);} 	
            	//System.out.print(place.toString()+"\n");
            	//System.out.print(posto.size());
            // Result processing is done here.

            		
        	
       	}
        
               

            qexec.close();
            return posto;
             
 
}

}