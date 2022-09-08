package bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import metier.FilDeDiscussion;
import metier.Groupe;
import metier.Message;
import metier.Utilisateur;

public class BddFilDeDiscussion extends Bdd {
	
	public void ajouterFil(FilDeDiscussion f) {
	
		Timestamp ts = Timestamp.valueOf(f.getDateCreation()); 
		ts.setNanos(0);
		String request = "Insert into fildediscussion(titre,dateCreation,nomGroupe,Createur) values ('"+f.getTitre()+"','"+ts+"','"+f.getGroupeConcerne().getNom()+"','"+f.getCreateur()+"')";
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(request);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		new BddMessage().ajouterMessage(f.getPremierMessage(),true);
	
		
	}
	
	public Set<FilDeDiscussion> filsDeDiscussionsGroupe(Groupe groupeConcerne) {
		
		 Set<FilDeDiscussion> fils = new HashSet<>();
		 
		 String request = "select * from fildediscussion where nomGroupe= '"+groupeConcerne.getNom()+"'";
		 ResultSet rs = null ;
		 try {
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(request);
				while(rs.next()) {
					int idFil = rs.getInt("idFil");
					String titre = rs.getString("titre");
					java.sql.Timestamp ts  =  rs.getTimestamp("dateCreation");
					ts.setNanos(0);
					LocalDateTime dateCreation =  ts.toLocalDateTime();
					String createurId = rs.getString("createur");
					Utilisateur createur = new BddUtilisateur().getUtilisateur(createurId);
					Message premierMessage = new BddFilDeDiscussion().getPremierMessage(idFil,createur );
				
					FilDeDiscussion f = new FilDeDiscussion(titre, premierMessage,createurId, groupeConcerne);
					Set<Message> messagesFil = new BddMessage().recupererMessages(f);
					f.recupererMessages(messagesFil);
					fils.add(f);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
			    try { rs.close(); } catch (Exception e) { /* Ignored */ }
			    try { con.close(); } catch (Exception e) { /* Ignored */ }
			}
		 
		return fils;
	}
	
	
	public int getId(LocalDateTime dateCreation,String nomGroupe, String createur) {
		
		int id = 0; 
		Timestamp ts = Timestamp.valueOf(dateCreation); 
		ts.setNanos(0);
		String request  = "select idFil from fildediscussion where dateCreation = '"+ts+"' and  nomGroupe = '" + nomGroupe +"'and createur ='"+createur+"'";
		ResultSet rs = null;
		try {
			
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(request);
			if(rs.next()) {
				id = rs.getInt("idFil");
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		    try { rs.close(); } catch (Exception e) { /* Ignored */ }
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
		}
		
		return id ;
	}
	
	
	public Message getPremierMessage(int idFil, Utilisateur createur) {
		
		String request = "select * from message where idFil ='"+ idFil + "' order by dateCreation ASC limit 1";
		Message premierMessage = null;
		ResultSet rs = null;
		try {
			
			Statement stmt = con.createStatement();
			 rs = stmt.executeQuery(request);
			boolean notEmpty = rs.next();
			if(notEmpty) {
				String contenuMessage = rs.getString("contenu");
				java.sql.Timestamp ts  =  rs.getTimestamp("dateCreation");
				ts.setNanos(0);
				LocalDateTime dateCreation =  ts.toLocalDateTime();
				premierMessage = new Message(createur, contenuMessage);
				premierMessage.setDate(dateCreation);
				premierMessage.setDateStringFormat();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		    try { rs.close(); } catch (Exception e) { /* Ignored */ }
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
		}
		
		
		
		return premierMessage;
		
	}
//	public static void main(String[] args) throws SQLException {
//		
//		BddFilDeDiscussion bd = new BddFilDeDiscussion();
//		Utilisateur user = new Utilisateur("ilyas13",null, null, null, null);
//		@SuppressWarnings("deprecation")
//		FilDeDiscussion f = new FilDeDiscussion("probleme",new Message(user, "ce matin nous avons trouvé le chauffage en panne dans la salle u3206") ,user, new Groupe("TDA1",null));
//		FilDeDiscussion f2 = new FilDeDiscussion("report projet",new Message(user, "Est ce qu'il serait posssible de reporter la date de rendu du projet") ,user, new Groupe("TDA2",null));
//		bd.ajouterFil(f2);
		
//		java.sql.Timestamp ts = new java.sql.Timestamp(	date.getTime()); 
//			
//		int id = bd.getId(ts, "TDA2", new Utilisateur("ilyas13",null,null,null));
//		System.out.println(bd.filsDeDiscussionsGroupe(new Groupe("TDA2", null)));
		
//	}


	

}
