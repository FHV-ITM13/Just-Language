import stdio; /* defines println*/

program EmJavaPrg {
	int globalVal;
	
	int doIt(byref int aParam, int aParam2) {
		boolean b = true;
		
		if (b) {
			int i = 0;

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
		int[] values = new int[10];
		
		start(processSomething, 1); //start coroutine with lowest priority
		
		synchronized(globalVal) {
			println(globalVal);
		}
		
		int i = 0;
		
		while (i < values.length) {
			println(values[i]);
		}
	}
}