package university;

import java.util.logging.Logger;

import java.util.*;

/**
 * This class is an extended version of the {@Link University} class.
 * 
 *
 */
public class UniversityExt extends University {
	
	protected static final int c_stud = 10000;
	private StudentEXT.VettStudentEXT vettStudScore = new StudentEXT.VettStudentEXT();
	
	private final static Logger logger = Logger.getLogger("University");

	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
	}
	@Override
	public int enroll(String first, String last){
		
		int id = super.enroll(first, last);;
		logger.info("New student enrolled: " + id + ", " + first +  " " + last);
		return id;
	}
	@Override
	public int activate(String title, String teacher){
		
		int c = super.activate(title, teacher);;
		logger.info("New course activated: " + c + ", " + title +  " " + teacher);
		return c;
	}
	@Override
	public void register(int studentID, int courseCode){
		
		super.register(studentID, courseCode);
		logger.info("Student " + studentID + " signed up for course " + courseCode);
	}

	public void exam(int studentId, int courseID, int grade) {
		
		if(m_iscr[studentId-c_stud][courseID-c_ins] == 99)
			m_iscr[studentId-c_stud][courseID-c_ins] = grade;
		logger.info("Student " + studentId + " took an exam in course " + courseID + " with grade " + grade);
	}

	public String studentAvg(int studentId) {
		
		float sum=0;
		float nEsamiSup=0;
		
		for(int j=0; (m_iscr[studentId-c_stud][j] != -1) && (j<m_iscr[studentId-c_stud].length); j++) {
			if(m_iscr[studentId-c_stud][j] < 90) { // cioè 0<voto<30
				sum += m_iscr[studentId-c_stud][j];
				nEsamiSup++;
			}
		}
		
		if(nEsamiSup > 0)
			return "Student " + studentId + ": " + (float)(sum/nEsamiSup);
		return "Student STUDENT_ID hasn't taken any exams";
	}
	
	public String courseAvg(int courseId) {
		float sum=0;
		float nStudSup=0;
		
		for(int i=0; (m_iscr[i][courseId-c_ins] != -1) &&  (i<m_iscr[courseId-c_ins].length); i++) {
			if(m_iscr[i][courseId-c_ins] < 90) { // cioè 0<voto<30
				sum += m_iscr[i][courseId-c_ins];
				nStudSup++;
			}
		}
		
		if(nStudSup > 0)
			return "The average for the course " +  courseId + " is: " + (float)(sum/nStudSup);
		return "No student has taken the exam in COURSE_TITLE";
	}
	
	public String topThreeStudents() {
		
		float sum=0;
		float nEsamiSup=0;
		float nEsamiIscr=0;
		float m=0, bonus=0;
		float best;
		String s = new String();
		
		for(int i=0; i<maxStud; i++) {
			for(int j=0; j<m_iscr[i].length; j++) {  // calcolo media
				if(m_iscr[i][j]>=0) {
					nEsamiIscr++;
					if(m_iscr[i][j]<90) {
						sum += m_iscr[i][j];
						nEsamiSup++;
					}
				}
			}
			
			m = sum/nEsamiSup;
			bonus = (nEsamiSup/nEsamiIscr)*10;
			vettStudScore.setScoreIndex(m + bonus, i);
			
			nEsamiIscr=0; sum=0;  nEsamiSup=0;  m=0; bonus=0; // inizializzo
		}
		
		vettStudScore.sortStudentEXT();
		
		for(int i=0; i<3; i++) {
			s = s + this.getStudentInVett(i + c_stud) + ": " + vettStudScore.S[i].getScoreIndex() ;
		}
		
		return s;
	}
}
