package university;

public class StudentEXT extends Student{

	float score;
	int index;
	public final int maxstud = 1000;
	
	public StudentEXT() {
		this.score = -1;
		this.index = -1;
	}
	
	public void setScoreIndex(float score, int index) {
		
		this.score = score;
		this.index = index;
	}
	
	public String getScoreIndex() {
		return score + "\n";
	}
	
	public static class VettStudentEXT extends StudentEXT{
		
		StudentEXT[] S;
		
		public VettStudentEXT() {	
			this.S = new StudentEXT[maxstud];
		}
		
		public void setScoreIndex(float score, int i) {
			
			this.S[i]  = new StudentEXT();
			this.S[i].setScoreIndex(score, i);
		}
		
		public StudentEXT[] sortStudentEXT(){
			
			for(int i=0;(i<S.length) && (S[i].score >= -1); i++) {
				for(int j=0; (j<S.length-i-1) && ((S[j+1].score >= -1)); j++) {
					if(S[j].score < S[j+1].score) {
						StudentEXT tmp = S[j];
						S[j] = S[j+1];
						S[j+1] = tmp;
					}
				}
			}
			
			return S;
		}
	}
	
	
}
