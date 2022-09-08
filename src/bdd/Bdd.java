package bdd;

import java.sql.Connection;

public abstract class Bdd {
	
	protected Connection con ; 
	protected BddManager bddMan ; 
	
	public Bdd () {
		bddMan = new BddManager();
		con = bddMan.getConnection();
	}
	public BddManager getbddManager() {
		return bddMan;
	}

}
