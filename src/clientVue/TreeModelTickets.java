package clientVue; 

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import metier.FilDeDiscussion;

public class TreeModelTickets extends DefaultTreeModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public TreeModelTickets(TreeNode root) {
		super(root);
	}


	@Override 
	public boolean isLeaf(Object node) {
		
		DefaultMutableTreeNode nodeLeaf = (DefaultMutableTreeNode) node; 
		return nodeLeaf.getUserObject() instanceof FilDeDiscussion;
		
	}



}
