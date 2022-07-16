package diet;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Represents an order in the take-away system
 */
public class Order implements Comparable<Order>{

	private OrderStatus oS;
	private PaymentMethod pM;
	private User userOrder;
	private String resturantOrder;
	private int[] time = new int[2];
	private String timeString = new String();
	Map<String, Integer> orderMenuList = new TreeMap<> ();

	
	public Order(User user, String restaurantName, int h, int m) {
		
		this.userOrder = user;
		this.resturantOrder = restaurantName;
		this.time[0] = h; this.time[1] = m;
		
		if(h>=0 && h<10)
			setTimeString(("0" + ((Integer)h).toString()));
		else
			setTimeString(((Integer)h).toString());
		
		setTimeString(getTimeString() + ":");
		
		if(m>=0 && m<10) 
			setTimeString(getTimeString() + ("0" + ((Integer)m).toString()));
		else
			setTimeString(getTimeString() + ((Integer)m).toString());
		
		this.oS = OrderStatus.ORDERED;
		this.pM = PaymentMethod.CASH;
	}
	
	public String getResturantOrder() {
		return resturantOrder;
	}

	public void setTime(int h, int m) {
		
		this.time[0] = h; this.time[1] = m;
	}

	public enum OrderStatus {
		ORDERED, READY, DELIVERED;
	}

	public enum PaymentMethod {
		PAID, CASH, CARD;
	}

	public double Price() {
		return -1.0;
	}
	
	public void setPaymentMethod(PaymentMethod method) {
		this.pM = method;
	}
	
	public PaymentMethod getPaymentMethod() {
		return this.pM;
	}
	
	public void setStatus(OrderStatus newStatus) {
		this.oS = newStatus;
	}
	

	public OrderStatus getStatus(){
		return this.oS;
	}
	

	public Order addMenus(String menu, int quantity) {
		
		/*
		for(String s: orderMenuList.keySet()) {
			if(s.equals(menu)) {
				
			}
		}*/
		
		orderMenuList.put(menu, quantity);
		return this;
	}
	
	public String getOrderWithStatus(String name, OrderStatus oS) {
		
		String s = new String();
		
		return s;
		
	}

	@Override
	public String toString() {
		
		String s = new String();
		int i=0;
		s = resturantOrder + ", " + userOrder.getFirstName() + " " + userOrder.getLastName() + " : (" + timeString + "):\n";
		
		for(String m: orderMenuList.keySet()) {

			s += "\t" + m + "->" + orderMenuList.get(m);
			if(i<orderMenuList.size()-1) {
				s += "\n";
			}
			i++;
		}

		return s;
	}

	@Override
	public int compareTo(Order o) {
		
		int cmp = (resturantOrder.compareTo(o.getResturantOrder()));
		
		if(cmp < 0)
			return -1;
		else if(cmp > 0)
			return 1;
		else {
			int cmp1 = userOrder.getFirstName().compareTo(o.getUserOrder().getFirstName());
			
			if(cmp1 < 0)
				return -1;
			else if(cmp1 > 0)
				return 1;
			else {
				int cmp2 = timeString.compareTo(o.getTimeString());
				
				if(cmp2 < 0)
					return -1;
				else if(cmp2 > 0)
					return 1;
			}
		}
		return 0;
	}

	public User getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(User userOrder) {
		this.userOrder = userOrder;
	}

	public int[] getTime() {
		return time;
	}

	public void setTime(int[] time) {
		this.time = time;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	
}
