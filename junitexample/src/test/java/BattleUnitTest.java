import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class BattleUnitTest {

    BattleUnit battleUnit;  // можно инициализировать перед каждым тестом и использовать один и тот же

    @BeforeAll // метод должнет быть статическим, выполнит перед всеми тестами
    static void voidBeforeAll(){
        // some code
        System.out.println("BeforeAll");
    }


    @AfterAll // метод должнет быть статическим, выполняется после всех тестов
    static void voidAfterAll(){
        // some code
        System.out.println("AfterAll");
    }

    @BeforeEach // метод выполняется перед каждым тестом (может его и не быть)
    void beforeEach(){
        // some code
        try {
            battleUnit = new BattleUnit(5, 7);  // будет создавать перед каждым методом,
        } catch (Exception e) {                               // в методе можно использовать
            e.printStackTrace();
        }
    }

    @AfterEach // метод выполняется после каждым тестом (может его и не быть)
    void afterEach(){
        // some code
    }

//    @Test  // ничего не возвращают, просто тестируют
//    @RepeatedTest(5) // тест метод будет выполнен 5 раз

//    @ParameterizedTest
//    @ValueSource(strings={"str 1", "str 2"})    // int chars
//    void someTest(String string){  // параметры попадут по очереди в string
//        // some code
//    }
//
//    @ParameterizedTest
//    @MethodSource("methodName")
//    void someOtherTest(String string){
//        // some code
//    }
//
//    @DisplayName("Имя метода");
//
//    @Disabled

    @DisplayName("Health and Attack must be pos")
    @Test
    void throwExceptionIfNegData(){
//        assertThrows(Exception.class, ()->new BattleUnit(-10, 10));
//        assertThrows(Exception.class, ()->new BattleUnit(0, 10));
        assertAll("unit", ()->assertThrows(Exception.class, ()->new BattleUnit(-10, 10)),
                ()->assertThrows(Exception.class, ()->new BattleUnit(0, 10)),
                ()->assertThrows(Exception.class, ()->new BattleUnit(10, -10)),
                ()->assertThrows(Exception.class, ()->new BattleUnit(10, 0)));

    }

    @Test
    void getUnitHealth() {
        BattleUnit unit1 = null;
        BattleUnit unit2 = null;
        try {
            unit1 = new BattleUnit(10, 20);
            unit2 = new BattleUnit(10, 20);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(10, unit1.getHealth());
        assertEquals(10, unit2.getHealth());

//        assertSame(unit1, unit2); // - проверяет на самого себя
        assertEquals(unit1, unit2); // проверяет на equals - hashCode() и equals()
    }

    private static BattleUnit[][] units() throws Exception {  // количество раз запускается метод, вызывающий
        return new BattleUnit[][]
                {
                        {
                            new BattleUnit(1, 4),
                            new BattleUnit(3, 6)
                        }
                };
    }

    @ParameterizedTest
    @MethodSource("units")
    void testUnitAttack(BattleUnit unit1, BattleUnit unit2){
        System.out.println(unit1);
        System.out.println(unit2);
        int temp = unit2.health - unit1.attack;
        unit1.attack(unit2);
        assertEquals(temp, unit2.health);
    }


}