package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends Element {

	
	
	public Tap(String name) {
		super(name);
	}
	
	public void setOpen(boolean open){
		
		if(open) {
			this.pOut = this.pIn;
		}else {
			this.pOut = 0.0;
		}
	}

}
