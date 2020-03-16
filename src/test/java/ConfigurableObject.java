import fr.shyrogan.swiftbird.annotation.Property;
import fr.shyrogan.swiftbird.annotation.listener.Listener;
import fr.shyrogan.swiftbird.annotation.number.Clamp;

public class ConfigurableObject {

    @Property(value = "Cheese", description = "Do you like jazz?")
    private boolean cheese = false;

    @Property(value = "Name", description = "IDK")
    @Listener("onChange")
    private Result value = Result.PERFECT;

    @Property("Uh")
    @Clamp(minimum = 0F, maximum = 50F)
    private float uh = 6F;

    public Result onChange(Result current, Result future) {
        System.out.println("current: " + current);
        System.out.println("future: " + future);
        return future;
    }

    public enum Result {
        PERFECT,
        UH_OH;
    }

}
