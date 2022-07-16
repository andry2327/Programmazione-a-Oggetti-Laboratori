package diet;

/**
 * Represent a take-away system user
 *  
 */
public class User implements Comparable<User>{
		
	private StringBuffer FirstName;
	private StringBuffer LastName;
	private StringBuffer Email;
	private StringBuffer Phone;
	
	public User(StringBuffer FirstName, StringBuffer LastName, StringBuffer Email, StringBuffer Phone) {
		
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Email = Email;
		this.Phone = Phone;
	}

	public StringBuffer getLastName() {
		return this.LastName;
	}
	
	public StringBuffer getFirstName() {
		return this.FirstName;
	}
	public StringBuffer getEmail() {
		return this.Email;
	}
	
	public StringBuffer getPhone() {
		return this.Phone;
	}
	
	public void SetEmail(StringBuffer email) {
		this.Email = email;
	}
	
	public void setPhone(StringBuffer phone) {
		this.Phone = phone;
	}

	@Override
	public int compareTo(User o) {
			
		int cmp = (o.getLastName().compareTo(LastName));
		
		if(cmp < 0)
			return 1;
		else if(cmp > 0)
			return -1;
		else {
			int cmp2 = (o.getFirstName().compareTo(FirstName));
			
			if(cmp2 < 0)
				return 1;
			else if(cmp2 > 0)
				return -1;
		}
		return 0;
	}
	
}
