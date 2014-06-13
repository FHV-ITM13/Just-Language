package JustVM.Code;

import java.util.List;

public class Method {
	private Type type;
	private String name;

	private int nrOfParams;
	private int nrOfLocals;
	
	private List<String> code;

	public Method(String name, Type type, int nrParams, int nrLocals, List<String> code) {
		this.name = name;
		this.type = type;
		this.nrOfLocals = nrLocals;
		this.nrOfParams = nrParams;
		this.code = code;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNrOfParams() {
		return nrOfParams;
	}

	public void setNrOfParams(int nrOfParams) {
		this.nrOfParams = nrOfParams;
	}

	public int getNrOfLocals() {
		return nrOfLocals;
	}

	public void setNrOfLocals(int nrOfLocals) {
		this.nrOfLocals = nrOfLocals;
	}

	public List<String> getCode() {
		return code;
	}

	public void setCode(List<String> code) {
		this.code = code;
	}
}
