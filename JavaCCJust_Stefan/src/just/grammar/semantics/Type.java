package just.grammar.semantics;

public enum Type {
	undefType("UDEF"), voidType("V"), boolType("B"), intType("I"), fieldType("A");
	
	private final String name;       

    private Type(String s) {
        name = s;
    }

    public String getName() {
    	return name;
    }
    
    public String toString(){
       return name;
    }
}