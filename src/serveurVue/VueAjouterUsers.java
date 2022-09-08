package serveurVue;

import javax.swing.*;
import javax.swing.border.Border;

import serveurControleur.ControleurAjouterUtilisateur;

import java.awt.*;

public class VueAjouterUsers extends JPanel {

    private ControleurAjouterUtilisateur controleurAjouterUtilisateur;
    /*ici*/JTextField nomT = new JTextField("nom...");
    /*ici*/JTextField prenomT = new JTextField("Prenom...");
    /*ici*/JTextField identT = new JTextField("Identifiant...");
    /*ici*/JTextField mdpT = new JTextField("Mot de Passe...");
    JComboBox<String> role = new JComboBox<>();
    
   

	public VueAjouterUsers(ControleurAjouterUtilisateur controleurAjouterUtilisateur) {
    	
    	this.controleurAjouterUtilisateur = controleurAjouterUtilisateur;
    
    	
    	role.addItem("USER");
        role.addItem("SERVICE");
        role.setSelectedItem(new String("USER"));
        role.addItemListener(controleurAjouterUtilisateur);
        controleurAjouterUtilisateur.ajouterRole(role);
       
    	
        JPanel panGServ= getPanGServ();
        JPanel panDAjouterUsers = getPanDAjouterUsers();
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        this.add(panGServ);
        this.add(panDAjouterUsers);

        //this.setBackground(Color.GRAY);
        this.setSize(1200,1000);
        this.setPreferredSize(new Dimension(1200,1000));
        this.setMaximumSize(new Dimension(1200, 1000));
        this.setMinimumSize(new Dimension(1200, 1000));



        layout.putConstraint(SpringLayout.NORTH, panGServ,0 , SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, panGServ, 0, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, panDAjouterUsers, 0, SpringLayout.NORTH, panGServ);
        layout.putConstraint(SpringLayout.WEST, panDAjouterUsers, 0, SpringLayout.EAST, panGServ);



    }
	
	public String getRole() {
		return (String)role.getSelectedItem();
	}

    public String getNomT() {
		return nomT.getText();
	}





	public String getPrenomT() {
		return prenomT.getText();
	}



	public String getIdentT() {
		return identT.getText();
	}



	public String getMdpT() {
		return mdpT.getText();
	}



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

    private JPanel getPanDAjouterUsers(){
        JPanel panDAjouterUsers = new JPanel();

        JLabel creation = new JLabel(" - Creation d'un compte utilisateur -");
        JLabel nom= new JLabel("Nom :");
        JLabel Role= new JLabel("Role :");
   
        JLabel prenom = new JLabel("Prenom :");
        JLabel ident = new JLabel("Identification :");
        JLabel mdp = new JLabel("Mot de passe :");
      
        /*ici*/JButton valider = new JButton("Valider");
        valider.addActionListener(controleurAjouterUtilisateur);
        /*ici*/JButton retour = new JButton("<");
        retour.addActionListener(controleurAjouterUtilisateur);
        retour.setActionCommand("RET");
        Font font = new Font("Arial", Font.BOLD, 25);
        Font fonts = new Font("Calibri", Font.BOLD, 40);
        
        //String[] groupesTitles = new String[] {"TDA1","TDA2","TDA3","TDA4","TDA5",};

        //JComboBox<String> groupesC = new JComboBox<>(groupesTitles);

        panDAjouterUsers.setLayout(new BorderLayout());
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        panDAjouterUsers.setLayout(layout);
        panDAjouterUsers.setBackground(Color.GRAY);

        creation.setFont(fonts);
        nom.setFont(font);
        prenom.setFont(font);
        ident.setFont(font);
        mdp.setFont(font);
        Role.setFont(font);
 

        //panDAjouterUsers.add(groupesC);
        panDAjouterUsers.add(role);
        panDAjouterUsers.add(Role);
      
  
        panDAjouterUsers.add(creation);
        panDAjouterUsers.add(nom);
        panDAjouterUsers.add(prenom);
        panDAjouterUsers.add(ident);
        panDAjouterUsers.add(mdp);
        panDAjouterUsers.add(nomT);
        panDAjouterUsers.add(prenomT);
        panDAjouterUsers.add(identT);
        panDAjouterUsers.add(mdpT);
        panDAjouterUsers.add(valider);
        panDAjouterUsers.add(retour);

        Role.setSize(200,80);
        //groupesC.setSize(250,40);
        role.setSize(700,40);
        creation.setSize(700,200);
        nom.setSize(150,50);
        prenom.setSize(200,100);
        ident.setSize(200,100);
        mdp.setSize(200,100);
        nomT.setPreferredSize(new Dimension(150,25));
        prenomT.setPreferredSize(new Dimension(150,25));
        identT.setPreferredSize(new Dimension(150,25));
        mdpT.setPreferredSize(new Dimension(150,25));
        retour.setSize(100,50);
        valider.setPreferredSize(new Dimension(150,50));

        //this.setSize(900,1000);
        //this.setMaximumSize(new Dimension(900, 1000));
        //this.setMinimumSize(new Dimension(900, 1000));
        panDAjouterUsers.setSize(900,1000);
        panDAjouterUsers.setPreferredSize(new Dimension(900,1000));


        layout.putConstraint(SpringLayout.NORTH, creation, 150, SpringLayout.NORTH, panDAjouterUsers);
        layout.putConstraint(SpringLayout.WEST, creation, 50, SpringLayout.WEST, panDAjouterUsers);

        layout.putConstraint(SpringLayout.NORTH, nom, 350, SpringLayout.NORTH, panDAjouterUsers);
        layout.putConstraint(SpringLayout.WEST, nom, 35, SpringLayout.WEST, panDAjouterUsers);

        layout.putConstraint(SpringLayout.NORTH, nomT, 5, SpringLayout.NORTH, nom);
        layout.putConstraint(SpringLayout.WEST, nomT, 70, SpringLayout.EAST, nom);

        layout.putConstraint(SpringLayout.NORTH, prenom, 0, SpringLayout.NORTH, nom);
        layout.putConstraint(SpringLayout.WEST, prenom, 190, SpringLayout.EAST, nomT);

        layout.putConstraint(SpringLayout.NORTH, prenomT, 5, SpringLayout.NORTH, prenom);
        layout.putConstraint(SpringLayout.WEST, prenomT, 40, SpringLayout.EAST, prenom);

        layout.putConstraint(SpringLayout.NORTH, ident, 150, SpringLayout.NORTH, nom);
        layout.putConstraint(SpringLayout.WEST, ident, 0, SpringLayout.WEST, nom);

        layout.putConstraint(SpringLayout.NORTH, identT, 5, SpringLayout.NORTH, ident);
        layout.putConstraint(SpringLayout.WEST, identT, 10, SpringLayout.EAST, ident);

        layout.putConstraint(SpringLayout.NORTH, mdp, 0, SpringLayout.NORTH, ident);
        layout.putConstraint(SpringLayout.WEST, mdp, 100, SpringLayout.EAST, identT);

        layout.putConstraint(SpringLayout.NORTH, mdpT, 5, SpringLayout.NORTH, mdp);
        layout.putConstraint(SpringLayout.WEST, mdpT, 10, SpringLayout.EAST,mdp);

        layout.putConstraint(SpringLayout.NORTH, Role, -3, SpringLayout.NORTH, role);
        layout.putConstraint(SpringLayout.WEST, Role, 60, SpringLayout.WEST, panDAjouterUsers);

   

        layout.putConstraint(SpringLayout.NORTH, role, 150, SpringLayout.NORTH, identT);
        layout.putConstraint(SpringLayout.WEST, role, 150, SpringLayout.WEST, Role);



        //layout.putConstraint(SpringLayout.NORTH, groupeT, 40, SpringLayout.NORTH, groupesC);
        //layout.putConstraint(SpringLayout.WEST, groupeT, -20, SpringLayout.EAST,Groupe);

        layout.putConstraint(SpringLayout.NORTH, valider, 820, SpringLayout.NORTH, panDAjouterUsers);
        layout.putConstraint(SpringLayout.WEST, valider, 380, SpringLayout.WEST, panDAjouterUsers);

        return panDAjouterUsers;
    }
}
