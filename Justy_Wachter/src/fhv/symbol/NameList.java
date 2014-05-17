package fhv.symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NameList {

	private HashMap<String, Integer> ids = new HashMap<>();

	private List<String> names = new ArrayList<>();

	public Symbol insert(String name, Kind kind) {
		if (ids.containsKey(name)) {
			int id = ids.get(name);
			if (kind.equals(Kind.methodKind)) {
				return new MethodSymbol(id, name, kind);
			} else {
				return new Symbol(id, name, kind);
			}
		} else {
			names.add(name);

			Integer id = names.size() - 1;
			ids.put(name, id);

			if (kind.equals(Kind.methodKind)) {
				return new MethodSymbol(id, name, kind);
			} else {
				return new Symbol(id, name, kind);
			}
		}
	}

	public Integer lookup(String name) {
		if (ids.containsKey(name)) {
			return ids.get(name);
		} else {
			return this.insert(name, Kind.noneKind).getId();
		}
	}

	public String getNameOf(Integer id) {
		return names.get(id);
	}

	public Integer getIndexOf(String name) {
		return ids.get(name);
	}
}
