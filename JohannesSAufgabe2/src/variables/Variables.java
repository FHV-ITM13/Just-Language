package variables;

import java.util.ArrayList;
import java.util.HashMap;

import aufgabe2.ParseException;

public class Variables {
	private static ArrayList<String> varNames = new ArrayList<>();
	private static HashMap<String, Integer> variables = new HashMap<>();
	private static HashMap<String, Boolean> usage = new HashMap<>();

	public static void setVar(String var, String value) throws ParseException {
		try {
			if(varNames.contains(var)){
				throw new ParseException("Variable " + var + " schon verwendet");
			}
			varNames.add(var);

		} catch (Exception e) {
			throw new ParseException("Variable " + var + " schon verwendet");
		}
		variables.put(var, Integer.parseInt(value));
		usage.put(var, false);
	}

	public static Integer getVar(String var) throws ParseException {
		try {
			if(!varNames.contains(var)){
				throw new ParseException("Variable " + var + " nicht definiert");
			}
			usage.put(var, true);
			return variables.get(var);
		} catch (Exception e) {
			throw new ParseException("Variable " + var + " nicht definiert");
		}
	}

	public static void getUnused() {
		for (String var : usage.keySet()) {
			if (!usage.get(var))
				System.out
						.println("WARNING: Nicht verwendete Variable: " + var);
		}

	}
	
	public static void clear()
	{
		usage.clear();
		varNames.clear();
		variables.clear();		
	}
}
