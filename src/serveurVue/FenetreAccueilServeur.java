package serveurVue;

import java.awt.GridLayout;

import javax.swing.JFrame;

import client.Client;
import clientControleur.ControleurConnexion;
import clientVue.CloseWindowsCloseConnexions;
import clientVue.VueChat;
import serveur.InterfaceGestionnaireServeur;

public class FenetreAccueilServeur extends JFrame {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FenetreAccueilServeur(InterfaceGestionnaireServeur interfaceGestionnaireServeur) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setLayout(new GridLayout(1,1));
	   
	    
	    VueAccueilPrincipal vueAccueilPrincipal = new VueAccueilPrincipal(interfaceGestionnaireServeur,this);
	    
	    add(vueAccueilPrincipal);
	    
	   // addWindowListener(new CloseWindowsCloseConnexions(interfaceGestionnaireServeur));
	
		setSize(1200, 1000);
		
	    setResizable(false);
	    
	    
	}


}
