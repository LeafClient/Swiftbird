package fr.shyrogan.swiftbird.annotation.number;

import com.leafclient.swiftbird.PropertyContainer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates a {@link fr.shyrogan.swiftbird.annotation.Property} annotated field to provide a step value to this property.
 * <b>This only works with primitive numbers.</b>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Increment {

    /**
     * @return the step value of marked property
     */
    double value();

}
