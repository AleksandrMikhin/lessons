package lesson6.lessonBattleunit;

public interface ISpeakerUnit {

    // модификатор private доступен, начиная с Java 9
    void say(String text);
    void sing(String song);

    default void greeting(){
        // default метод с реализацией
        System.out.println("Hello");
    }

}