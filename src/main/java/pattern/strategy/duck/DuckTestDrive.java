package pattern.strategy.duck;

public class DuckTestDrive {

    public static void main(String[] args) {

        DecoyDuck decoy = new DecoyDuck();
        decoy.performQuack();

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
