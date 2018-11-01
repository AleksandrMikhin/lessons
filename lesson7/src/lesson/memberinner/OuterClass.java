package lesson7.memberinner;

public class OuterClass {
    String value;
    static int intVar;

    public OuterClass(String value) {
        this.value = value;
    }

    static class InnerClass{
        String val;

        public InnerClass(String val) {
            this.val = val;
        }

        public  void someVoid(){
            intVar += 2;
            //value = "jkjhkjhkjhkj"; - недоступна
            System.out.println(intVar);
            // не имеет доступа к нестатическим методам внешнего класса
        }

    }

}
