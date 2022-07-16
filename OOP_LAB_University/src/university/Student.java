package university;

public class Student {

	String first;
	String last;
	protected int id;
	
	public void setStudent(String first, String last, int id) {
		
		this.first = first;
		this.last = last;
		this.id = id;
	}
	
	public Student getStudent() {
		return this;
	}
	
	public String getStudent_str(int id) {
		return id + " " + first + " " + last;
	}
	
}
