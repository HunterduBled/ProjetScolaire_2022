package serveurVue;

import javax.swing.*;
import javax.swing.border.Border;

import metier.Groupe;
import metier.Utilisateur;
import serveurControleur.ControleurGestionGroupe;

import java.awt.*;
import java.util.List;

public class VueGestionGroupe extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField rechercheIdSupp = new JTextField("id...");
    JTextField rechercheIdSAdd= new JTextField("id...");
    Groupe groupeConcerne;
    ControleurGestionGroupe controleurGestionGroupe;
	private List<Utilisateur> userGroupeConcerne;
	private List<Utilisateur> userHorsGroupeConcerne;

    public VueGestionGroupe(Groupe groupeConcerne, List<Utilisateur> userGroupeConcerne, List<Utilisateur> userHorsGroupeConcerne, ControleurGestionGroupe controleurGestionGroupe){
    	
    	this.userGroupeConcerne = userGroupeConcerne;
    	this.userHorsGroupeConcerne = userHorsGroupeConcerne;
    	this.groupeConcerne = groupeConcerne;
    	this.controleurGestionGroupe = controleurGestionGroupe;
    	

    	
        JPanel panGServ= getPanGServ();
        JPanel panDGroupeUsers = getPanDGestionGroupUsers();
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        this.add(panGServ);
        this.add(panDGroupeUsers);


        this.setSize(1200,1000);
        this.setPreferredSize(new Dimension(1200,1000));
        this.setMaximumSize(new Dimension(1200, 1000));
        this.setMinimumSize(new Dimension(1200, 1000));



        layout.putConstraint(SpringLayout.NORTH, panGServ,0 , SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, panGServ, 0, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, panDGroupeUsers, 0, SpringLayout.NORTH, panGServ);
        layout.putConstraint(SpringLayout.WEST, panDGroupeUsers, 0, SpringLayout.EAST, panGServ);

    }
    
    

    public String getRechercheIdSupp() {
		return rechercheIdSupp.getText();
	}



	public String getRechercheIdSAdd() {
		return rechercheIdSAdd.getText();
	}



	public Groupe getGroupeConcerne() {
		return groupeConcerne;
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

    private JPanel getPanDGestionGroupUsers(){
        JPanel panDGestionGroupUsers = new JPanel();
        JScrollPane panSupprUsersGroup = getJscrollGroupSupprUtilisateurGroup();
        JScrollPane panAddUsersGroup = getJscrollGroupAddUtilisateurGroup();
        /*ici*/JButton retourGestionGroupUsers = new JButton("<");
        retourGestionGroupUsers.setActionCommand("RET");
        retourGestionGroupUsers.addActionListener(controleurGestionGroupe);
        JPanel panSuppUsersGroupRechercheSupp = getPansuppUsersGroupRecherche();
        JPanel panUsersGroupRechercheAdd =getPanAddUsersGroupRecherche();
        JLabel gestionGroupe = new JLabel("Gestion des utilisateurs du groupe "+groupeConcerne.getNom());
        /*ihm*/JPanel definitionGJscroll= getJScrollDefinitionGroupUsers();
        /*ihm*/ JPanel definitionDJscroll= getJScrollDefinitionGroupUsers();
        Font font = new Font("Arial", Font.BOLD, 25);


        gestionGroupe.setFont(font);

        panDGestionGroupUsers.add(panSupprUsersGroup);
        panDGestionGroupUsers.add(panAddUsersGroup);
        panDGestionGroupUsers.add(retourGestionGroupUsers);
        panDGestionGroupUsers.add(panSuppUsersGroupRechercheSupp);
        panDGestionGroupUsers.add(panUsersGroupRechercheAdd);
        panDGestionGroupUsers.add(gestionGroupe);
        /*ihm*/panDGestionGroupUsers.add(definitionGJscroll);
        /*ihm*/panDGestionGroupUsers.add(definitionDJscroll);

        gestionGroupe.setPreferredSize(new Dimension(700,150));

        SpringLayout layout = new SpringLayout();
        panDGestionGroupUsers.setLayout(layout);
        panDGestionGroupUsers.setBackground(Color.GRAY);

        layout.putConstraint(SpringLayout.NORTH, gestionGroupe, 50, SpringLayout.NORTH, panDGestionGroupUsers);
        layout.putConstraint(SpringLayout.WEST, gestionGroupe, 150, SpringLayout.WEST, panDGestionGroupUsers);

        layout.putConstraint(SpringLayout.NORTH, retourGestionGroupUsers, 0, SpringLayout.NORTH, panDGestionGroupUsers);
        layout.putConstraint(SpringLayout.WEST, retourGestionGroupUsers, 0, SpringLayout.WEST, panDGestionGroupUsers);

        /*ihm*/layout.putConstraint(SpringLayout.NORTH, definitionGJscroll, 450, SpringLayout.NORTH, panDGestionGroupUsers);
        /*ihm*/layout.putConstraint(SpringLayout.WEST, definitionGJscroll, 20, SpringLayout.WEST, panDGestionGroupUsers);

        /*ihm*/layout.putConstraint(SpringLayout.NORTH, panSupprUsersGroup, 0, SpringLayout.SOUTH, definitionGJscroll);
        /*ihm*/layout.putConstraint(SpringLayout.WEST, panSupprUsersGroup,0 , SpringLayout.WEST, definitionGJscroll);

        /*ihm*/layout.putConstraint(SpringLayout.NORTH, definitionDJscroll, 450, SpringLayout.NORTH, panDGestionGroupUsers);
        /*ihm*/layout.putConstraint(SpringLayout.WEST, definitionDJscroll, 470, SpringLayout.WEST, panDGestionGroupUsers);

        /*ihm*/layout.putConstraint(SpringLayout.NORTH,panAddUsersGroup, 0, SpringLayout.SOUTH, definitionDJscroll);
        /*ihm*/layout.putConstraint(SpringLayout.WEST, panAddUsersGroup, 0, SpringLayout.WEST, definitionDJscroll);

        layout.putConstraint(SpringLayout.NORTH,panSuppUsersGroupRechercheSupp ,250, SpringLayout.NORTH, panDGestionGroupUsers);
        layout.putConstraint(SpringLayout.WEST, panSuppUsersGroupRechercheSupp, 20, SpringLayout.WEST, panDGestionGroupUsers);

        layout.putConstraint(SpringLayout.NORTH,panUsersGroupRechercheAdd, 250, SpringLayout.NORTH, panDGestionGroupUsers);
        layout.putConstraint(SpringLayout.WEST, panUsersGroupRechercheAdd, 470, SpringLayout.WEST, panDGestionGroupUsers);

        panDGestionGroupUsers.setSize(900,1000);
        panDGestionGroupUsers.setPreferredSize(new Dimension(900,1000));

        return panDGestionGroupUsers;
    }

    private JScrollPane getJscrollGroupSupprUtilisateurGroup(){
        Box box = Box.createVerticalBox();
        for (Utilisateur u  : userGroupeConcerne){
            JPanel pan = getPanScrollBarSupprUtilisateurGroup(u);
            box.add(pan);
        }
        JScrollPane jscrolGroupSupprUtilisateurGroup = new JScrollPane(box);
        jscrolGroupSupprUtilisateurGroup.setPreferredSize(new Dimension(400,400));
        //jscrol.setSize(620,400);

        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        return jscrolGroupSupprUtilisateurGroup;

    }


    private JPanel getPanScrollBarSupprUtilisateurGroup(Utilisateur u){
        JPanel panScrollBarSupprUtilisateurGroup= new JPanel();
        /*ici*/JLabel nomPanScrollBarSupprUtilisateurGroup = new JLabel(u.getNom());
        /*ici*/JLabel prenomPanScrollBarSupprUtilisateurGroup = new JLabel(u.getPrenom());
        /*ici*/JLabel idPanScrollBarSupprUtilisateurGroup = new JLabel(u.getIdentifiant());
        
        nomPanScrollBarSupprUtilisateurGroup.setHorizontalAlignment(SwingConstants.CENTER);
        prenomPanScrollBarSupprUtilisateurGroup.setHorizontalAlignment(SwingConstants.CENTER);
        idPanScrollBarSupprUtilisateurGroup.setHorizontalAlignment(SwingConstants.CENTER);
        
        nomPanScrollBarSupprUtilisateurGroup.setForeground(Color.white);
        prenomPanScrollBarSupprUtilisateurGroup.setForeground(Color.white);
        idPanScrollBarSupprUtilisateurGroup.setForeground(Color.white);
        
        /*ici*/ JButtonUtilisateur<Utilisateur> jbuttonRetirerUtilisateurGroupe = new JButtonUtilisateur<>();
        jbuttonRetirerUtilisateurGroupe.setText("suppr");
        jbuttonRetirerUtilisateurGroupe.setValue(u);
        
        jbuttonRetirerUtilisateurGroupe.addActionListener(controleurGestionGroupe);
        jbuttonRetirerUtilisateurGroupe.setActionCommand("DEL_USR");
        

        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        nomPanScrollBarSupprUtilisateurGroup.setBorder(lineborder);
        prenomPanScrollBarSupprUtilisateurGroup.setBorder(lineborder);
        idPanScrollBarSupprUtilisateurGroup.setBorder(lineborder);
        jbuttonRetirerUtilisateurGroupe.setBorder(lineborder);

        SpringLayout layout = new SpringLayout();
        panScrollBarSupprUtilisateurGroup.setLayout(layout);
        panScrollBarSupprUtilisateurGroup.setBackground(Color.GRAY);

        panScrollBarSupprUtilisateurGroup.add(nomPanScrollBarSupprUtilisateurGroup);
        panScrollBarSupprUtilisateurGroup.add(prenomPanScrollBarSupprUtilisateurGroup);
        panScrollBarSupprUtilisateurGroup.add(idPanScrollBarSupprUtilisateurGroup);
        panScrollBarSupprUtilisateurGroup.add(jbuttonRetirerUtilisateurGroupe);

        panScrollBarSupprUtilisateurGroup.setSize(380,40);
        panScrollBarSupprUtilisateurGroup.setPreferredSize(new Dimension(380,40));

        nomPanScrollBarSupprUtilisateurGroup.setPreferredSize(new Dimension(95,40));
        prenomPanScrollBarSupprUtilisateurGroup.setPreferredSize(new Dimension(95,40));
        idPanScrollBarSupprUtilisateurGroup.setPreferredSize(new Dimension(95,40));
        jbuttonRetirerUtilisateurGroupe.setPreferredSize(new Dimension(95,30));

        layout.putConstraint(SpringLayout.NORTH, nomPanScrollBarSupprUtilisateurGroup, 0, SpringLayout.NORTH, panScrollBarSupprUtilisateurGroup);
        layout.putConstraint(SpringLayout.WEST, nomPanScrollBarSupprUtilisateurGroup, 0, SpringLayout.WEST, panScrollBarSupprUtilisateurGroup);

        layout.putConstraint(SpringLayout.NORTH,prenomPanScrollBarSupprUtilisateurGroup, 0, SpringLayout.NORTH, nomPanScrollBarSupprUtilisateurGroup);
        layout.putConstraint(SpringLayout.WEST, prenomPanScrollBarSupprUtilisateurGroup, 0, SpringLayout.EAST, nomPanScrollBarSupprUtilisateurGroup);

        layout.putConstraint(SpringLayout.NORTH, idPanScrollBarSupprUtilisateurGroup, 0, SpringLayout.NORTH, prenomPanScrollBarSupprUtilisateurGroup);
        layout.putConstraint(SpringLayout.WEST, idPanScrollBarSupprUtilisateurGroup, 0, SpringLayout.EAST,prenomPanScrollBarSupprUtilisateurGroup);

        layout.putConstraint(SpringLayout.NORTH, jbuttonRetirerUtilisateurGroupe, 5, SpringLayout.NORTH, idPanScrollBarSupprUtilisateurGroup);
        layout.putConstraint(SpringLayout.WEST, jbuttonRetirerUtilisateurGroupe, 0, SpringLayout.EAST,idPanScrollBarSupprUtilisateurGroup);

        return panScrollBarSupprUtilisateurGroup;

    }

    private JScrollPane getJscrollGroupAddUtilisateurGroup(){
        Box box = Box.createVerticalBox();
        for (Utilisateur u : userHorsGroupeConcerne){
            JPanel pan = getPanScrollBarAddUtilisateurGroup(u);
            box.add(pan);
        }
        JScrollPane jscrolPanScrollBarAddUtilisateurGroup = new JScrollPane(box);
        jscrolPanScrollBarAddUtilisateurGroup.setPreferredSize(new Dimension(400,400));
        //jscrol.setSize(620,400);

        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        return jscrolPanScrollBarAddUtilisateurGroup;



    }


    private JPanel getPanScrollBarAddUtilisateurGroup(Utilisateur u){
        JPanel panScrollBarAddUtilisateurGroup= new JPanel();
        /*ici*/JLabel nomPanScrollBarAddUtilisateurGroup = new JLabel(u.getNom());
        /*ici*/JLabel prenomPanScrollBarAddUtilisateurGroup = new JLabel(u.getPrenom());
        /*ici*/JLabel idPanScrollBarAddUtilisateurGroup = new JLabel(u.getIdentifiant());
        
        nomPanScrollBarAddUtilisateurGroup.setHorizontalAlignment(SwingConstants.CENTER);
        prenomPanScrollBarAddUtilisateurGroup.setHorizontalAlignment(SwingConstants.CENTER);
        idPanScrollBarAddUtilisateurGroup.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        nomPanScrollBarAddUtilisateurGroup.setForeground(Color.white);
        prenomPanScrollBarAddUtilisateurGroup.setForeground(Color.white);
        idPanScrollBarAddUtilisateurGroup.setForeground(Color.white);
        
        
        
        /*ici*/ JButtonUtilisateur<Utilisateur> jbuttonAddUtilisateurGroupe = new JButtonUtilisateur<Utilisateur>();
        jbuttonAddUtilisateurGroupe.setText("ajouter");
        jbuttonAddUtilisateurGroupe.addActionListener(controleurGestionGroupe);
        jbuttonAddUtilisateurGroupe.setActionCommand("ADD_USR");
        jbuttonAddUtilisateurGroupe.setValue(u);

        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        nomPanScrollBarAddUtilisateurGroup.setBorder(lineborder);
        prenomPanScrollBarAddUtilisateurGroup.setBorder(lineborder);
        idPanScrollBarAddUtilisateurGroup.setBorder(lineborder);
        jbuttonAddUtilisateurGroupe.setBorder(lineborder);

        SpringLayout layout = new SpringLayout();
        panScrollBarAddUtilisateurGroup.setLayout(layout);
        panScrollBarAddUtilisateurGroup.setBackground(Color.GRAY);

        panScrollBarAddUtilisateurGroup.add(nomPanScrollBarAddUtilisateurGroup);
        panScrollBarAddUtilisateurGroup.add(prenomPanScrollBarAddUtilisateurGroup);
        panScrollBarAddUtilisateurGroup.add(idPanScrollBarAddUtilisateurGroup);
        panScrollBarAddUtilisateurGroup.add(jbuttonAddUtilisateurGroupe);

        panScrollBarAddUtilisateurGroup.setSize(380,40);
        panScrollBarAddUtilisateurGroup.setPreferredSize(new Dimension(380,40));

        nomPanScrollBarAddUtilisateurGroup.setPreferredSize(new Dimension(95,40));
        prenomPanScrollBarAddUtilisateurGroup.setPreferredSize(new Dimension(95,40));
        idPanScrollBarAddUtilisateurGroup.setPreferredSize(new Dimension(95,40));
        jbuttonAddUtilisateurGroupe.setPreferredSize(new Dimension(95,30));

        layout.putConstraint(SpringLayout.NORTH, nomPanScrollBarAddUtilisateurGroup, 0, SpringLayout.NORTH, panScrollBarAddUtilisateurGroup);
        layout.putConstraint(SpringLayout.WEST, nomPanScrollBarAddUtilisateurGroup, 0, SpringLayout.WEST, panScrollBarAddUtilisateurGroup);

        layout.putConstraint(SpringLayout.NORTH,prenomPanScrollBarAddUtilisateurGroup, 0, SpringLayout.NORTH, nomPanScrollBarAddUtilisateurGroup);
        layout.putConstraint(SpringLayout.WEST, prenomPanScrollBarAddUtilisateurGroup, 0, SpringLayout.EAST, nomPanScrollBarAddUtilisateurGroup);

        layout.putConstraint(SpringLayout.NORTH, idPanScrollBarAddUtilisateurGroup, 0, SpringLayout.NORTH, prenomPanScrollBarAddUtilisateurGroup);
        layout.putConstraint(SpringLayout.WEST, idPanScrollBarAddUtilisateurGroup, 0, SpringLayout.EAST,prenomPanScrollBarAddUtilisateurGroup);

        layout.putConstraint(SpringLayout.NORTH, jbuttonAddUtilisateurGroupe, 5, SpringLayout.NORTH, idPanScrollBarAddUtilisateurGroup);
        layout.putConstraint(SpringLayout.WEST, jbuttonAddUtilisateurGroupe, 0, SpringLayout.EAST,idPanScrollBarAddUtilisateurGroup);

        return panScrollBarAddUtilisateurGroup;

    }

    private JPanel getPansuppUsersGroupRecherche(){
        JPanel panGestionIdSuppUsersGroup = new JPanel();
        JLabel suppUsersGroup = new JLabel("rechercher");
        this.rechercheIdSupp = new JTextField("id...");
        
        /*ici*/ JButton suppDuGroupe = new JButton("rechercher");
        suppDuGroupe.addActionListener(controleurGestionGroupe);
        suppDuGroupe.setActionCommand("SEARCH_DEL");

        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        panGestionIdSuppUsersGroup.setBorder(lineborder);

        panGestionIdSuppUsersGroup.setBackground(Color.GRAY);

        suppUsersGroup.setSize(200,50);
        this.rechercheIdSupp.setPreferredSize(new Dimension(150, 30));
        suppDuGroupe.setPreferredSize(new Dimension(130, 30));

        panGestionIdSuppUsersGroup.add(suppDuGroupe);
        panGestionIdSuppUsersGroup.add(this.rechercheIdSupp);
        panGestionIdSuppUsersGroup.add(suppUsersGroup);

        SpringLayout layout = new SpringLayout();
        panGestionIdSuppUsersGroup.setLayout(layout);

        Font font = new Font("Arial", Font.BOLD, 25);
        suppUsersGroup.setFont(font);

        panGestionIdSuppUsersGroup.setSize(400,120);
        panGestionIdSuppUsersGroup.setPreferredSize(new Dimension(400,120));

        layout.putConstraint(SpringLayout.NORTH,suppUsersGroup , 10, SpringLayout.NORTH, panGestionIdSuppUsersGroup);
        layout.putConstraint(SpringLayout.WEST, suppUsersGroup, 10, SpringLayout.WEST, panGestionIdSuppUsersGroup);

        layout.putConstraint(SpringLayout.NORTH, this.rechercheIdSupp, 0, SpringLayout.NORTH, suppUsersGroup);
        layout.putConstraint(SpringLayout.WEST, this.rechercheIdSupp, 50, SpringLayout.EAST, suppUsersGroup);

        layout.putConstraint(SpringLayout.NORTH,  suppDuGroupe,10 , SpringLayout.SOUTH,this.rechercheIdSupp);
        layout.putConstraint(SpringLayout.WEST,  suppDuGroupe, -60, SpringLayout.WEST, this.rechercheIdSupp);



        return panGestionIdSuppUsersGroup;
    }

    private JPanel getPanAddUsersGroupRecherche(){
        JPanel PanAddUsersGroupRecherche = new JPanel();
        JLabel AddUsersGroup = new JLabel("recherche");
        this.rechercheIdSAdd = new JTextField("id...");
        
        /*ici*/ JButton addDuGroupe = new JButton("ajouter");
        addDuGroupe.addActionListener(controleurGestionGroupe);
        addDuGroupe.setActionCommand("SEARCH_ADD");

        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        PanAddUsersGroupRecherche.setBorder(lineborder);

        PanAddUsersGroupRecherche.setBackground(Color.GRAY);

        AddUsersGroup.setSize(200,50);
        this.rechercheIdSAdd.setPreferredSize(new Dimension(150, 30));
        addDuGroupe.setPreferredSize(new Dimension(130, 30));

        PanAddUsersGroupRecherche.add(AddUsersGroup);
        PanAddUsersGroupRecherche.add(this.rechercheIdSAdd);
        PanAddUsersGroupRecherche.add(addDuGroupe);

        SpringLayout layout = new SpringLayout();
        PanAddUsersGroupRecherche.setLayout(layout);

        Font font = new Font("Arial", Font.BOLD, 25);
        AddUsersGroup.setFont(font);

        PanAddUsersGroupRecherche.setSize(400,120);
        PanAddUsersGroupRecherche.setPreferredSize(new Dimension(400,120));

        layout.putConstraint(SpringLayout.NORTH,AddUsersGroup , 10, SpringLayout.NORTH, PanAddUsersGroupRecherche);
        layout.putConstraint(SpringLayout.WEST, AddUsersGroup, 10, SpringLayout.WEST, PanAddUsersGroupRecherche);

        layout.putConstraint(SpringLayout.NORTH, this.rechercheIdSAdd, 0, SpringLayout.NORTH, AddUsersGroup);
        layout.putConstraint(SpringLayout.WEST, this.rechercheIdSAdd, 50, SpringLayout.EAST, AddUsersGroup);

        layout.putConstraint(SpringLayout.NORTH,addDuGroupe,10 , SpringLayout.SOUTH, this.rechercheIdSAdd);
        layout.putConstraint(SpringLayout.WEST, addDuGroupe, -60, SpringLayout.WEST,  this.rechercheIdSAdd);



        return PanAddUsersGroupRecherche;
    }
    
    /*ihm*/private JPanel getJScrollDefinitionGroupUsers(){
        JPanel definitionJscroll = new JPanel();
        /*ici*/JLabel nom = new JLabel("nom");
        /*ici*/JLabel prenom = new JLabel("prenom");
        /*ici*/JLabel id = new JLabel("id");
        JLabel action = new JLabel("action");
        
        nom.setHorizontalAlignment(SwingConstants.CENTER);
        prenom.setHorizontalAlignment(SwingConstants.CENTER);
        id.setHorizontalAlignment(SwingConstants.CENTER);
        action.setHorizontalAlignment(SwingConstants.CENTER);
        
        nom.setForeground(Color.white);
        prenom.setForeground(Color.white);
        id.setForeground(Color.white);
        action.setForeground(Color.white);

        definitionJscroll.add(nom);
        definitionJscroll.add(prenom);
        definitionJscroll.add(id);
        definitionJscroll.add(action);

        SpringLayout layout = new SpringLayout();
        definitionJscroll.setLayout(layout);
        definitionJscroll.setBackground(Color.GRAY);

        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        nom.setBorder(lineborder);
        prenom.setBorder(lineborder);
        id.setBorder(lineborder);
        action.setBorder(lineborder);

        nom.setPreferredSize(new Dimension(95,40));
        prenom.setPreferredSize(new Dimension(95,40));
        id.setPreferredSize(new Dimension(95,40));
        action.setPreferredSize(new Dimension(95,40));


        definitionJscroll.setSize(380,40);
        definitionJscroll.setPreferredSize(new Dimension(380,40));

        layout.putConstraint(SpringLayout.NORTH, nom, 0, SpringLayout.NORTH, definitionJscroll);
        layout.putConstraint(SpringLayout.WEST, nom, 0, SpringLayout.WEST, definitionJscroll);

        layout.putConstraint(SpringLayout.NORTH, prenom, 0, SpringLayout.NORTH, nom);
        layout.putConstraint(SpringLayout.WEST, prenom, 0, SpringLayout.EAST, nom);

        layout.putConstraint(SpringLayout.NORTH, id, 0, SpringLayout.NORTH, prenom);
        layout.putConstraint(SpringLayout.WEST, id, 0, SpringLayout.EAST, prenom);

        layout.putConstraint(SpringLayout.NORTH, action, 0, SpringLayout.NORTH, id);
        layout.putConstraint(SpringLayout.WEST, action, 0, SpringLayout.EAST,id);

        return definitionJscroll;

    }
    
    
}
