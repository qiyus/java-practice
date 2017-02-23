package pattern.singleton;

//
// Danger!  This implementation of PressingSingleton not
// guaranteed to work prior to Java 5
//

public class DoubleCheckSingleton {
	private volatile static DoubleCheckSingleton uniqueInstance;
 
	private DoubleCheckSingleton() {}
 
	public static DoubleCheckSingleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (DoubleCheckSingleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new DoubleCheckSingleton();
				}
			}
		}
		return uniqueInstance;
	}
}
