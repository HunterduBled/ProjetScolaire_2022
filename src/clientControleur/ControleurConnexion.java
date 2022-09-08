package clientControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import client.Client;
import client.StatutConnexion;
import clientVue.FenetreChat;
import clientVue.FenetreConnexion;
import clientVue.VueChat;
import clientVue.VueConnexion;
import metier.Utilisateur;

public class ControleurConnexion implements ActionListener {
	
	
	private VueConnexion vueConnexion ;
	private VueChat vueChat;
	private Client client;
	private FenetreConnexion fenetreConnexion ;
	private FenetreChat fenetreChat ;
	boolean premiereConnexion = true;
	
	private JButton boutonConnexion;
	private JButton boutonDeconnexion;
	
	private StatutConnexion statut = StatutConnexion.DEC;

	public ControleurConnexion(VueConnexion vueConnexion, Client client, FenetreConnexion fenetreConnexion) {
		this.vueConnexion =vueConnexion;
		this.client = client;
		this.fenetreConnexion = fenetreConnexion;
		
	}
	
	
	public FenetreChat getFenetreChat() {
		return fenetreChat;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		
		switch(statut) {
			case DEC : 
				
				String idUtilisateur = vueConnexion.getId();
				String mdpUtilisateur = vueConnexion.getMdp();
				Utilisateur user =new Utilisateur(idUtilisateur, null, null, mdpUtilisateur,null);
				client.setUser(user);
				boolean connecte = client.seConnecter();
				if(connecte) {
					
					fenetreConnexion.setVisible(false);
					if(premiereConnexion) {
						fenetreChat = new FenetreChat(client, this);
					}
					fenetreChat.setVisible(true);
					/*fenetreChat = new FenetreChat(client, this);
					fenetreChat.setVisible(true);*/
					statut = StatutConnexion.CON;
				}
				
				break;
				
			case CON : 
				//fenetreConnexion = new FenetreConnexion(client);
				fenetreConnexion.setVisible(true);
				fenetreChat.setVisible(false);
				client.seDeconnecter();
				statut = StatutConnexion.DEC;
			default:
				break;
			
		}
		
	}


}
