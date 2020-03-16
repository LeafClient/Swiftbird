package fr.shyrogan.swiftbird.annotation;

import com.leafclient.swiftbird.PropertyContainer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates a {@link java.lang.reflect.Field} to allow {@link com.leafclient.swiftbird.Swiftbird} to wrap it into a
 * {@link PropertyContainer}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {

    /**
     * @return the label of the {@link PropertyContainer} marked by this annotation.
     */
    String value();

    /**
     * @return the description of the {@link PropertyContainer} marked by this annotation.
     */
    String description() default "";

}
