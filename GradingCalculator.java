import java.util.ArrayList;
import java.util.List;

public class GradingCalculator {

	private List<Class> classes;
	private Interface i;
	
	public GradingCalculator() {
		this.classes = new ArrayList<>();
		this.i = new Interface(this);
	}
	
	
	
	public boolean addClass(String name) {
		if(!classes.isEmpty()) {
			for(Class c: classes) {
				if(c.className.equals(name)) {
					return false;
				}
			}
		}
		Class c = new Class(name);
		classes.add(c);
		
		return true;
	}
	
	public boolean removeClass(String name) {
		if(classes.isEmpty()) {
			return false;
		}
		for(Class c: classes) {
			if(c.className.equals(name)) {
				classes.remove(c);
				return true;
			}
		}
		return false;
	}
	
	public List<Class> getClasses() {return this.classes;}
	
	public String[] getClassList() {
		if(classes.isEmpty()) return null;
		String[] classArr = new String[classes.size()];

		int i = 0;
		for(Class c: classes) {
			classArr[i] = c.className;
			i++;
		}
		return classArr;
	}
	
	public Class getClass(String name) {
		for(Class c: classes) {
			if(c.className.equals(name)) {
				return c;
			}
		}
		return null;
	}
}
