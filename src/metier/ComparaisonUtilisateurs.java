package metier;

import java.util.Comparator;

public class ComparaisonUtilisateurs  implements Comparator<Utilisateur>{

	@Override
	public int compare(Utilisateur o1, Utilisateur o2) {
		
		int comparaison = o1.getNom().compareTo(o2.getNom());
		if(comparaison == 0) {
			comparaison = o1.getPrenom().compareTo(o2.getPrenom());
			if(comparaison == 0) {
				comparaison = o1.getIdentifiant().compareTo(o2.getIdentifiant());
			}
		}
		
		return comparaison;
	}

}
