package serveurVue;

import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class FenetreModifUtilisateur extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	public FenetreModifUtilisateur(VueModifUtilisateur vueModifUtilisateur) throws HeadlessException {
		super();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setLayout(new GridLayout(1,1));
	 
	    
	    add(vueModifUtilisateur);
	  
	    
	   // addWindowListener(new CloseWindowsCloseConnexions(interfaceGestionnaireServeur));

		setSize(1200, 1000);
		
	    setResizable(false);
		
	}
	
	

}





