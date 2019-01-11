package lesson19.homeTasks.task2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRandomPencils {
    int min();
    int max();
}
