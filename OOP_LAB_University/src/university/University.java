package university;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	
	String uni_name;
	
	String rector_first;
	String rector_last;
	
	public static final int maxStud = 1000;
	public static final int maxIns = 50;
	
	protected static final int c_stud = 10000;
	private int i_stud = 0;
	protected Student[] vett_s = new Student[maxStud];
	
	protected static final int c_ins = 10;
	private int i_ins = 0;
	protected Courses[] vett_c = new Courses[maxIns];
	
	protected int[][] m_iscr = new int[maxStud][maxIns];

	public University() {
		for(int i=0; i<maxStud; i++) {
			for(int j=0; j<maxIns; j++) {
				m_iscr[i][j] = -1;
			}
		}
	}
	
	public int getNstud() {
		return this.i_stud;
	}
	
	public int getNCourse() {
		return this.i_ins;
	}
	
	public String getStudentInVett(int id) {	
		
		if(vett_s[id-c_stud]==null)
			return "null";
		return vett_s[id-c_stud].first.toString() + " " + vett_s[id-c_stud].last.toString();
	}
	
	public int checkMaxIns(int studentID) {
		
		int max_ins=0;
		
		for(int i=0; i<maxIns; i++) {
			if(m_iscr[studentID-c_stud][i]==99)
				max_ins++;
		}
		return max_ins;
	}
	
	public int checkMaxStud(int courseCode) {
		
		int max_course=0;
		
		for(int j=0; j<maxStud; j++) {
			if(m_iscr[j][courseCode-c_ins]==99)
				max_course++;
		}
		return max_course;
	}
	
		
		
	public University(String name){
		this.uni_name = name;
		
		for(int i=0; i<maxStud; i++) {
			for(int j=0; j<maxIns; j++) {
				m_iscr[i][j] = -1;
			}
		}
	}
	

	public String getName(){
		return this.uni_name;
	}
	
	public void setRector(String first, String last){
		this.rector_first = first;
		this.rector_last = last;
	}
	
	public String getRector(){
		return rector_first + " " + rector_last;
	}
	
	public int enroll(String first, String last){
		
		vett_s[i_stud] = new Student(); // prima invoco il costruttore
		vett_s[i_stud].setStudent(first, last, c_stud+i_stud);
		i_stud++;
		return c_stud + i_stud-1;
	}
	
	public String student(int id){
		return vett_s[id-c_stud].getStudent_str(id);
	}
	
	public int activate(String title, String teacher){
		vett_c[i_ins] = new Courses(); // prima invoco il costruttore
		vett_c[i_ins].setCourse(title, teacher, c_ins+i_ins);
		i_ins++;
		return c_ins + i_ins-1;
	}
	
	public String course(int code){
		return vett_c[code-c_ins].getCourse_str(code) ;
	}
	
	public void register(int studentID, int courseCode){
		
		if( (checkMaxIns(studentID)<=25) && (checkMaxStud(courseCode)<=100)) // controlli sui limiti di studenti e corsi
			m_iscr[studentID-c_stud][courseCode-c_ins] = 99;
	}
	
	public String listAttendees(int courseCode){
		
		int i;
		String t1_str = new String();
		
		for(i=0; i<vett_s.length; i++) {
			if(m_iscr[i][courseCode-c_ins]==99)
				t1_str = t1_str + vett_s[i].getStudent_str(c_stud+i) + "\n";
		}
		return t1_str;
	}

	public String studyPlan(int studentID){
		int j;
		String t2_str = new String();
		
		for(j=0; j<vett_c.length; j++)
			if(m_iscr[studentID-c_stud][j]==99) {
				t2_str = t2_str + vett_c[j].getCourse_str(c_ins+j) + "\n";
			}
				
		return t2_str;
	}
}
