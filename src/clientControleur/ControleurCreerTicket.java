package clientControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;

import client.Client;
import clientVue.FenetreChat;
import clientVue.FenetreCreerTicket;
import clientVue.VueChat;
import clientVue.VueCreerTicket;
import metier.ComparaisonFilDeDiscussion;
import metier.ComparaisonMessages;
import metier.FilDeDiscussion;
import metier.Groupe;
import metier.Message;
import metier.Tache;
import metier.Utilisateur;

public class ControleurCreerTicket implements ActionListener,ItemListener {
	
	
	Client client;
	VueChat vueChat ; 
	VueCreerTicket vueCreerTicket;
	FenetreCreerTicket fenetreCreerTicket;
	FenetreChat fenetreChat;
	Groupe groupeChoisi = null;

	public ControleurCreerTicket(Client client, VueChat vueChat) {
		this.client = client; 
		this.vueChat = vueChat;
		 vueCreerTicket = new VueCreerTicket(client,this);
		 fenetreCreerTicket = new FenetreCreerTicket(vueCreerTicket,client);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton source = (JButton) e.getSource();
		Tache t = Tache.valueOf(source.getActionCommand());
		switch(t) {
		
			case CREER_FIL:
				if(groupeChoisi == null) {
					groupeChoisi =(Groupe) vueCreerTicket.getGroupe().getSelectedItem();
				}
				Utilisateur emetteur = client.getUser();
				String messageString = vueCreerTicket.getMessage();
				Message premierMessage = new Message(client.getUser(), messageString);
				String titre = vueCreerTicket.getTitre();
				FilDeDiscussion filCreer = new FilDeDiscussion(titre, premierMessage, emetteur.getIdentifiant(), groupeChoisi);
				premierMessage.setFilDeDiscussion(filCreer);
			
				ajouterFil(client.getUser() , filCreer);
				
				client.creerFil(filCreer);
				
		   case RET : 
				fenetreCreerTicket.setVisible(false);
				fenetreChat.setVisible(true);
				break;
		
			case OPEN_CREER_FIL:
				fenetreCreerTicket = new FenetreCreerTicket(vueCreerTicket,client);
				fenetreCreerTicket.setVisible(true);
				fenetreChat = vueChat.getControleurConnexion().getFenetreChat();
				fenetreChat.setVisible(false);
				
				
				break;
			
		
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		JComboBox<Groupe> jc = (JComboBox<Groupe>) e.getSource();
		groupeChoisi = (Groupe) jc.getSelectedItem();
		
		
	}
	
	public synchronized void ajouterFil(Utilisateur u , FilDeDiscussion filCreer) {
		
		Groupe groupeFil = filCreer.getGroupeConcerne();
		if(u.getFilsDeDiscussions().keySet().contains(groupeFil)) {
			
			u.getFilsDeDiscussions().get(groupeFil).add(filCreer); // ajouter a l'utilisateur le fil
			
		}else {
			NavigableSet<FilDeDiscussion> fils = new TreeSet<>(new ComparaisonFilDeDiscussion());
			fils.add(filCreer);
			u.getFilsDeDiscussions().put(groupeFil, fils);
			
		}
		
		
		vueChat.ajouterFil(groupeFil, filCreer);
	}
/*
	public List<Groupe> getGroupesCreerFil() {
		
		client.getGroupes();
		return null;
	}*/

	public List<Groupe> getGroupesCreerFil() {
		return client.getGroupesCreerFil();
	}

	

}
