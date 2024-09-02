import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Interface <E>{
	
	private Scanner scanner;
	private GradingCalculator gc;
	private SaveFile sf;
	
	private int currentMenu;
	
	// Main Menu
	private String[] menu0 = {"Save Data", "Add Class", "Remove Class", "Edit Class", "Quit"};
	private String[] menu1 = {"Back", "Add Category", "Remove Category", "Edit Category\n"};
	private String[] menu2 = {"Back", "Add Assignment", "Add Ungraded Assignment", "Remove Assignment", "Edit Assignment\n"};
	
	public Interface(GradingCalculator gc) {
		this.scanner = new Scanner(System.in);
		this.currentMenu = 0;
		this.gc = gc;
		this.sf = new SaveFile(gc);
		mainMenu();
	}
	
	private int getOption(String[] currentOptions) {
		String input = this.scanner.nextLine();
		int inputValue = -1;
		try {
			inputValue = Integer.parseInt(input);
		}catch(Exception e) {
			System.out.println("Invalid input.");
			return -1;
		}
		if(inputValue < 1 || inputValue > currentOptions.length) {
			System.out.println("Invalid Input");
			return -1;
		}
		return inputValue;
	}
	
	
	public void printOptions(String[] options) {
		System.out.println();
		for(int i = 0; i < options.length; i++) {
			System.out.println((i+1)+ ". " + options[i]);
		}
	}
	
	private boolean isDouble(String input) {
		try{
			Double.parseDouble(input);
			return true;
		}catch(Exception e) {
			System.out.println("Error: input value should be a number. (ex. 10, 25.6, 90)");
			return false;
		}
	}
	
	private void printClasses(boolean printAssignments) {
		List<Class> classes = gc.getClasses();
		System.out.println("\nClasses:");
		
		if(classes.isEmpty()) {
			System.out.println("You have no classes. Press 2 to add a class!");
			return;
		}
		for(Class c: classes) {
			System.out.println(c.toString(printAssignments));
		}
	}
	
	private String optionSelection(String[] options) {
		for(int i = 0; i < options.length; i++) {
			System.out.println(i+1 + ". " + options[i]);
		}
		
		int selection = getOption(options);
		
		if(selection == -1) {
			return null;
		}
		
		return(options[selection - 1]);
	}
	
	private void mainMenu() {
		int input = 0;
		while(input != 5) {
			if(currentMenu == 0) {
			    
			    System.out.println("\n~~~MAIN MENU~~~");
				printOptions(menu0);
				printClasses(false);
				input = getOption(menu0);
				
				if(input == 1) {
					// Save Data
					sf.saveData();
				}
				
				if(input == 2) {
					// Add Class
					System.out.println("Enter a class name");
					String name = scanner.nextLine();
					boolean added = gc.addClass(name);
					if(!added) {
						System.out.println("Error. Adding class was unsucsessful. Make sure there are no repeat named classes.");
					}
				}
				if(input == 3) {
					// Remove Class
					System.out.println("Select a class to remove.");
					String name = optionSelection(gc.getClassList());
					boolean removed = gc.removeClass(name);
					if(!removed) {
						System.out.println("Error. Removing class was unsucsessful. Make sure your class name is spelled right.");
						currentMenu = 0;
					}
				}
				if(input == 4) {
					// Edit Class Categories
					currentMenu = 1;
					System.out.println("Which class would you like to edit?");
					
					Class c = gc.getClass(optionSelection(gc.getClassList()));
					
					if(c == null) {
						System.out.println("Invalid Class\n");
						currentMenu = 0;
					}else {
						editClass(c);
						currentMenu = 0;
					}
				}
			}
		}
		
		
		
	}
	
	private void editClass(Class c) {
		int input = 0;
		
		while(input != 1) {
			System.out.println("\nEditing " + c.className);
			printOptions(menu1);
			System.out.println(c.toString(true));
			input = getOption(menu1);
			
			if(input == 2) {
				// Add Category
				System.out.println("Enter a category name");
				String name = scanner.nextLine();
				System.out.println("Enter a category weight (10.5% should be entered as 10.5)");
				String weight = scanner.nextLine();
				if(isDouble(weight)) {
					boolean added = c.addCategory(name, Double.parseDouble(weight));
					if(!added) {
						System.out.println("Error. Adding category was unsucsessful. Make sure there are no repeat named category.");
					}
				}
			}
			
			if(input == 3) {
				// Remove Category
				System.out.println("Enter a category name");
				String name = optionSelection(c.getCategoryList());
				boolean removed = c.removeCateogry(name);
				if(!removed) {
					System.out.println("Error. Removing category was unsucsessful. Make sure your category name is spelled right.");
				}
			}
			
			if(input == 4) {
				// Edit Category
				currentMenu = 2;
				System.out.println("Which category would you like to edit?");
				
				Category ca = c.getCategory(optionSelection(c.getCategoryList()));
				
				if(ca == null) {
					System.out.println("Invalid Category\n");
					currentMenu = 1;
				}else {
					editCategory(c, ca);
					currentMenu = 1;
				}
			}
			
			
		}
	}
	
	private void editCategory(Class cl, Category ca) {
		int input = 0;
		while(input != 1) {
			System.out.println("\nEditing " + cl.className + ": " + ca.toString(false) + "\n----------------------------------");
			System.out.println("\t" + ca.toString(true));
			printOptions(menu2);
			input = getOption(menu2);
			
			if(input == 2) {
				// Add Assignment
				System.out.println("Enter an Assignment Name");
				String name = scanner.nextLine();
				System.out.println("Enter an assignment Grade (80.5% should be entered as 80.5)");
				String grade = scanner.nextLine();
				if(isDouble(grade)) {
					boolean added = ca.addAssignment(name, Double.parseDouble(grade));
					if(!added) {
						System.out.println("Error. Adding assignment was unsucsessful. Make sure there are no repeat named category.");
					}
				}
			}
			
//			if(input == 3) {
//				// Add Ungraded Assignment
//				System.out.println("Enter an assignment name");
////				System.out.println("Enter an Assignment Name");
//				String name = scanner.nextLine();
//
//				boolean added = ca.addAssignment(name, -1);
//				
//				if(!added) {
//					System.out.println("Error. Adding assignment was unsucsessful. Make sure there are no repeat named category.");
//				}
//			}
			
			if(input == 3) {
				// Remove Assignment
				System.out.println("Enter an assignment name");
				String name = optionSelection(ca.getAssignmentList());
				boolean removed = ca.removeAssignment(name);
				if(!removed) {
					System.out.println("Error. Removing assignment was unsucsessful. Make sure your assignment name is spelled right.");
				}
			}
			if(input == 4) {
				// Edit Assignment
				System.out.println("Enter an assignment name");
				String name = optionSelection(ca.getAssignmentList());
				System.out.println("Enter a new grade");
				String newGrade = scanner.nextLine();
				if(isDouble(newGrade)) {
					boolean edited = ca.editAssignment(name,Double.parseDouble(newGrade));
					if(!edited) {
						System.out.println("Error. Editing assignment was unsucsessful. Make sure your assignment name is spelled right.");
					}
				}
			}
		}
	}
}
