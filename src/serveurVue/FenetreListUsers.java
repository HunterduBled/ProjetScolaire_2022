package serveurVue;

import java.awt.GridLayout;

import javax.swing.JFrame;

import serveurControleur.ControleurListeUtilisateur;

public class FenetreListUsers extends JFrame {
	
	
	/**
	 * 
	 */
	private VueListUsers vueListUsers ; 
	private static final long serialVersionUID = 1L;
	public FenetreListUsers(ControleurListeUtilisateur controleurListeUtilisateur) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setLayout(new GridLayout(1,1));
	 
	    VueListUsers vueListUsers = new VueListUsers(controleurListeUtilisateur,this);
	    
	    
	    add(vueListUsers);
	    
	    
	   // addWindowListener(new CloseWindowsCloseConnexions(interfaceGestionnaireServeur));
	
		setSize(1200, 1000);
		
	    setResizable(false);
    
	}
	
	public  VueListUsers getVueListUsers() {
		return vueListUsers;
	}
    
    
}
