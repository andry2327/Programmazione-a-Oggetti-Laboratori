package university;

public class Courses {

	String title;
	String teacher;
	private int code;
	
	public void setCourse(String title, String teacher, int code) {
		
		this.title = title;
		this.teacher = teacher;
		this.code = code;
	}
	
	public String getCourse_str(int code) {
		return code + ", " + title + ", " + teacher;
	}
	
}
