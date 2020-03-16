package fr.shyrogan.swiftbird;

import com.leafclient.struct.maths.Range;
import com.leafclient.swiftbird.PropertyContainer;
import com.leafclient.swiftbird.factory.PropertyFactory;
import fr.shyrogan.swiftbird.annotation.Property;
import fr.shyrogan.swiftbird.annotation.listener.Listener;
import fr.shyrogan.swiftbird.annotation.number.Clamp;
import fr.shyrogan.swiftbird.annotation.number.Increment;
import fr.shyrogan.swiftbird.setting.FieldPropertyContainer;
import fr.shyrogan.swiftbird.setting.number.NumberPropertyContainer;
import fr.shyrogan.swiftbird.setting.primitive.BooleanPropertyContainer;
import fr.shyrogan.swiftbird.setting.primitive.EnumPropertyContainer;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings("all")
public final class AnnotatedFieldFactory implements PropertyFactory {


    private final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();

    /**
     * Checks whether the specified element is a {@link Field}, annotated {@link Property}.
     *
     * @param element AnnotatedElement
     * @return Whether the factory can be used for specified element
     */
    @Override
    public boolean canApplyTo(AnnotatedElement element) {
        return element.isAnnotationPresent(Property.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public <O, T> PropertyContainer<T> createSetting(Class<T> valueClass, O object, AnnotatedElement element) {
        Field field = (Field) element;
        field.setAccessible(true);

        try {
            MethodHandles.Lookup caller = LOOKUP.in(object.getClass());
            MethodHandle handleGetter = caller.unreflectGetter(field)
                    .bindTo(object);
            MethodHandle handleSetter = caller.unreflectSetter(field)
                    .bindTo(object);

            // TODO: LambdaMetafactory?
            MethodHandle handleListener = null;

            // Value
            final Property property = element.getAnnotation(Property.class);
            // Listener
            final Listener listener = element.getAnnotation(Listener.class);
            if(listener != null) {
                for(Method method: object.getClass().getDeclaredMethods()) {
                    if(method.getName().equalsIgnoreCase(listener.value())) {
                        if(method.getParameterCount() != 2)
                            throw new RuntimeException("Unable to find listener " + listener.value());

                        handleListener = caller
                                .unreflect(method)
                                .bindTo(object);
                    }
                }
            }

            // Boolean
            if (Boolean.TYPE == valueClass || valueClass == Boolean.class) {
                return (PropertyContainer<T>) new BooleanPropertyContainer(property.value(), property.description(), handleGetter, handleSetter, handleListener);
            }
            else if (Byte.TYPE == valueClass || valueClass == Byte.class) {
                final Clamp clamp = element.getAnnotation(Clamp.class);
                final Increment increment = element.getAnnotation(Increment.class);
                final Range<Byte> range = clamp == null ? null : Range.of((byte)clamp.minimum(), (byte)clamp.maximum());
                Byte step = null;
                if(increment != null)
                    step = (byte)increment.value();
                else if(range != null)
                    step = (byte)((range.getMaximum() - range.getMinimum()) / 50);

                return (PropertyContainer<T>) new NumberPropertyContainer<>(property.value(), property.description(),
                        range, step, handleGetter, handleSetter, handleListener);
            }
            else if (Short.TYPE == valueClass || valueClass == Short.class) {
                final Clamp clamp = element.getAnnotation(Clamp.class);
                final Increment increment = element.getAnnotation(Increment.class);
                final Range<Short> range = clamp == null ? null : Range.of((short)clamp.minimum(), (short)clamp.maximum());
                Short step = null;
                if(increment != null)
                    step = (short)increment.value();
                else if(range != null)
                    step = (short)((range.getMaximum() - range.getMinimum()) / 50);

                return (PropertyContainer<T>) new NumberPropertyContainer<>(property.value(), property.description(),
                        range, step, handleGetter, handleSetter, handleListener);
            }
            else if (Integer.TYPE == valueClass || valueClass == Integer.class) {
                final Clamp clamp = element.getAnnotation(Clamp.class);
                final Increment increment = element.getAnnotation(Increment.class);
                final Range<Integer> range = clamp == null ? null : Range.of((int)clamp.minimum(), (int)clamp.maximum());
                Integer step = null;
                if(increment != null)
                    step = (int)increment.value();
                else if(range != null)
                    step = (range.getMaximum() - range.getMinimum()) / 50;

                return (PropertyContainer<T>) new NumberPropertyContainer<>(property.value(), property.description(),
                        range, step, handleGetter, handleSetter, handleListener);
            }
            else if (Long.TYPE == valueClass || valueClass == Long.class) {
                final Clamp clamp = element.getAnnotation(Clamp.class);
                final Increment increment = element.getAnnotation(Increment.class);
                final Range<Long> range = clamp == null ? null : Range.of((long)clamp.minimum(), (long)clamp.maximum());
                Long step = null;
                if(increment != null)
                    step = (long)increment.value();
                else if(range != null)
                    step = (range.getMaximum() - range.getMinimum()) / 50;

                return (PropertyContainer<T>) new NumberPropertyContainer<>(property.value(), property.description(),
                        range, step, handleGetter, handleSetter, handleListener);
            }
            else if (Float.TYPE == valueClass || valueClass == Float.class) {
                final Clamp clamp = element.getAnnotation(Clamp.class);
                final Increment increment = element.getAnnotation(Increment.class);
                final Range<Float> range = clamp == null ? null : Range.of((float)clamp.minimum(), (float)clamp.maximum());
                Float step = null;
                if(increment != null)
                    step = (float)increment.value();
                else if(range != null)
                    step = (range.getMaximum() - range.getMinimum()) / 50;

                return (PropertyContainer<T>) new NumberPropertyContainer<>(property.value(), property.description(),
                        range, step, handleGetter, handleSetter, handleListener);
            }
            else if (Double.TYPE == valueClass || valueClass == Double.class) {
                final Clamp clamp = element.getAnnotation(Clamp.class);
                final Increment increment = element.getAnnotation(Increment.class);
                final Range<Double> range = clamp == null ? null : Range.of(clamp.minimum(), clamp.maximum());
                Double step = null;
                if(increment != null)
                    step = increment.value();
                else if(range != null)
                    step = (range.getMaximum() - range.getMinimum()) / 50;

                return (PropertyContainer<T>) new NumberPropertyContainer<>(property.value(), property.description(),
                        range, step, handleGetter, handleSetter, handleListener);
            }
            else if (Enum.class.isAssignableFrom(valueClass)) {
                return (PropertyContainer<T>)new EnumPropertyContainer(property.value(), property.description(), handleGetter, handleSetter, handleListener);
            }
            else {
                return new FieldPropertyContainer<>(property.value(), property.description(), handleGetter, handleSetter, handleListener);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
