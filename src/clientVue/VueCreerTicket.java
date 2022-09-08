package clientVue;

import java.awt.*;
import java.util.List;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.Border;

import client.Client;
import clientControleur.ControleurConnexion;
import clientControleur.ControleurCreerTicket;
import metier.Groupe;
import metier.Utilisateur;

public class VueCreerTicket extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    private JTextArea message;
	private Client client;
	private  Utilisateur user;
	private JComboBox<Groupe> groupeBox ;
	private ControleurConnexion controleurConnexion;


	private JTextField titre;

	private JPanel getPanGServ() {

		JPanel panGServ = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panGServ, BoxLayout.Y_AXIS);
		panGServ.setLayout(boxlayout);

		panGServ.setSize(300,1000);
		panGServ.setPreferredSize(new Dimension(300,1000));

		JPanel mesService = getMesServices();
		JPanel arbre = getArbre();
		JPanel enBasDeLarbre = getEnBasDeLarbre() ;

		panGServ.add(mesService);
		panGServ.add(arbre);
		panGServ.add(enBasDeLarbre);


		return panGServ;

	}

	private JPanel getMesServices() {

		JPanel panServ = new JPanel();
		SpringLayout layout = new SpringLayout();
		panServ.setLayout(layout);

		Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
		panServ.setBorder(lineborder);


		panServ.setBackground(Color.GRAY);
		panServ.setMaximumSize(new Dimension(300, 200));
		panServ.setMinimumSize(new Dimension(300, 200));

		Font font = new Font("Arial", Font.BOLD, 25);

		JLabel mesServices = new JLabel("Mes services");
		mesServices.setFont(font);

		JLabel universitaires = new JLabel("Universitaires");
		universitaires.setFont(font);






		panServ.add(mesServices);
		panServ.add(universitaires);




		layout.putConstraint(SpringLayout.WEST, mesServices, 55, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, mesServices, 34, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, universitaires, 48, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, universitaires, 68, SpringLayout.NORTH, mesServices);

		return panServ;
	}

	private JPanel getArbre() {

		JPanel arbre = new JPanel();
		arbre.setLayout(new GridLayout(1, 1));



		Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
		arbre.setBorder(lineborder);

		//arbre.setBackground(Color.LIGHT_GRAY);
		arbre.setMaximumSize(new Dimension(300,700));
		arbre.setMinimumSize(new Dimension(300,700));

		return arbre;
	}

	private JPanel getEnBasDeLarbre(){

		JPanel enBasDeLarbre = new JPanel();

		Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
		enBasDeLarbre.setBorder(lineborder);


		enBasDeLarbre.setBackground(Color.GRAY);
		enBasDeLarbre.setMaximumSize(new Dimension(300,100));
		enBasDeLarbre.setMinimumSize(new Dimension(300,100));

		return enBasDeLarbre;
	}
	

	public VueCreerTicket(Client client, ControleurCreerTicket controleurCreerTicket) {

		JPanel panGServ= getPanGServ();
		this.setBackground(Color.GRAY);



		this.client = client;
		this.user =  client.getUser();
		List<Groupe> groupes = controleurCreerTicket.getGroupesCreerFil();
		

		
		JButton buttonValide= new JButton("Valider");
		buttonValide.addActionListener(controleurCreerTicket);
		buttonValide.setActionCommand("CREER_FIL");
		JButton buttonRetour= new JButton("Retour");
		buttonRetour.addActionListener(controleurCreerTicket);
		buttonRetour.setActionCommand("RET");
		titre = new JTextField("titre du ticket");
		message = new JTextArea("message");
		groupeBox = new JComboBox<>();


		
		for(Groupe g : groupes) {
			groupeBox.addItem(g);
	
		}
		groupeBox.addItemListener(controleurCreerTicket);
		if(groupes.size() > 0) {
			
			groupeBox.setSelectedItem(groupes.get(0));

		}
		
		JLabel groupe1 = new JLabel(" Groupe :");
		JLabel textEntree1 = new JLabel(user.getNom() +" "+ user.getPrenom());

		Font font = new Font("Arial", Font.BOLD, 25);

		textEntree1.setFont(font);
		groupe1.setFont(font);
	    
	    SpringLayout layout = new SpringLayout();
	    setLayout(layout);
	    
	 // _____________  Ajouts des composants : _________________
	    
	    add(buttonValide);
	    add(buttonRetour);
	    add(textEntree1);
	    add(groupeBox);
	    add(titre);
	    add(message);
	    add(groupe1);
		add(panGServ);
	    
	 // _____________  Composants Graphique : _________________

		this.setSize(1200,1000);
		this.setPreferredSize(new Dimension(1200, 1000));
		this.setMaximumSize(new Dimension(1200, 1000));
		this.setMinimumSize(new Dimension(1200, 1000));

	    groupe1.setPreferredSize(new Dimension(300, 50));
	    textEntree1.setPreferredSize(new Dimension(300, 50));
	    buttonValide.setPreferredSize(new Dimension(250, 30));
	    buttonRetour.setPreferredSize(new Dimension(250, 30));
	    groupeBox.setPreferredSize(new Dimension(150, 30));
	    titre.setPreferredSize(new Dimension(570, 30));
	    message.setPreferredSize(new Dimension(570,150));
	    

	    					
	    // _____________  Disposition Graphique : _________________


		layout.putConstraint(SpringLayout.NORTH, panGServ,0 , SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, panGServ, 0, SpringLayout.WEST, this);
	    
	    layout.putConstraint(SpringLayout.WEST, textEntree1, 350, SpringLayout.EAST, panGServ);
	    layout.putConstraint(SpringLayout.NORTH, textEntree1, 200, SpringLayout.NORTH, panGServ);
	    
	    layout.putConstraint(SpringLayout.NORTH, groupe1, 30, SpringLayout.SOUTH, textEntree1);
	    layout.putConstraint(SpringLayout.WEST, groupe1, -150, SpringLayout.WEST, textEntree1);
	    
	    layout.putConstraint(SpringLayout.NORTH, groupeBox, 10, SpringLayout.NORTH, groupe1);
	    layout.putConstraint(SpringLayout.WEST, groupeBox, 130, SpringLayout.WEST, groupe1);
	    
	    layout.putConstraint(SpringLayout.NORTH, titre, 40, SpringLayout.SOUTH ,groupe1);
	    layout.putConstraint(SpringLayout.WEST, titre, 0, SpringLayout.WEST, groupe1);
	    
	    layout.putConstraint(SpringLayout.NORTH, message, 10, SpringLayout.SOUTH, titre);
	    layout.putConstraint(SpringLayout.WEST, message, 0, SpringLayout.WEST, titre);
	   
	    layout.putConstraint(SpringLayout.NORTH, buttonValide, 40, SpringLayout.SOUTH, message);
	    layout.putConstraint(SpringLayout.WEST, buttonValide, 300, SpringLayout.WEST, message);
	    
	    layout.putConstraint(SpringLayout.NORTH, buttonRetour, 40, SpringLayout.SOUTH, message);
	    layout.putConstraint(SpringLayout.WEST, buttonRetour, 20, SpringLayout.WEST, message);
	}
	
	public String getMessage() {
		return message.getText();
	}
	
	public JComboBox<Groupe> getGroupe(){
		return groupeBox;
	}
	
	public String getTitre() {
		return titre.getText();
	}
	


}