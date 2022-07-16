import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Test4 {
  static int count = 0;
  static JButton bouton = new JButton("Pause");
  public static void main(String[] args){

    JFrame fen = new JFrame("EDT");      
    fen.getContentPane().add(bouton);
    fen.setSize(500, 100);
    fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fen.setLocationRelativeTo(null);
    fen.setVisible(true);
    System.out.println("Avant updateBouton() " + Thread.currentThread());
    updateBouton();

    System.out.println("Reprise du thread principal");
  }
  
  public static void updateBouton(){
	  SwingWorker sw = new SwingWorker(){
		  protected Object doInBackground() throws Exception{
			  for(int i = 0; i < 5; i++){
				  try{
					 
					  System.out.println("Dans le swingworker ! "
								  +Thread.currentThread());
					  //on change la propri�t� d'�tat
					  setProgress(i);
					  Thread.sleep(1000);
				  }catch(InterruptedException e){
					  e.printStackTrace();
				  }
			  }
			  return null;
		  }
		  
		  public void done(){
			  if(SwingUtilities.isEventDispatchThread())
				  System.out.println("Dans l'EDT ! ");
			  bouton.setText("Traitement termin� ");
		  }
	  };
	  
	  //on �coute le changement de valeur pour la propri�t�
	  sw.addPropertyChangeListener(new PropertyChangeListener(){
		  public void propertyChange(PropertyChangeEvent e){
			  //On v�rifie tout de m�me le nom de la propri�t�
			  if("progress".equals(e.getPropertyName())){
				  if(SwingUtilities.isEventDispatchThread())
					  System.out.println("Dans le listener, donc dans l'EDT ! "
							  +Thread.currentThread());
				  //On r�cup�re sa nouvelle valeur 
				  bouton.setText("Pause " + (Integer)e.getNewValue());
			  }
		  }
	  });
	  
	  //On lance le SwingWorker
	  sw.execute();
  }
}
