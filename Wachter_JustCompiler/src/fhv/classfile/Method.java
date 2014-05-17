package fhv.classfile;

import fhv.code.Code;

public class Method {
	private String name;
	private Code code;

	public Method(String name, Code code) {
		this.name = name;
		this.code = code;
	}
	
	public Code getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
}
