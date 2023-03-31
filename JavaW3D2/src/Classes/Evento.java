package Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name= "events_jpa" )
@NamedQuery(name = "Events.findAll", query= "SELECT e FROM Evento e")
public class Evento {
	
	public enum tipo_evento {PUBBLICO, PRIVATO}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_event")
	private long id;
	
	@Column(nullable = false)
	private String titolo;
	
	@Column(nullable = false)
	private Date data_evento;
	
	@Column(nullable = false)
	private String descrizione;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private tipo_evento tipo_evento;
	
	@Column(nullable = false)
	private Integer numero_massimo_partecipanti;
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public Date getDataEvento() {
		return data_evento;
	}
	public void setDataEvento(String s) {
		SimpleDateFormat data =	new SimpleDateFormat(s);
		try {
            Date date = new Date(data.parse(s).getTime());
            this.data_evento =  date;
        } catch (ParseException e) {
            System.out.println("Errore durante il parsing della data: " + e.getMessage());
        }
		
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Enum getTipoEvento() {
		return tipo_evento;
	}
	public void setTipoEvento(tipo_evento tipoEvento) {
		this.tipo_evento = tipoEvento;
	}
	public Integer getNumeroMassimoPartecipanti() {
		return numero_massimo_partecipanti;
	}
	public void setNumeroMassimoPartecipanti(Integer numeroMassimoPartecipanti) {
		this.numero_massimo_partecipanti = numeroMassimoPartecipanti;
	}
	public long getId() {
		return id;
	}
	
	
}
