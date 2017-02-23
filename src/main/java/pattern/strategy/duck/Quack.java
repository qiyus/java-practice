package pattern.strategy.duck;

public class Quack implements QuackBehavior {
	public void quack() {
		System.out.println("Quack");
	}
}
