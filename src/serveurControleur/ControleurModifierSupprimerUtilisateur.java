package serveurControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import metier.Utilisateur;
import serveur.InterfaceGestionnaireServeur;
import serveurVue.FenetreListUsers;
import serveurVue.FenetreModifUtilisateur;
import serveurVue.JButtonUtilisateur;
import serveurVue.VueListUsers;
import serveurVue.VueModifUtilisateur;

public class ControleurModifierSupprimerUtilisateur implements ActionListener{
	

	private VueListUsers vueListUsers;
	private FenetreListUsers fenetreListUsers;

	private FenetreModifUtilisateur fenetreModifUtilisateur;
	private VueModifUtilisateur vueModifUtilisateur ;
	private InterfaceGestionnaireServeur interfaceGestionnaireServeur;
	
	

	public ControleurModifierSupprimerUtilisateur(VueListUsers vueListUsers , InterfaceGestionnaireServeur interfaceGestionnaireServeur,FenetreListUsers fenetreListUsers) {
		
		super();
		
		this.interfaceGestionnaireServeur = interfaceGestionnaireServeur ;
		this.vueListUsers =vueListUsers ;
		this.fenetreListUsers = fenetreListUsers;

		
		
		
	}
	
	

	public void setFenetreListUsers(FenetreListUsers fenetreListUsers) { //updater les references pour pas avoir de duplication d objets et donc de fenetres
		this.fenetreListUsers = fenetreListUsers;
		vueListUsers.ajouterFenetreListUsers(fenetreListUsers);
	}



	public VueListUsers getVueListUsers() {
		return vueListUsers;
	}


	public InterfaceGestionnaireServeur getInterfaceGestionnaireServeur() {
		return interfaceGestionnaireServeur;
	}
	
	public FenetreListUsers getFenetreListUsers() {
		return fenetreListUsers;
	}
	
	public FenetreModifUtilisateur getFenetreModifUtilisateur() {
		return fenetreModifUtilisateur;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String action = button.getActionCommand() ;
		
		if(action.equals("M") || action.equals("MID")) {
			JButtonUtilisateur<Utilisateur> b = (JButtonUtilisateur<Utilisateur>) button;
			
			Utilisateur userModif = null;
			boolean idTrouve = false;
			if(action.equals("MID")) {
				
				String id = vueListUsers.getRechercheId();
				
				for (Utilisateur u : vueListUsers.getGroupesUtilisateurs().keySet()) {
					
					if(u.getIdentifiant().equals(id)) {
						idTrouve = true;
						userModif = u;
					}
				}
				
				
			}else {
				
				userModif= b.getValue();
				userModif= b.getValue();
				idTrouve = true;
				
			}
			if(idTrouve ==  true) {
				
				vueModifUtilisateur = new VueModifUtilisateur(userModif,this);
				this.fenetreModifUtilisateur =  new FenetreModifUtilisateur(vueModifUtilisateur);
				fenetreListUsers.setVisible(false);
				fenetreModifUtilisateur.setVisible(true);
			}
			
		
			
			
			
		}else if(action.equals("S") || action.equals("SID")) {
			
			JButtonUtilisateur<Utilisateur> b = (JButtonUtilisateur<Utilisateur>) button;

			boolean idTrouve = false;
			Utilisateur userSupr = null;
			if(action.equals("SID")) {
				
				String id = vueListUsers.getRechercheId();
				
				for (Utilisateur u : vueListUsers.getGroupesUtilisateurs().keySet()) {
					
					if(u.getIdentifiant().equals(id)) {
						idTrouve = true;
						userSupr = u;
					}
				}
				
				
			}else {
				 userSupr= b.getValue();
				 idTrouve = true;
			}
			
			if(idTrouve) {
				interfaceGestionnaireServeur.supprimerUtilisateur(userSupr);
				this.fenetreListUsers.setVisible(false);
				setFenetreListUsers(new FenetreListUsers(vueListUsers.getControleurListeUtilisateur()));
				fenetreListUsers.setVisible(true);
			}
			
		}else if(action.equals("RET")) {
			fenetreModifUtilisateur.setVisible(false);
			fenetreListUsers.setVisible(true);
		}
			
		
	}

	


}
