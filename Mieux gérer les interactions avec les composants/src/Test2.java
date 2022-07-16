import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test2 {
	  static int count = 0;
	  static JButton bouton = new JButton("Pause");
	  public static void main(String[] args){
	    /*
	    JFrame fen = new JFrame("EDT");      
	    fen.getContentPane().add(bouton);
	    fen.setSize(200, 100);
	    fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    fen.setLocationRelativeTo(null);
	    fen.setVisible(true);
	     */
	    updateBouton();
	    System.out.println("Reprise du thread principal");
	  }
	  
	 public static void updateBouton(){
		 //Le second thread
		 new Thread(new Runnable(){
			 public void run(){
				 for(int i = 0; i < 5; i++){
					 try{
						 Thread.sleep(1000);
					 }catch(InterruptedException e){
						 e.printStackTrace();
					 }
					 //Modificaton de mon composant dans l'EDT
					 Thread t = new Thread(new Runnable(){
						 public void run(){
							 JFrame fen = new JFrame("EDT");      
							 fen.getContentPane().add(bouton);
							 fen.setSize(200, 100);
							 fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							 fen.setLocationRelativeTo(null);
							 fen.setVisible(true);
							 bouton.setText("Pause " + ++count);
						 }
					 });
					 if(SwingUtilities.isEventDispatchThread()){
						 t.start();
					 }
					 else{
						 //Lancement dans l'EDT
						 /*
						 try{
							 SwingUtilities.invokeAndWait(t);
						 }catch(InterruptedException e){
							 e.printStackTrace();
						 }catch(InvocationTargetException e){
							 e.printStackTrace();
						 }
						 
						 */
						 SwingUtilities.invokeLater(t);
					
					 }
				 }
			 }
		 }).start();
	 }
}
