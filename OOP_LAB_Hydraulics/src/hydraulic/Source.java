package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * The status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends Element {

	private double flow;
	
	public Source(String name) {
		super(name);
		this.flow = 0;
	}

	public void setFlow(double flow){
		this.flow = flow;  // m^3/s erogati
		this.pIn = flow;
		this.pOut = flow;
	}
	
}
