package just.grammar.semantics;

public enum Type {
	undefType("UDEF", "undefined"), voidType("V", "void"), boolType("B", "boolean"), intType("I", "integer"), fieldType("A", "array");
	
	private final String shortName;       
	private final String longName;       

    private Type(String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }

    public String getShortName() {
    	return shortName;
    }
    
    public String getLongName() {
    	return longName;
    }
    
    public String toString(){
       return shortName + " - " + longName;
    }
}