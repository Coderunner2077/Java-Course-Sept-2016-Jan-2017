import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Test3 {
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

    System.out.println("Reprise du thread principal");
  }
  public static void updateBouton(){
	  //on crée le SwingWorker
	  SwingWorker sw = new SwingWorker(){
		  protected Object doInBackground() throws Exception{
			  for(int i = 0; i < 5; i++){
		          try {
		            Thread.sleep(1000);
		          } catch (InterruptedException e) {
		            e.printStackTrace();
		          }   
			  }
			  return null;
		  }
		  
		  public void done(){
			  if(SwingUtilities.isEventDispatchThread())
				  System.out.println("Dans l'EDT ! " + Thread.currentThread());
			  bouton.setText("Traitement terminé ");
		  }
	  };
	  //On lance le SwingWorker
	  sw.execute();
  }
}
