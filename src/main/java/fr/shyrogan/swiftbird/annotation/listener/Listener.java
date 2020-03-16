package fr.shyrogan.swiftbird.annotation.listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used by {@link com.leafclient.swiftbird.Swiftbird} to specify a property listener.
 *
 * <p>
 *     A property listener respect this form (T represent our property type):
 *     <b>T listenerName(T previousValue, T futureValue)</b>
 *     The returned value allows the listener to change the future value.
 * </p>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Listener {

    /**
     * @return listener method's name
     * @see Listener
     */
    String value();

}
