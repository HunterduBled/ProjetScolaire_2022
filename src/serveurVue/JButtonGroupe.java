package serveurVue;

import javax.swing.JButton;

public class JButtonGroupe<Groupe>  extends JButton{

	 
	private static final long serialVersionUID = 1L;
	
	
	
	private Groupe u;

	 

	public Groupe getValue() {
		  
		  return u;
		  
	  }

	  public void setValue(Groupe u) {
		  
	    this.u = u;
	    
	  }

}
