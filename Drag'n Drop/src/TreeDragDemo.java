import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DropMode;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class TreeDragDemo extends JFrame{
	JTree tree;
	public TreeDragDemo(){
	    setTitle("Drag'n Drop avec un JLabel !");
	    setSize(400, 200);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	    JPanel pan = new JPanel();
	    pan.setLayout(new GridLayout(1, 1));
	    pan.setBackground(Color.white);
	      
	    JLabel srcLib = new JLabel("Source de drag : ", JLabel.RIGHT);
	    JLabel src = new JLabel("Noeud 1");
	      
	    //------------------------------------------------------
	    //j'utilise mon objet MyTransferHandler2
	    src.setTransferHandler(new MyTransferHandler2());
	    src.addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e){
	    		JComponent comp = (JComponent)e.getSource();
	    		TransferHandler handler = comp.getTransferHandler();
	    		handler.exportAsDrag(comp, e, TransferHandler.MOVE);
	    	}
	    });
	    
	    JLabel destLib = new JLabel("Destination de drag : ", JLabel.RIGHT);
	    JTextField dest = new JTextField();
	    
	    dest.setDragEnabled(true);
	    tree = new JTree(getModel());
	    tree.setDragEnabled(true);
	    tree.setTransferHandler(new TreeTransferHandler(tree));
	    
	    pan.add(src);
	    
	    pan.add(new JScrollPane(tree));
	    JTextField tField = new JTextField();
	    tField.setDragEnabled(true);
	    
	    //Pour le choix des actions
	    JComboBox combo = new JComboBox();
	    combo.addItem("USE_SELECTION");
	    combo.addItem("ON");
	    combo.addItem("INSERT");
	    combo.addItem("ON_OR_INSERT");
	    
	    combo.addItemListener(new ItemListener(){
	    	public void itemStateChanged(ItemEvent event){
	    		String value = event.getItem().toString();
	    		
	    		if(value.equals("USE_SELECTION"))
	    			tree.setDropMode(DropMode.USE_SELECTION);
	    		if(value.equals("ON"))
	    			tree.setDropMode(DropMode.ON);
	    		if(value.equals("INSERT"))
	    			tree.setDropMode(DropMode.INSERT);
	    		if(value.equals("ON_OR_INSERT"))
	    			tree.setDropMode(DropMode.ON_OR_INSERT);
	    	}
	    });
	    
	    add(tField, BorderLayout.NORTH);
	    add(pan, BorderLayout.CENTER);
	    add(combo, BorderLayout.SOUTH);
	    setVisible(true);
	}
	
	private TreeModel getModel(){

	    DefaultMutableTreeNode root = new DefaultMutableTreeNode("SDZ");

	    DefaultMutableTreeNode forum = new DefaultMutableTreeNode("Forum");
	    forum.add(new DefaultMutableTreeNode("C++"));
	    forum.add(new DefaultMutableTreeNode("Java"));
	    forum.add(new DefaultMutableTreeNode("PHP"));
	      
	    DefaultMutableTreeNode tuto = new DefaultMutableTreeNode("Tutoriel");
	    tuto.add(new DefaultMutableTreeNode("Tutoriel"));
	    tuto.add(new DefaultMutableTreeNode("Programmation"));
	    tuto.add(new DefaultMutableTreeNode("Mapping"));
	      
	    root.add(tuto);
	    root.add(forum);

	    return new DefaultTreeModel(root);
	  }
	public static void main(String[] args) {
		new TreeDragDemo();

	}

}
