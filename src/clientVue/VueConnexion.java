package clientVue;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import client.Client;
import clientControleur.ControleurConnexion;

public class VueConnexion extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton button ;
    private JTextField jtextId;
    private JPasswordField jtextMdp;
    private String id= new String();
    private String mdp = new String();
    ControleurConnexion controleurConnexion;
    
    public VueConnexion(Client client, FenetreConnexion fenetreConnexion) {

        JPanel panGServ= getPanGServ();
        this.setBackground(Color.GRAY);

    	controleurConnexion = new ControleurConnexion(this, client,fenetreConnexion);
    	
    	button = new JButton("Connexion");
    	button.addActionListener(controleurConnexion);
    	
    	jtextId=new JTextField();
    	
    	
    	jtextMdp=new JPasswordField();
    	
    	
        JLabel jlabId = new JLabel();
        jlabId.setText("identifiant");
        JLabel jlabMdp = new JLabel();
        jlabMdp.setText("mot de passe");
        
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        
        add(jlabId);
        add(jtextId);
        add(jlabMdp);
        add(jtextMdp);
        add(button);
        add(panGServ);
        
        //button.addActionListener(l);
        int fontSize = 20;
        Font f = new Font("serif", Font.BOLD, fontSize);
        jlabId.setFont(f);
        jlabMdp.setFont(f);

        this.setSize(1200,1000);
        this.setPreferredSize(new Dimension(1200, 1000));
        this.setMaximumSize(new Dimension(1200, 1000));
        this.setMinimumSize(new Dimension(1200, 1000));
        
        
        jtextId.setPreferredSize(new Dimension(300, 30));
        jtextMdp.setPreferredSize(new Dimension(300, 30));
        button.setPreferredSize(new Dimension(150, 40));
        
        panGServ.setSize(300,1000);
        panGServ.setPreferredSize(new Dimension(300, 1000));
        

  
        
        //int distanceHautFenetre = 250 ; //tout est relatif par rapport ? jlabId, tu changes les distances ?a modifie tous les autres.
        //int distanceGaucheFenetre = 75;

        layout.putConstraint(SpringLayout.NORTH, panGServ,0 , SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, panGServ, 0, SpringLayout.WEST, this);
        
        layout.putConstraint(SpringLayout.WEST, jlabId, 200, SpringLayout.EAST, panGServ);
        layout.putConstraint(SpringLayout.NORTH, jlabId, 350, SpringLayout.NORTH, panGServ);
        
        layout.putConstraint(SpringLayout.NORTH, jtextId, 0, SpringLayout.NORTH, jlabId);
        layout.putConstraint(SpringLayout.WEST, jtextId, 150, SpringLayout.EAST, jlabId);
        
        layout.putConstraint(SpringLayout.WEST, jlabMdp, 0, SpringLayout.WEST, jlabId);
        layout.putConstraint(SpringLayout.NORTH, jlabMdp, 20, SpringLayout.SOUTH, jlabId);
        
        layout.putConstraint(SpringLayout.NORTH, jtextMdp, 0, SpringLayout.NORTH, jlabMdp);
        layout.putConstraint(SpringLayout.WEST, jtextMdp, 120, SpringLayout.EAST, jlabMdp);
        
        layout.putConstraint(SpringLayout.NORTH, button, 60, SpringLayout.SOUTH, jlabMdp);
        layout.putConstraint(SpringLayout.WEST, button, 40, SpringLayout.EAST, jlabMdp);

        
        
        
        
        
    }

	public String getId() {
		return jtextId.getText();
	}
	
	public String getMdp() {
		return String.valueOf(jtextMdp.getPassword());
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

   
	
	

}
