program ATest {
	var int global;

	int doIt(byval int i) {
		return i;
	}
	void main() {
		var int a;
   		a = doIt(10);
   		global = a;
   	}
}