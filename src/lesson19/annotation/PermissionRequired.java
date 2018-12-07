package lesson19.annotation;

//описание аннотации - @interface
//@Target() указывает на сферу ответственности аннотации
//   если не указана, то принима ко всему
//METHOD
//CONSTRUCTOR
//FIELD

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Target(ElementType.FIELD) - если так, то применяются к полям
@Retention(RetentionPolicy.RUNTIME) //как долго хранить аннотацию - в данном случае будемт применяться во время выполнения

public @interface PermissionRequired {

    //    методы в аннотациях без параметров
    //    могут возвращать только примитива, String, enum и аннотации
    User.Permission value();


}
