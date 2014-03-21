package just.grammar.context;

import java.util.ArrayList;
import java.util.HashMap;

public class Namelist {
	public static Namelist NameList = new Namelist();
	
	// name -> spix
	private HashMap<String, Integer> m_nameIndices;
	// spix -> name
	private ArrayList<String> m_names;
	
	public Namelist() {
		m_nameIndices = new HashMap<>();
		m_names = new ArrayList<>();
	}
	
	public String nameOf(Integer spix) {
		return m_names.get(spix);
	}
	
	public Integer spixOf(String name) {
		return m_nameIndices.get(name);
	}
	
	public Integer insert(String name) {
		m_names.add(name);

		Integer index = m_names.size() - 1;
		
		m_nameIndices.put(name, index);
		
		return index;	
	}
}
