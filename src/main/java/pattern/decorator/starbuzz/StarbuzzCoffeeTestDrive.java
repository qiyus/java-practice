package pattern.decorator.starbuzz;

public class StarbuzzCoffeeTestDrive {
 
	public static void main(String args[]) {
		Beverage beverage = new Espresso();
		System.out.println(beverage.getDescription() 
				+ " $" + beverage.cost());
 
		Beverage darkRoast = new DarkRoast();
		darkRoast = new Mocha(darkRoast);
		darkRoast = new Mocha(darkRoast);
		darkRoast = new Whip(darkRoast);
		System.out.println(darkRoast.getDescription()
				+ " $" + darkRoast.cost());
 
		Beverage houseBlend = new HouseBlend();
		houseBlend = new Soy(houseBlend);
		houseBlend = new Mocha(houseBlend);
		houseBlend = new Whip(houseBlend);
		System.out.println(houseBlend.getDescription()
				+ " $" + houseBlend.cost());
	}
}
