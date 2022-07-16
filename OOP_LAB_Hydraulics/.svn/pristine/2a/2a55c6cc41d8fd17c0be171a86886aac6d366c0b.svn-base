package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element {

	Element out2;
	Element[] v2Out = new Element[2];
	
	public Split(String name) {
		super(name);
		this.out2 = null;
	}

    public Element[] getOutputs(){
        return v2Out;
    }

	public void connect(Element elem, int noutput){
		
    	if(noutput == 0) {
    		this.out = elem;
    		v2Out[0] = elem;
    		this.pOut = this.pIn/2;
    	}
    	else {
    		this.out2 = elem;
    		v2Out[1] = elem;
    		this.pOut = this.pIn/2;
    	}
    	
	}
}
