import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFile {
	private GradingCalculator gc;
	
	private String fileName = "GradingCalculatorData.csv";
	
	
	public SaveFile(GradingCalculator gc) {
		this.gc = gc;
		read();
	}
	
	public void saveData() {
		String finalString = "Class Name,Cateogy Name,Category Weight,Assignment Name,Assignment Grade\n";
		boolean classNum = false;
		boolean categoryNum = false;
		
		for(Class cl: gc.getClasses()) {
			finalString += cl.className + ",";
			classNum = false;
			for(Category ca: cl.getCategories()){
				if(classNum) {
					finalString += ",";
				}
				finalString += ca.categoryName + "," + ca.getWeight() + ",";
				categoryNum = false;

				for(Assignment a: ca.getAssignments()) {		
					if(classNum && categoryNum) {
						finalString += ",,,";
					}
					finalString += a.assignmentName + "," + a.getGrade() + "\n";
					classNum = true;
					categoryNum = true;	
				}
			}
		}
		
		System.out.println("\nData has been Sucsessfully Saved!\n");
		
		write(finalString);
	}
	
	private void write(String s) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(s);
        } 
		catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

	}
	
	private void read() {
		 try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			 String line = reader.readLine();
			 Class currentClass = null;
			 Category currentCategory = null;
			 while ((line = reader.readLine()) != null) {
				 //System.out.println("Read from file: " + line);
				 String[] splitLine = line.split(",");
				 
				 // splitLine[0] = Class Name
				 if(!splitLine[0].equals("")) {
					 gc.addClass(splitLine[0]);
					 currentClass = gc.getClass(splitLine[0]);
				 }
				 // splitLine[1] = Category Name
				 
				 // splitLine[2] = Category Weight
				 if(!splitLine[1].equals("")) {
					 currentClass.addCategory(splitLine[1], Double.parseDouble(splitLine[2]));
					 currentCategory = currentClass.getCategory(splitLine[1]);
				 }
				 // splitLine[3] = Assignment Name
				 // splitLine[4] = Assignment Grade
				 if(!splitLine[3].equals("")) {
					 currentCategory.addAssignment(splitLine[3], Double.parseDouble(splitLine[4]));
				 }
				 
				 
			 }
		 } catch (IOException e) {
			 System.err.println("Error reading from file: " + e.getMessage());
		 }
	}
}
