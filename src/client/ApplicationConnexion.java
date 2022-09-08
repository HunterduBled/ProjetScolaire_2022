package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import clientVue.FenetreConnexion;

public class ApplicationConnexion {

	public static void main(String[] args) throws UnknownHostException {
		
		Socket socket = null ;
		try {
			socket = new Socket("localhost", 6500);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Veuillez vous connecter au serveur tout d'abord");
			System.exit(1);
		}
		Client cl = new Client(socket);
		

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
            	FenetreConnexion fenetre = new FenetreConnexion(cl);
            	fenetre.setVisible(true);
            }
        });
		

		
	}

}
