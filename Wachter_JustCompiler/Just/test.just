import stdio;

program EmJavaPrg { 
	var int globalVal; 
 
	int doIt(byref int aParam, int aParam2) { 
		var boolean b = true; 
		if (b) { 
			var int i = 0; 
			while (i < 10) { 
			i = i + 1; 
			} 
		} 
 
		return 0; 
	} 
	
	int doIt(byref int aParam, int aParam2) { 
		var boolean b = true; 
		if (b) { 
			var int i = 0; 
			while (i < 10) { 
			i = i + 1; 
			} 
		} 
 
		return 0; 
	} 
 
	coroutine void processSomething() { 
		while (true) { 
 			synchronized(globalVal) { 
 				globalVal = globalVal + 1; 
 			} 
 		} 
 	} 
 
 	void main() { 
 		var int[] values = new int[10]; 
 		call start(processSomething, 1); 
 
 		synchronized(globalVal) { 
 			call println(globalVal); 
 		} 
 
		var int i = 0; 
 		while (i < values.length) { 
 			call println(values[i]); 
 		} 
 	} 
}