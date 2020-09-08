package Trip;

import java.io.Serializable;

public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	String descrizione;
	String provincia;

	String regione;
	String website;
	String immagine;
	String nome;
	float longi;
	float lati;

	public City() {
		provincia = "";
		descrizione=  "";
		regione ="";
		website="";
		immagine="";
		nome = "";	
	    longi = 0;
		lati = 0;

	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getLongi() {
		return longi;
	}

	public void setLongi(float longi) {
		this.longi = longi;
	}

	public float getLati() {
		return lati;
	}

	public void setLati(float lati) {
		this.lati = lati;
	}

	@Override
	public String toString() {
		return "City [popolazione=" + ", descrizione=" + descrizione + ", provincia=" + provincia
				+ ", regione=" + regione + ", website=" + website + ", immagine=" + immagine + ", nome=" + nome
				+ ", longi=" + longi + ", lati=" + lati + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((immagine == null) ? 0 : immagine.hashCode());
		result = prime * result + Float.floatToIntBits(lati);
		result = prime * result + Float.floatToIntBits(longi);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((regione == null) ? 0 : regione.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (immagine == null) {
			if (other.immagine != null)
				return false;
		} else if (!immagine.equals(other.immagine))
			return false;
		if (Float.floatToIntBits(lati) != Float.floatToIntBits(other.lati))
			return false;
		if (Float.floatToIntBits(longi) != Float.floatToIntBits(other.longi))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
	
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (regione == null) {
			if (other.regione != null)
				return false;
		} else if (!regione.equals(other.regione))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}
	
	
	
}