package pattern.singleton;

/**
 * Created by Vigor on 2017/2/22.
 * 紧迫型单例。
 */
public class PressingSingleton {
	private static PressingSingleton uniqueInstance = new PressingSingleton();
 
	private PressingSingleton() {}
 
	public static PressingSingleton getInstance() {
		return uniqueInstance;
	}
	
	// other useful methods here
	public String getDescription() {
		return "I'm a statically initialized PressingSingleton!";
	}
}
