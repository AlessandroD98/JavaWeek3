package Prestito;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Main.Archivio;

@Entity
@Table(name = "utenti")
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numeroTessera;
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	private Boolean prova;
	

	public Utente() {}
	
	public Utente(Boolean prova) {
		  this.setNome();
		  this.setCognome();
		  this.setDataDiNascita();
		  this.prova = prova;
	}
	
	public Utente(String nome, String cognome, Date dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
	}
	
	public Utente(Long numeroTessera, String nome, String cognome, Date dataDiNascita) {
		this.numeroTessera = numeroTessera;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
	}
	
	public Boolean getCheck() {
		return this.prova;
	}
	
	public void setCheck(Boolean check) {
		this.prova = check;
	}
	
	public Long getNumeroTessera() {
		return numeroTessera;
	}
	public String getNome() {
		return nome;
	}
	public void setNome() {
		System.out.println(">> Iniziamo! inserisci il tuo Nome");
		String name = Archivio.s.nextLine();
		this.nome = name;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome() {
		System.out.println(">> inserisci il tuo Cognome");
		String lastname = Archivio.s.nextLine();
		this.cognome = lastname;
	}
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita() {
		System.out.println(">> inserisci la tua data di nascita (DD/MM/YYYY)");
		String s = Archivio.s.nextLine();
		
		while(true) {
			try {
				SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
				Date oggi = new Date();
				Date date = new Date(formatdate.parse(s).getTime());
				if(date.after(oggi)) {
					System.err.println("Non sapevo sapessi viaggiare nel tempo! Prova un data valida");
					s = Archivio.s.nextLine();
				} else {				
					this.dataDiNascita = date;
					break;
				}
			} catch (Exception e) {
				System.out.println("Inserisci una data valida!");
				s = Archivio.s.nextLine();
			}
		}
	}

	@Override
	public String toString() {
		return "Utente [numeroTessera=" + numeroTessera + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita="
				+ dataDiNascita +"]  \n";
	}

}
