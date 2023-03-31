package Runnable;


import Classes.Evento;
import Classes.Evento.tipo_evento;
import Classes.EventoDAO;

public class Main {

	public static void main(String[] args) {
		
		Evento e = new Evento();
		e.setTitolo("Prova");
		e.setDataEvento("29/03/2023");
		e.setDescrizione("Evento di prova");
		e.setTipoEvento(tipo_evento.PRIVATO);
		e.setNumeroMassimoPartecipanti(5);
		
		EventoDAO.addEvent(e);
	}

}
