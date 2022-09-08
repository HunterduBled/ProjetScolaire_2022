package serveurControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;

import metier.Groupe;
import metier.Utilisateur;
import serveur.InterfaceGestionnaireServeur;
import serveurVue.FenetreAjouterUtilisateur;
import serveurVue.FenetreListUsers;
import serveurVue.VueAjouterUsers;
import serveurVue.VueListUsers;

public class ControleurAjouterUtilisateur implements ActionListener, ItemListener {
	
	private ControleurListeUtilisateur controleurListeUtilisateur;
	private VueListUsers vueListUsers;
	private InterfaceGestionnaireServeur interfaceGestionnaireServeur;
	private FenetreListUsers fenetreListUsers;
	private FenetreAjouterUtilisateur fenetreAjouterUtilisateur;
	private VueAjouterUsers vueAjouterUsers;
	private String role;

	public ControleurAjouterUtilisateur(ControleurListeUtilisateur controleurListeUtilisateur, VueListUsers vueListUsers) {
		
		this.controleurListeUtilisateur = controleurListeUtilisateur;
		
		this.interfaceGestionnaireServeur  = controleurListeUtilisateur.getInterfaceGestionnaireServeur();
				
		this.vueListUsers = vueListUsers;
		this.fenetreListUsers = vueListUsers.getFenetreListUsers() ;
		
		fenetreAjouterUtilisateur = new FenetreAjouterUtilisateur(this);
		
		vueAjouterUsers  = fenetreAjouterUtilisateur.getVueAjouterUsers();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		JButton b = (JButton) e.getSource();
		String action = b.getActionCommand();
		
		if(action.equals("CREER")) {
			fenetreListUsers.setVisible(false);
			fenetreAjouterUtilisateur.setVisible(true);
		}else if(action.equals("RET")){
			
			fenetreListUsers.setVisible(true);
			fenetreAjouterUtilisateur.setVisible(false);
			
		}else {
			 if(role == null) {
				 role = vueAjouterUsers.getRole();
				 System.out.println("fuck");
				 return;
				
			 }
			
		
			 String  nom = vueAjouterUsers.getNomT() ;
			
			 String  prenom = vueAjouterUsers.getPrenomT();	
			 String  id = vueAjouterUsers.getIdentT() ;
			 String mdp = vueAjouterUsers.getMdpT() ;
			 
			 if(nom == "" ||prenom == "" || id == "" || mdp == "") {
				 return;
			 }
		
			 NavigableMap< Utilisateur ,  Set<Groupe>> users = controleurListeUtilisateur.getGroupesUtilisateur();
			 
			 for(Utilisateur u :  users.keySet()) {
				 if(u.getNom().equals(id)) {
					 return; //id deja pris
				 }
			 }
		/*
			 List<Groupe> groupes = interfaceGestionnaireServeur.getGroupes();
			 boolean groupeOk = false;
			 for(Groupe g :  groupes) {
				 if(g.getNom().equals(groupe)) {// a rajouter changer groupes peut envoyer seulement a types differents
					 //if(g.getType().equals(role)) {
						 groupeOk = true;
						 break;
					 //}
					 
				 }
			 }
				 
			 if(! groupeOk) {
				 return;
			 }*/
			 
			 Utilisateur userCreer = new Utilisateur(id, nom, prenom, mdp, role) ;
			 //Groupe g = new Groupe(groupe,role);
			 interfaceGestionnaireServeur.ajouterUtilisateur(userCreer);
			 fenetreAjouterUtilisateur.setVisible(false);
			 //mettre à jour les references de l'objet fenetre
			 FenetreListUsers f = new FenetreListUsers(controleurListeUtilisateur);
			 vueListUsers.ajouterFenetreListUsers(f);; // pour pouvoir supprimer un objet en memoire je dois supprimer toutes les references de cet objet pour que le ramasse miette vienne le recuperer
			 //updater reference accueil principal
			 fenetreListUsers = f;
			 fenetreListUsers.setVisible(true);
		
				
			
			
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		JComboBox<String> jc = (JComboBox<String>) e.getSource();
	
		role = (String) jc.getSelectedItem().toString();
		
		
	}

	public void ajouterRole(JComboBox<String> roleDefault) {
		
		role = (String)roleDefault.getSelectedItem();
		
	}
	
	
	

}
