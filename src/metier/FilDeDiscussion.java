package metier;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class FilDeDiscussion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titre ; 
	private Message premierMessage ; 
	private String createur ; 
	private Groupe groupeConcerne ;
	private LocalDateTime dateCreation ;
	private NavigableSet<Message> messages = new TreeSet<>(new ComparaisonMessages());
	

	public FilDeDiscussion(String titre , Message premierMessage, String createur, Groupe groupeConcerne) {
		this.titre = titre ; 
		this.premierMessage = premierMessage;
		dateCreation  = premierMessage.getDate();
		this.createur = createur; 
		this.groupeConcerne = groupeConcerne;
		messages.add(premierMessage);
		
	}
	
	public void recupererMessages(Set<Message> messagesSelect) {
		messages.addAll(messagesSelect);
		
	}
	
	public void ajouterMessage(Message message) {
		messages.add(message);
	}

	public String getTitre() {
		return titre;
	}

	public Message getPremierMessage() {
		return premierMessage;
	}

	public String getCreateur() {
		return createur;
	}

	public Groupe getGroupeConcerne() {
		return groupeConcerne;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}
	

	public NavigableSet<Message> getMessages() {
		return messages;
	}

	@Override
	public String toString() {
		return titre;
	}
	
	public String getString() {
		return "FilDeDiscussion [titre=" + titre + ", premierMessage=" + premierMessage + ", createur=" + createur
				+ ", groupeConcerne=" + groupeConcerne + ", dateCreation=" + dateCreation + "]";
	}
	

	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (! (o instanceof FilDeDiscussion))
			return false;
		
		FilDeDiscussion f = (FilDeDiscussion) o;
		return createur.equals(f.createur) && groupeConcerne.equals(f.groupeConcerne) && dateCreation.equals(f.dateCreation);
	}


	
	


}
