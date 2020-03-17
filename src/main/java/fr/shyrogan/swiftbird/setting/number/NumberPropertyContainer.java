package fr.shyrogan.swiftbird.setting.number;

import com.leafclient.struct.maths.Range;
import fr.shyrogan.swiftbird.setting.FieldPropertyContainer;

import java.lang.invoke.MethodHandle;

import static com.leafclient.struct.maths.MathOperationsHelper.add;
import static com.leafclient.struct.maths.MathOperationsHelper.round;
import static com.leafclient.struct.maths.MathOperationsHelper.multiplication;
import static com.leafclient.struct.maths.MathOperationsHelper.cast;

public final class NumberPropertyContainer<T extends Number> extends FieldPropertyContainer<T> {

    private final Range<T> range;
    private final T step;

    public NumberPropertyContainer(String label, String description, Range<T> range, T step, MethodHandle valueGetter, MethodHandle valueSetter, MethodHandle listener) {
        super(label, description, valueGetter, valueSetter, listener);
        this.range = range;
        this.step = step;
    }

    /**
     * Sets the value to a value coerced into {@link this#range} stepped
     * to {@link this#step}
     *
     * @param value New value
     * @return Setting for a fluent syntax
     */
    @Override
    public void setValue(T value) {
        if(step == null && range == null) {
            super.setValue(value);
        } else {
            super.setValue(range.coerce(round(value, step)));
        }
    }

    /**
     * Increases the current value by {@link this#step} * factor.
     * If the value has no step value this operation is not supported
     * @see fr.shyrogan.swiftbird.annotation.number.Increment
     *
     * @param factor Specified factor
     */
    public void increment(int factor) {
        if(step == null)
            return;

        setValue(
                add(
                        getValue(),
                        multiplication(
                                step,
                                cast(factor, getValue())
                        )
                )
        );
    }

    /**
     * Decreases the current value by {@link this#step} * factor.
     * If the value has no step value this operation is not supported.
     * @see fr.shyrogan.swiftbird.annotation.number.Increment
     *
     * @param factor Specified factor
     */
    public void reduce(int factor) {
        increment(-factor);
    }

    /**
     * @return `true` if this value is not clamped
     */
    public boolean isIncrementable() {
        return step != null;
    }

    /**
     * @return `true` if this value is not clamped
     */
    public boolean isInfinite() {
        return range == null;
    }

}
