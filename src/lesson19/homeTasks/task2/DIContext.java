package lesson19.homeTasks.task2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DIContext {

    public <T> T get(String nameClass) throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        Class aClass = Class.forName(nameClass);
        Object obj = aClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean isAccessible = field.isAccessible();
            InjectRandomPencils annotation = field.getAnnotation(InjectRandomPencils.class);
            if (annotation != null) {
                int count = new Random().nextInt(annotation.max() - annotation.min()) + annotation.min();
                List<Pencil> random = new ArrayList<>(count);
                for (int i = 0; i < count; i++) {
                    random.add( get("lesson19.homeTasks.task2.Pencil"));
                }
                field.setAccessible(true);
                field.set(obj, random);
                field.setAccessible(isAccessible);
                continue;
            }

            if (field.isAnnotationPresent(InjectRandomColor.class)) {
                field.setAccessible(true);
                Color random = Color.values()[new Random().nextInt(Color.values().length)];
                field.set(obj, random);
                field.setAccessible(isAccessible);
                continue;
            }

        }

        return (T) obj;
    }
}
