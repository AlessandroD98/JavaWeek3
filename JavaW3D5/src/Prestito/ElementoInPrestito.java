package Prestito;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ElementiCatalogo.ElementoCatalogo;

@Entity
@Table(name = "prestiti")
@NamedQuery(name = "prestiti.FindAll", query = "SELECT p FROM ElementoInPrestito p")
public class ElementoInPrestito {
	@Id
	@JoinColumn(name = "numeroTessera")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Utente utente; 
	@ManyToOne
	private ElementoCatalogo elementoPrestato;
	private Date inizioPrestito;
	private Date restituzionePrevista; 
	private Date restituzioneEffettiva;
	
	public ElementoInPrestito() {
	}

	public ElementoInPrestito(ElementoCatalogo elementoPrestato, Date inizioPrestito,
			Date restituzionePrevista, Date restituzioneEffettiva) {
		this.elementoPrestato = elementoPrestato;
		this.inizioPrestito = inizioPrestito;
		this.restituzionePrevista = restituzionePrevista;
		this.restituzioneEffettiva = restituzioneEffettiva;
	}
	
	public ElementoCatalogo getElementoPrestato() {
		return elementoPrestato;
	}
	public void setElementoPrestato(ElementoCatalogo elementoPrestato) {
		this.elementoPrestato = elementoPrestato;
	}
	public Date getInizioPrestito() {
		return inizioPrestito;
	}
	public void setInizioPrestito(Date inizioPrestito) {
		this.inizioPrestito = inizioPrestito;
	}
	public Date getRestituzionePrevista() {
		return restituzionePrevista;
	}
	public void setRestituzionePrevista(Date restituzionePrevista) {
		this.restituzionePrevista = restituzionePrevista;
	}
	public Date getRestituzioneEffettiva() {
		return restituzioneEffettiva;
	}
	public void setRestituzioneEffettiva(Date restituzioneEffettiva) {
		this.restituzioneEffettiva = restituzioneEffettiva;
	}
	public Long getId() {
		return id;
	}
	
	
	
}
