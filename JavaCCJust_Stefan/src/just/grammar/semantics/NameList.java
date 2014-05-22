package just.grammar.semantics;

import java.util.ArrayList;
import java.util.HashMap;

public class NameList {
	public static NameList NameList = new NameList();
	
	// name -> spix
	private HashMap<String, Integer> nameIndices;
	// spix -> name
	private ArrayList<String> names;
	
	public NameList() {
		nameIndices = new HashMap<>();
		names = new ArrayList<>();
	}
	
	public String nameOf(Integer spix) {
		return names.get(spix);
	}
	
	public Integer spixOf(String name) {
		return nameIndices.get(name);
	}
	
	public Integer insert(String name) {
		names.add(name);

		Integer index = names.size() - 1;
		
		nameIndices.put(name, index);
		
		return index;	
	}
}
