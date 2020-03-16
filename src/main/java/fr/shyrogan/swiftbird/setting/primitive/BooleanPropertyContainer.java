package fr.shyrogan.swiftbird.setting.primitive;

import com.leafclient.struct.structure.Toggleable;
import fr.shyrogan.swiftbird.setting.FieldPropertyContainer;

import java.lang.invoke.MethodHandle;

/**
 * The boolean implementation for {@link FieldPropertyContainer}.
 */
public final class BooleanPropertyContainer extends FieldPropertyContainer<Boolean> implements Toggleable {

    public BooleanPropertyContainer(String label, String description, MethodHandle valueGetter, MethodHandle valueSetter, MethodHandle listener) {
        super(label, description, valueGetter, valueSetter, listener);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isRunning() {
        return getValue();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setRunning(boolean value) {
        setValue(value);
    }



}
