package Trip;

import java.io.Serializable;

public class Event implements Serializable {
	private static final long serialVersionUID = 1L;
	String evento;
	String descrizione;
	String provincia;
	String regione;
	String comune;
	String website;
	String immagine;
	String nome;
	String luogo;
	String address;
	String code;
	String datainizio;
	String datafine;
	//String data;
	//String type;
	float longi;
	float lati;

	public Event() {
		evento = "";
		provincia = "";
		comune="";
		code="";
		descrizione=  "";
		regione ="";
		datainizio="";
		datafine="";
		website="";
		luogo="";
		immagine="";
		address = "";
		nome = "";
		//data = "";
	
		//type="";

	}

	public String getDatainizio() {
		return datainizio;
	}

	public void setDatainizio(String datainizio) {
		this.datainizio = datainizio;
	}

	public String getDatafine() {
		return datafine;
	}

	public void setDatafine(String datafine) {
		this.datafine = datafine;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/*public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
*/
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((comune == null) ? 0 : comune.hashCode());
		result = prime * result + ((datafine == null) ? 0 : datafine.hashCode());
		result = prime * result + ((datainizio == null) ? 0 : datainizio.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		result = prime * result + ((immagine == null) ? 0 : immagine.hashCode());
		result = prime * result + Float.floatToIntBits(lati);
		result = prime * result + Float.floatToIntBits(longi);
		result = prime * result + ((luogo == null) ? 0 : luogo.hashCode());
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
		Event other = (Event) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (comune == null) {
			if (other.comune != null)
				return false;
		} else if (!comune.equals(other.comune))
			return false;
		if (datafine == null) {
			if (other.datafine != null)
				return false;
		} else if (!datafine.equals(other.datafine))
			return false;
		if (datainizio == null) {
			if (other.datainizio != null)
				return false;
		} else if (!datainizio.equals(other.datainizio))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (evento == null) {
			if (other.evento != null)
				return false;
		} else if (!evento.equals(other.evento))
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
		if (luogo == null) {
			if (other.luogo != null)
				return false;
		} else if (!luogo.equals(other.luogo))
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

	@Override
	public String toString() {
		return "Event [evento=" + evento + ", descrizione=" + descrizione + ", provincia=" + provincia + ", regione="
				+ regione + ", comune=" + comune + ", website=" + website + ", immagine=" + immagine + ", nome=" + nome
				+ ", luogo=" + luogo + ", address=" + address + ", code=" + code + ", datainizio=" + datainizio
				+ ", datafine=" + datafine + ", longi=" + longi + ", lati=" + lati + "]";
	} 
	
	

}
