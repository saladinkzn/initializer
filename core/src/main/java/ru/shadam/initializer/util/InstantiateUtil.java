package ru.shadam.initializer.util;

/**
 *
 */
public class InstantiateUtil {

    /**
     * Instantiate instance by class
     *
     * @param clazz class to instantiate
     * @param <T> type
     * @return new instance (by default constructor)
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
