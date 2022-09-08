package clientVue;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;

import client.Client;
import clientControleur.ControleurConnexion;
import clientControleur.ControleurCreerTicket;
import clientControleur.ControleurMessages;
import clientControleur.ControleurSelectionnerFil;
import metier.FilDeDiscussion;
import metier.Groupe;
import metier.Message;
import metier.Utilisateur;

import java.awt.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class VueChat extends JPanel{

	JButton boutonDeconnexion =  new JButton("Deconnexion") ;
	JTree jtree ;
	JButton boutonEnvoieMsg = new JButton("Envoyer");
	JButton boutonCreerFil = new JButton("- Nouveau Ticket -");
	JTextField barreMessage = new JTextField();
	Box box = Box.createVerticalBox();
	TreeModelTickets jtreeModel;
	JScrollPane jscrol;

	ControleurMessages controleurMessageEnvoi;
	private ControleurConnexion controleurConnexion;
	ControleurCreerTicket controleurTicket;
	ControleurSelectionnerFil controleurSelectionnerFil ;
	

	Utilisateur user ;
	FilDeDiscussion curFil = null;
	List<Groupe> groupesUser ;
	Client client;



	public VueChat(Client client, ControleurConnexion controleurConnexion){

		this.client = client;
		this.controleurConnexion = controleurConnexion;
		user = client.getUser();

		client.ecouter(controleurConnexion);
		controleurMessageEnvoi = new ControleurMessages(this, client);
		controleurSelectionnerFil = new ControleurSelectionnerFil(client,this);
		controleurTicket  =  new ControleurCreerTicket(client,this);
		client.ajouterControleurTicket(controleurTicket);
		client.ajouterControleurMessages(controleurMessageEnvoi);
		client.ajouterControleurSelectionner(controleurSelectionnerFil);

		boutonEnvoieMsg.addActionListener(controleurMessageEnvoi);
		boutonDeconnexion.addActionListener(controleurConnexion);
		boutonCreerFil.setActionCommand("OPEN_CREER_FIL");
        boutonCreerFil.addActionListener(controleurTicket);



		JPanel panG= getPanG();
        JPanel panMes=  getPanMes();
        jtree.addTreeSelectionListener(controleurSelectionnerFil);
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        this.add(panG);
        this.add(panMes);



        this.setBackground(Color.GRAY);
        this.setSize(1200,1000);
        this.setPreferredSize(new Dimension(1200,1000));
        this.setMaximumSize(new Dimension(1200, 1000));
        this.setMinimumSize(new Dimension(1200, 1000));



        layout.putConstraint(SpringLayout.NORTH, panG,0 , SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, panG, 0, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, panMes, 0, SpringLayout.NORTH, panG);
        layout.putConstraint(SpringLayout.WEST, panMes, 0, SpringLayout.EAST, panG);

    }

	public ControleurConnexion getControleurConnexion() {
		return controleurConnexion;
	}

	public String getMessage() {
		return barreMessage.getText();
	}
	public void setMessage() {
		
		barreMessage.setText("");
		
	}
	
	public void setCurFil(FilDeDiscussion curFil) {
		this.curFil = curFil;
	}
	
	public FilDeDiscussion getCurFil() {
		return curFil;
	}

	public void initJtree() {

		 NavigableMap<Groupe, NavigableSet<FilDeDiscussion>> fddg =  user.getFilsDeDiscussions();
		 Set<Groupe> groupes = fddg.keySet();
		 Iterator<Groupe> iter = groupes.iterator();
		 DefaultMutableTreeNode racineNode = new DefaultMutableTreeNode("Mes tickets");
		 for(;iter.hasNext() ;) {

			 Groupe g = iter.next();
			 DefaultMutableTreeNode gNode = new DefaultMutableTreeNode(g);
			 gNode.setAllowsChildren(true);
			 for(FilDeDiscussion f : fddg.get(g)) {

				 DefaultMutableTreeNode fNode = new DefaultMutableTreeNode(f);
				 fNode.setAllowsChildren(false);
				 gNode.add(fNode);

			 }

			 racineNode.add(gNode);

		 }
		 jtreeModel = new TreeModelTickets(racineNode);
		 jtreeModel.reload();
		 jtree = new JTree(jtreeModel);

	}


	//rajouter break et setparentNode qd on clique sur le fil aller directement au parent
	public synchronized void ajouterFil(Groupe groupeChoisi, FilDeDiscussion filCreer) {

		TreeModelTickets model = (TreeModelTickets) jtree.getModel();
		DefaultMutableTreeNode nodeRoot = (DefaultMutableTreeNode) model.getRoot();
		
		DefaultMutableTreeNode nodeFilCreer = new DefaultMutableTreeNode(filCreer);


		boolean nouveauGroupe = true ;
		int nbGroupes = nodeRoot.getChildCount();

		for(int i = 0 ; i < nbGroupes ; i ++) {
			DefaultMutableTreeNode nodeGrpe = (DefaultMutableTreeNode) nodeRoot.getChildAt(i);
			Groupe curGroupe =(Groupe) nodeGrpe.getUserObject();
			if(curGroupe.equals(groupeChoisi)) {

				nouveauGroupe = false;
				nodeGrpe.insert(nodeFilCreer, 0);
				
			}
		}
		if(nouveauGroupe) {
			NavigableMap<Groupe, DefaultMutableTreeNode> groupes = new TreeMap<>();
			DefaultMutableTreeNode newGroupeNode = new DefaultMutableTreeNode(groupeChoisi);
			newGroupeNode.insert(nodeFilCreer,0);
			
			groupes.put(groupeChoisi,newGroupeNode );
			nbGroupes = nodeRoot.getChildCount();
			for(int i = 0 ; i < nbGroupes ; i ++) {

				DefaultMutableTreeNode curNode = (DefaultMutableTreeNode) nodeRoot.getChildAt(i);
				groupes.put((Groupe) curNode.getUserObject(), curNode);

			}
			nodeRoot.removeAllChildren();
			for(Groupe g : groupes.keySet()) {
				nodeRoot.add(groupes.get(g));
			}
		}

		model.reload();
	}

	
	public synchronized void ajouterMessage(Message messageRecu) {
		
		FilDeDiscussion filMessage = messageRecu.getFilDeDiscussion();
		
		//ajouter message a l utilisateur 
		for( FilDeDiscussion fil : user.getFilsDeDiscussions().get(filMessage.getGroupeConcerne()) ) {
			
			if(fil.equals(filMessage)) {
				fil.ajouterMessage(messageRecu);
				break;
			}
			
		}
	
	
		// si l'utilisateur a cliqué sur le fil de discussion l afficher sur la vueChat
		if(controleurSelectionnerFil.getCurFil() != null) {
			
			if(controleurSelectionnerFil.getCurFil().equals(filMessage)) {
				
				box.add(componentMessage(messageRecu));
				
				jscrol.repaint();
				jscrol.updateUI();
				box.revalidate();
				box.repaint();
				box.updateUI();
				
				
				
			}
			
		}
		
		
		
		
		
		
	}




	public void afficherMessages(FilDeDiscussion filRecu) {
		box.removeAll();
		NavigableSet<FilDeDiscussion> fils = user.getFilsDeDiscussions().get(filRecu.getGroupeConcerne());
		FilDeDiscussion filAffiche = null ;
		for(FilDeDiscussion f : fils) {
			if(f.equals(filRecu)) {
				System.out.println("ici ok");
				filAffiche = f;
				setCurFil(filAffiche);
			}
		}
		if(filAffiche == null) {
			throw new TicketNonTrouveException("ticket non trouvé");
		}
		for(Message m : filAffiche.getMessages()) {
			JPanel mess = componentMessage(m); // a modifier
			 box.add(mess);
		}
		
		
	}






	/************************************************************************************************************************************************/
		private JPanel getPanG() {

			JPanel panG = new JPanel();
			BoxLayout boxlayout = new BoxLayout(panG, BoxLayout.Y_AXIS);
			panG.setLayout(boxlayout);

			panG.setSize(300,1000);
			panG.setPreferredSize(new Dimension(300,1000));

			JPanel mesService = getMesServices();
			JPanel arbre = getArbre();
			JPanel enBasDeLarbre = EnBasDeLarbre() ;

			panG.add(mesService);
			panG.add(arbre);
			panG.add(enBasDeLarbre);


			return panG;

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


		        boutonDeconnexion.setSize(150,60);


		        panServ.add(boutonDeconnexion);
		        panServ.add(mesServices);
		        panServ.add(universitaires);



						layout.putConstraint(SpringLayout.WEST, boutonDeconnexion, 80, SpringLayout.WEST, panServ);
		        layout.putConstraint(SpringLayout.NORTH, boutonDeconnexion, 150, SpringLayout.NORTH, panServ);
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

		        initJtree();

		    	jscrol = new JScrollPane(jtree);


		        jscrol.setBackground(Color.LIGHT_GRAY);

		        arbre.add(jscrol);



		        return arbre;
			}


			private JPanel EnBasDeLarbre(){

				JPanel enBasDeLarbre = new JPanel();
				SpringLayout layout = new SpringLayout();
		    enBasDeLarbre.setLayout(layout);

				Border lineborder = BorderFactory.createLineBorder(Color.black, 1);
				enBasDeLarbre.setBorder(lineborder);

				enBasDeLarbre.setBackground(Color.GRAY);
				enBasDeLarbre.setMaximumSize(new Dimension(300,100));
				enBasDeLarbre.setMinimumSize(new Dimension(300,100));



		        boutonCreerFil.setPreferredSize(new Dimension(150,40));
		        enBasDeLarbre.add(boutonCreerFil);
		        layout.putConstraint(SpringLayout.NORTH, boutonCreerFil, 10, SpringLayout.NORTH,enBasDeLarbre);
		        layout.putConstraint(SpringLayout.WEST, boutonCreerFil, 65, SpringLayout.WEST,enBasDeLarbre);

				return enBasDeLarbre;
		    }



		private JPanel getPanMes() {

			JPanel panMes = new JPanel();

			//SpringLayout layout = new SpringLayout();
			//panMes.setLayout(layout);
			panMes.setLayout(new BoxLayout(panMes, BoxLayout.Y_AXIS));

			panMes.setSize(900,1000);
			panMes.setPreferredSize(new Dimension(900,1000));
			panMes.setBackground(Color.LIGHT_GRAY);

			Font font = new Font("Arial", Font.PLAIN, 13);
			boutonCreerFil.setFont(font);

			JScrollPane jscrol = new JScrollPane(box);
			jscrol.setPreferredSize(new Dimension(500,750));


	        JPanel interfaceEnvoieMessage = new JPanel();
	        interfaceEnvoieMessage.setLayout(new FlowLayout());


	        

	        barreMessage.setBackground(Color.WHITE);
	        barreMessage.setPreferredSize(new Dimension(850,60));


	        interfaceEnvoieMessage.add(barreMessage);
	        interfaceEnvoieMessage.add(boutonEnvoieMsg);

	        panMes.add(jscrol);
	        panMes.add(interfaceEnvoieMessage);


	        return panMes;

		}

			private JPanel componentMessage(Message m) {


				JPanel compMessage = new JPanel();
				SpringLayout layout = new SpringLayout();
				compMessage.setLayout(layout);

				compMessage.setPreferredSize(new Dimension(880,250));
				compMessage.setBackground(Color.LIGHT_GRAY);

				JTextArea message= new JTextArea(m.getContenuMessage());


		        message.setSize(730,100);

		        Font font = new Font("Arial",Font.PLAIN,20);
		        Font fontMes = new Font("Arial",Font.TRUETYPE_FONT,15);
		        message.setLineWrap(true);
		        message.setWrapStyleWord(true);
		        message.setEditable(false);
		        message.setBackground(Color.LIGHT_GRAY);
		        message.setOpaque(true);
		        message.setFont(fontMes);

		        String identite = m.getEmetteur().getNom() + " " + m.getEmetteur().getPrenom();
		        JLabel nomEmetteur = new JLabel(identite );

		        nomEmetteur.setMaximumSize(new Dimension(100,50));
		        nomEmetteur.setMinimumSize(new Dimension(100,50));

		        nomEmetteur.setBackground(Color.LIGHT_GRAY);
		        nomEmetteur.setOpaque(true);
		        nomEmetteur.setFont(font);
		        m.setDateStringFormat();
		        JLabel date= new JLabel(m.getDateStringFormat());

		        date.setMaximumSize(new Dimension(200,50));
		        date.setMinimumSize(new Dimension(200,50));

		        date.setBackground(Color.LIGHT_GRAY);
		        date.setOpaque(true);
		        date.setFont(font);


		        compMessage.add(nomEmetteur);
		        compMessage.add(date);
		        compMessage.add(message);



		        layout.putConstraint(SpringLayout.NORTH, nomEmetteur, 0, SpringLayout.NORTH, compMessage);
		        layout.putConstraint(SpringLayout.WEST, nomEmetteur, 50, SpringLayout.WEST, compMessage);
		        layout.putConstraint(SpringLayout.NORTH, date, 0, SpringLayout.NORTH, compMessage);
		        layout.putConstraint(SpringLayout.WEST, date, 250, SpringLayout.WEST, compMessage);
		        layout.putConstraint(SpringLayout.NORTH, message, 50, SpringLayout.NORTH, compMessage);
		        layout.putConstraint(SpringLayout.WEST, message, 50, SpringLayout.WEST, compMessage);
		        return compMessage;

			}

			

			



}
