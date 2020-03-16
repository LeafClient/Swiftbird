import com.leafclient.swiftbird.Swiftbird;
import org.junit.Test;

public class Settings {

    private static Swiftbird swiftbird = Swiftbird.create();
    private static ConfigurableObject configurableObject = new ConfigurableObject();

    @Test
    public void simpleLookUp() {
        swiftbird.in(configurableObject)
                .forEach(setting -> System.out.println(setting.getLabel() + " " + setting.getValue()));
    }

}
