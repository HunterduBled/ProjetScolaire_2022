package metier;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Groupe implements Serializable, Comparable<Groupe> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String type;
	private Set<Utilisateur> utilisateurs = new HashSet<>();
	private List<FilDeDiscussion> filsDeDiscussion = new LinkedList<>(); 
	
	public Groupe(String nom, String type) {
		this.nom = nom ;
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public String getType() {
		return type;
	}
	
	public Set<Utilisateur> getUtilisateur() {
		return utilisateurs;
	}
	
	public void ajouterUtilisateur(Utilisateur u) {
		utilisateurs.add(u);
	}
	
	
	
	@Override
	public String toString() {
		return nom;
	}
	
	public String getString() {
		
		return "Groupe [nom=" + nom + ", type=" + type + "]";

	}

	@Override
	public boolean equals(Object g) {
		
		if(g == null) {
			return false;
		}
		if(this == g) {
			return true;
		}
		if(! (g instanceof Groupe)) {
			return false;
		}
		
		Groupe groupe = (Groupe) g;
		return nom.equals(groupe.nom);
		
		
	}
	
	@Override 
	public int hashCode() {
		return 31 * nom.hashCode();
	}

	@Override
	public int compareTo(Groupe o) {
		return nom.compareTo(o.nom);
	}
	
	

	
	

}
