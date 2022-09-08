package serveurVue;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class FenetreGroupe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public  FenetreGroupe (VueGroupeUsers vue) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setLayout(new GridLayout(1,1));
	 
	    
	    
	    add(vue);
	    
	    
	   // addWindowListener(new CloseWindowsCloseConnexions(interfaceGestionnaireServeur));

		setSize(1200, 1000);
		
	    setResizable(false);
	}
	

}
