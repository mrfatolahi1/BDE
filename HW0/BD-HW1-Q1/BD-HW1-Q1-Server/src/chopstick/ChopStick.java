package chopstick;

import dish.Dish;

public class ChopStick {
    private Dish rightDish;
    private Dish leftDish;
    private Dish currentDish;

    public ChopStick(Dish rightDish, Dish leftDish) {
        this.rightDish = rightDish;
        this.leftDish = leftDish;
    }

    public Dish getRightDish() {
        return rightDish;
    }

    public Dish getLeftDish() {
        return leftDish;
    }

    public Dish getCurrentDish() {
        return currentDish;
    }

    public void setCurrentDish(Dish currentDish) {
        this.currentDish = currentDish;
    }
}
