package fr.shyrogan.swiftbird.annotation.number;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates a {@link fr.shyrogan.swiftbird.annotation.Property} annotated field to provide a minimum
 * and maximum value to that property. This only works with primitive numbers.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Clamp {

    /**
     * @return the minimum value of marked property
     */
    double minimum();

    /**
     * @return the maximum value of marked property
     */
    double maximum();

}
