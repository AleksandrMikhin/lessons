package patterns.builder;

public class NutritionFacts {
    private final int servings;
    private final int calories;
    private final int fat;

    private NutritionFacts(Builder builder){
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
    }
    public static class Builder {
        private final int servings;  //обязательные final
        private int calories = 0;
        private int fat = 0;

        public Builder( int servings) {
            this.servings = servings;
        }

        public Builder setCalories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder setFat(int fat) {
            this.fat = fat;
            return this;
        }

        public NutritionFacts buid() {
            return new NutritionFacts(this);
        }
    }


    public static void main(String[] args) {
        NutritionFacts beaf = new Builder(10)
                .setCalories(100)
                .setFat(5)
                .buid();

        NutritionFacts meat = new Builder(10)
                .setCalories(200)
                .setFat(5)
                .buid();

    }
}
