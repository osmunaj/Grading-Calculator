import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.List;

public class Class {
	
	public String className;
	private List<Category> categories;
	private DecimalFormat df;
	private GradingScale gs;
	
	public Class(String n) {
		this.className = n;
		df = new DecimalFormat("#.##");
		this.categories = new ArrayList<>();
		gs = new GradingScale();
	}
	
	public boolean equals(Class c) {
		return (this.className.equals(c.className));
	}
	
	public List<Category> getCategories(){return categories;}
	
	public String[] getCategoryList() {
		if(categories.isEmpty()) return null;
		String[] categoryArr = new String[categories.size()];

		int i = 0;
		for(Category c: categories) {
			categoryArr[i] = c.categoryName;
			i++;
		}
		return categoryArr;
	}
	
	public String toString(boolean printAssignments) {
		String finalStr = "\n" + this.className + " - overall grade: " + df.format(calculateGrade()) + "% (" + gs.getLetterGrade(calculateGrade())+ ")\n----------------------------------";
		for(Category c: categories) {
			finalStr += ("\n\t" + c.toString(printAssignments));
		}
		
		finalStr += "\n----------------------------------\n";
		return finalStr;
	}
	
	public boolean addCategory(String name, double value) {
		if(!categories.isEmpty()) {
			for(Category c: categories) {
				if(c.categoryName.equals(name)) {
					return false;
				}
			}
		}
		
		Category c = new Category(name, value);
		categories.add(c);
		
		return true;
	}
	
	public boolean removeCateogry(String name) {
		if(categories.isEmpty()) {
			return false;
		}
		for(Category c: categories) {
			if(c.categoryName.equals(name)) {
				categories.remove(c);
				return true;
			}
		}
		return false;
	}
	
	public Category getCategory(String name) {
		for(Category c: categories) {
			if(c.categoryName.equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	private double calculateGrade() {
		double finalGrade = 0.0;
		double totalWeight = 0.0;
		
		if(categories.isEmpty()) {
			return finalGrade;
		}
		for(Category c: categories) {
			if(!c.getAssignments().isEmpty()) {
				finalGrade += (c.getCategoryGrade())*(c.getWeight()/100);
				totalWeight += (c.getWeight()/100);
			}
		}
		return finalGrade / totalWeight;
	}
	
//	private double calculateDesiredGrade(double desiredGrade) {
//		double neededGrade = 0;
//		int ungradedAssignments = 0;
//		
//		
//		for(Category category: categories) {
//			
//			// For each Category
//			
//			
//		}
//		
//		/*
//		 * DesiredGrade * (w1 + w2 + w3) = w1(a1 + a2 + x) + w2(a1 + a2 + a3) + w3(a1 + a2 + a3)
	
//		 * For each category 
//		 * If the category does not have an unknown assignment
//		 * 		Divide the left side by category total 
//		 *  
//		 *  (DesireGrade * (w1+w2 + w3) ) 
//		 * 
//		 */
//		
//
//		
//		
//		return neededGrade;
//	}
}
