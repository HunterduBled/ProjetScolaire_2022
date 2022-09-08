package client;

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
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

import clientControleur.ControleurConnexion;
import clientControleur.ControleurCreerTicket;
import clientControleur.ControleurMessages;
import clientControleur.ControleurSelectionnerFil;
import metier.FilDeDiscussion;
import metier.Groupe;
import metier.Message;
import metier.Tache;
import metier.Utilisateur;

public class Client {

	private Socket socket;
	private Utilisateur user;
	private AtomicBoolean clientConnecte =  new AtomicBoolean(false);
	private boolean premierConnexion = true;
	private BlockingQueue< List<Groupe>> queueGroupes = new LinkedBlockingQueue<>();
	private BlockingQueue<Message> queueMessages = new LinkedBlockingQueue<>();

	private OutputStream os ; 
	private InputStream is ;
	
	private DataOutputStream dos ;
	private DataInputStream dis;
	
	private ObjectOutputStream oos ;
	private ObjectInputStream ois ;
	
	private InputStreamReader isr; 
	private BufferedReader br ;
	private ControleurCreerTicket controleurTicket;
	private Thread ecouteThread;
	private ControleurSelectionnerFil controleurSelectionnerFil;
	private ControleurMessages controleurMessages;
	
	
	
	public Client(Socket socket ) {
		
		this.socket = socket;
		try {
			
			os = socket.getOutputStream();
			is = socket.getInputStream();
			
			
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
	
	
	public Utilisateur getUser() {
		return user;
	}


	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
	public void seDeconnecter() {
		

		try {
			dos.writeUTF(Tache.DEC.name());
			clientConnecte.set(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public boolean seConnecter() {
		boolean estConnecte = false ;
		try {
			
			dos.writeUTF(Tache.CON.name());
			oos.writeObject(user);
			estConnecte = dis.readBoolean();
			if(estConnecte) {
				user =(Utilisateur) ois.readObject();
				System.out.println(user.getIdentifiant() + " est connecte");
				premierConnexion = false;
			}else {
				user = null;
			}
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clientConnecte.set(true);
		return estConnecte;
	}
	
	public void envoyerMessage(Message m) {
		
		try {
			dos.writeUTF(Tache.ENV_MSG.name());
			oos.writeObject(m);
		} catch (IOException e1) {
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
	
	public  void ecouter(ControleurConnexion controleurConnexion) {
		   Thread ecouteThread = new Thread( new Runnable() {

			@Override
			public void run() {
				
				
					while(clientConnecte.get()) {
						String action = null ;
						
							try {
								action= dis.readUTF();
								System.out.println(action);

								Tache t = Tache.valueOf(action);
								switch(t) {
									case GET_GRP :
										
										try {
											List<Groupe> groupes = (List<Groupe>) ois.readObject();
											queueGroupes.put(groupes);
											
										} catch (ClassNotFoundException | InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										break;
									case CREER_FIL:
										try {
											FilDeDiscussion filCreer = (FilDeDiscussion)ois.readObject();
											controleurTicket.ajouterFil(getUser(), filCreer);
											
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										break;
									case REC_MSG :
										
										
										try {
											
											Message messageRecu = (Message)ois.readObject();
											controleurMessages.receptionnerMessage(messageRecu);

											
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										break;
										
									case CLOSE :
											
									case DEC : 
										clientConnecte.set(false);
										break;
									
										
								}
						
							} catch (IOException e) {
								e.printStackTrace();
								System.out.println(action);
								seDeconnecter();
								System.exit(1);
								
							}
						
						
					}
			
				
			}
			
		});
		this.ecouteThread = ecouteThread;
		ecouteThread.start();
		
		
	}
	public List<Groupe> getGroupes() {
		
		try {
			return  queueGroupes.take();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public Message getMessageRecu() {
		try {
			return  queueMessages.take();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<Groupe> getGroupesCreerFil() {
		
		try {
			dos.writeUTF(Tache.GET_GRP.name());
			return getGroupes();
		} catch (IOException e1) {
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return null;
	}
	
	public void creerFil(FilDeDiscussion filCreer) {
		

		try {
			
			dos.writeUTF(Tache.CREER_FIL.name());
			oos.writeObject(filCreer);
			
		} catch (IOException e1) {
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	public void recupererMessagesFil(FilDeDiscussion f) {
		
		try {
			
			dos.writeUTF(Tache.REC_MSG.name());
			oos.writeObject(f);
			
		} catch (IOException e1) {
			
		}
		
		
	}
	
	
	public void fermerConnexions() {
		
		try {
			
			
			
			queueGroupes.clear(); //pour pas que le threas qui ecoute soit bloque en attente
			
			dos.writeUTF(Tache.CLOSE.name());
			if(! premierConnexion) {
				try {
					ecouteThread.join();
				} catch (InterruptedException e) {
					while(clientConnecte.get());
				}
			}
			
			os.close();			
			dos.close();
			oos.close();
			
			dis.close();
			is.close();
			ois.close();
			isr.close();
			br.close();
			
			
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
	}


	public void ajouterControleurTicket(ControleurCreerTicket controleurTicket) {
		this.controleurTicket = controleurTicket;
		
	}


	public void ajouterControleurSelectionner(ControleurSelectionnerFil controleurSelectionnerFil) {
		this.controleurSelectionnerFil = controleurSelectionnerFil;
		
	}


	public void ajouterControleurMessages(ControleurMessages controleurMessages) {
		
		this.controleurMessages = controleurMessages;
	}





		
			
	

}
