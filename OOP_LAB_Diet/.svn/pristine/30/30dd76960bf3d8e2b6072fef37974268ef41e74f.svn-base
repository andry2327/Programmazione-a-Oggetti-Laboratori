package diet;

import java.util.Collection;
import java.util.TreeSet;

import diet.Order.OrderStatus;

/**
 * Represents the main class in the
 * take-away system.
 * 
 * It allows adding restaurant, users, and creating orders.
 *
 */
public class Takeaway {
	
	Collection<Restaurant> restaurantList = new TreeSet<Restaurant> ();
	Collection<String> restaurantNameList = new TreeSet<String> ();
	Collection<Restaurant> openedRestaurantList = new TreeSet<Restaurant> ();;
	Collection<User> userList = new TreeSet<User> ();
	Collection<String> userNameList = new TreeSet<String> ();
	Collection<Order> orderList = new TreeSet<Order> ();
	
	public void addRestaurant(Restaurant r) {
		
		restaurantList.add(r);
		restaurantNameList.add(r.getName());
	}
	
	public Collection<String> restaurants() {
		return restaurantNameList;
	}
	
	public User registerUser(String firstName, String lastName, String email, String phoneNumber) {
		
		StringBuffer s1 = new StringBuffer(); s1.append(firstName);
		StringBuffer s2 = new StringBuffer(); s2.append(lastName);
		StringBuffer s3 = new StringBuffer(); s3.append(email);
		StringBuffer s4 = new StringBuffer(); s4.append(phoneNumber);
		
		User u = new User(s1, s2, s3, s4);
		userList.add(u);
		userNameList.add(u.getLastName() + " " + u.getFirstName());
		
		return u;
	}
	
	public Collection<User> users(){
		return userList;
	}

	public Order createOrder(User user, String restaurantName, int h, int m) {
		
		Order or = new Order(user, restaurantName, h, m);
		if(isUserIn(user) && isResturantIn(restaurantName)) {
			if(!isOpenedRestaurantListIn(restaurantName, h, m)) {
				String sTime = new String();
				if(h>=0 && h<10)
					sTime = ("0" + ((Integer)h).toString());
				else
					sTime = ((Integer)h).toString();
				
				sTime += ":";
				
				if(m>=0 && m<10) 
					sTime += ("0" + ((Integer)m).toString());
				else
					sTime += ((Integer)m).toString();
				
				Restaurant r = this.getResturantInList(restaurantName);
				String newOpening = r.getFirstOpening(sTime);
				String tempH = newOpening.substring(0, 2);
				String tempM = newOpening.substring(3, 5);
				or.setTime(Integer.parseInt(tempH), Integer.parseInt(tempM));
				or.setTimeString(newOpening);
			}
			orderList.add(or);
			Restaurant r = this.getResturantInList(restaurantName);
			r.getResturantOrders().add(or);
			return or;
		}
		return null;
	}
	
	public boolean isUserIn(User user) {
		
		for(User u: userList)
			if(u.equals(user))
				return true;
		return false;
	}
	
	public boolean isResturantIn(String restaurant) {
		
		for(String s: restaurantNameList)
			if(s.equals(restaurant))
				return true;
		return false;
	}
	
	public Restaurant getResturantInList(String restaurant) {
		
		for(Restaurant r: restaurantList)
			if(r.getName().equals(restaurant))
				return r;
		return null;
	}
	
	public boolean isOpenedRestaurantListIn(String restaurant, int h, int m) {
		
		String time = new String();
		time += ((Integer)h).toString() + ":" + ((Integer)m).toString() ;
		Collection<Restaurant> oR = this.openedRestaurants(time);
		
		for(Restaurant s: oR)
			if(s.getName().equals(restaurant))
				return true;
		return false;
	}
	
	/**
	 * Retrieves the collection of restaurant that are open
	 * at the given time.
	 * 
	 * @param time time to check open
	 * 
	 * @return collection of restaurants
	 */
	public Collection<Restaurant> openedRestaurants(String time){
		
		Collection<Restaurant> openedRestaurantList = new TreeSet<Restaurant> ();
		
		for(Restaurant r: restaurantList) {
			if(r.isOpen(time)) {
				openedRestaurantList.add(r);
			}
		}
		return openedRestaurantList;
	}
	
	public String getOrderWithStatus(String name, OrderStatus oS) {
		
		String s = new String();
		
		for(Order o: orderList) {
			if(o.getStatus()==oS && o.getResturantOrder()==name) {
				s.concat(o.toString());
			}
		}
		
		return s;
		
	}

	
	
}
