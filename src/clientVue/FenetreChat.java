package clientVue;

import java.awt.GridLayout;

import javax.swing.JFrame;

import client.Client;
import clientControleur.ControleurConnexion;

public class FenetreChat extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		

	public FenetreChat(Client client,ControleurConnexion controleurConnexion) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setLayout(new GridLayout(1,1));
	   
	    
	    VueChat vueChat = new VueChat(client, controleurConnexion);
	    
	    add(vueChat);
	    
	    addWindowListener(new CloseWindowsCloseConnexions(client));
	
		setSize(1200, 1000);
		
	    setResizable(false);
	    
	    
	}


}
