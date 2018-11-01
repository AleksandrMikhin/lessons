package homeTask;

import java.util.Arrays;

public class Pupil {
    final static int COUNT_EXAM = 2;
    int id;
    ExamResult[] examResult;

    public class ExamResult {
        private String name;
        private int mark;
        private Boolean condition;

        public ExamResult(String name, int mark) {
            this.name = name;
            this.mark = mark;
            this.condition = mark > 2;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        public Boolean getCondition() {
            return condition;
        }

        @Override
        public String toString() {
            return name + " -" + (condition ? "" : " не") + " сдал";
        }
    }

    public Pupil(int id) {
        this.id = id;
        this.examResult = new ExamResult[COUNT_EXAM];
    }

    public void setExams(String[] ex, int[] marks) {
        if (ex.length != marks.length) {
            System.out.println("Вводимые данные не верны");
            return;
        }
        for (int i = 0; i < ex.length; i++) {
            this.examResult[i] = new ExamResult(ex[i], marks[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder examsString = new StringBuilder();
        for (int i = 0; i < examResult.length; i++){
            examsString.append("\n");
            examsString.append(examResult[i].toString());
        }

        return "Студент: " + this.id
                + examsString.toString();
    }
}
