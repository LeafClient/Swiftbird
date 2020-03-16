package com.leafclient.swiftbird;

import com.leafclient.struct.structure.Describable;
import com.leafclient.struct.structure.Labelable;

/**
 * Represents a {@link PropertyContainer} to {@link Swiftbird}, it provides some essentials methods for the
 * library.
 *
 * @param <T> Value's type.
 */
public interface PropertyContainer<T> extends Labelable, Describable {

    /**
     * @return Returns the current value of this property.
     */
    T getValue();

    /**
     * Modifies the value of this property.
     *
     * @param value New value
     */
    void setValue(T value);

}
