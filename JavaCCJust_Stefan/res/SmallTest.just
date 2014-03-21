program testcode
{
	int test;
	boolean[] arr;
	
	function coroutine void method(byref int param1)
	{
		int var = 1;
		
		if(var == 1) {
			var = 2;
		} else {
			var = 3;
		}
		
		if(var == 2) {
			arr = new boolean[10];
		}
	}
	
	function void method2(int param1)
	{
		int[] var = new int[10];
	}
}