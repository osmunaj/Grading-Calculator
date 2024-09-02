import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Category {
	public String categoryName;
	private double weight;
	private List<Assignment> assignments;

	private DecimalFormat df;
	private GradingScale gs;
	
	public Category(String name, double weight) {
		this.categoryName = name;
		this.weight = weight;
		assignments = new ArrayList<Assignment>();
		df = new DecimalFormat("#.##");
		gs = new GradingScale();
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public boolean addAssignment(String name, double grade) {
		if(!assignments.isEmpty()) {
			for(Assignment a: assignments) {
				if(a.assignmentName.equals(name)) {
					return false;
				}
			}
		}
		
		Assignment a = new Assignment(name, grade);
		assignments.add(a);
		return true;
	}
	
	public boolean editAssignment(String name, double grade) {
		if(assignments.isEmpty()) {
			return false;
		}
		for(Assignment a: assignments) {
			if(a.assignmentName.equals(name)) {
				a.editAssignment(grade);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeAssignment(String name) {
		if(assignments.isEmpty()) {
			return false;
		}
		for(Assignment a: assignments) {
			if(a.assignmentName.equals(name)) {
				assignments.remove(a);
				return true;
			}
		}
		return false;
	}
	
	public void editCategory(String newName, double newValue) {
		this.categoryName = newName;
		this.weight = newValue;
	}
	
	
	public String toString(boolean printAssignments) {
		String finalStr = categoryName + " (" + this.weight/100 + "): " + df.format(this.getCategoryGrade()) + "% (" + gs.getLetterGrade(getCategoryGrade()) + ")";
		if(printAssignments) {
			if(assignments.isEmpty()) {
				return finalStr;
			}
			for(Assignment a: assignments) {
				finalStr += a.toString();
			}
		}
		return finalStr;
	}
	
	public List<Assignment> getAssignments(){return assignments;}
	
	public double getCategoryGrade() {
		double total = 0.0;
		int ungradedAssignments = 0;
		
		if(assignments.isEmpty()) {
			return total;
		}
		for(Assignment a: assignments) {
			if(a.getGrade() != -1) {
				// Is graded
				total += a.getGrade();
			}else {
				// Is Ungraded
				ungradedAssignments++;
			}
		}
		
		return total/(assignments.size() - ungradedAssignments);	
	}
	
	public String[] getAssignmentList() {
		if(assignments.isEmpty()) return null;
		String[] assignmentArr = new String[assignments.size()];
		int i = 0;
		for(Assignment a: assignments) {
			assignmentArr[i] = a.assignmentName;
			i++;
		}
		return assignmentArr;
	}
	
}
