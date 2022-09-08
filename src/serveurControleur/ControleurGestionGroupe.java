package serveurControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import metier.Groupe;
import metier.Utilisateur;
import serveurVue.FenetreGestionGroupe;
import serveurVue.JButtonGroupe;
import serveurVue.JButtonUtilisateur;
import serveurVue.VueGestionGroupe;
import serveurVue.VueGroupeUsers;

public class ControleurGestionGroupe implements ActionListener {
	
	VueGroupeUsers vueGroupeUsers;
	ControleurGroupe controleurGroupe;
	FenetreGestionGroupe fenetreGestionGroupe;
	List<Utilisateur> userGroupeConcerne ;
	List<Utilisateur> userHorsGroupeConcerne;
	VueGestionGroupe vueGestionGroupe;
	Groupe groupeConcerne ;
	

	public ControleurGestionGroupe(VueGroupeUsers vueGroupeUsers, ControleurGroupe controleurGroupe) {
		this.vueGroupeUsers =vueGroupeUsers;
		this.controleurGroupe = controleurGroupe;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource() ;
		String action = button.getActionCommand();
		if(action.equals("MNG_GRP")) {
			JButtonGroupe<Groupe> jbuttonGrp= (JButtonGroupe<Groupe>)button;
			groupeConcerne = jbuttonGrp.getValue();
			
			userGroupeConcerne  = controleurGroupe.getUtilisateursGroupe(groupeConcerne);
			userHorsGroupeConcerne = controleurGroupe.getInterfaceGestionnaireServeur().getUtilisateursHorsGroupe(groupeConcerne);
			
			vueGestionGroupe = new VueGestionGroupe(groupeConcerne,userGroupeConcerne,userHorsGroupeConcerne,this);
			fenetreGestionGroupe= new FenetreGestionGroupe(vueGestionGroupe);
			
			fenetreGestionGroupe.setVisible(true);
			controleurGroupe.fenetreGroupe.setVisible(false);
		}else if(action.equals("RET")) {
			
			this.fenetreGestionGroupe.setVisible(false);
			controleurGroupe.fenetreGroupe.setVisible(true);
			
			
		}else if(button instanceof JButtonUtilisateur) {
			
			JButtonUtilisateur<Utilisateur> b = (JButtonUtilisateur<Utilisateur>) button ; 
			groupeConcerne = vueGestionGroupe.getGroupeConcerne();
			if(action.equals("DEL_USR")) {
				Utilisateur userARetirer = b.getValue() ;
				
				controleurGroupe.getInterfaceGestionnaireServeur().supprimmerUserGroupe(groupeConcerne.getNom(), userARetirer.getIdentifiant());
				userGroupeConcerne.remove(userARetirer);
				userHorsGroupeConcerne.add(userARetirer);
				
			}else if(action.equals("ADD_USR")) {
				
				Utilisateur userAAjouter = b.getValue();
				
				controleurGroupe.getInterfaceGestionnaireServeur().ajouterUserGroupe(groupeConcerne, userAAjouter);
				userHorsGroupeConcerne.remove(userAAjouter);
				userGroupeConcerne.add(userAAjouter);
				
				
			}
			
			reinitialiserPage();	
	    }else if(action.equals("SEARCH_DEL")){
    		String idUserSup = vueGestionGroupe.getRechercheIdSupp();
    		Utilisateur userASup = new Utilisateur(idUserSup, null, null, null, null);
    		if(userGroupeConcerne.contains(userASup)){
    			userGroupeConcerne.clear();
    			userGroupeConcerne.add(userASup);
    			reinitialiserPage();
    		}
	    }else if(action.equals("SEARCH_ADD")){
	    	String idUserAdd = vueGestionGroupe.getRechercheIdSAdd();
    		Utilisateur userAdd = new Utilisateur(idUserAdd, null, null, null, null);
    		if(userHorsGroupeConcerne.contains(userAdd)) {
    			userHorsGroupeConcerne.clear();
    			userHorsGroupeConcerne.add(userAdd);
    			reinitialiserPage();
    		}

	    }
	
	}
	
	
	 private void reinitialiserPage() {
		 
		    fenetreGestionGroupe.setVisible(false);
			
			vueGestionGroupe = new VueGestionGroupe(groupeConcerne, userGroupeConcerne, userHorsGroupeConcerne, this);
			fenetreGestionGroupe = new FenetreGestionGroupe(vueGestionGroupe);
			fenetreGestionGroupe.setVisible(true);
			
	 }

}
