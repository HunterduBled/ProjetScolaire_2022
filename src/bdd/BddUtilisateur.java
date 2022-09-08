package bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import metier.ComparaisonUtilisateurs;
import metier.FilDeDiscussion;
import metier.Groupe;
import metier.Message;
import metier.Utilisateur;

public class BddUtilisateur extends Bdd {
	
	
	public void ajouterUtilisateur (Utilisateur user ) {
		
		
		String idUtilisateur = user.getIdentifiant();
		String nom = user.getNom();
		String prenom = user.getPrenom();
		String motDePasse = user.getPassword();
		String type = user.getTypeUtilisateur();
		String request  = "INSERT INTO utilisateur (idUtilisateur, nom, prenom ,motDePasse,typeUtilisateur) VALUES ( '"+idUtilisateur + "' , '" +  nom +"', '" + prenom +"', '"+ motDePasse + "' ,'"+type+"')" ;
		Statement stmt; 
		int rs ;
		try {
		    stmt = con.createStatement();
			stmt.executeUpdate(request);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		    try { con.close(); } catch (Exception e) { }
		}
	
		
	}
	
	public void supprimerUtilisateur (Utilisateur user ) {
			
			
			String idUtilisateur = user.getIdentifiant();
			
			String request  = "DELETE from appartient where idUtilisateur =  '"+idUtilisateur+"'" ;
			
			try {
				Statement stmt = con.createStatement();
				int rs = stmt.executeUpdate(request);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request = "SET FOREIGN_KEY_CHECKS=0";
			
			try {
				Statement stmt = con.createStatement();
				boolean rs = stmt.execute(request);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			request  = "DELETE from utilisateur where idUtilisateur =  '"+idUtilisateur+"'" ;
			
			try {
				Statement stmt = con.createStatement();
				int rs = stmt.executeUpdate(request);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request = "SET FOREIGN_KEY_CHECKS=1";
			
			try {
				Statement stmt = con.createStatement();
				boolean rs = stmt.execute(request);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
			   
			    try { con.close(); } catch (Exception e) { /* Ignored */ }
			}
			
		
		
			
	}
	
	public void modifierUtilisateur (String  idUtilisateurInitial , Utilisateur modifie ) {
		
		
	
		String idUtilisateur = modifie.getIdentifiant();
		String nom = modifie.getNom();
		String prenom = modifie.getPrenom();
		String motDePasse = modifie.getPassword();
		String request  = "UPDATE utilisateur set idUtilisateur ='" + idUtilisateur + "',nom ='" + nom +"',prenom ='" + prenom +"', motDePasse ='" + motDePasse + "' where idUtilisateur = '"+idUtilisateurInitial+"'";
	
		
		try {
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(request);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		 
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
		}
	
		
	}
	
	public Utilisateur getUtilisateur(String idUtilisateur) {
		
		Utilisateur user = null;
		 String request = "select * from utilisateur where idUtilisateur= '"+idUtilisateur+"'";
		 
		 Statement stmt;
		 ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(request);
			while(rs.next()) {
				
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String motDePasse = rs.getString("motDePasse");
				String typeUtilisateur = rs.getString("typeUtilisateur");
				user =  new Utilisateur(idUtilisateur, nom, prenom, motDePasse,typeUtilisateur);
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		    try { rs.close(); } catch (Exception e) { /* Ignored */ }
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
		}
		 
		 return user;
		
		 
	}
	
	public NavigableSet<Utilisateur> listeUtilisateur(){
		NavigableSet<Utilisateur> liste = new TreeSet<>(new ComparaisonUtilisateurs());
		String request  = "select * from utilisateur";
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(request);
			while(rs.next()) {
				
				String idUtilisateur = rs.getString("idUtilisateur");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String motDePasse = rs.getString("motDePasse");
				String typeUtilisateur = rs.getString("typeUtilisateur");
				Utilisateur user =  new Utilisateur(idUtilisateur, nom, prenom, motDePasse,typeUtilisateur);
				liste.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		    try { rs.close(); } catch (Exception e) { /* Ignored */ }
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
	    }
		
		
		return liste;
		
	}
	
	public boolean seConnecter(Utilisateur user) {
		String idUtilisateur = user.getIdentifiant();
		String motDePasse = user.getPassword();
		String request  = "select count(*) from utilisateur where idUtilisateur = '"+idUtilisateur+"' and motDePasse = '"+motDePasse+"'";
		boolean connexion = false;
		ResultSet rs = null ;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(request);
			rs.next();
			connexion =  rs.getInt(1) == 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
		    try { rs.close(); } catch (Exception e) { /* Ignored */ }
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
	    }
		
		return connexion;
	}
	
	public Map<Groupe, Set<FilDeDiscussion>> filsDeDiscussionsParGroupeParUtilisateur(Utilisateur user){
		
		Map<Groupe,Set<FilDeDiscussion>> fg = new ConcurrentHashMap<>();
		List<Groupe> groupesUtilisateur =new BddUtilisateur().groupesParUtilisateur(user);
		for(Groupe g : groupesUtilisateur) {
			
			Set<FilDeDiscussion> filsGroupe = new BddFilDeDiscussion().filsDeDiscussionsGroupe(g);
			fg.put(g, filsGroupe);
			
		}
		
		
		return fg;
		
	}
	public Set<FilDeDiscussion> filsCreesParUtilisateurHorsGroupes(Utilisateur user){
		
		Set<FilDeDiscussion> fils = new HashSet<>();
		String idUtilisateur = user.getIdentifiant() ;
		String request  ="select titre, dateCreation , createur , fildediscussion.nomGroupe"
				+" from fildediscussion" 
				+" where createur = '"+idUtilisateur+"'"
				+"and fildediscussion.nomGroupe not in"
					+"(select groupe.nomGroupe from utilisateur , groupe,  appartient"
						+" where utilisateur.idUtilisateur = appartient.idUtilisateur" 
						+" and appartient.nomGroupe = groupe.nomGroupe" 
						+" and utilisateur.idUtilisateur = '"+idUtilisateur+"')";
		ResultSet rs= null ;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(request);
			while(rs.next()) {
				
				String titre = rs.getString("titre");
				
				Timestamp ts  =  rs.getTimestamp("dateCreation");
				ts.setNanos(0);
				LocalDateTime dateCreation = ts.toLocalDateTime();
				
				Utilisateur createur = user ;
				String nomGroupe = rs.getString("nomGroupe");
				Groupe groupe = new BddGroupe().getGroupe(nomGroupe);
				int idFil = new BddFilDeDiscussion().getId(dateCreation, nomGroupe, createur.getIdentifiant());
				Message premierMessage = new BddFilDeDiscussion().getPremierMessage(idFil,createur);
				FilDeDiscussion f = new FilDeDiscussion(titre, premierMessage, createur.getIdentifiant(), groupe);
				Set<Message> messagesFil = new BddMessage().recupererMessages(f);
				f.recupererMessages(messagesFil);
				fils.add(f);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try { con.close(); } catch (Exception e) { /* Ignored */ }

			try { rs.close(); } catch (Exception e) { /* Ignored */ }
		}
		//select * from fildediscussion where nomGroupe = "TDA3"
		
		return fils;
	}
	
	public List<Groupe> groupesParUtilisateur(Utilisateur user){
		
		
		List<Groupe> liste = new ArrayList<>();
		
		String request  = "select * from groupe,appartient where groupe.nomGroupe = appartient.nomGroupe  and appartient.idUtilisateur = '"+user.getIdentifiant()+"'";

		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(request);
			while(rs.next()) {
				
				String nomGroupe = rs.getString("nomGroupe");
				String type = rs.getString("typeGroupe");
				Groupe group =  new Groupe(nomGroupe, type);
				liste.add(group);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try { rs.close(); } catch (Exception e) { /* Ignored */ }
			try { con.close(); } catch (Exception e) { /* Ignored */ }
		}
		
		return liste;
		
	}
	

	
	public void recupererDonnees(Utilisateur user) {
		
		String idUtilisateur = user.getIdentifiant();
		String motDePasse = user.getPassword();
		String nom = null;
	    String prenom = null;
	    String typeUtilisateur = null;
		String request  = "select * from utilisateur where idUtilisateur = '"+idUtilisateur+"' and motDePasse = '"+motDePasse+"'";
		boolean connexion = false;
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(request);
			
			rs.next();//penser a mettre une exception si aucun resultat(normalement identifiant et mdp doivent y etre puisque connexion ok)
		    nom = rs.getString("nom");
		    prenom = rs.getString("prenom");
		    typeUtilisateur = rs.getString("typeUtilisateur");
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			try { rs.close(); } catch (Exception e) { /* Ignored */ }
			try { con.close(); } catch (Exception e) { /* Ignored */ }

		}
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setTypeUtilisateur(typeUtilisateur);		
		
		user.setFilsDeDiscussionGroupes(new BddUtilisateur().filsDeDiscussionsParGroupeParUtilisateur(user));
		user.setFilsDeDiscussionsHorsGroupe(new BddUtilisateur().filsCreesParUtilisateurHorsGroupes(user));
		user.setFilsDeDiscussions();
	
	
		
		
		
	}
	
//	public static void main(String[] args) {
//		BddUtilisateur test  = new BddUtilisateur();
//		//Utilisateur user = new Utilisateur("babek13", "babek", "bensalem","babekPower");
//		System.out.println(test.getUtilisateur("ilyas13"));
//		
//		test.getbddManager().closeConnection();
//	}

	
		
	
	

	

}
