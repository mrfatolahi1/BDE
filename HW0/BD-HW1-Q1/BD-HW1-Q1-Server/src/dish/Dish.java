package dish;

import chopstick.ChopStick;

public class Dish {
    private final ChopStick rightChopStick;
    private final ChopStick leftChopStick;
    private Dish rightDish;
    private Dish leftDish;
    private DishStatus status;

    public Dish(ChopStick rightChopStick, ChopStick leftChopStick) {
        this.rightChopStick = rightChopStick;
        this.leftChopStick = leftChopStick;
        this.status = DishStatus.HAS_NO_CHOPSTICK;
    }

    public ChopStick getRightChopStick() {
        return rightChopStick;
    }

    public ChopStick getLeftChopStick() {
        return leftChopStick;
    }

    public DishStatus getStatus() {
        return status;
    }

    public void setStatus(DishStatus status) {
        this.status = status;
    }

    public Dish getRightDish() {
        return rightDish;
    }

    public void setRightDish(Dish rightDish) {
        this.rightDish = rightDish;
    }

    public Dish getLeftDish() {
        return leftDish;
    }

    public void setLeftDish(Dish leftDish) {
        this.leftDish = leftDish;
    }
}
