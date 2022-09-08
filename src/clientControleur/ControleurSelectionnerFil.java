package clientControleur;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import client.Client;
import clientVue.TreeModelTickets;
import clientVue.VueChat;
import metier.FilDeDiscussion;

public class ControleurSelectionnerFil implements TreeSelectionListener  {
	
	

	private VueChat vueChat;
	private Client client;
	private FilDeDiscussion curFil ;

	public ControleurSelectionnerFil(Client client, VueChat vueChat) {
		super();
		this.client = client; 
		client.ajouterControleurSelectionner(this);
		this.vueChat = vueChat ; 
		
	}
    /*
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
		JTree jtree =(JTree )  e.getSource();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtree.getLastSelectedPathComponent();
		if(node == null ) {
			return;
		}
		TreeModelTickets treeModel = (TreeModelTickets) jtree.getModel();
		if(treeModel.isLeaf(node)) {
			FilDeDiscussion f = (FilDeDiscussion) node.getUserObject();
			client.recupererMessagesFil(f) ; 
			FilDeDiscussion filRecu = client.getFilRecu();
			if(filRecu != null) {
				vueChat.recupererMessages(filRecu);
				vueChat.afficherMessages(filRecu);
			}
		}
	}*/
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
		JTree jtree =(JTree )  e.getSource();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtree.getLastSelectedPathComponent();
		if(node == null ) {
			return;
		}
		TreeModelTickets treeModel = (TreeModelTickets) jtree.getModel();
		if(treeModel.isLeaf(node)) {
			FilDeDiscussion f = (FilDeDiscussion) node.getUserObject();
			curFil = f;
			vueChat.afficherMessages(f);
		}
	}

	public FilDeDiscussion getCurFil() {
		return curFil;
	}
	
	

	
	

}
