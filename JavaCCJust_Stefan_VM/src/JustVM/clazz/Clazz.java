package JustVM.clazz;

import java.util.ArrayList;
import java.util.List;

public class Clazz {
	private String name;
	private List<Method> methods;
	private List<ClazzVar> clazzVars;
	
	public Clazz(String name) {
		this.name = name;
		methods = new ArrayList<Method>();
		clazzVars = new ArrayList<ClazzVar>();
	}
	
	public void addMethod(Method method) {
		methods.add(method);
	}
	
	public void addClazzVar(ClazzVar clazzVar) {
		clazzVars.add(clazzVar);
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
