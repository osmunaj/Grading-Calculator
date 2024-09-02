
public class Assignment {
	
	public String assignmentName;
	private double grade;
	//private boolean unGraded;
	
	private GradingScale gs;
	
	public Assignment(String name, double grade) {
		this.assignmentName = name;
		this.grade = grade;
		
		gs = new GradingScale();
	}
	
	
	public double getGrade() {
		return this.grade;
	}
	
	public void editAssignment(double newGrade) {
		this.grade = newGrade;
	}
	
	public String toString() {
		
		if(this.grade == -1) {
			return "\n\t\t" + this.assignmentName + " | " + "----" + "% (Ungraded)";
		}
		return "\n\t\t" + this.assignmentName + " | " + this.grade + "% (" + gs.getLetterGrade(grade) + ")";
	}
}
