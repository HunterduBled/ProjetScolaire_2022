package bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.Groupe;
import metier.Utilisateur;

public class BddGroupe extends Bdd {
	
	public void ajouterGroupe (Groupe group ) {
		
		
		String nomGroupe = group.getNom();
		String typeGroupe = group.getType();
		String request  = "INSERT INTO groupe (nomGroupe, typeGroupe) VALUES ( '"+nomGroupe +"','"+typeGroupe+"')" ;
		
		try {
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(request);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
		}
		 
	
		
	}
	public void supprimerUtilisateurGroupe(String idUser,String nomGroupe ) {
		
		
		String request  = "DELETE from appartient where idUtilisateur =  '"+idUser+"' AND nomGroupe='"+nomGroupe+"'" ;
		
		try {
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(request);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
		}
		 
		
	}

	
	public void ajouterUtilisateurGroupe (Utilisateur user, Groupe group ) {
		
		
		String nomGroupe = group.getNom();
		String idUtilisateur = user.getIdentifiant();
		String request  = "INSERT INTO appartient (nomGroupe, idUtilisateur)  VALUES ( '"+nomGroupe +"','"+idUtilisateur+"')" ;
		
		try {
			Statement stmt = con.createStatement();
			int rs = stmt.executeUpdate(request);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
		}
		 
	
		
	}
	
	public Groupe getGroupe(String nomGroupe) {
		
		Groupe groupe = null ; 
		
		String request  = "select * from groupe where nomGroupe = '" + nomGroupe +"'";
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			 rs = stmt.executeQuery(request);
			while(rs.next()) {
				
				String type = rs.getString("typeGroupe");
				groupe =  new Groupe(nomGroupe, type);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		    try { rs.close(); } catch (Exception e) { /* Ignored */ }
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
		}
		 
		
		
		return groupe;
		
	}
	
	public List<Groupe> groupes() {
		
		
		List<Groupe> liste = new ArrayList<>();
		
		String request  = "select * from groupe";
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
	
	
	
	public List<Utilisateur> utilisateursGroupe(Groupe groupe){
		
		List<Utilisateur> liste = new ArrayList<>();
		
		String request  = "select * from Utilisateur,appartient where utilisateur.idUtilisateur = appartient.idUtilisateur and nomGroupe = '"+groupe.getNom()+"'";
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
				Utilisateur user = new Utilisateur(idUtilisateur, nom, prenom, motDePasse,typeUtilisateur);
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
	

	public List<Utilisateur> utilisateursHorsGroupe(Groupe groupe){
		
		List<Utilisateur> liste = new ArrayList<>();
		String request  = "select * from utilisateur   where idutilisateur not in(select u.idUtilisateur from appartient as a , utilisateur as u where u.idUtilisateur = a.idUtilisateur  and a.nomGroupe =  '"+groupe.getNom()+"')";
		System.out.println(request);
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
				Utilisateur user = new Utilisateur(idUtilisateur, nom, prenom, motDePasse,typeUtilisateur);
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
	
//	public static void main(String[] args) {
//		BddGroupe test  = new BddGroupe();
//		Groupe groupe = new Groupe ("TDA1", "utilisateurs");
//		Groupe groupe2 = new Groupe ("TDA2", "utilisateurs");
//		Groupe groupe3 = new Groupe ("TDA3", "utilisateurs");
//		Utilisateur user = new Utilisateur("babek13", "babek", "bensalem","babekPower");
//		Utilisateur user2 = new Utilisateur("ilyas13", "ilyas", "bensalem","ilyasPower");
//		
//
//
//		
//		test.getbddManager().closeConnection();
//	}

	

}
