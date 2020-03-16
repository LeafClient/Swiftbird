package fr.shyrogan.swiftbird.setting.primitive;

import fr.shyrogan.swiftbird.setting.FieldPropertyContainer;

import java.lang.invoke.MethodHandle;

/**
 * The enum implementation for {@link FieldPropertyContainer}.
 */
@SuppressWarnings("unchecked")
public final class EnumPropertyContainer<T extends Enum<?>> extends FieldPropertyContainer<T> {

    public EnumPropertyContainer(String label, String description, MethodHandle valueGetter, MethodHandle valueSetter, MethodHandle listener) {
        super(label, description, valueGetter, valueSetter, listener);
    }

    /**
     * Returns the enum constants of this value
     *
     * @return Enum constants
     */
    public T[] getEnumConstants() {
        return (T[])getValue().getClass()
                .getEnumConstants();
    }

}
