import javax.swing.JButton;
import javax.swing.JFrame;

public class Test1 {
	static int count = 0, count2 = 0;
	static JButton bouton = new JButton("Pause");
	
	public static void main(String[] args) {
		JFrame fen = new JFrame("EDT");
		fen.getContentPane().add(bouton);
		fen.setSize(200, 100);
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setLocationRelativeTo(null);
		fen.setVisible(true);
		updateBouton();
		System.out.println("Reprise du thread principal");
	}
	public static void updateBouton(){
		for(int i = 0; i < 5; i++){
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			bouton.setText("Pause "+ ++count);
		}
	}
}
