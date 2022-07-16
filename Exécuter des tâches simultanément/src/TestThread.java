
public class TestThread extends Thread {
	/*
	public TestThread(String name){
		super(name);
	}
	public void run(){
		for(int i = 0; i < 10; i++)
			System.out.println(this.getName());
	}
	
	 */
	Thread t;
	public TestThread(String name){
		super(name);
		System.out.println("statut du thread " + name + " = " + this.getState());
		this.start();
		System.out.println("statut du thread " + name + " après invocation de start() = "
				+ this.getState());
	}
	public TestThread(String name, Thread t){
		super(name);
		this.t = t;
		System.out.println("statut du thread " + name + " = " + this.getState());
		this.start();
		System.out.println("statut du thread " + name + " après invocation de start() = "
				+ this.getState());
	}
	public void run(){
		for(int i = 0; i < 10; i++){
			System.out.println("statut " + this.getName() + " = " + this.getState());
			if(t != null)
				System.out.println("statut de " + t.getName() + " pendant le thread "+
						this.getName() + " (" + this.getState() + ")  = " + t.getState());
		}
	}
	
	public void setThread(Thread t){
		this.t = t;
	}
	
}
