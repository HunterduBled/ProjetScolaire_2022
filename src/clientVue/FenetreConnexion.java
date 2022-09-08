package clientVue;

import java.awt.GridLayout;

import javax.swing.JFrame;

import client.Client;

public class FenetreConnexion extends JFrame  {


	private static final long serialVersionUID = 1L;
	

	public FenetreConnexion(Client client) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setLayout(new GridLayout(1,1));
	   
	    
	    VueConnexion vueConnexion = new VueConnexion(client,this);
	    
	    add(vueConnexion);
	    
	    addWindowListener(new CloseWindowsCloseConnexions(client));

	
	    setSize(1200, 1000);
		
	    //setResizable(false);
	    
	    
	}

	


}
