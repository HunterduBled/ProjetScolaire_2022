package metier;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDateTime date ; 
	private String contenuMessage ; 
	private Utilisateur emetteur; 
	private FilDeDiscussion filDeDiscussion;
	private String dateString;
	
	public Message(Utilisateur emetteur ,String contenuMessage)  {
		
		this.emetteur = emetteur; 
		this.contenuMessage = contenuMessage; 
		date = LocalDateTime.now();
		
		
	}

	public FilDeDiscussion getFilDeDiscussion() {
		return filDeDiscussion;
	}

	public void setFilDeDiscussion(FilDeDiscussion filDeDiscussion) {
		this.filDeDiscussion = filDeDiscussion;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalDateTime getDate() {
		return date;
	}
	
	
	public void setDateStringFormat() {
		
		int jour = date.getDayOfMonth();
		String jourString = jour + "";
		if(jour < 10 ) {
			jourString += "0"+jourString;
		}
		int mois = date.getMonthValue();
		String moisString = mois + "";
		if(mois < 10 ) {
			moisString += "0"+moisString;
		}
	
		int annee = date.getYear() ; 
		int heure = date.getHour();
		
		int minutes = date.getMinute();
		int seconde = date.getSecond();
		dateString = jour + "/" + mois +  "/" + annee + " " + heure + ":"+ minutes + ":"+ seconde;
      
	}
	
	public String getDateStringFormat() {
		return dateString;
	}

	public String getContenuMessage() {
		return contenuMessage;
	}

	public Utilisateur getEmetteur() {
		return emetteur;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (! (o instanceof Message))
			return false;
		
		Message m = (Message) o;
		return date.equals(m.date) && emetteur.getIdentifiant().equals(m.emetteur.getIdentifiant());
	}

	@Override
	public String toString() {
		return "Message [emetteur=" + emetteur + ", contenuMessage=" + contenuMessage + ", date=" + date + "]";
	}

	
	


}
