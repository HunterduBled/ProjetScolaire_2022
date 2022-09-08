package serveurVue;

import java.awt.GridLayout;

import javax.swing.JFrame;

import serveurControleur.ControleurAjouterUtilisateur;
import serveurControleur.ControleurListeUtilisateur;

public class FenetreAjouterUtilisateur extends JFrame{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	 private VueAjouterUsers vueListUsers ; 

	
	public FenetreAjouterUtilisateur(ControleurAjouterUtilisateur controleurAjouterUtilisateur) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setLayout(new GridLayout(1,1));
	 
	    vueListUsers = new VueAjouterUsers(controleurAjouterUtilisateur);
	    
	    
	    add(vueListUsers);
	    
	    
	   // addWindowListener(new CloseWindowsCloseConnexions(interfaceGestionnaireServeur));
	
		setSize(1200, 1000);
		
	    setResizable(false);
    
	}

	public VueAjouterUsers getVueAjouterUsers() {
		return vueListUsers;
		
	}
	

}
