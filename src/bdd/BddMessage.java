package bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import metier.FilDeDiscussion;
import metier.Message;
import metier.Utilisateur;

public class BddMessage extends Bdd {

	
	
    public void ajouterMessage(Message m,boolean premierMessage) {
    	
    	String contenu = m.getContenuMessage();
		String nomGroupe = m.getFilDeDiscussion().getGroupeConcerne().getNom();
		Timestamp ts = Timestamp.valueOf(m.getDate());
		ts.setNanos(0);
		String emetteur = m.getEmetteur().getIdentifiant();
		int idFilDeDiscussion ; 
		if(premierMessage) {
			
			idFilDeDiscussion = new BddFilDeDiscussion().getId(m.getDate(), nomGroupe, emetteur);
		}else {
			Message firstMessage = m.getFilDeDiscussion().getPremierMessage() ;
			String createurFil = firstMessage.getEmetteur().getIdentifiant();
			idFilDeDiscussion = new BddFilDeDiscussion().getId(firstMessage.getDate(), nomGroupe, createurFil);
		}
		System.out.println(idFilDeDiscussion);
		String request  = "INSERT INTO message (contenu,dateCreation,idUtilisateur,idFil) VALUES (?,?,?,?)" ;
		
//		String request  = "INSERT INTO message (contenu,dateCreation,idUtilisateur,idFil) VALUES ( '"+contenu +"','"+ts+"','"+emetteur+"',"+idFilDeDiscussion+")" ;
		PreparedStatement  stmt = null;
		try {
			
			
		    stmt = con.prepareStatement(request);
			
			stmt.setString(1, contenu);
			stmt.setTimestamp(2, ts);
			stmt.setString(3, emetteur);
			stmt.setInt(4, idFilDeDiscussion);
			
			int rs = stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { stmt.close(); } catch (Exception e) { /* Ignored */ }
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
		}
		
	}
    
  

    
    public Set<Message> recupererMessages (FilDeDiscussion f){
    	
    	Set<Message> messages = new HashSet<>() ;
    	int idFil = new BddFilDeDiscussion().getId(f.getDateCreation(), f.getGroupeConcerne().getNom(), f.getCreateur());
    	String request  = "select * from message where idFil = "+idFil;
    	
    	ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			 rs = stmt.executeQuery(request);
			while(rs.next()) {
				
				String contenuMessage = rs.getString("contenu");
				Timestamp ts = rs.getTimestamp("dateCreation");
				ts.setNanos(0);
				LocalDateTime dateCreation =  ts.toLocalDateTime();
				String emetteurString = rs.getString("idUtilisateur");
				Utilisateur emetteur = new BddUtilisateur().getUtilisateur(emetteurString);
				Message m = new Message(emetteur, contenuMessage);
				m.setDate(dateCreation);
				m.setDateStringFormat();
				messages.add(m);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		    try { rs.close(); } catch (Exception e) { /* Ignored */ }
		    try { con.close(); } catch (Exception e) { /* Ignored */ }
		}
		 
		return messages; 
    	
    }
//	@SuppressWarnings("deprecation")
//	public static void main(String[] args) {
//		
//     	BddMessage test  = new BddMessage();
//
//		Message m = new Message(new Utilisateur("ilyas13", null, null , null, null), "Est ce que vous etes d'accord pour demander collectivement aux profs de reporter le rendu du projet");	
//	
//		/*LocalDateTime d = (new LocalDateTime(new LocalDate(2022, 01, 05), new LocalTime(17, 34, 25, 0)) );
//		2022-01-05 17:34:25*/
//		LocalDateTime ldt = LocalDateTime.of(2022,01,05,17,43,46);
//
//		m.setDate(ldt);
//		FilDeDiscussion f = new FilDeDiscussion(null, m, null, new Groupe("TDA2",null));
//		m.setFilDeDiscussion(f);
//		
//		test.ajouterMessage(m);
//
//	}

}
