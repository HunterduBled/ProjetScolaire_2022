package serveurVue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import serveur.InterfaceGestionnaireServeur;
import serveurControleur.ControleurGroupe;
import serveurControleur.ControleurListeUtilisateur;

public class VueAccueilPrincipal  extends JPanel {
	
	JButton boutonExtinctionServeur =  new JButton("fermer Serveur") ;

	
	JButton users = new JButton("Liste d'utilisateurs");

	private ControleurGroupe controleurGroupe;

	public VueAccueilPrincipal(InterfaceGestionnaireServeur interfaceGestionnaireServeur, FenetreAccueilServeur fenetreAccueilServeur){
		
		ControleurListeUtilisateur controleurListeUtilisateur = new ControleurListeUtilisateur(interfaceGestionnaireServeur,fenetreAccueilServeur);
	    controleurGroupe = new ControleurGroupe(this,interfaceGestionnaireServeur,fenetreAccueilServeur) ;
		users.addActionListener(controleurListeUtilisateur);
		   
        JPanel panGServ= getPanGServ();
        JPanel panDUsersModif = getPanDUsersAjoutModif();
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        this.add(panGServ);
        this.add(panDUsersModif);
        
        this.boutonExtinctionServeur.setVisible(false);


        this.setSize(1200,1000);
        this.setPreferredSize(new Dimension(1200,1000));
        this.setMaximumSize(new Dimension(1200, 1000));
        this.setMinimumSize(new Dimension(1200, 1000));



        layout.putConstraint(SpringLayout.NORTH, panGServ,0 , SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, panGServ, 0, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, panDUsersModif, 0, SpringLayout.NORTH, panGServ);
        layout.putConstraint(SpringLayout.WEST, panDUsersModif, 0, SpringLayout.EAST, panGServ);
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


        boutonExtinctionServeur.setSize(150,60);


        panServ.add(boutonExtinctionServeur);
        panServ.add(mesServices);
        panServ.add(universitaires);



        layout.putConstraint(SpringLayout.WEST, boutonExtinctionServeur, 80, SpringLayout.WEST, panServ);
        layout.putConstraint(SpringLayout.NORTH, boutonExtinctionServeur, 150, SpringLayout.NORTH, panServ);
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

    private JPanel getPanDUsersAjoutModif(){

        JPanel panDUsersAjoutModif= new JPanel();
        JLabel admin= new JLabel("Bienvenue, Administrateur Serveur");
        
        /*ici*/JButton groupe = new JButton("Groupe d'utilisateur");
        groupe.addActionListener(controleurGroupe);
        groupe.setActionCommand("GoGroup");
        ///*ici*/JButton retour = new JButton ("<");
        Font font = new Font("Arial", Font.BOLD, 25);

        SpringLayout layout = new SpringLayout();
        panDUsersAjoutModif.setLayout(layout);

        panDUsersAjoutModif.setBackground(Color.GRAY);
        admin.setFont(font);

        panDUsersAjoutModif.setSize(900,1000);
        panDUsersAjoutModif.setPreferredSize(new Dimension(900, 1000));

        admin.setSize(700,200);
        users.setSize(200,100);
        groupe.setSize(200,100);
        //retour.setSize(100,50);

        panDUsersAjoutModif.add(admin);
        panDUsersAjoutModif.add(users);
        panDUsersAjoutModif.add(groupe);
        //panDUsersAjoutModif.add(retour);



        layout.putConstraint(SpringLayout.NORTH, admin, 200, SpringLayout.NORTH, panDUsersAjoutModif);
        layout.putConstraint(SpringLayout.WEST, admin, 200, SpringLayout.WEST, panDUsersAjoutModif);

        layout.putConstraint(SpringLayout.NORTH, users, 850, SpringLayout.NORTH, panDUsersAjoutModif);
        layout.putConstraint(SpringLayout.WEST, users, 150, SpringLayout.WEST, panDUsersAjoutModif);

        layout.putConstraint(SpringLayout.NORTH, groupe, 850, SpringLayout.NORTH, panDUsersAjoutModif);
        layout.putConstraint(SpringLayout.WEST, groupe, 550, SpringLayout.WEST, panDUsersAjoutModif);

        return panDUsersAjoutModif;
	}

}
