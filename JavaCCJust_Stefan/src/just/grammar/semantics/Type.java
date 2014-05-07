package just.grammar.semantics;

public enum Type {
	undefType("undefined"), voidType("void"), boolType("boolean"), intType("integer"), fieldType("array");
	
	private final String name;       

    private Type(String s) {
        name = s;
    }

    public String toString(){
       return name;
    }
}