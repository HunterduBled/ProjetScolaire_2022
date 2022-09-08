package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	//cd Desktop\L3_Informatique_Cours\Projet_S5\GestionRessourcesUniversitaires\gestionRessourcesUniversitaires\bin\
	
	private ServerSocket serverSocket ; 
	public Serveur(ServerSocket serverSocket) {
		this.serverSocket = serverSocket ; 
	}
	
	public void demarrerServeur() {
		
		try {// le serveur va constamment tourné jusqu'à ce qu'on ferme la connexion serverSocket
			while(! serverSocket.isClosed()) {
				Socket client = serverSocket.accept();//bloquant
				CommunicationClient com = new CommunicationClient(client);
				Thread thread = new Thread(com);
				thread.start();
				
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(6500);
			Serveur serveur = new Serveur(ss);
			InterfaceGestionnaireServeur InterfaceGestionServeur = new InterfaceGestionnaireServeur();
			Thread thread = new Thread(InterfaceGestionServeur);
			thread.start();
			serveur.demarrerServeur();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
