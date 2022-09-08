package serveurVue;

import javax.swing.JButton;

public class JButtonUtilisateur<Utilisateur>  extends JButton{

	 
	private static final long serialVersionUID = 1L;
	
	
	
	private Utilisateur u;

	 

	public Utilisateur getValue() {
		  
		  return u;
		  
	  }

	  public void setValue(Utilisateur u) {
		  
	    this.u = u;
	    
	  }

}
