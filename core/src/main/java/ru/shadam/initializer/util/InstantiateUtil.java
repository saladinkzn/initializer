package ru.shadam.initializer.util;

/**
 *
 */
public class InstantiateUtil {

    public static  <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
