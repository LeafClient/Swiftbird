package com.leafclient.swiftbird;

import com.leafclient.swiftbird.factory.PropertyFactory;
import fr.shyrogan.swiftbird.AnnotatedFieldFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Swiftbird is a lightweight and intuitive Setting library.
 */
public final class Swiftbird {

    public static PropertyFactory[] DEFAULT_FACTORIES = new PropertyFactory[]{ new AnnotatedFieldFactory()};

    private final Map<Object, List<PropertyContainer<?>>> cache = new WeakHashMap<>();
    private final PropertyFactory[] factories;

    /**
     * Creates a new {@link Swiftbird} instance using the specified factories.
     *
     * @param factories Factories
     */
    Swiftbird(PropertyFactory[] factories) {
        this.factories = factories;
    }

    /**
     * @param object Object's instance
     * @return Each property in its wrapped form inside of specified object.
     */
    public List<PropertyContainer<?>> in(Object object) {
        final List<PropertyContainer<?>> cached = cache.get(object);
        if(cached != null)
            return cached;

        final List<PropertyContainer<?>> result = new ArrayList<>();
        Class<?> objectClass = object.getClass();
        in(objectClass, object, result);
        while (objectClass.getSuperclass() != Object.class) {
            objectClass = objectClass.getSuperclass();
            in(objectClass, object, result);
        }

        cache.put(object, result);
        return Collections.unmodifiableList(result);
    }

    /**
     * Utility method for {@link this#in(Object)}
     */
    private void in(Class<?> objectClass, Object object, List<PropertyContainer<?>> result) {
        for(Field field: objectClass.getDeclaredFields()) {
            for(PropertyFactory factory: factories) {
                if(factory.canApplyTo(field)) {
                    final PropertyContainer<?> propertyContainer = factory.createSetting(field.getType(), object, field);
                    if(propertyContainer != null)
                        result.add(propertyContainer);
                }
            }
        }
    }

    /**
     * Creates a {@link Swiftbird} instance using the {@link Swiftbird#DEFAULT_FACTORIES}.
     *
     * @return Swiftbird instance
     */
    public static Swiftbird create() {
        return new Swiftbird(DEFAULT_FACTORIES);
    }

    /**
     * Creates a {@link Swiftbird} instance using specified factories.
     *
     * @return Swiftbird instance
     */
    public static Swiftbird create(PropertyFactory... factories) {
        return new Swiftbird(factories);
    }

}
