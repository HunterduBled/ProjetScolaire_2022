package serveurVue;

import javax.swing.*;
import javax.swing.border.Border;

import metier.Groupe;
import metier.Utilisateur;
import serveurControleur.ControleurGestionGroupe;
import serveurControleur.ControleurGroupe;

import java.awt.*;
import java.util.List;

public class VueGroupeUsers extends JPanel {

    private ControleurGroupe controleurGroupe;
    private ControleurGestionGroupe controleurGestionGroupe;
	private List<Groupe> groupes;

	 JTextField rechercheGroupe = new JTextField("groupe...");
     JTextField ajouterGroupeT = new JTextField("nv groupe...");

     

	public VueGroupeUsers(ControleurGroupe controleurGroupe, List<Groupe> groupes) {
    	
    	this.controleurGroupe = controleurGroupe;
    	this.controleurGestionGroupe = new ControleurGestionGroupe(this,controleurGroupe);
    	this.groupes = groupes;
    	
        JPanel panGServ= getPanGServ();

        JPanel panDGroupeUsers = getPanDGroupeUsers();
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

   public String groupeAAjouter() {
	   return this.ajouterGroupeT.getText();
   }

	public String getRechercheGroupe() {
		return rechercheGroupe.getText();
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

    private JScrollPane getJscrollGroup(){
        Box box = Box.createVerticalBox();
        
        
        for (Groupe g :  groupes){
            JPanel pan = getpanDGroupe(g);
            box.add(pan);
        }

        JScrollPane jscrol = new JScrollPane(box);
        jscrol.setPreferredSize(new Dimension(530,400));
        //jscrol.setSize(620,400);

        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        return jscrol;



    }

    private JPanel getpanDGroupe(Groupe g){
        JPanel panelDGroup= new JPanel();
   
        JLabel nomGroupe= new JLabel(g.getNom());
        JLabel comboboxRole = new JLabel();
        
        nomGroupe.setForeground(Color.white);
        comboboxRole.setForeground(Color.white);
        
        comboboxRole.setText(g.getType());
        
        nomGroupe.setHorizontalAlignment(SwingConstants.CENTER);
        comboboxRole.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        
        

        JButtonGroupe<Groupe> modifier = new JButtonGroupe<Groupe>();
        modifier.setText("gérer ce groupe");
        modifier.setActionCommand("MNG_GRP");
        modifier.setValue(g);
        modifier.addActionListener(controleurGestionGroupe);
        

        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        nomGroupe.setBorder(lineborder);

        SpringLayout layout = new SpringLayout();
        panelDGroup.setLayout(layout);
        panelDGroup.setBackground(Color.GRAY);

        panelDGroup.add(nomGroupe);
        panelDGroup.add(comboboxRole);
        panelDGroup.add(modifier);

        panelDGroup.setSize(510,40);
        panelDGroup.setPreferredSize(new Dimension(510,40));

        nomGroupe.setPreferredSize(new Dimension(150,40));
        comboboxRole.setPreferredSize(new Dimension(150,30));
        modifier.setPreferredSize(new Dimension(210,30));

        layout.putConstraint(SpringLayout.NORTH, nomGroupe, 0, SpringLayout.NORTH, panelDGroup);
        layout.putConstraint(SpringLayout.WEST, nomGroupe, 0, SpringLayout.WEST, panelDGroup);

        layout.putConstraint(SpringLayout.NORTH, comboboxRole, 5, SpringLayout.NORTH, nomGroupe);
        layout.putConstraint(SpringLayout.WEST, comboboxRole, 0, SpringLayout.EAST, nomGroupe);

        layout.putConstraint(SpringLayout.NORTH, modifier, 0, SpringLayout.NORTH, comboboxRole);
        layout.putConstraint(SpringLayout.WEST, modifier, 0, SpringLayout.EAST,comboboxRole);





        return panelDGroup;

    }



    private JPanel getPanDGroupeUsers() {

        JPanel panDGroupeUsers = new JPanel();
        JLabel modifUsers = new JLabel("Choisir groupes ");
        ///*ici*/JButton modifier = new JButton("Modifier");
        ///*ici*/JButton supprimer = new JButton("Supprimer");
        JLabel ajouterGroupe = new JLabel("Ajouter Groupe : ");
        /*ici*/JButton creer = new JButton("Creer");
        creer.addActionListener(controleurGroupe);
        creer.setActionCommand("CREER");
        /*ici*/JButton retour = new JButton("<");
        /*ihm*/ JPanel definitionJscroll= getDefinitionJscrollGroupe();
        retour.addActionListener(controleurGroupe);
        retour.setActionCommand("RET");
        
       
        
        /*ici*/ JButton valider = new JButton("rechercher");
        valider.addActionListener(controleurGroupe);
        
        valider.setActionCommand("Rechercher");
        
        
        /*ici*/String[] bookTitles = new String[] { "USER","SERVICE"};
   
        /*ici*/ JComboBox comboboxRole = new JComboBox(bookTitles);
        comboboxRole.addItemListener(controleurGroupe);
        controleurGroupe.setTypeNewGroupe();
        
        
        /*ici*/ JScrollPane JscrollGroup= getJscrollGroup();

        Font font = new Font("Arial", Font.BOLD, 25);
        Font font2 = new Font("Arial", Font.BOLD, 18);

        SpringLayout layout = new SpringLayout();
        panDGroupeUsers.setLayout(layout);
        panDGroupeUsers.setBackground(Color.GRAY);

        modifUsers.setSize(700, 200);
        //modifier.setPreferredSize(new Dimension(150, 50));
        creer.setPreferredSize(new Dimension(150, 30));
        //supprimer.setPreferredSize(new Dimension(150, 50));
        retour.setSize(100, 50);
        ajouterGroupe.setSize(150,40);
        ajouterGroupeT.setPreferredSize(new Dimension(150, 30));
        comboboxRole.setPreferredSize(new Dimension(150, 30));
        rechercheGroupe.setPreferredSize(new Dimension(150, 30));
        valider.setSize(100, 50);
        modifUsers.setFont(font);
        ajouterGroupe.setFont(font2);

        panDGroupeUsers.add(modifUsers);
        //panDGroupeUsers.add(modifier);
        panDGroupeUsers.add(creer);
        //panDGroupeUsers.add(supprimer);
        panDGroupeUsers.add(retour);
        panDGroupeUsers.add(rechercheGroupe);
        panDGroupeUsers.add(valider);
        panDGroupeUsers.add(ajouterGroupe);
        panDGroupeUsers.add(ajouterGroupeT);
        panDGroupeUsers.add(comboboxRole);
        panDGroupeUsers.add(JscrollGroup);
        /*ihm*/ panDGroupeUsers.add(definitionJscroll);


        panDGroupeUsers.setSize(900, 1000);
        panDGroupeUsers.setPreferredSize(new Dimension(900, 1000));

        layout.putConstraint(SpringLayout.NORTH, modifUsers, 200, SpringLayout.NORTH, panDGroupeUsers);
        layout.putConstraint(SpringLayout.WEST, modifUsers, 350, SpringLayout.WEST, panDGroupeUsers);

        layout.putConstraint(SpringLayout.NORTH, rechercheGroupe, 0, SpringLayout.NORTH, modifUsers);
        layout.putConstraint(SpringLayout.WEST, rechercheGroupe, 50, SpringLayout.EAST, modifUsers);

        layout.putConstraint(SpringLayout.NORTH, valider,10 , SpringLayout.SOUTH, rechercheGroupe);
        layout.putConstraint(SpringLayout.WEST, valider, 20, SpringLayout.WEST, rechercheGroupe);

        layout.putConstraint(SpringLayout.NORTH, ajouterGroupe, 900, SpringLayout.NORTH, panDGroupeUsers);
        layout.putConstraint(SpringLayout.WEST, ajouterGroupe, 50, SpringLayout.WEST, panDGroupeUsers);

        layout.putConstraint(SpringLayout.NORTH, ajouterGroupeT, 0, SpringLayout.NORTH, ajouterGroupe);
        layout.putConstraint(SpringLayout.WEST, ajouterGroupeT, 50, SpringLayout.EAST, ajouterGroupe);

        layout.putConstraint(SpringLayout.NORTH, comboboxRole, 0, SpringLayout.NORTH, ajouterGroupeT);
        layout.putConstraint(SpringLayout.WEST, comboboxRole, 50, SpringLayout.EAST, ajouterGroupeT);

        layout.putConstraint(SpringLayout.NORTH, creer, 0, SpringLayout.NORTH, comboboxRole);
        layout.putConstraint(SpringLayout.WEST, creer, 50, SpringLayout.EAST, comboboxRole);

        /*ihm*/layout.putConstraint(SpringLayout.NORTH, definitionJscroll, 400, SpringLayout.NORTH,panDGroupeUsers);
        /*ihm*/layout.putConstraint(SpringLayout.WEST, definitionJscroll, 200, SpringLayout.WEST, panDGroupeUsers);

        /*ihm*/layout.putConstraint(SpringLayout.NORTH, JscrollGroup, 0, SpringLayout.SOUTH,definitionJscroll);
        /*ihm*/layout.putConstraint(SpringLayout.WEST, JscrollGroup, 0, SpringLayout.WEST, definitionJscroll);


        return panDGroupeUsers;
        }

    private JPanel getDefinitionJscrollGroupe(){
        JPanel definitionJscroll = new JPanel();
        /*ici*/JLabel nom = new JLabel("nomGroupe");
        /*ici*/JLabel prenom = new JLabel("role");
        JLabel action = new JLabel("action");
        
        nom.setForeground(Color.white);
        prenom.setForeground(Color.white);
        action.setForeground(Color.white);
        
        nom.setHorizontalAlignment(SwingConstants.CENTER);
        prenom.setHorizontalAlignment(SwingConstants.CENTER);
        action.setHorizontalAlignment(SwingConstants.CENTER);
      

        definitionJscroll.add(nom);
        definitionJscroll.add(prenom);
        definitionJscroll.add(action);
        
        nom.setForeground(Color.white);
        prenom.setForeground(Color.white);
        action.setForeground(Color.white);

        SpringLayout layout = new SpringLayout();
        definitionJscroll.setLayout(layout);
        definitionJscroll.setBackground(Color.GRAY);

        Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
        nom.setBorder(lineborder);
        prenom.setBorder(lineborder);
        
        action.setBorder(lineborder);

        nom.setPreferredSize(new Dimension(150,40));
        prenom.setPreferredSize(new Dimension(150,40));
        
        action.setPreferredSize(new Dimension(210,40));

        definitionJscroll.setSize(600,40);
        definitionJscroll.setPreferredSize(new Dimension(600,40));

        layout.putConstraint(SpringLayout.NORTH, nom, 0, SpringLayout.NORTH, definitionJscroll);
        layout.putConstraint(SpringLayout.WEST, nom, 0, SpringLayout.WEST, definitionJscroll);

        layout.putConstraint(SpringLayout.NORTH, prenom, 0, SpringLayout.NORTH, nom);
        layout.putConstraint(SpringLayout.WEST, prenom, 0, SpringLayout.EAST, nom);

        layout.putConstraint(SpringLayout.NORTH, action, 0, SpringLayout.NORTH, prenom);
        layout.putConstraint(SpringLayout.WEST,action, 0, SpringLayout.EAST, prenom);

      

        return definitionJscroll;

    }

}
