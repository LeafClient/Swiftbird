package fr.shyrogan.swiftbird.setting;

import com.leafclient.swiftbird.PropertyContainer;

import java.lang.invoke.MethodHandle;

@SuppressWarnings("unchecked")
public class FieldPropertyContainer<T> implements PropertyContainer<T> {

    private final String label, description;
    private transient final MethodHandle valueGetter, valueSetter, listener;

    public FieldPropertyContainer(String label, String description, MethodHandle valueGetter, MethodHandle valueSetter, MethodHandle listener) {
        this.label = label;
        this.description = description;
        this.valueGetter = valueGetter;
        this.valueSetter = valueSetter;
        this.listener = listener;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the current value of this {@link PropertyContainer} by getting using the {@link this#valueGetter}.
     *
     * @return Current value
     */
    @Override
    public T getValue() {
        try {
            return (T) valueGetter.invoke();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Modifies the value of the field using the {@link this#valueSetter}
     * @param value New value
     *
     * @return This setting for fluent syntax
     */
    @Override
    public void setValue(T value) {
        try {
            if(listener != null)
                valueSetter.invoke((T) listener.invoke(getValue(), value));
            else
                valueSetter.invoke(value);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
