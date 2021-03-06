package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	
	private Element[] vettElem;
	private int currN = 0;
	
	public HSystem() {
		
		this.vettElem = new Element[0];
		this.currN = 0;
	}
	
	public void addElement(Element elem){
		
		Element[] tmpVettElem = new Element[currN];
		int i=0;
		
		for(Element t: vettElem) {
			
			tmpVettElem[i] = t;
			i++;
		}
		
		i=0;
		currN++;
		
		this.vettElem = new Element[currN];
		
		for(Element t: tmpVettElem) {
			
			this.vettElem[i] = t;
			i++;
		}
		
		this.vettElem[vettElem.length-1] = elem;
	}
	
	public Element[] getElements(){	
			return this.vettElem;	
	}

	public void simulate(SimulationObserver observer){

		for(Element e: vettElem) {
			if(e instanceof Source) {
				System.out.println("\nNew HSystem:");
				observer.notifyFlow(e.getClass().getName(), e.name, e.pIn, e.pOut);
				//e.out.pIn = e.pOut;
				simulateR(observer, e.out);
				}
			}
		}
	
	public void simulateR (SimulationObserver observer, Element e) {
		
		if(e == null)
			return;
		else{	
			if (e instanceof Split) {	
				
				Split eS = (Split)e;
				
				observer.notifyFlow(eS.getClass().getName(), eS.name, eS.pIn, eS.pOut/2);
				simulateR (observer, eS.out);
				simulateR (observer,  eS.out2);
			}else {
				observer.notifyFlow(e.getClass().getName(), e.name, e.pIn, e.pOut);
				simulateR (observer,  e.out);
			}	
		}
	}
}
