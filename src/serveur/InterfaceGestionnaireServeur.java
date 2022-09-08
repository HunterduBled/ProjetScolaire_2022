package serveur;

import java.util.List;
import java.util.NavigableSet;

import bdd.BddGroupe;
import bdd.BddUtilisateur;
import clientVue.FenetreConnexion;
import metier.Groupe;
import metier.Utilisateur;
import serveurVue.FenetreAccueilServeur;

public class InterfaceGestionnaireServeur implements Runnable {

	@Override
	public void run() {
		
		FenetreAccueilServeur fenetreAccueilServeur = new FenetreAccueilServeur (this);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

            	fenetreAccueilServeur.setVisible(true);
            }
        });
		
		
		
		
	}
	public List<Groupe> groupesParUtilisateur(Utilisateur u){
		return new BddUtilisateur().groupesParUtilisateur(u);
	}

	public NavigableSet<Utilisateur> getUtilisateurs() {
		
		return new BddUtilisateur().listeUtilisateur();
	}

	public void supprimerUtilisateur(Utilisateur userSupr) {
		new BddUtilisateur().supprimerUtilisateur(userSupr);
		
	}

	public List<Groupe> getGroupes() {
		return new BddGroupe().groupes();
		
	}

	public void ajouterUtilisateur(Utilisateur userCreer) {
		
		new BddUtilisateur().ajouterUtilisateur(userCreer);
		
	}
	
	public  List<Utilisateur> getUtilisateursGroupe(Groupe g){
		return new BddGroupe().utilisateursGroupe(g);
		
	}
	
	public  List<Utilisateur> getUtilisateursHorsGroupe(Groupe g){
		return new BddGroupe().utilisateursHorsGroupe(g);
		
	}
	

	public void supprimmerUserGroupe(String nomGroupe, String userARetirer) {
		
		new BddGroupe().supprimerUtilisateurGroupe(userARetirer, nomGroupe);
		
		
	}
	public void ajouterUserGroupe(Groupe groupe,Utilisateur userAAjouter) {
		new BddGroupe().ajouterUtilisateurGroupe(userAAjouter, groupe);
	}

	public void ajouterNouveauGroupe(String nomGroupeACreer, String typeGroupe) {
		new BddGroupe().ajouterGroupe(new Groupe(nomGroupeACreer, typeGroupe));
		
	}

	public void modifierUtilisateur(Utilisateur userNonModif, Utilisateur userModifie) {
		new BddUtilisateur().modifierUtilisateur(userNonModif.getIdentifiant(), userModifie);
	}

}
