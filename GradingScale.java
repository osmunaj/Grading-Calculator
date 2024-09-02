
public class GradingScale {

	int[] gradingScale = {60, 63, 67, 70, 73, 77, 80, 83, 87, 90, 93};
	String[] letterScale = {"D-", "D", "D+", "C-", "C", "C+", "B-", "B", "B+", "A-", "A+"};
	
	public String getLetterGrade(double numberGrade) {
		String finalGrade = "F";
		
		for(int i = 0; i < gradingScale.length; i++) {
			if(numberGrade >= gradingScale[i]) {
				finalGrade = letterScale[i];
			}
		}
		
		return finalGrade;
	}
}
