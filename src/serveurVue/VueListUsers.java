package serveurVue;

import javax.swing.*;
import javax.swing.border.Border;

import bdd.BddUtilisateur;
import metier.Groupe;
import metier.Utilisateur;
import serveurControleur.ControleurAjouterUtilisateur;
import serveurControleur.ControleurListeUtilisateur;
import serveurControleur.ControleurModifierSupprimerUtilisateur;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.NavigableMap;
import java.util.Set;

public class VueListUsers extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NavigableMap<Utilisateur, Set<Groupe>> groupesUtilisateurs;
	

	private ControleurListeUtilisateur controleurListeUtilisateur;
	private ControleurModifierSupprimerUtilisateur controleurModifierSupprimerUtilisateur;
	private FenetreListUsers fenetreListUsers;
	private JTextField rechercheId = new JTextField("id...");
	private ControleurAjouterUtilisateur controleurAjouterUtilisateur;

	 public VueListUsers(ControleurListeUtilisateur controleurListeUtilisateur, FenetreListUsers fenetreListUsers) {
		 
		 this.controleurListeUtilisateur =controleurListeUtilisateur;
		 this.groupesUtilisateurs = controleurListeUtilisateur.getGroupesUtilisateur();
		 this.fenetreListUsers= fenetreListUsers;
		 
		 this.controleurModifierSupprimerUtilisateur = new ControleurModifierSupprimerUtilisateur(this,controleurListeUtilisateur.getInterfaceGestionnaireServeur(),fenetreListUsers);
		this.controleurAjouterUtilisateur = new ControleurAjouterUtilisateur(controleurListeUtilisateur , this);
        JPanel panGServ= getPanGServ();
        JPanel panDGroupeUsers = getPanDListUsers();
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        this.add(panGServ);
        this.add(panDGroupeUsers);

        //this.setBackground(Color.GRAY);
        this.setSize(1200,1000);
        this.setPreferredSize(new Dimension(1200,1000));
        this.setMaximumSize(new Dimension(1200, 1000));
        this.setMinimumSize(new Dimension(1200, 1000));



        layout.putConstraint(SpringLayout.NORTH, panGServ,0 , SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, panGServ, 0, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, panDGroupeUsers, 0, SpringLayout.NORTH, panGServ);
        layout.putConstraint(SpringLayout.WEST, panDGroupeUsers, 0, SpringLayout.EAST, panGServ);



    }
	
	

	public NavigableMap<Utilisateur, Set<Groupe>> getGroupesUtilisateurs() {
			return groupesUtilisateurs;
		}
	 
	 public String getRechercheId() {
		 return rechercheId.getText();
	 }
	 
	 
	 
 	public ControleurListeUtilisateur getControleurListeUtilisateur() {
		return controleurListeUtilisateur;
	}




	public ControleurAjouterUtilisateur getControleurAjouterUtilisateur() {
		return controleurAjouterUtilisateur;
	}



	public void ajouterFenetreListUsers(FenetreListUsers fenetreListUsers) {
		
		this.fenetreListUsers = fenetreListUsers;
		controleurListeUtilisateur.setFenetreListUsers(fenetreListUsers) ; 
		
	}
	
	
	public FenetreListUsers getFenetreListUsers() {
		return fenetreListUsers;
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

    private JScrollPane getJscrollUsers(){
        Box box = Box.createVerticalBox();

        for(Utilisateur u : this.groupesUtilisateurs.keySet()) {
        	
            JPanel pan = getpanDUsers(u);
            box.add(pan);
        }

        JScrollPane jscrol = new JScrollPane(box);
        jscrol.setPreferredSize(new Dimension(740,400));
        //jscrol.setSize(620,400);

        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        return jscrol;



    }

    private JPanel getpanDUsers(Utilisateur u){
    	
        JPanel panelDUsers= new JPanel();
        /*ici*/JLabel nom = new JLabel(u.getNom());
        /*ici*/JLabel prenom = new JLabel(u.getPrenom());
        /*ici*/JLabel id = new JLabel(u.getIdentifiant());
        /*ici*/JLabel role = new JLabel(u.getTypeUtilisateur());
        
        nom.setHorizontalAlignment(SwingConstants.CENTER);
        prenom.setHorizontalAlignment(SwingConstants.CENTER);
        id.setHorizontalAlignment(SwingConstants.CENTER);
        role.setHorizontalAlignment(SwingConstants.CENTER);
        
        nom.setForeground(Color.white);
        prenom.setForeground(Color.white);
        id.setForeground(Color.white);
        role.setForeground(Color.white);
        
        
        
        /*ici*/ JComboBox<Groupe> comboboxGroup = new JComboBox<Groupe>();
        		for(Groupe g : controleurListeUtilisateur.getInterfaceGestionnaireServeur(). groupesParUtilisateur(u)) {
        			comboboxGroup.addItem(g);
        			System.out.println(g);
        		}
        		//comboboxGroup.setSelectedIndex(0);
        		

        	
         JButtonUtilisateur<Utilisateur> modifier = new JButtonUtilisateur<Utilisateur>();
        		modifier.setText("modifier");
        		modifier.addActionListener(controleurModifierSupprimerUtilisateur);
        		modifier.setActionCommand("M");
        		modifier.setValue(u);
        		
         JButtonUtilisateur<Utilisateur> supprimer = new JButtonUtilisateur<Utilisateur>();
        		supprimer.setText("supprimer");
        		supprimer.addActionListener(controleurModifierSupprimerUtilisateur);
        		supprimer.setActionCommand("S");
        		supprimer.setValue(u);
        	
 
        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        nom.setBorder(lineborder);
        prenom.setBorder(lineborder);
        id.setBorder(lineborder);
        role.setBorder(lineborder);

        SpringLayout layout = new SpringLayout();
        panelDUsers.setLayout(layout);
        panelDUsers.setBackground(Color.GRAY);

        panelDUsers.add(nom);
        panelDUsers.add(prenom);
        panelDUsers.add(id);
        panelDUsers.add(role);
        panelDUsers.add(comboboxGroup);
        panelDUsers.add(modifier);
        panelDUsers.add(supprimer);

        panelDUsers.setSize(720,40);
        panelDUsers.setPreferredSize(new Dimension(720,40));

        nom.setPreferredSize(new Dimension(100,40));
        prenom.setPreferredSize(new Dimension(100,40));
        id.setPreferredSize(new Dimension(100,40));
        role.setPreferredSize(new Dimension(100,40));
        comboboxGroup.setPreferredSize(new Dimension(100,30));
        modifier.setPreferredSize(new Dimension(110,30));
        supprimer.setPreferredSize(new Dimension(110,30));

        layout.putConstraint(SpringLayout.NORTH, nom, 0, SpringLayout.NORTH, panelDUsers);
        layout.putConstraint(SpringLayout.WEST, nom, 0, SpringLayout.WEST, panelDUsers);

        layout.putConstraint(SpringLayout.NORTH,prenom, 0, SpringLayout.NORTH, nom);
        layout.putConstraint(SpringLayout.WEST, prenom, 0, SpringLayout.EAST, nom);

        layout.putConstraint(SpringLayout.NORTH, id, 0, SpringLayout.NORTH, prenom);
        layout.putConstraint(SpringLayout.WEST, id, 0, SpringLayout.EAST,prenom);

        layout.putConstraint(SpringLayout.NORTH, role, 0, SpringLayout.NORTH, id);
        layout.putConstraint(SpringLayout.WEST, role, 0, SpringLayout.EAST,id);

        layout.putConstraint(SpringLayout.NORTH, comboboxGroup, 5, SpringLayout.NORTH, role);
        layout.putConstraint(SpringLayout.WEST, comboboxGroup, 0, SpringLayout.EAST,role);

        layout.putConstraint(SpringLayout.NORTH, modifier, 0, SpringLayout.NORTH, comboboxGroup);
        layout.putConstraint(SpringLayout.WEST, modifier, 0, SpringLayout.EAST,comboboxGroup);

        layout.putConstraint(SpringLayout.NORTH, supprimer, 0, SpringLayout.NORTH, modifier);
        layout.putConstraint(SpringLayout.WEST, supprimer, 0, SpringLayout.EAST,modifier);



        return panelDUsers;

    }

    public JPanel getPanDListUsers(){

        JPanel panDListUsers = new JPanel();
        JLabel modifUsers =new JLabel("Choisir utilisateurs ");
        /*ihm*/JPanel definitionScrollPan= getDefinitionScroll();
       
       
   
        JButtonUtilisateur<Utilisateur> modifier = new JButtonUtilisateur<Utilisateur>();
       		modifier.setText("modifier");
       		modifier.addActionListener(controleurModifierSupprimerUtilisateur);
       		modifier.setActionCommand("MID");
       		
       		
        JButtonUtilisateur<Utilisateur> supprimer = new JButtonUtilisateur<Utilisateur>();
       		supprimer.setText("supprimer");
       		supprimer.addActionListener(controleurModifierSupprimerUtilisateur);
       		supprimer.setActionCommand("SID");
 
        /*ici*/JButton retour = new JButton("<");
        retour.addActionListener(controleurListeUtilisateur);
        retour.setActionCommand("RET");
        JButton creer = new JButton("creer utilisateur");
        creer.addActionListener(controleurAjouterUtilisateur);
        creer.setActionCommand("CREER");
        
        /*ici*/ JScrollPane JscrollUsers= getJscrollUsers();


        Font font = new Font("Arial", Font.BOLD, 25);

        SpringLayout layout = new SpringLayout();
        panDListUsers.setLayout(layout);

        panDListUsers.setBackground(Color.GRAY);

        modifUsers.setSize(700,200);
        rechercheId.setPreferredSize(new Dimension(150, 30));
        modifier.setPreferredSize(new Dimension(130, 30));
        supprimer.setPreferredSize(new Dimension(130, 30));
        creer.setPreferredSize(new Dimension(130, 40));
        retour.setSize(100,50);

        modifUsers.setFont(font);

        panDListUsers.add(modifUsers);
        panDListUsers.add(retour);
        panDListUsers.add(JscrollUsers);
        panDListUsers.add(supprimer);
        panDListUsers.add(modifier);
        panDListUsers.add(rechercheId);
        panDListUsers.add(creer);
        panDListUsers.add(definitionScrollPan);



        panDListUsers.setSize(900,1000);
        panDListUsers.setPreferredSize(new Dimension(900,1000));

        layout.putConstraint(SpringLayout.NORTH, modifUsers, 200, SpringLayout.NORTH, panDListUsers);
        layout.putConstraint(SpringLayout.WEST, modifUsers, 300, SpringLayout.WEST, panDListUsers);

        layout.putConstraint(SpringLayout.NORTH, rechercheId, 0, SpringLayout.NORTH, modifUsers);
        layout.putConstraint(SpringLayout.WEST, rechercheId, 50, SpringLayout.EAST, modifUsers);

        layout.putConstraint(SpringLayout.NORTH, modifier,10 , SpringLayout.SOUTH,rechercheId);
        layout.putConstraint(SpringLayout.WEST, modifier, -60, SpringLayout.WEST, rechercheId);

        layout.putConstraint(SpringLayout.NORTH, supprimer,0 , SpringLayout.NORTH,modifier);
        layout.putConstraint(SpringLayout.WEST, supprimer, 20, SpringLayout.EAST, modifier);

        /*ihm*/layout.putConstraint(SpringLayout.NORTH, definitionScrollPan, 400, SpringLayout.NORTH,panDListUsers);
        /*ihm*/layout.putConstraint(SpringLayout.WEST, definitionScrollPan, 100, SpringLayout.WEST, panDListUsers);

        /*ihm*/layout.putConstraint(SpringLayout.NORTH, JscrollUsers, 0, SpringLayout.SOUTH,definitionScrollPan);
        /*ihm*/layout.putConstraint(SpringLayout.WEST, JscrollUsers, 0, SpringLayout.WEST, definitionScrollPan);
        
        layout.putConstraint(SpringLayout.NORTH, creer, 900, SpringLayout.NORTH,panDListUsers);
        layout.putConstraint(SpringLayout.WEST, creer, 400, SpringLayout.WEST, panDListUsers);

        return panDListUsers;
    }

	public void nullifierFenetreListUsers() {
		this.fenetreListUsers = null;
	}
	
	/*ihm*/private JPanel getDefinitionScroll(){
        JPanel panelDUsers= new JPanel();
        /*ici*/JLabel nom = new JLabel("nom");
        /*ici*/JLabel prenom = new JLabel("prenom");
        /*ici*/JLabel id = new JLabel("id");
        /*ici*/JLabel role = new JLabel("role");
        /*ici*/ JLabel groupe = new JLabel("groupe");
        /*ici*/ JLabel modifier = new JLabel("modifier");
        /*ici*/ JLabel supprimer = new JLabel("supprimer");
        
        nom.setForeground(Color.white);
        prenom.setForeground(Color.white);
        id.setForeground(Color.white);
        role.setForeground(Color.white);
        groupe.setForeground(Color.white);
        modifier.setForeground(Color.white);
        supprimer.setForeground(Color.white);
        
        nom.setHorizontalAlignment(SwingConstants.CENTER);
        prenom.setHorizontalAlignment(SwingConstants.CENTER);
        id.setHorizontalAlignment(SwingConstants.CENTER);
        role.setHorizontalAlignment(SwingConstants.CENTER);
        groupe.setHorizontalAlignment(SwingConstants.CENTER);
        modifier.setHorizontalAlignment(SwingConstants.CENTER);
        supprimer.setHorizontalAlignment(SwingConstants.CENTER);
        


        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        nom.setBorder(lineborder);
        prenom.setBorder(lineborder);
        id.setBorder(lineborder);
        role.setBorder(lineborder);
        groupe.setBorder(lineborder);
        modifier.setBorder(lineborder);
        supprimer.setBorder(lineborder);

        SpringLayout layout = new SpringLayout();
        panelDUsers.setLayout(layout);
        panelDUsers.setBackground(Color.GRAY);

        panelDUsers.add(nom);
        panelDUsers.add(prenom);
        panelDUsers.add(id);
        panelDUsers.add(role);
        panelDUsers.add(groupe);
        panelDUsers.add(modifier);
        panelDUsers.add(supprimer);

        panelDUsers.setSize(720,40);
        panelDUsers.setPreferredSize(new Dimension(720,40));

        nom.setPreferredSize(new Dimension(100,40));
        prenom.setPreferredSize(new Dimension(100,40));
        id.setPreferredSize(new Dimension(100,40));
        role.setPreferredSize(new Dimension(100,40));
        groupe.setPreferredSize(new Dimension(100,40));
        modifier.setPreferredSize(new Dimension(110,40));
        supprimer.setPreferredSize(new Dimension(110,40));

        layout.putConstraint(SpringLayout.NORTH, nom, 0, SpringLayout.NORTH, panelDUsers);
        layout.putConstraint(SpringLayout.WEST, nom, 0, SpringLayout.WEST, panelDUsers);

        layout.putConstraint(SpringLayout.NORTH,prenom, 0, SpringLayout.NORTH, nom);
        layout.putConstraint(SpringLayout.WEST, prenom, 0, SpringLayout.EAST, nom);

        layout.putConstraint(SpringLayout.NORTH, id, 0, SpringLayout.NORTH, prenom);
        layout.putConstraint(SpringLayout.WEST, id, 0, SpringLayout.EAST,prenom);

        layout.putConstraint(SpringLayout.NORTH, role, 0, SpringLayout.NORTH, id);
        layout.putConstraint(SpringLayout.WEST, role, 0, SpringLayout.EAST,id);

        layout.putConstraint(SpringLayout.NORTH, groupe, 0, SpringLayout.NORTH, role);
        layout.putConstraint(SpringLayout.WEST, groupe, 0, SpringLayout.EAST,role);

        layout.putConstraint(SpringLayout.NORTH, modifier, 0, SpringLayout.NORTH, groupe);
        layout.putConstraint(SpringLayout.WEST, modifier, 0, SpringLayout.EAST,groupe);

        layout.putConstraint(SpringLayout.NORTH, supprimer, 0, SpringLayout.NORTH, modifier);
        layout.putConstraint(SpringLayout.WEST, supprimer, 0, SpringLayout.EAST,modifier);



        return panelDUsers;

    }





	



	
}
