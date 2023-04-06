package Prestito;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
import Main.Archivio;

@Entity
@Table(name = "prestiti")
@NamedQuery(name = "prestiti.FindAll", query = "SELECT p FROM ElementoInPrestito p")
public class ElementoInPrestito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "numeroTessera")
	private Utente utente; 
	@ManyToOne
	private ElementoCatalogo elementoPrestato;
	private LocalDate inizioPrestito;
	private LocalDate restituzionePrevista; 
	private Date restituzioneEffettiva;
	
	public ElementoInPrestito() {
	}

	public ElementoInPrestito(ElementoCatalogo elementoPrestato, LocalDate inizioPrestito,
			LocalDate restituzionePrevista, Date restituzioneEffettiva) {
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
	public LocalDate getInizioPrestito() {
		return inizioPrestito;
	}
	public void setInizioPrestito() {
		System.out.println(">> inserisci la data di inizio prestito (DD/MM/YYYY)");
		String s = Archivio.s.nextLine();

		while (true) {
		    try {
			SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");

			Date oggi = new Date();
			Date date = new Date(formatdate.parse(s).getTime());
			if (date.after(oggi)) {
			    System.err.println("La data deve essere massimo il giorno corrente");
			    s = Archivio.s.nextLine();
			} else {
			    LocalDate date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    this.inizioPrestito = date1;
			    break;
			}
		    } catch (Exception e) {
			System.out.println("Inserisci una data valida!");
			s = Archivio.s.nextLine();
		    }
		}
		
	}
	public LocalDate getRestituzionePrevista() {
		return restituzionePrevista;
	}
	public void setRestituzionePrevista() {
		this.restituzionePrevista = this.inizioPrestito.plusMonths(1);
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

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	
	public static void toString(ElementoInPrestito e) {
		System.out.println("Utente: " + e.utente.getNome() + ", Tessera n: " + e.utente.getNumeroTessera() + ", Data prevista riconsegna: " + e.restituzionePrevista + " \n")  ;
	}
}
