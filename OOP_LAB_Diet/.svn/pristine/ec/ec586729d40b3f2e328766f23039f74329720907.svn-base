package diet;
import java.util.Collection;
import java.util.TreeSet;

import diet.Order.OrderStatus;

/**
 * Represents a restaurant in the take-away system
 *
 */
public class Restaurant implements Comparable<Restaurant>{
	
	private String name;
	private Food food;
	private String[] openingTime;
	private String[] closingTime;
	private Collection<Menu> resturantMenu = new TreeSet<Menu> ();
	private Collection<Order> resturantOrders = new TreeSet<Order> ();
	
	public Restaurant(String name, Food food) {

		this.name = name;
		this.food = food;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setHours(String ... hm) {
		
		int i=0;
		
		this.openingTime = new String[hm.length/2];
		this.closingTime = new String[hm.length/2];
		
		while(i<hm.length) {
			openingTime[i/2] = hm[i];
			closingTime[i/2] = hm[i+1];
			i+=2;
		}
	}
	
	public boolean isOpen(String time) {
		
		int i;
		
		for(i=0; i<openingTime.length; i++) {
			if( openingTime[i].compareTo(time)<=0 && closingTime[i].compareTo(time)>0){
				return true;
			}
		}
		return false;
	}
	
	public String getFirstOpening(String time) {
		
		for(String t: openingTime) {
			if(t.compareTo(time)>0)
				return t;
		}
		return openingTime[0];
	}
	
	public Menu getMenu(String name) {
		
		Menu tmpM;
		
		for(Menu m: resturantMenu) {
			if(m.getName().compareTo(name)==0) {
				return m;
			}
		}
		return null;
	}
	
	public Menu createMenu(String name) {
		
		Menu m = new Menu(name, food.recipes, food.products);
		resturantMenu.add(m);
		
		return m;
	}

	/**
	 * Find all orders for this restaurant with 
	 * the given status.
	 * 
	 * The output is a string formatted as:
	 * <pre>
	 * Napoli, Judi Dench : (19:00):
	 * 	M6->1
	 * Napoli, Ralph Fiennes : (19:00):
	 * 	M1->2
	 * 	M6->1
	 * </pre>
	 * 
	 * The orders are sorted by name of restaurant, name of the user, and delivery time.
	 * 
	 * @param status the status of the searched orders
	 * 
	 * @return the description of orders satisfying the criterion
	 */
	public String ordersWithStatus(OrderStatus status) {
		
		String s = new String();
		
		for(Order o: resturantOrders) {
			if(o.getStatus()==status) {
				s += o.toString();
				s += "\n";
			}
		}
		
		return s;
	}

	@Override
	public int compareTo(Restaurant o) {
		
		int cmp = (o.getName().compareTo(name));
		
		if(cmp < 0)
			return 1;
		else if(cmp > 0)
			return -1;
		return 0;
	}

	public Collection<Order> getResturantOrders() {
		return resturantOrders;
	}

	public void setResturantOrders(Collection<Order> resturantOrders) {
		this.resturantOrders = resturantOrders;
	}
}
