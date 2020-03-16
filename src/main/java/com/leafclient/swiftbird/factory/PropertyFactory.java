package com.leafclient.swiftbird.factory;

import com.leafclient.swiftbird.PropertyContainer;

import java.lang.reflect.AnnotatedElement;

/**
 * Swiftbird is focused on being customizable and {@link PropertyFactory} are essential for this purpose.
 *
 * They are used by the {@link com.leafclient.swiftbird.Swiftbird} instance to get each {@link PropertyContainer}
 * forms and instantiate them inside a specified object.
 */
public interface PropertyFactory {

    /**
     * Returns if the {@link AnnotatedElement} can be registered by this {@link PropertyFactory}.
     *
     * @param element AnnotatedElement
     * @return True if this factory can be applied to specified element
     */
    boolean canApplyTo(AnnotatedElement element);

    /**
     * Returns every form of {@link PropertyContainer} instantiated from specified object.
     *
     * @param valueClass Value's class
     * @param object Object's instance
     * @param element Annotated element
     * @return Listeners
     */
    <O, T> PropertyContainer<T> createSetting(Class<T> valueClass, O object, AnnotatedElement element);

}
