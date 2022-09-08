package clientVue;

import java.awt.GridLayout;

import javax.swing.JFrame;

import client.Client;

public class FenetreCreerTicket extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FenetreCreerTicket(VueCreerTicket vueCreerTicket, Client client) {
		
		super();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setLayout(new GridLayout(1,1));
	    
	    add(vueCreerTicket);
	    
	    addWindowListener(new CloseWindowsCloseConnexions(client));

	    setSize(1200, 1000);
		
	    setResizable(false);
	    
	}
	
	

}


	
