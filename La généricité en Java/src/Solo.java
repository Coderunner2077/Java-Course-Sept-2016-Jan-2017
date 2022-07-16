
public class Solo {
	private Object obj;
	Solo(){
		obj = new Object();
	}
	Solo(Object val){
		obj = val;
	}
	public Object getValeur(){
		return this.obj;
	}
	public void setValeur(Object val){
		this.obj = val;
	}
}
