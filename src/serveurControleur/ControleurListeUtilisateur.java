package serveurControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JButton;

import bdd.BddUtilisateur;
import metier.ComparaisonUtilisateurs;
import metier.Groupe;
import metier.Utilisateur;
import serveur.InterfaceGestionnaireServeur;
import serveurVue.FenetreAccueilServeur;
import serveurVue.FenetreListUsers;
import serveurVue.VueAccueilPrincipal;
import serveurVue.VueListUsers;

public class ControleurListeUtilisateur implements ActionListener {
	
	
	private InterfaceGestionnaireServeur interfaceGestionnaireServeur;
	private FenetreAccueilServeur fenetreAccueilServeur;
	private FenetreListUsers fenetreListUsers;
	private VueListUsers vueListUsers;
	

	public ControleurListeUtilisateur(InterfaceGestionnaireServeur interfaceGestionnaireServeur, FenetreAccueilServeur fenetreAccueilServeur) {
		
		this.fenetreAccueilServeur = fenetreAccueilServeur ; 
		this.interfaceGestionnaireServeur =interfaceGestionnaireServeur;
		
		this.fenetreListUsers = new FenetreListUsers(this);
		
		this.vueListUsers = fenetreListUsers.getVueListUsers();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		
		
		if(b.getActionCommand().equals("RET")) {
			fenetreAccueilServeur.setVisible(true);
			fenetreListUsers.setVisible(false);
		}else {
			fenetreAccueilServeur.setVisible(false);
			fenetreListUsers.setVisible(false);
			fenetreListUsers = new FenetreListUsers(this);
			fenetreListUsers.setVisible(true);
		}
		
		
		
	}
	public NavigableMap<Utilisateur, Set<Groupe>> getGroupesUtilisateur(){
		
		NavigableMap<Utilisateur, Set<Groupe>> groupesUtilisateur = new TreeMap<>(new ComparaisonUtilisateurs());
		NavigableSet<Utilisateur> listeUtilisateurs = interfaceGestionnaireServeur.getUtilisateurs();
		for(Utilisateur u :listeUtilisateurs) {
			
			List<Groupe> groupes = new BddUtilisateur().groupesParUtilisateur(u);
			Set<Groupe> groupesU = new TreeSet<Groupe>();
			groupesU.addAll(groupes);
			groupesUtilisateur.put(u, groupesU);
			
		}
		
		return groupesUtilisateur;
	}

	public InterfaceGestionnaireServeur getInterfaceGestionnaireServeur() {
		// TODO Auto-generated method stub
		return interfaceGestionnaireServeur;
	}

	public void setFenetreListUsers(FenetreListUsers fenetreListUsers) {
		this.fenetreListUsers = fenetreListUsers;
		
	}
	

}
