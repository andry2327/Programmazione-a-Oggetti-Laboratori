package hydraulic;

/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 */
public abstract class Element {
	
	String name;
	Element out;
	protected double pIn;
	protected double pOut;
	
	public Element(String name){
		
		this.pIn = 0;
		this.pOut = this.pIn;
		this.name = name;
		this.out = null;
	}

	public String getName(){
		return this.name;
	}
	
	public void connect(Element elem){
		
		if(!(this instanceof Sink)) {
			
			this.out = elem;
			if(this instanceof Split) {
				elem.pIn = this.pOut;
			}else {
				elem.pIn = this.pOut;
				elem.pOut = elem.pIn;
			}
		}
	}
	
	public Element getOutput(){	
		return this.out;
	}
	
}
