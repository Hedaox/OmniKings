package callout.OmniKings.utils;

import JinRyuu.JRMCore.JRMCoreH;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Utils {
    public static void setPrivateFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }

    public static Field getPrivateField(String fieldName, Class classToGetField) throws Exception {
        return classToGetField.getDeclaredField(fieldName);
    }
}
