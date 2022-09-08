package serveurControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import metier.Utilisateur;
import serveurVue.FenetreListUsers;
import serveurVue.FenetreModifUtilisateur;
import serveurVue.VueListUsers;
import serveurVue.VueModifUtilisateur;

public class ControleurModifierUSer implements ActionListener, ItemListener {
	
	private FenetreListUsers fenetreListUsers; 

	private VueModifUtilisateur vueModifUtilisateur;
	
	private String roleChoisi ;

	private ControleurModifierSupprimerUtilisateur controleurModifierSupprimerUtilisateur;

	private FenetreModifUtilisateur fenetreModifUtilisateur; 

	public ControleurModifierUSer(VueModifUtilisateur vueModifUtilisateur, ControleurModifierSupprimerUtilisateur controleurModifierSupprimerUtilisateur) {
		
		super();
		this.vueModifUtilisateur = vueModifUtilisateur;
		this.controleurModifierSupprimerUtilisateur = controleurModifierSupprimerUtilisateur ; 
		 fenetreListUsers = controleurModifierSupprimerUtilisateur.getFenetreListUsers();
		 fenetreModifUtilisateur = controleurModifierSupprimerUtilisateur.getFenetreModifUtilisateur();
		 roleChoisi = vueModifUtilisateur.getUserInit().getTypeUtilisateur();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Utilisateur userNonModif = vueModifUtilisateur.getUserInit();
		
		String nom = vueModifUtilisateur.getNomT();
		String prenom = vueModifUtilisateur.getPrenomT();
		String mdp = vueModifUtilisateur.getMdpT();
		String typeUtilisateur = roleChoisi;
		String id = userNonModif.getIdentifiant();
		
		Utilisateur userModifie = new Utilisateur(id, nom, prenom, mdp, typeUtilisateur);
		controleurModifierSupprimerUtilisateur.getInterfaceGestionnaireServeur().modifierUtilisateur(userNonModif ,userModifie);
		
		ControleurListeUtilisateur controleurListeUtilisateur = controleurModifierSupprimerUtilisateur.getVueListUsers().getControleurListeUtilisateur();
		
		fenetreListUsers = new FenetreListUsers(controleurListeUtilisateur);//creer reference en memoire sur nouvel objet pour eviter duplication on va mettre a jour
		//anciennes references de cet objet ak l utilisateur modifié.
		controleurModifierSupprimerUtilisateur.setFenetreListUsers(fenetreListUsers);
		
		fenetreListUsers.setVisible(true);
		
		fenetreModifUtilisateur = controleurModifierSupprimerUtilisateur.getFenetreModifUtilisateur();

		fenetreModifUtilisateur.setVisible(false);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		this.roleChoisi = (String) e.getItem();
	}

}
