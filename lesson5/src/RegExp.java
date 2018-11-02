import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
    public static void main(String[] args) {
        String str = "Строка1 1А для поиска и замены СИМВОЛОВ ё";
        String pattern = "[иск]";
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "[^иск]"; //исключающие
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "[\\d]"; //цифровой
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "[\\D]"; //не цифровой символ
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "[\\s]"; //любые пробельные символы - пробел, табуляция, перенос строк0
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "[\\S]"; //любые не пробельные символы
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "[а-я, ё]"; //любые из
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "[А-Я0-9]"; // все А-Я и 0-9
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "[А-Я][0-9]"; // заменит если сначала идет заглавная, а потом цифра
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "[\\S]"; //любые не пробельные символы
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "[\\S]"; //любые не пробельные символы
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "а{2,4}"; //а - повторение от 2 до 4х раз
        System.out.println(str.replaceAll(pattern, "*"));
        pattern = "а{3}"; //а - повторение 3


        System.out.println(str.replaceAll(pattern, "*"));


//        Pattern | Matcher

        str = "String B1 to search and replace CHARS";
        Pattern pattern1 = Pattern.compile(".{6,}"); // . - любой символ встречающийся ()повторение от 6 и более раз
        pattern1 = Pattern.compile("(?=.*\\d)(?=.*\\[a-z])(?=.*\\[A-Z]).{8}");
//? - указывает на повторения вконце {}
//        (?=.*\d) - должны быть обязательно числа * - повторение
//  | - или
        Matcher matcher = pattern1.matcher(str);
        System.out.println(matcher.find()); //true|false проверка на наличие
        System.out.println(matcher.matches()); //true|false проверка на полное соответствие



    }
}
