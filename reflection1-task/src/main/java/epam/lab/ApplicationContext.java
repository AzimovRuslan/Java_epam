package epam.lab;

import annotations.Service;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContext.class);
    private final List<Class<?>> classes = new ArrayList<>();
    private static final Reflections REFLECTIONS = new Reflections("epam.lab");
    private final Set<Class<?>> annotatedClasses = REFLECTIONS.getTypesAnnotatedWith(Service.class);

    public void registerBean(Class<?> implementationClass) {
        classes.add(implementationClass);
    }

    public Object getBean(String id) {
        Class<?> classObj = null;
        for (Class<?> clazz : annotatedClasses) {
            Service service = clazz.getAnnotation(Service.class);
            if (service.id().equals(id)) {
                try {
                    classObj = Class.forName(clazz.getName());
                } catch (ClassNotFoundException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }

        try {
            assert classObj != null;
            return classObj.getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            return e.getMessage();
        }
    }

    public Object getBean(Class<?> implementationClassOrInterface) {
        Class<?> classObj = null;
        for (Class<?> clazz : annotatedClasses) {
            if (implementationClassOrInterface.isAssignableFrom(clazz)) {
                try {
                    classObj = Class.forName(clazz.getName());
                } catch (ClassNotFoundException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }

        try {
            assert classObj != null;
            return classObj.getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            return e.getMessage();
        }
    }


    public Object getBean(String id, Class<?> implementationClassOrInterface) {
        Class<?> classObj = null;
        for (Class<?> clazz : annotatedClasses) {
            Service service = clazz.getAnnotation(Service.class);
            if (service.id().equals(id) && implementationClassOrInterface.isAssignableFrom(clazz)) {
                try {
                    classObj = Class.forName(clazz.getName());
                } catch (ClassNotFoundException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }

        try {
            assert classObj != null;
            return classObj.getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            return e.getMessage();
        }
    }

    public Deque<Object> build() {
        Deque<Object> beans = new ArrayDeque<>();
        for (Class<?> clazz : classes) {
            Service service = clazz.getAnnotation(Service.class);
            if (service.dependsOn().length == 0) {
                try {
                    beans.addFirst(clazz.getDeclaredConstructor().newInstance());
                } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                    LOGGER.error(e.getMessage());
                }
            } else {
                try {
                    beans.addLast(clazz.getDeclaredConstructor().newInstance());
                } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
        return beans;
    }
}
