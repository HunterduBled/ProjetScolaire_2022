package metier;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class Utilisateur implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private String identifiant ; 
	

	private String nom; 
	private String prenom;
	private String password ;
	private String typeUtilisateur ;

	private NavigableSet <FilDeDiscussion> filsDeDiscussionsHorsGroupe = new TreeSet<>(new ComparaisonFilDeDiscussion());
	private Map<Groupe, Set<FilDeDiscussion>> filsDeDiscussionGroupes = new ConcurrentHashMap<Groupe, Set<FilDeDiscussion>>();
	private NavigableMap<Groupe, NavigableSet<FilDeDiscussion>> filsDeDiscussions = new TreeMap<>();

	//fils de discussions soit les fils du groupe auquel il appartient, soit ceux qui l a cree
	
	public Utilisateur(String identifiant, String nom, String prenom,String password,String typeUtilisateur) {
		super();
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.typeUtilisateur = typeUtilisateur;
		
	}
	
	/*public void ajouterFilDeDiscussion(FilDeDiscussion f) {
		filsDeDiscussions.add(f);
	}*/

	public String getIdentifiant() {
		return identifiant;
	}

	public String getNom() {
		return nom;
	}

	public String getPassword() {
		return password;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public String getTypeUtilisateur() {
		return typeUtilisateur;
	}

	public void setTypeUtilisateur(String typeUtilisateur) {
		this.typeUtilisateur = typeUtilisateur;
	}


	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public NavigableSet<FilDeDiscussion> getFilsDeDiscussionsHorsGroupe() {
		return filsDeDiscussionsHorsGroupe;
	}

	public void setFilsDeDiscussionsHorsGroupe(Set<FilDeDiscussion> filsDeDiscussionsHorsGroupe) {
		this.filsDeDiscussionsHorsGroupe.addAll(filsDeDiscussionsHorsGroupe);;
	}

	public Map<Groupe, Set<FilDeDiscussion>> getFilsDeDiscussionGroupes() {
		return filsDeDiscussionGroupes;
	}

	public void setFilsDeDiscussionGroupes(Map<Groupe, Set<FilDeDiscussion>> filsDeDiscussionGroupes) {
		this.filsDeDiscussionGroupes = filsDeDiscussionGroupes;
	}

	public void setFilsDeDiscussions() {

		//ajouter les fils de discussion des groupes
		for(Groupe g : filsDeDiscussionGroupes.keySet()) {
			
			NavigableSet<FilDeDiscussion> fils = new TreeSet<FilDeDiscussion>(new ComparaisonFilDeDiscussion());
			fils.addAll(filsDeDiscussionGroupes.get(g));
			
			filsDeDiscussions.put(g,fils);
			
		}
		//ajouter fils de discussions creer par l'utilisateur hors groupe
		
		for(FilDeDiscussion f : filsDeDiscussionsHorsGroupe ) {
			
			Groupe groupeConcerne = f.getGroupeConcerne();
			if(filsDeDiscussions.get(groupeConcerne) == null) { // emetteur n'a jamais envoyé de fil de discussion dans ce groupe
				NavigableSet<FilDeDiscussion> filsHorsGroupe = new TreeSet<>(new ComparaisonFilDeDiscussion());
				filsHorsGroupe.add(f);
				filsDeDiscussions.put(groupeConcerne, filsHorsGroupe);
			}else {
				filsDeDiscussions.get(groupeConcerne).add(f);
			}
				
			
		}
	}
	

	public NavigableMap<Groupe, NavigableSet<FilDeDiscussion>> getFilsDeDiscussions() {
		return filsDeDiscussions;
	}

	

	@Override
	public String toString() {
		return "Utilisateur [identifiant=" + identifiant + ", nom=" + nom + ", prenom=" + prenom + ", password="
				+ password + ", typeUtilisateur=" + typeUtilisateur + "]";
	}

	@Override
	public int hashCode() {
		return 31 * identifiant.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (! (obj instanceof Utilisateur))
			return false;
		
		Utilisateur user = (Utilisateur) obj;
		return identifiant.equals(user.identifiant);
	}

	
	
	
	
	

}
