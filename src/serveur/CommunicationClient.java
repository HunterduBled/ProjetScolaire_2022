package serveur;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import bdd.BddFilDeDiscussion;
import bdd.BddGroupe;
import bdd.BddMessage;
import bdd.BddUtilisateur;
import metier.FilDeDiscussion;
import metier.Groupe;
import metier.Message;
import metier.Tache;
import metier.Utilisateur;

public class CommunicationClient implements Runnable{
	
	//quand clique sur fil de discussion --> tous les messages du fil de discussion sont marqué comme lu
	//faire requete map<Groupe,List< FilDeDiscussion >> dans utilisateur pour montrer tous les groupes ds lequel l'utilisateur fait partie
	//faire requete List<FilDeDiscussion> dans utilisateur ou on a les discussions que
	//supprimer un utilisateur et voir si il est toujours present dans la table bdd appartient.
	//quand on creer un fil de discussion, ce dernier utilise des methodes du premier message, si on veut pas avoir de nullpointerException il faut creer le premier message avant le fil utilisateur
	// les messages on les charge tous des la connexion ou est ce qu'on les recupere du serveur a chaque fois que l utilisateur clique.
	
	
	private Socket client ; 
	
	private OutputStream os ; 
	private InputStream is ;
	
	private DataOutputStream dos ;
	private DataInputStream dis;
	
	private ObjectOutputStream oos ;
	private ObjectInputStream ois ;
	
	private InputStreamReader isr; 
	private BufferedReader br ;
	private Utilisateur user ;


	
	private static Map<Utilisateur,Boolean> clientsConnectes = new ConcurrentHashMap<>();
	private static Map<Utilisateur,CommunicationClient> clients = new ConcurrentHashMap<>();
	
	
	public CommunicationClient(Socket client) {
		
		this.client = client;
		try {
			os = client.getOutputStream();
			is = client.getInputStream();
			
			dos = new DataOutputStream(os);
			dis = new DataInputStream(is);
			
			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);
			
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		
		try {
			

			String action = dis.readUTF();
			Tache tache = Tache.valueOf(action);
			
			while(tache != Tache.CLOSE) {
				
				switch(tache) {
				
					case CON :
						
						Utilisateur utilisateur  = (Utilisateur) ois.readObject(); //idenfie de quel utilisateur il s'agit.
						
						seConnecter(utilisateur);
						
						break;
					
					case DEC :
						seDeconnecter();
						dos.writeUTF(Tache.DEC.name());
						break;
						
					case CREER_FIL :
						
						FilDeDiscussion filCreer = (FilDeDiscussion) ois.readObject();
						
						new BddFilDeDiscussion().ajouterFil(filCreer);
						envoyerFil(filCreer); 
						break;
				
					case ENV_MSG :
						
						Message messageEnv = (Message) ois.readObject();
						
						//recuperer de la bdd les utilisateurs qui participent au fil de conversation de ce message
						new BddMessage().ajouterMessage(messageEnv,false);
						
						
						envoyerMessage(messageEnv); //envoi a tous les autres utilisateurs
						
						FilDeDiscussion curFil = messageEnv.getFilDeDiscussion();	
						/*faire la gestion hors connexion dans la prochaine etape*/
						
						
						
						break;
					case GET_GRP :
						List<Groupe> groupes = new BddGroupe().groupes();
						dos.writeUTF(Tache.GET_GRP.name());
						oos.writeObject(groupes);
						break;
					case REC_MSG : 
						/*
						FilDeDiscussion filSelect = (FilDeDiscussion) ois.readObject(); 
						Set<Message> messagesSelect = new BddMessage().recupererMessages(filSelect);
						filSelect.recupererMessages(messagesSelect);
						dos.writeUTF(Tache.REC_MSG.name());
						oos.writeObject(filSelect);*/
						break;
					case CLOSE : 
						dos.writeUTF(Tache.CLOSE.name());
						seDeconnecter();
						clients.remove(user);
						fermerConnexions();
						break;
					default : 
						break;
				}
				action = dis.readUTF();
				tache = Tache.valueOf(action);
				
			}
			dos.writeUTF(Tache.CLOSE.name());
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} 
		fermerConnexions();
		
	}
	
	
	private void envoyerMessage(Message messageEnv) {
		
		for(Utilisateur u : clientsConnectes.keySet()) { //
			
			if(! u.equals(user)) {//faire selon groupe : coder utilisateur.getGroupes
		
					
				FilDeDiscussion fil  = messageEnv.getFilDeDiscussion();
				String createurFil = fil.getCreateur();
				
				Groupe groupeFilDeDiscussion = messageEnv.getFilDeDiscussion().getGroupeConcerne();
				Set<Groupe> groupesRecepteur = u.getFilsDeDiscussionGroupes().keySet();
				
				if(groupesRecepteur.contains(groupeFilDeDiscussion) || u.getIdentifiant().equals(createurFil)){
					
					if(clientsConnectes.get(u)) {
						try {
							System.out.println(u);
							clients.get(u).dos.writeUTF(Tache.REC_MSG.name());
							clients.get(u).oos.writeObject(messageEnv);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						//traitement nonConnecte avec bdd
					}
				}		
			}	
		}
		
	}

	private void envoyerFil(FilDeDiscussion filCreer) {

		for(Utilisateur u : clientsConnectes.keySet()) { //
			
			if(! u.equals(user)) {//faire selon groupe : coder utilisateur.getGroupes
				
				Set<Groupe> groupesU = u.getFilsDeDiscussionGroupes().keySet();
				
				if(groupesU.contains(filCreer.getGroupeConcerne())) {
				
					if(clientsConnectes.get(u)) {
						
							System.out.println(u);
							try {
								clients.get(u).dos.writeUTF(Tache.CREER_FIL.name());
								clients.get(u).oos.writeObject(filCreer);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}else {
						//traitement hors connexion
					}
		    }	
		}
		
	}

	private void fermerConnexions() {
		
		try {
			
			dis.close();
			os.close();
			is.close();
			dos.close();
			oos.close();
			ois.close();
			isr.close();
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private void seDeconnecter() {
		
		clientsConnectes.put(user, false);
		//clients.remove(user);
		
	}

	private void seConnecter(Utilisateur utilisateur) throws IOException {
		
		boolean connexion = false; // l'utilisateur doit se connecter.
		
		connexion = new BddUtilisateur().seConnecter(utilisateur); //appel à la bd pour vérifier les identifiants
		dos.writeBoolean(connexion); //retour au client.
		if(connexion) {
			user = utilisateur;
			new BddUtilisateur().recupererDonnees(user);
			clientsConnectes.put(user, true); //connecter
			clients.put(user, this);
			oos.writeObject(user); // renvoyé l'utilisateur car dans lors de la connexion, on recupere les infos de la BD
			
		}
		
		
	}
	private void envoyer(Object o) {
		
		
		for(Utilisateur u : clientsConnectes.keySet()) {
			
			if(! u.equals(user)) {
				
				if(clientsConnectes.get(u)) {
					
					try {
						dos.writeUTF(Tache.CREER_FIL.name());
						clients.get(u).oos.writeObject(o);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}
	}
	


}
