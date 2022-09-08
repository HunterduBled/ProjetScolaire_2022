package serveurControleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.mysql.cj.protocol.x.ReusableOutputStream;

import bdd.BddGroupe;
import metier.Groupe;
import metier.Utilisateur;
import serveur.InterfaceGestionnaireServeur;
import serveurVue.FenetreAccueilServeur;
import serveurVue.FenetreGroupe;
import serveurVue.JButtonGroupe;
import serveurVue.JButtonUtilisateur;
import serveurVue.VueAccueilPrincipal;
import serveurVue.VueGroupeUsers;

public class ControleurGroupe implements ActionListener, ItemListener{


	VueAccueilPrincipal vueAccueilPrincipal;
	InterfaceGestionnaireServeur interfaceGestionnaireServeur;
	FenetreAccueilServeur fenetreAccueilServeur ;
	VueGroupeUsers vueGroupeUsers;
	FenetreGroupe  fenetreGroupe ;

	private String userARetirer;
	
	
	private String typeNewGroupe;
	
	
	public ControleurGroupe(VueAccueilPrincipal vueAccueilPrincipal, InterfaceGestionnaireServeur interfaceGestionnaireServeur, FenetreAccueilServeur fenetreAccueilServeur) {
		
		this.vueAccueilPrincipal = vueAccueilPrincipal;
		this.interfaceGestionnaireServeur =interfaceGestionnaireServeur ;
		this.fenetreAccueilServeur = fenetreAccueilServeur;
		
		this.vueGroupeUsers = new VueGroupeUsers(this,getGroupes());
		this.fenetreGroupe = new  FenetreGroupe(vueGroupeUsers);

		

	}
	public InterfaceGestionnaireServeur getInterfaceGestionnaireServeur() {
		return interfaceGestionnaireServeur;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	JButton b =	(JButton) e.getSource() ;
	
	if(b instanceof JButtonGroupe) {
		
		JButtonGroupe<Groupe> modifier = (JButtonGroupe<Groupe>) b ;
		
		Groupe grprModif = modifier.getValue() ; 
		
		
		supprimmerUserGroupe(grprModif.getNom() , userARetirer);
		
		return;
	}
	String action = b.getActionCommand() ;
	if(action.equals("GoGroup")) {
		reinitialiserVue(getGroupes());
		fenetreAccueilServeur.setVisible(false);
	}else if(action.equals("RET")) {
		fenetreGroupe.setVisible(false);
		fenetreAccueilServeur.setVisible(true);
	}else if(action.equals("Rechercher")) {
		
		List <Groupe> groupes = new LinkedList<Groupe>() ;
		groupes.addAll(getGroupes());
		String nomGroupe = vueGroupeUsers.getRechercheGroupe();
		
		Groupe g = new Groupe(nomGroupe,null);
		if(groupes.contains(g)) {
			List<Groupe>grpeTrouve= new LinkedList<>();
			grpeTrouve.add(g);
			reinitialiserVue(grpeTrouve);
			
		}
		
		
	}else if(action.equals("CREER")) {
		
		String nomGroupeACreer = vueGroupeUsers.groupeAAjouter();
		interfaceGestionnaireServeur.ajouterNouveauGroupe(nomGroupeACreer,typeNewGroupe);
		reinitialiserVue(getGroupes());
	}
		
	}
	
	public void reinitialiserVue(List<Groupe> grpeTrouve) {
		
		vueGroupeUsers = new VueGroupeUsers(this, grpeTrouve);
		fenetreGroupe.setVisible(false);
		fenetreGroupe = new FenetreGroupe(vueGroupeUsers);
		fenetreGroupe.setVisible(true);
		
	}
	public void supprimmerUserGroupe(String nomGroupe, String userARetirer) {
		
		interfaceGestionnaireServeur.supprimmerUserGroupe(nomGroupe,userARetirer); 
	}
	public List<Groupe> getGroupes() {
		
		return interfaceGestionnaireServeur.getGroupes();
	}
	
	public  List<Utilisateur> getUtilisateursGroupe(Groupe g){
		return interfaceGestionnaireServeur.getUtilisateursGroupe(g);
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		this.typeNewGroupe = (String) e.getItem();
	}
	public void setTypeNewGroupe() {
		this.typeNewGroupe = "USER";
		
	}

}
