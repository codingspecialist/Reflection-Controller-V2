package site.metacoding.reflect.util;

public class UtilsLog {
	
	private static UtilsLog instance = new UtilsLog();
	
	private UtilsLog() {}
	
	public static UtilsLog getInstance() {
		return instance;
	}
	
	public void info(String TAG, String msg) {
		System.out.println("=================INFO "+TAG+msg);
	}
}
