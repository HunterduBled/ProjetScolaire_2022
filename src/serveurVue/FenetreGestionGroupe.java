package serveurVue;

import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class FenetreGestionGroupe extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	public FenetreGestionGroupe(VueGestionGroupe vueGestionGroupe ) throws HeadlessException {
		super();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setLayout(new GridLayout(1,1));
	 
	    
	    add(vueGestionGroupe);
	  
	    
	   // addWindowListener(new CloseWindowsCloseConnexions(interfaceGestionnaireServeur));

		setSize(1200, 1000);
		
	    setResizable(false);
		
	}
	
	

}





