package lesson14.homeTask;

//    Написать "злой" (выбрасывает свои checked исключения) калькулятор,
//    поддерживающий работу с двумя операндами и следующими операциями: +, -, *, /, а также переменные.
//    Например (> ввод пользователя, # - ответ калькулятора):
//    > x = 20
//    > y = 10
//    > x + y
//    # 30
//    > x - 5
//    # 15

import java.util.*;


public class EvilCalc {

    private static Map<String, Double> hashMap = new HashMap<>();

    private static double getArg(String s) throws FaultOperandException {

        if (s.replaceAll("[\\d\\.]", "").length() == 0){
            return Double.parseDouble(s);

        }else if (s.replaceAll("[a-z][\\d]", "").length() != 0){
            try {
                return hashMap.get(s);
            }
            catch (NullPointerException e){
                throw new FaultOperandException("Переменная " + s + " не задана!");
            }
        }else
            throw new FaultOperandException( "Аргумент " + s + " не распознан.");
    }



    private static double calc(String str) throws FaultOperationException, FaultOperandException, DivisionByZeroException, SetValueVariableException {

        String operationStr, leftStr, rightStr;
        double left=1, right=1;

        operationStr = str.replaceAll("[\\s\\w\\.]", "");

        if (operationStr.length() > 1)
            throw new FaultOperationException("Операция не распознана!");

        try {
            leftStr = str.substring(0, str.indexOf(operationStr)).trim().toLowerCase();
            rightStr = str.substring(str.indexOf(operationStr) + 1).trim().toLowerCase();
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new FaultOperandException("Неверный один из операндов!");
        }

        switch (operationStr.charAt(0)) {
            case '-': {
                return getArg(leftStr) - getArg(rightStr);
            }
            case '+': {
                return getArg(leftStr) + getArg(rightStr);
            }
            case '*': {
                return getArg(leftStr) * getArg(rightStr);
            }
            case '/': {
                try {
                    return getArg(leftStr) / getArg(rightStr);
                }
                catch (ArithmeticException e)
                {
                    throw new DivisionByZeroException("Деление на ноль недопустимо!");
                }
            }
            case '=': {
                if (leftStr.replaceAll("[a-z][\\d]", "").length() != 0){
                    hashMap.put(leftStr, getArg(rightStr));
                    throw new SetValueVariableException();
                } else
                    throw new FaultOperandException( "Некорректное имя переменной - " + leftStr);
            }
        }

        throw new FaultOperationException("Операция не равна одной из: + - / * =");
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String str, operationStr, leftStr = "", rightStr = "";

        do{
            System.out.print("> ");
            str = in.nextLine();
            if (str.equals("q")) break;

            if (str.equals("?")) {
                for (Map.Entry entry: hashMap.entrySet()){
                    System.out.println(entry.getKey() + " = " + entry.getValue());
                }
                continue;
            }
            if (str.length() == 0) continue;

            try {
                System.out.println( "# " + calc(str));
            } catch (FaultOperandException e) {
                e.printStackTrace();
            } catch (FaultOperationException e) {
                e.printStackTrace();
            } catch (DivisionByZeroException e) {
                e.printStackTrace();
            } catch (SetValueVariableException e) {
                //ни чего не делаем
            }
        }while (true);
    }
}

class FaultOperationException extends Exception {
    FaultOperationException(String msg) {
        super(msg);
    }
}

class FaultOperandException extends Exception {
    FaultOperandException(String msg) {
        super(msg);
    }
}

class DivisionByZeroException extends Exception {
    DivisionByZeroException(String msg) {
        super(msg);
    }
}

class SetValueVariableException extends Exception {
    SetValueVariableException() {
    }
}