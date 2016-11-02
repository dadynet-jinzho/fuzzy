public class TestJni
{
	public native void print(String str);
	static{
		System.loadLibrary("MyJni");
	}
	public static void main(String []args){
		new TestJni().print("hello jni");
	}
}
