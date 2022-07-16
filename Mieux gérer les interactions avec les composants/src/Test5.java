import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Test5 {
  static int count = 0;
  static JButton bouton = new JButton("Pause");
  public static void main(String[] args){
      
    JFrame fen = new JFrame("EDT");      
    fen.getContentPane().add(bouton);
    fen.setSize(500, 100);
    fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fen.setLocationRelativeTo(null);
    fen.setVisible(true);
    updateBouton();

    System.out.println("Reprise du thread principal " + Thread.currentThread());
  }
  
  public static void updateBouton(){
	  //On cr�e un worker g�n�rique cette fois
	  SwingWorker sw = new SwingWorker<Integer, String>(){
		  protected Integer doInBackground() throws Exception {
			  int i;
			  for(i = 0; i < 5; i++){
				  try{
					  //On change la propri�t� d'�tat
					  setProgress(i);
					  //ON publie le r�sultat interm�diaire
					  publish("Tour de boucle N�" + (i+1));
					  Thread.sleep(1000);
				  }catch(InterruptedException e){
					  e.printStackTrace();
				  }
				 
			  }
			  return i;
		  }
		  
		  public void done(){
			  if(SwingUtilities.isEventDispatchThread())
				  System.out.println("Dans l'EDT !");
			  try{
				  //On utilise la m�thode get() pour r�cup�rer le r�sultat
				  //de la m�thode doInBackground()
				  bouton.setText("Traitement termin� au bout de " + get() + " fois !");
			  }catch(InterruptedException e){
				  e.printStackTrace();
			  }catch(ExecutionException e){
				  e.printStackTrace();
			  }
		  }
		  
		  //La m�thode g�rant les r�sultats interm�diaires
		  public void process(List<String> list){
			  System.out.println("dans process ("+ Thread.currentThread()+") : " + list.get(0));
			  for(String str : list)
				  System.out.println("dans process ("+ Thread.currentThread()+") : " + str);
			  
		  }
	  };
	  
	  //on �coute le changement de valeur pour la propri�t� 
	  sw.addPropertyChangeListener(new PropertyChangeListener(){
		  public void propertyChange(PropertyChangeEvent e){
			  if("progress".equals(e.getPropertyName())){
				  if(SwingUtilities.isEventDispatchThread())
					  System.out.println("Dans le listener donc dans l'EDT");
				  //On r�cup�re sa valeur
				  bouton.setText("Pause " + (Integer)e.getNewValue());
			  }
		  }
	  });
	  
	  //On lance le worker
	  sw.execute();
  }


}
