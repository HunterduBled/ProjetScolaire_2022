package clientControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.Client;
import clientVue.VueChat;
import metier.FilDeDiscussion;
import metier.Message;
import metier.Utilisateur;

public class ControleurMessages implements ActionListener{
	
	private VueChat vueChat ;
	
	private FilDeDiscussion filDeDiscussionCourant;
	
	private Utilisateur user;

	private Client client;
	
	public  ControleurMessages(VueChat vueChat, Client client) {
		
		this.vueChat = vueChat;
		this.client = client;
		user = client.getUser();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String contenuMessage = vueChat.getMessage();
		vueChat.setMessage();
		if(contenuMessage.equals(new String("")) || contenuMessage == null) {
			return;
		}
		
		Utilisateur emetteur = client.getUser();
		
		Message mes = new Message(emetteur, contenuMessage);
		
		FilDeDiscussion filMessage = vueChat.getCurFil();
		
	
		mes.setFilDeDiscussion(filMessage);
		
		mes.setDateStringFormat();
		
		vueChat.ajouterMessage(mes);
		
		client.envoyerMessage(mes);
		
		
		
	}

	public void receptionnerMessage(Message messageRecu) {
		
		
		vueChat.ajouterMessage(messageRecu);

	
	}
	
	

}
